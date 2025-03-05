import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    // Create an ActivityResultLauncher for picking an image from the gallery
    val getContent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            onImageSelected(uri) // Pass the URI to the callback when a file is selected
        }
    )

    // Create an ActivityResultLauncher for taking a picture using the camera
    val takePicture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                onImageSelected(selectedImageUri) // Return the taken picture's URI
            }
        }
    )

    // Function to create a URI for a temporary image file
    fun createImageUri(): Uri? {
        // Create a temporary file in the app's cache directory
        val storageDir = File(context.cacheDir, "images") // Create an 'images' directory in cacheDir
        if (!storageDir.exists()) {
            storageDir.mkdirs() // Create the directory if it doesn't exist
        }

        val file = try {
            File.createTempFile("IMG_", ".jpg", storageDir) // Create a temp file within the images directory
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

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                // Ask the user if they want to pick an image from the gallery or take a photo
                // Here we open the camera directly as an example
                val imageUri = createImageUri()
                if (imageUri != null) {
                    takePicture.launch(imageUri) // Start the camera intent
                } else {
                    Toast.makeText(context, "Failed to create image file", Toast.LENGTH_SHORT).show()
                }
            },
        shape = RoundedCornerShape(large),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier
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
            // Handle the image URI (e.g., show it in an Image composable or use it)
            println("Selected image: $uri")
        }
    )
}

