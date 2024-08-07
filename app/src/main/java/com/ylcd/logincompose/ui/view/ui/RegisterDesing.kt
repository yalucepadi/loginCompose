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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ylcd.logincompose.R
import com.ylcd.logincompose.ui.theme.AppComposeTheme
import com.ylcd.logincompose.ui.view.fragments.RegisterFragment
import com.ylcd.logincompose.ui.view.navigation.Screen
import com.ylcd.logincompose.util.Validations

class RegisterDesing {

    @Composable
    fun RegisterScreen(navController: NavController, registerFragment: RegisterFragment) {
        AppComposeTheme {
            val context = LocalContext.current
            var validations: Validations = Validations()
            var mail by remember { mutableStateOf("") }
            var tel by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var confirmPassword by remember { mutableStateOf("") }
            var passwordVisible by remember { mutableStateOf(false) }

            var errorMessageMail by remember { mutableStateOf("") }
            var errorMessageTel by remember { mutableStateOf("") }
            var errorMessagePass by remember { mutableStateOf("") }
            var errorMessageConfirmPass by remember { mutableStateOf("") }
            var showErrorMessages by remember { mutableStateOf(false) }


            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 1.dp)
                    ) {
                        val imageBitmap: ImageBitmap =
                            ImageBitmap.imageResource(id = R.drawable.textura)


                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val path = Path()


                            path.moveTo(0f, 0f)


                            path.lineTo(size.width, 0f)


                            path.lineTo(
                                size.width,
                                size.height * 0.2f
                            )
                            path.cubicTo(
                                size.width * 0.75f, size.height * 0.3f,
                                size.width * 0.25f, size.height * 0.1f,
                                0f, size.height * 0.2f
                            )


                            path.close()


                            val shader =
                                ImageShader(imageBitmap, TileMode.Repeated, TileMode.Repeated)


                            val brush = ShaderBrush(shader)


                            drawPath(
                                path = path,
                                brush = brush
                            )
                        }


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

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(90.dp))
                    RegistroText()
                    Spacer(modifier = Modifier.height(32.dp))
                    OutlinedTextField(
                        value = mail,
                        onValueChange = {
                            mail = it
                            errorMessageMail =
                                if (validations.isValidEmail(it)) "" else "Correo inválido"
                        },
                        label = { Text("Correo electrónico") },
                        placeholder = { Text("Ingresar correo electrónico") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                        isError = errorMessageMail.isNotEmpty() && showErrorMessages

                    )

                    if (showErrorMessages && errorMessageMail.isNotEmpty()) {
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
                        onValueChange = {
                            tel = it
                            errorMessageTel =
                                if (validations.isValidPhoneNumber(it)) "" else "Número inválido"

                        },
                        label = { Text("Número de teléfono") },
                        placeholder = { Text("Ej: +54 12345678") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Call, contentDescription = null) },
                        isError = errorMessageTel.isNotEmpty() && showErrorMessages

                    )

                    if (showErrorMessages && errorMessageTel.isNotEmpty()) {
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
                        onValueChange = {
                            password = it
                            errorMessagePass =
                                if (validations.isValidPassword(it)) "" else "Contraseña débil"
                            errorMessageConfirmPass = if (validations.passwordsMatch(
                                    it,
                                    confirmPassword
                                )
                            ) "" else "Las contraseñas no coinciden"
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
                        },
                        isError = errorMessagePass.isNotEmpty() && showErrorMessages


                    )
                    if (showErrorMessages && errorMessagePass.isNotEmpty()) {
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
                        onValueChange = {
                            confirmPassword = it
                            errorMessageConfirmPass = if (validations.passwordsMatch(
                                    password,
                                    it
                                )
                            ) "" else "Las contraseñas no coinciden"

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
                        },
                        isError = errorMessageConfirmPass.isNotEmpty() && showErrorMessages
                    )

                    if (showErrorMessages && errorMessageConfirmPass.isNotEmpty()) {
                        Text(
                            text = errorMessageConfirmPass,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            // Perform validations when button is clicked
                            showErrorMessages = true
                            errorMessageMail =
                                if (validations.isValidEmail(mail)) "" else "Correo inválido"
                            errorMessageTel =
                                if (validations.isValidPhoneNumber(tel)) "" else "Número inválido"
                            errorMessagePass =
                                if (validations.isValidPassword(password)) "" else "Contraseña débil"
                            errorMessageConfirmPass =
                                if (validations.passwordsMatch(
                                        password,
                                        confirmPassword
                                    )
                                ) "" else "Las contraseñas no coinciden"
                            val verificar = registerFragment.register(
                                mail.toString(), tel.toString(), password.toString(), context
                            )
                            if (errorMessageMail.isEmpty() && errorMessageTel.isEmpty() &&
                                errorMessagePass.isEmpty() && errorMessageConfirmPass.isEmpty()
                            ) {
                                if (verificar) {

                                    navController.navigate(Screen.MainScreen.route)
                                }
                            }
                        },
                        modifier = Modifier
                            .width(300.dp)
                            .height(40.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            "Crear cuenta",
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxHeight()
                                .wrapContentHeight(Alignment.CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Logo colocado en el lado izquierdo
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Logo(size = 100.dp)
                            Spacer(modifier = Modifier.width(8.dp))
                        }


                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
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
                                    overflow = TextOverflow.Ellipsis,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.End,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier
                                        .widthIn(min = 32.dp)
                                        .wrapContentWidth(Alignment.CenterHorizontally)
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
                .size(size)
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
                        .size(size)
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
            strokeWidth = 5f
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 0.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
            BasicText(
                text = "Registro",
                style = TextStyle(
                    color = Color(secondaryColor),
                    fontSize = 25.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .drawWithContent {
                        drawContent()
                        drawIntoCanvas { canvas ->

                            val width = paint.measureText("Registro")
                            val yPosition = size.height + 12f
                            canvas.nativeCanvas.drawLine(
                                1f,
                                yPosition,
                                140f,
                                yPosition,
                                paint
                            )
                        }
                    }
            )
        }
    }

}

