package com.ylcd.logincompose.ui.view.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ylcd.logincompose.R
import com.ylcd.logincompose.ui.theme.AppComposeTheme

class LoginDesing {
    @Preview
    @Composable
    fun LoginScreen(){
        AppComposeTheme {
            var mail by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var rememberMe by remember { mutableStateOf(false) }
            var passwordVisible by remember { mutableStateOf(false) }

            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Fondo con degradado y logo
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color(0xFF8080FF), Color.White),
                                    startY = 0f,
                                    endY = 1000f
                                )
                            )
                    ) {
                        // Forma de onda
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val path = Path()
                            path.moveTo(0f, size.height * 0.2f)  // Ajuste del punto de inicio de la onda
                            path.quadraticBezierTo(
                                size.width * 0.5f, size.height * 0.15f,  // Ajuste del punto medio de la onda
                                size.width, size.height * 0.2f  // Ajuste del punto final de la onda
                            )
                            path.lineTo(size.width, size.height)
                            path.lineTo(0f, size.height)
                            path.close()

                            drawPath(
                                path = path,
                                color = Color.White
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Logo() // Logo dentro del degradado y la forma de onda
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(240.dp)) // Añadir espacio para el logo
                        Text(
                            text = "Ingreso",
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .drawBehind {
                                    drawLine(
                                        color = Color.Blue,
                                        start = Offset(0f, size.height),
                                        end = Offset(size.width, size.height),
                                        strokeWidth = 2.dp.toPx()
                                    )
                                }
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        OutlinedTextField(
                            value = mail,
                            onValueChange = { mail = it },
                            label = { Text("Correo electrónico") },
                            placeholder = { Text("Ingresar correo electrónico") },
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Contraseña") },
                            singleLine = true,
                            placeholder = { Text("Ej: abcABC#123") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        painter = if (passwordVisible) painterResource(id = R.drawable.visibility) else painterResource(
                                            id = R.drawable.visibility_off
                                        ),
                                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = rememberMe,
                                    onCheckedChange = { rememberMe = it }
                                )
                                Text("Recordarme")
                            }
                            TextButton(onClick = { /* Acción de recuperar contraseña */ }) {
                                Text("Contraseña olvidada?", color = Color.Blue)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { /* Acción de ingreso */ },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors()
                        ) {
                            Text("Ingresar", color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("No tienes una cuenta?")
                            TextButton(onClick = { /* Acción de registro */ }) {
                                Text("Registrarse", color = Color.Blue)
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Logo() {
        Box(
            modifier = Modifier
                .size(120.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(shape = RectangleShape)  //
                )
            }
        }
    }
}
