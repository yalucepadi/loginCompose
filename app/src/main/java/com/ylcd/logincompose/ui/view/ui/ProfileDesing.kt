package com.ylcd.logincompose.ui.view.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ylcd.logincompose.R
import com.ylcd.logincompose.ui.theme.AppComposeTheme
import com.ylcd.logincompose.ui.view.navigation.Screen

class ProfileDesing {
    @Composable
    fun ProfileScreen(navController: NavController, email: String) {
        AppComposeTheme {
            val context = LocalContext.current
            val packageName = context.packageName
            val defaultUriString = "android.resource://$packageName/${R.drawable.textura}"

            val defaultUri = remember { Uri.parse(defaultUriString) }

            var selectedUri by remember { mutableStateOf<Uri>(defaultUri) }
            val singlePhotoPicker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.PickVisualMedia(),
                onResult = { uri ->
                    if (uri != null) {
                        selectedUri = uri
                    } else {
                        selectedUri = defaultUri
                    }
                }
            )

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Background Image
                Image(
                    painter = rememberAsyncImagePainter(selectedUri),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop // This ensures the image covers the entire screen
                )

                // Content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Bienvenido, $email!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // Changed to white for better visibility on the background
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Elige un fondo de pantalla:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White // Changed to white for better visibility on the background
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                singlePhotoPicker.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                Icons.Default.AddCircle,
                                contentDescription = null,
                                tint = Color.White // Changed to white for better visibility on the background
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            navController.navigate(Screen.MainScreen.route)
                        },
                        modifier = Modifier
                            .width(200.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
                    ) {
                        Text(
                            text = "Cerrar sesión",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }}