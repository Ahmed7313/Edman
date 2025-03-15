import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.daman.edman.R
import com.daman.edman.screens.components.IconTextView
import com.daman.edman.ui.theme.SkyColorBlue
import com.trend.camelx.ui.theme.large
import java.io.File
import java.io.IOException

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    icon: Int,
    text: String,
    secText: String,
    onImageSelected: (Uri?) -> Unit // Callback to return selected image URI
) {
    val context = LocalContext.current
    // Image URI to hold selected or captured image
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    // State to store the URI for launching camera after permission is granted
    var imageUriForCamera by remember { mutableStateOf<Uri?>(null) }
    // State to control showing the selection dialog
    var showDialog by remember { mutableStateOf(false) }

    // Launcher for taking a picture using the camera
    val takePicture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                onImageSelected(selectedImageUri) // Return the taken picture's URI
            }
        }
    )

    // Launcher to request CAMERA permission
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            imageUriForCamera?.let { uri ->
                takePicture.launch(uri)
            }
        } else {
            Toast.makeText(context, "Camera permission is required", Toast.LENGTH_SHORT).show()
        }
    }

    // Launcher for picking an image from the gallery
    val getContent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            onImageSelected(uri) // Return the selected image's URI
        }
    )

    // Function to create a URI for a temporary image file
    fun createImageUri(): Uri? {
        val storageDir = File(context.cacheDir, "images")
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }
        val file = try {
            File.createTempFile("IMG_", ".jpg", storageDir)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
        return if (file != null) {
            val contentUri = FileProvider.getUriForFile(
                context, "com.daman.edman.fileprovider", file
            )
            selectedImageUri = contentUri
            contentUri
        } else {
            null
        }
    }

    // Show an AlertDialog with options to choose from Gallery or Camera
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Select Image Source") },
            text = { Text("Choose an option below to select your image.") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    // Launch gallery selection
                    getContent.launch("image/*")
                }) {
                    Text("Gallery")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    // Launch camera capture
                    val imageUri = createImageUri()
                    if (imageUri != null) {
                        imageUriForCamera = imageUri
                        if (ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            takePicture.launch(imageUri)
                        } else {
                            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    } else {
                        Toast.makeText(context, "Failed to create image file", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text("Camera")
                }
            }
        )
    }

    // Main surface that triggers the dialog on click
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { showDialog = true },
        shape = RoundedCornerShape(large),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconTextView(
                    text = text,
                    icon = icon,
                    tint = SkyColorBlue
                )
                Icon(
                    painter = painterResource(R.drawable.ic_upload),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = secText,
                color = Color.Gray
            )
            // Show selected image if any
            selectedImageUri?.let {
                Text(
                    text = "Selected image: $it",
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun ImagePickerPreview() {
    ImagePicker(
        icon = R.drawable.ic_check,
        text = "Image Picker",
        secText = "Select an image from the gallery or take a photo",
        onImageSelected = { uri ->
            // Handle the image URI (e.g., display it in an Image composable)
            println("Selected image: $uri")
        }
    )
}
