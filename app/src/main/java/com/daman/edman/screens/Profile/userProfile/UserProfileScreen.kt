package com.daman.edman.screens.Profile.userProfile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.aramex.mypos.Presentation.Components.MainEditTextFramed
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.ToolBarView
import com.daman.edman.ui.theme.EdmanTheme
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.buttonColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.camelx.ui.theme.spacing

@Composable
fun UserProfileScreen(
    navHostController: NavHostController,
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val savedUser  by viewModel.savedUser
    val isEditMode by viewModel.isEditMode
    val state      by viewModel.state
    val selectedImageUri by viewModel.selectedImageUri

    // ── Form fields — pre-filled from persisted user ──────────────────────────
    var name     by remember(savedUser) { mutableStateOf(savedUser.name.orEmpty()) }
    var email    by remember(savedUser) { mutableStateOf(savedUser.email.orEmpty()) }
    var phone    by remember(savedUser) { mutableStateOf(savedUser.phone) }
    var idNumber by remember(savedUser) { mutableStateOf(savedUser.code.orEmpty()) }

    // ── Gallery launcher ──────────────────────────────────────────────────────
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.selectedImageUri.value = it }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ToolBarView("الملف الشخصي", navHostController)

        AppSpacerHeight()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .verticalScroll(rememberScrollState())
                .padding(start = large, end = large, top = large, bottom = large)
        ) {

            // ── Header row: title + edit/save button ──────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HeaderText(text = "بياناتك", fontSize = 16, color = Color.Gray)

                HeaderText(
                    text     = if (isEditMode) "حفظ" else "تعديل",
                    fontSize = 14,
                    color    = SkyColorBlue,
                    modifier = Modifier.clickable {
                        if (isEditMode) {
                            // Save — call the API
                            viewModel.updateProfile(
                                UserProfileViewModel.UpdateProfileModel(
                                    name     = name,
                                    email    = email,
                                    phone    = phone,
                                    idNumber = idNumber,
                                )
                            )
                        } else {
                            viewModel.isEditMode.value = true
                        }
                    }
                )
            }

            AppSpacerHeight()

            // ── Profile image ─────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable(enabled = isEditMode) {
                        if (isEditMode) imagePickerLauncher.launch("image/*")
                    }
            ) {
                val painter = when {
                    selectedImageUri != null ->
                        rememberAsyncImagePainter(selectedImageUri)
                    !savedUser.image.isNullOrEmpty() ->
                        rememberAsyncImagePainter(savedUser.image)
                    else ->
                        painterResource(R.drawable.logo)
                }

                Image(
                    painter            = painter,
                    contentDescription = null,
                    contentScale       = ContentScale.Crop,
                    modifier           = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                        .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
                        .then(if (!isEditMode) Modifier.alpha(1f) else Modifier.alpha(0.7f))
                )

                // Camera icon overlay — only in edit mode
                if (isEditMode) {
                    Icon(
                        painter            = painterResource(R.drawable.ic_camera_icon),
                        contentDescription = null,
                        tint               = Color.White,
                        modifier           = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .align(Alignment.BottomEnd)
                            .background(shape = CircleShape, color = SkyColorBlue)
                    )
                }
            }

            AppSpacerHeight()

            // ── Name ──────────────────────────────────────────────────────────
            MainEditTextFramed(
                text        = name,
                onTextChange = { if (isEditMode) name = it },
                label       = "الاسم الكامل",
                aboveText   = "الاسم",
                enabled     = isEditMode,
            )

            AppSpacerHeight()

            // ── Email ─────────────────────────────────────────────────────────
            MainEditTextFramed(
                text        = email,
                onTextChange = { if (isEditMode) email = it },
                label       = "البريد الإلكتروني",
                aboveText   = "البريد الإلكتروني",
                enabled     = isEditMode,
            )

            AppSpacerHeight()

            // ── Phone (read-only — shown for display, changed via updatePhone) ─
            MainEditTextFramed(
                text        = phone,
                onTextChange = {},
                label       = "رقم الهاتف",
                aboveText   = "رقم الهاتف",
                enabled     = false,
                trailingIcon = {
                    Icon(
                        painter            = painterResource(id = R.drawable.ic_wonder_mark),
                        contentDescription = null,
                        tint               = SkyColorBlue
                    )
                }
            )

            AppSpacerHeight()

            // ── National ID ───────────────────────────────────────────────────
            MainEditTextFramed(
                text        = idNumber,
                onTextChange = { if (isEditMode) idNumber = it },
                label       = "رقم البطاقة الوطنية",
                aboveText   = "رقم البطاقة",
                enabled     = isEditMode,
            )

            AppSpacerHeight()

            // ── Loading indicator ─────────────────────────────────────────────
            if (state.isLoading) {
                CircularProgressIndicator(
                    color    = SkyColorBlue,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterHorizontally)
                )
                AppSpacerHeight()
            }

            // ── Save button (visible only in edit mode) ───────────────────────
            if (isEditMode) {
                Button(
                    onClick = {
                        viewModel.updateProfile(
                            UserProfileViewModel.UpdateProfileModel(
                                name     = name,
                                email    = email,
                                phone    = phone,
                                idNumber = idNumber,
                            )
                        )
                    },
                    enabled  = !state.isLoading,
                    modifier = Modifier.fillMaxWidth(),
                    shape    = RoundedCornerShape(spacing),
                    colors   = ButtonDefaults.buttonColors(containerColor = SkyColorBlue)
                ) {
                    HeaderText(text = "حفظ التغييرات", fontSize = 14, color = Color.White)
                }
            }

            AppSpacerHeight()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    EdmanTheme {
        UserProfileScreen(navHostController = rememberNavController())
    }
}
