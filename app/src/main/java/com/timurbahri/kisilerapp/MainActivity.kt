package com.timurbahri.kisilerapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timurbahri.kisilerapp.entity.Kisiler
import com.timurbahri.kisilerapp.ui.theme.KisilerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KisilerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Anasayfa()
                }
            }
        }
    }
}

@Composable
fun Anasayfa() {
    val aramaYapılıyorMu = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    val kisilerListesi = remember { mutableStateListOf<Kisiler>() }

    LaunchedEffect(key1 = true) {
        val k1 = Kisiler(1, "Timur", "Bahri")
        val k2 = Kisiler(2, "Timur", "Bahri")
        kisilerListesi.add(k1)
        kisilerListesi.add(k2)
    }

        Scaffold(
        topBar = {
            TopAppBar(title = {

                if (aramaYapılıyorMu.value) {
                    TextField(value = tf.value,
                        onValueChange = {
                            tf.value = it
                            Log.e("Kişi arama",it) },

                        label = { Text(text = "Ara")},
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedLabelColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            unfocusedIndicatorColor = Color.White
                        )
                        )
                }else {
                    Text(text = "Kişiler")
                }
                },
                actions = {
                    if (aramaYapılıyorMu.value) {
                        IconButton(onClick = {
                            aramaYapılıyorMu.value = false
                            tf.value = ""
                        }) {
                            Icon(painter = painterResource(id = R.drawable.close_icon),
                                contentDescription = "", tint = Color.White)
                        }

                    }else {
                        IconButton(onClick = {
                            aramaYapılıyorMu.value = true
                        }) {
                            Icon(painter = painterResource(id = R.drawable.search_icon),
                                contentDescription = "", tint = Color.White)
                        }
                    }
                }

            )


        },
        content = {
                  LazyColumn{
                      items(
                          count = kisilerListesi.count(),
                          itemContent = {
                            val kisi = kisilerListesi[it]
                              Card(modifier = Modifier
                                  .padding(all = 5.dp)
                                  .fillMaxWidth()) {
                                Row(modifier = Modifier.clickable {

                                }) {
                                    Row(modifier = Modifier
                                        .padding(all = 7.dp)
                                        .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(text = "${kisi.kisi_adi} - ${kisi.kisi_tel}")
                                        Icon(
                                            painter = painterResource(id = R.drawable.delete_icon),
                                            contentDescription = "", tint = Color.Gray)

                                    }
                                }
                              }
                          }

                      )
                  }

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                backgroundColor = colorResource(id = R.color.teal_200),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "", tint = Color.White)

                }
            )
        }
    )


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KisilerAppTheme {
        Anasayfa()
    }
}