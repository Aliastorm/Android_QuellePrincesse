package com.example.quelleprincesse

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.quelleprincesse.ui.theme.QuellePrincesseTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuellePrincesseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldComposable()
                }
            }
        }
    }
}


var princesses: List<String> = listOf(
    "raiponce", "elsa", "merida", "mirabel", "pocahontas"
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldComposable() {
    Scaffold(
        topBar = { Top() },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            ) {
                Body()
            }
        }

    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top() {
    TopAppBar(
        title = { Text(text = "Quelle princesse êtes-vous?") },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)

    )
}


@Composable
fun Body() {
    val local = LocalConfiguration.current
    val height = local.screenHeightDp
    val width = local.screenWidthDp
    val context = LocalContext.current

    var index by remember {
        mutableStateOf(-1)
    }

    val currentPrincess2 by remember { mutableStateOf(-1) }



    val currentPrincess= remember(index) {
        if (index >= 0) {

            princesses[index] to context.resources.getIdentifier(
                princesses[index],
                "drawable",
                context.packageName
            )

        } else {
            "Pas commencé" to R.drawable.all
        }
    }


    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = currentPrincess.second),
            contentDescription = null,
            modifier = Modifier
                .height((height / 2).dp)
                .width((width * 0.8).dp)
                .padding(20.dp)
                .clip(RoundedCornerShape(percent = 5)),
            contentScale = ContentScale.Crop,
        )

        Text(text = "vous êtes ... ${currentPrincess.first}")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = {
                index = (princesses.indices).random()

            }) {
                Text(text = "C'est parti !!!")
            }
            IconButton(onClick = {index = -1}) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)

            }
        }


    }


}
