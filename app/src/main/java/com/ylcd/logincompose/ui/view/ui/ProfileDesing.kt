package com.ylcd.logincompose.ui.view.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

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

class ProfileDesing {
    @Preview
    @Composable
    fun ProfileScreen() {
        val email:String = "pepe@gmail.com"
        // navController: NavController, email: String
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
                        // Si se selecciona una nueva URI, actualiza selectedUri
                        selectedUri = uri
                        // profileFragment.updatePhoto(context, email, uri.toString())
                    } else {
                        // Si no se selecciona ninguna URI, establece una URI predeterminada
                        selectedUri = defaultUri
                        // profileFragment.updatePhoto(context, email, defaultUri.toString())
                    }
                }
            )

            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Bienvenido, $email!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Image(
                        painter = rememberAsyncImagePainter(selectedUri),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Gray)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Elige un fondo de pantalla:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val backgrounds = listOf(
                            R.drawable.textura
                        )
                        backgrounds.forEach { bg ->
                            BackgroundOption(painterResource(id = bg)) {
                                selectedUri = Uri.parse("android.resource://$packageName/$bg")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            // Perform logout action
                            //navController.navigate("login")
                        },
                        modifier = Modifier
                            .width(200.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
                    ) {
                        Text(
                            text = "Cerrar sesión",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun BackgroundOption(painter: Painter, onClick: () -> Unit) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray)
                .clickable(onClick = onClick)
        )
    }
}