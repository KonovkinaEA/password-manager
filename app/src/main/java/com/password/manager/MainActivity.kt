package com.password.manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.password.manager.data.encryption.CryptoManager
import com.password.manager.ui.theme.PasswordManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cryptoManager = CryptoManager()

        setContent {
            PasswordManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    var masterPassword by remember { mutableStateOf("") }
                    var masterPasswordEntered by remember { mutableStateOf("") }

                    var password by remember { mutableStateOf("") }

                    var encryptedPassword by remember { mutableStateOf("") }
                    var decryptedPassword by remember { mutableStateOf("") }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            TextField(value = masterPassword,
                                onValueChange = { masterPassword = it },
                                placeholder = { Text(text = "Master-password") })
                            Spacer(modifier = Modifier.width(10.dp))
                            Button(onClick = {
                                masterPasswordEntered =
                                    if (cryptoManager.checkSecretKey(masterPassword)) {
                                        "Master-password entered"
                                    } else {
                                        "Wrong master-password"
                                    }
                            }) {
                                Text(text = "Enter")
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = masterPasswordEntered)

                        Spacer(modifier = Modifier.height(10.dp))

                        Row {
                            TextField(value = password,
                                onValueChange = { password = it },
                                placeholder = { Text(text = "Password to encrypt") })
                            Spacer(modifier = Modifier.width(10.dp))
                            Button(onClick = {
                                cryptoManager.encrypt(password)?.let {
                                    encryptedPassword = it
                                }
                                cryptoManager.decrypt(encryptedPassword)?.let {
                                    decryptedPassword = it
                                }
                            }) {
                                Text(text = "Enter")
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        Text(text = "Encrypted password:")
                        Text(text = encryptedPassword)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Decrypted password: $decryptedPassword")
                    }
                }
            }
        }
    }
}
