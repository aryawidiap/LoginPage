package com.example.loginpage.ui.theme

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.loginpage.R

@Composable
fun LoginScreen(onLoginClick:() -> Unit){
    val (email, setEmail) = rememberSaveable {
        mutableStateOf("")
    }
    val (password, setPassword) = rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // Login Image
        Image(painter = painterResource(
            id = R.drawable.login_img),
            contentDescription = "Login image",
            modifier = Modifier.size(200.dp)
        )
        // Welcome Text
        Text(text = "Welcome Back")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Login to your account")

        // Login Form
        // Username Form
        OutlinedTextField(
            value = email,
            onValueChange = setEmail,
            label = { Text(text =  "Email address") }
        )
        // Password Form
        OutlinedTextField(
            value = password,
            onValueChange = setPassword,
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(onClick = onLoginClick) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(32.dp))

        // Forgot Password link
        Text(text = "Forgot Password?", modifier = Modifier.clickable {

        })
        Spacer(modifier = Modifier.height(32.dp))
        // Other sign in methods
        Text(text = "Or sign in with")

        AlternativeLoginOptions(
            onImageClick = {index ->
                when(index){
                    0 -> {
                        Toast.makeText(context, "Facebook login clicked.", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(context, "Google login clicked.", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(context, "X login clicked.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )


    }
}

@Composable
fun AlternativeLoginOptions(
    onImageClick:(index:Int) -> Unit,
    modifier: Modifier = Modifier
){
    data class LoginAlt(val imgResource: Int, val description: String = "Generic description")
    val loginList = listOf(
        LoginAlt(R.drawable.facebook_logo_primary, description = "Facebook Login"),
        LoginAlt(R.drawable.google_logo, description = "Google Login"),
        LoginAlt(R.drawable.x_logo_black, description = "X / Twitter Login"),

        )
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        loginList.forEachIndexed {index, loginAltItem ->
            Image(painter = painterResource(id = loginAltItem.imgResource), contentDescription = loginAltItem.description,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onImageClick(index)
                    }
            )
        }
    }
}