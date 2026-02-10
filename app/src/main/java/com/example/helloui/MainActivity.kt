package com.example.helloui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.helloui.ui.theme.HelloUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDark by rememberSaveable { mutableStateOf(false) }
            HelloUITheme(darkTheme = isDark ) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HelloUI(
                        modifier = Modifier.padding(innerPadding),
                        isDark = isDark,
                        toggleDark = { isDark = !isDark }
                    )
                }
            }
        }
    }
}

@Composable
fun HelloUI(
    modifier : Modifier = Modifier,
    isDark : Boolean,
    toggleDark : () -> Unit
){
    var input by remember {mutableStateOf("")}
    var name by remember {mutableStateOf("World")}

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello, $name!",
            style = MaterialTheme.typography.headlineLarge
        )

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Masukkan nama!" )},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (input.isNotBlank()){
                name = input
            }
        }) {
            Text("Sapa")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Switch(
            checked = isDark,
            onCheckedChange = { toggleDark() }
        )
    }
}