package com.ylcd.logincompose.ui.view.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ylcd.logincompose.R
import com.ylcd.logincompose.ui.theme.AppComposeTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ylcd.logincompose.ui.view.fragments.LoginFragment
import com.ylcd.logincompose.ui.view.navigation.Screen
import com.ylcd.logincompose.util.Validations

class LoginDesing {

    @Composable
    fun LoginScreen(navController: NavController, loginFragment: LoginFragment) {
        AppComposeTheme {
            val context = LocalContext.current
            var validations: Validations = Validations()
            var mail by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var passwordR by remember { mutableStateOf("") }
            var rememberMe by remember { mutableStateOf(false) }
            var passwordVisible by remember { mutableStateOf(false) }
            var errorMessageMail by remember { mutableStateOf("") }
            var errorPassword by remember { mutableStateOf("") }
            var showErrorMessages by remember { mutableStateOf(false) }

            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Background with image
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val imageBitmap: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.textura)

                        // Wave shape
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val path = Path()

                            // Starting point
                            path.moveTo(0f, 0f)

                            // Add top edge to the path
                            path.lineTo(size.width, 0f)

                            // Wavy curve
                            path.lineTo(size.width, size.height * 0.35f)
                            path.cubicTo(
                                size.width * 0.75f, size.height * 0.45f,  // First control point
                                size.width * 0.25f, size.height * 0.25f,  // Second control point
                                0f, size.height * 0.35f                   // End point
                            )

                            // Close the path
                            path.close()

                            // Create a shader from the imageBitmap
                            val shader = ImageShader(imageBitmap, TileMode.Repeated, TileMode.Repeated)

                            // Create a brush from the shader
                            val brush = ShaderBrush(shader)

                            // Draw the path with the textured brush
                            drawPath(
                                path = path,
                                brush = brush
                            )
                        }

                        // Logo placed at the top
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 80.dp),  // Adjust this value as needed
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Logo(size = 200.dp)
                        }
                    }
                }
                // Contenido principal
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(250.dp))  // Asegura que el contenido esté debajo de la curva
                    IngresoText()
                    Spacer(modifier = Modifier.height(32.dp))
                    OutlinedTextField(
                        value = mail,
                        onValueChange = {
                            mail = it
                            errorMessageMail = if (validations.isValidEmail(it)) "" else "Correo inválido"

                            // Llamar a obtenertoCompare solo si el email no está vacío y es válido
                            if (it.isNotEmpty() && validations.isValidEmail(it)) {
                                loginFragment.obtenertoCompare(it, context) { passR ->
                                    passwordR = passR
                                }
                            }
                        },
                        label = { Text("Correo electrónico") },
                        placeholder = { Text("Ingresar correo electrónico") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                        isError = errorMessageMail.isNotEmpty() && showErrorMessages
                    )

                    if (errorMessageMail.isNotEmpty() && showErrorMessages) {
                        Text(
                            text = errorMessageMail,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            errorPassword = if (validations.passwordsMatch(it, passwordR)) "" else "Contraseña incorrecta"
                        },
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
                        },
                        isError = errorPassword.isNotEmpty() && showErrorMessages
                    )
                    if (showErrorMessages && errorPassword.isNotEmpty()) {
                        Text(
                            text = errorPassword,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))
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
                            Text("Recordarme", color = MaterialTheme.colorScheme.primary)
                        }
                        TextButton(onClick = { /* Acción de recuperar contraseña */ }) {
                            Text("Contraseña olvidada?", color = MaterialTheme.colorScheme.secondary)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            showErrorMessages = true
                            errorMessageMail = if (validations.isValidEmail(mail)) "" else "Correo inválido"

                            errorPassword = if (validations.passwordsMatch(password, passwordR)) "" else "Contraseña incorrecta"
                            if (errorPassword.isEmpty() && errorMessageMail.isEmpty()) {
                                navController.navigate(Screen.ProfileScreen.route)
                            }

                        },
                        modifier = Modifier
                            .width(250.dp)
                            .height(30.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
                        contentPadding = PaddingValues(0.dp) // Remove default padding
                    ) {
                        Text(
                            "Ingresar",
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 15.sp, // Reduced font size to fit in the small button
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center, // Center the text horizontally
                            modifier = Modifier
                                .fillMaxHeight()
                                .wrapContentHeight(Alignment.CenterVertically) // Center vertically
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End // This aligns the content to the left
                    ) {
                        Text("No tienes una cuenta?", color = MaterialTheme.colorScheme.primary)
                        TextButton(onClick = { navController.navigate(Screen.RegisterScreen.route) }) {
                            Text("Registrarse", color = MaterialTheme.colorScheme.secondary)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Logo(size = 50.dp)
                }
            }
        }
    }

    @Composable
    fun Logo(size: Dp) {
        Box(
            modifier = Modifier
                .size(size)  // Usa el parámetro size
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
                        .size(size)  // Usa el parámetro size para el tamaño de la imagen
                        .clip(shape = RectangleShape)
                )
            }
        }
    }

    @Composable
    fun IngresoText() {
        val secondaryColor = MaterialTheme.colorScheme.secondary.toArgb()

        val paint = android.graphics.Paint().apply {
            textSize = 25f
            color = secondaryColor
            strokeWidth = 5f  // Adjust this value to make the line thicker
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()  // Occupies the entire available width
                .padding(
                    start = 0.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )  // Adjust the start padding to move the text to the left
        ) {
            BasicText(
                text = "Ingreso",
                style = TextStyle(
                    color = Color(secondaryColor),
                    fontSize = 25.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)  // Aligns the text to the left
                    .drawWithContent {
                        drawContent()
                        drawIntoCanvas { canvas ->
                            // Measure the width of the text "Ingreso"
                            val width = paint.measureText("Ingreso")
                            val yPosition = size.height + 12f  // Lower the line by 5 pixels
                            canvas.nativeCanvas.drawLine(
                                1f,
                                yPosition,
                                140f,  // Adjust the end point to the text width
                                yPosition,
                                paint
                            )
                        }
                    }
            )
        }
    }

}
