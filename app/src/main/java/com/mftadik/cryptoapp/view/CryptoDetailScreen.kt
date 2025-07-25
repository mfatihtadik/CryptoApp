package com.mftadik.cryptoapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.mftadik.cryptoapp.model.Crypto
import com.mftadik.cryptoapp.util.Resource
import com.mftadik.cryptoapp.viewmodel.CryptoDetailViewModel

@Composable
fun CryptoDetailScreen(
    id: String,
    price : String,
    navController: NavController,
    viewModel : CryptoDetailViewModel = hiltViewModel()
) {

    //Step1 --> Wrong
    //Devamlı bir şekilde istek atar...
    //Coroutine içinde atılan istek, Bir state değişken olan cryptoItem ' ı değiştirdiği için recomposition'a yol açıyor.
    //Bu yüzden fonksiyon devamlı devamlı baştan çağrılıyor...
    /*
    val scope = rememberCoroutineScope()
    var cryptoItem by remember { mutableStateOf<Resource<Crypto>>(Resource.Loading()) }
        scope.launch {
            cryptoItem = viewModel.getCrypto()
            println(cryptoItem.data)
        }
    */
     //RememberCoroutineScope daha çok kullanıcının tetikleyebileceği olaylarda kullanılır...
    //Butona tıklamak gibi vs.



    //Step2 --> Better

    /*
    var cryptoItem by remember { mutableStateOf<Resource<Crypto>>(Resource.Loading()) }

    LaunchedEffect(key1 = Unit) {
            cryptoItem = viewModel.getCrypto()
            println(cryptoItem.data)
    }

     */

    //Step3 --> Shortest

    val cryptoItem = produceState<Resource<Crypto>>(initialValue = Resource.Loading()) {
        value = viewModel.getCrypto()
    }.value


    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when(cryptoItem) {
                is Resource.Success -> {
                    val selectedCrypto = cryptoItem.data!![0]
                    Text(text = selectedCrypto.name,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                        )

                    Image(
                        painter = rememberImagePainter(data = selectedCrypto.logo_url),
                        contentDescription = selectedCrypto.name,
                        modifier = Modifier.padding(16.dp)
                            .size(200.dp, 200.dp)
                            .clip(CircleShape)
                            .border(2.dp, color = Color.Gray)
                    )

                    Text(text = price,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        textAlign = TextAlign.Center
                    )
                }
                is Resource.Error -> {
                    Text(text = cryptoItem.message!!)
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

        }
    }


}