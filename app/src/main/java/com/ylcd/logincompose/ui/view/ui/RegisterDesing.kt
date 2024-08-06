package com.ylcd.logincompose.ui.view.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
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
import androidx.navigation.NavController
import com.ylcd.logincompose.R
import com.ylcd.logincompose.ui.theme.AppComposeTheme
import com.ylcd.logincompose.ui.view.navigation.Screen

class RegisterDesing {

    @Composable
    fun RegisterScreen(navController: NavController) {
        AppComposeTheme {
            var mail by remember { mutableStateOf("") }
            var tel by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var confirmPassword by remember { mutableStateOf("") }
            var passwordVisible by remember { mutableStateOf(false) }

            var errorMessageMail by remember { mutableStateOf("") }
            var errorMessageTel by remember { mutableStateOf("") }
            var errorMessagePass by remember { mutableStateOf("") }
            var errorMessageConfirmPass by remember { mutableStateOf("") }


            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Background with image and wave shape
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 1.dp) // Ensure the content is below the wave curve
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
                            path.lineTo(size.width, size.height * 0.2f) // Reduced height of the wave
                            path.cubicTo(
                                size.width * 0.75f, size.height * 0.3f,  // First control point
                                size.width * 0.25f, size.height * 0.1f,  // Second control point
                                0f, size.height * 0.2f                   // End point
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

                        // Logo placed at the top right
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, end = 16.dp),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Logo(size = 100.dp)
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
                    Spacer(modifier = Modifier.height(90.dp))  // Asegura que el contenido esté debajo de la curva
                    RegistroText()
                    Spacer(modifier = Modifier.height(32.dp))
                    OutlinedTextField(
                        value = mail,
                        onValueChange = { mail = it
                                        errorMessageMail = if (isValidEmail(it)) "" else "Correo inválido"
                                        },
                        label = { Text("Correo electrónico") },
                        placeholder = { Text("Ingresar correo electrónico") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                        isError = errorMessageMail.isNotEmpty()

                    )

                    if (errorMessageMail.isNotEmpty()) {
                        Text(
                            text = errorMessageMail,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }


                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = tel,
                        onValueChange = { tel = it
                            errorMessageTel = if (isValidPhoneNumber(it)) "" else "Número inválido"

                        },
                        label = { Text("Número de teléfono") },
                        placeholder = { Text("Ej: +54 12345678") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Call, contentDescription = null) }
                    )

                    if (errorMessageTel.isNotEmpty()) {
                        Text(
                            text = errorMessageTel,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it
                            errorMessagePass = if (isValidPassword(it)) "" else "Contraseña débil"
                            errorMessageConfirmPass = if (passwordsMatch(it, confirmPassword)) "" else "Las contraseñas no coinciden"
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
                            .padding(vertical = 8.dp),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    painter = if (passwordVisible) painterResource(id = R.drawable.visibility) else painterResource(
                                        id = R.drawable.visibility_off
                                    ),
                                    contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                                )
                            }
                        } ,
                        isError = errorMessagePass.isNotEmpty()


                    )
                    if (errorMessagePass.isNotEmpty()) {
                        Text(
                            text = errorMessagePass,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it
                            errorMessageConfirmPass = if (passwordsMatch(password, it)) "" else "Las contraseñas no coinciden"

                        },
                        label = { Text("Confirmar contraseña") },
                        singleLine = true,
                        placeholder = { Text("Reingresar contraseña") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    painter = if (passwordVisible) painterResource(id = R.drawable.visibility) else painterResource(
                                        id = R.drawable.visibility_off
                                    ),
                                    contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                                )
                            }
                        } ,
                        isError = errorMessageConfirmPass.isNotEmpty()
                    )
                    if (errorMessageConfirmPass.isNotEmpty()) {
                        Text(
                            text = errorMessageConfirmPass,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { /* Acción de ingreso */ },
                        modifier = Modifier
                            .width(300.dp)
                            .height(40.dp),  // Adjusted height for better appearance
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
                        contentPadding = PaddingValues(0.dp) // Remove default padding
                    ) {
                        Text(
                            "Crear cuenta",
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 16.sp, // Reduced font size to fit in the small button
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
                        horizontalArrangement = Arrangement.SpaceBetween // Esto distribuirá el contenido con espacio entre ellos
                    ) {
                        // Logo colocado en el lado izquierdo
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Logo(size = 100.dp) // Tamaño reducido para el logo en la parte inferior
                            Spacer(modifier = Modifier.width(8.dp))
                        }

                        // Texto "Ya tienes una cuenta?" y "Ingresar" colocado en el lado derecho
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End // Esto alinea el contenido a la derecha
                        ) {
                            Text(
                                "Ya tienes una cuenta?",
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.End
                            )
                            TextButton(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                                Text(
                                    "Ingresar",
                                    color = MaterialTheme.colorScheme.secondary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis, // Añade puntos suspensivos si el texto es demasiado largo
                                    style = TextStyle(
                                        fontSize = 12.sp, // Ajusta el tamaño de la fuente según sea necesario
                                        textAlign = TextAlign.End,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier
                                        .widthIn(min = 32.dp) // Establece un ancho mínimo para el texto
                                        .wrapContentWidth(Alignment.CenterHorizontally) // Ajusta el ancho del contenido
                                )
                            }
                        }
                    }

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
    fun RegistroText() {
        val secondaryColor = MaterialTheme.colorScheme.secondary.toArgb()

        val paint = android.graphics.Paint().apply {
            textSize = 25f
            color = secondaryColor
            strokeWidth = 5f  // Adjust this value to make the line thicker
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()  // Occupies the entire available width
                .padding(start = 0.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)  // Adjust the start padding to move the text to the left
        ) {
            BasicText(
                text = "Registro",
                style = TextStyle(
                    color = Color(secondaryColor),
                    fontSize = 25.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)  // Aligns the text to the left
                    .drawWithContent {
                        drawContent()
                        drawIntoCanvas { canvas ->
                            // Measure the width of the text "Registro"
                            val width = paint.measureText("Registro")
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

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
fun isValidPhoneNumber(phone: String): Boolean {
    val regex = """\+\d{1,3} \d{8,9}""".toRegex()
    return regex.matches(phone)
}


fun isValidPassword(password: String): Boolean {
    // Regex to check the password format: at least one lowercase letter, one uppercase letter,
    // one digit, and one special character, and a minimum length of 8 characters
    val regex = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#\$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,}$""".toRegex()
    return regex.matches(password)
}

fun passwordsMatch(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}