package com.timurbahri.kisilerapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun KisiKayıtSayfa() {
    val tfKisiAd = remember { mutableStateOf("") }
    val tfKisiTel = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Kişi Kayıt") })

        },
        content = {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = tfKisiAd.value,
                    onValueChange = { tfKisiAd.value = it }, label = { Text(text = "Kişi Adı")})

                TextField(value = tfKisiTel.value,
                    onValueChange = { tfKisiTel.value = it }, label = { Text(text = "Kişi Tel")})

                Button(onClick = {
                    val kisiAdı = tfKisiAd.value
                    val kisiTel = tfKisiTel.value
                    Log.e("Kişi Kayıt","$kisiAdı - $kisiTel")
                    localFocusManager.clearFocus()

                }, modifier = Modifier.size(250.dp,50.dp)) {
                    Text(text = "Kaydet")
                }

            }
        }

    )


}