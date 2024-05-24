package ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.dp
import data.model.Country
import data.CountryApiClient
import io.ktor.client.plugins.*
import kotlinx.coroutines.launch
import java.net.URL
import javax.imageio.ImageIO

@Composable
fun mainView() {
    val countriesState = remember { mutableStateOf<List<Country>?>(null) }
    val isLoading = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        isLoading.value = true // Set loading indicator to true before fetching data
        scope.launch {
            try {
                val loadedCountries = CountryApiClient.getCountries()
                countriesState.value = loadedCountries
            } catch (e: ClientRequestException) {
                println("Error fetching data: ${e.message}")
            } finally {
                isLoading.value = false // Set loading indicator to false after fetching data
            }
        }
    }

    val countries = countriesState.value

    MaterialTheme {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            if (isLoading.value) {
                // Show loading indicator
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                // Show country list
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    item {
                        Text(
                            text = "Countries of South America",
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    items(countries ?: emptyList()) { country ->
                        CountryListItem(country = country)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun CountryListItem(country: Country) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        backgroundColor = Color.LightGray,
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clickable { expanded = !expanded }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = country.name.common ?: "",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Capital: ${country.capital.joinToString()}",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(1f)
                )
                val flagImage = loadImage(country.flags.png)
                flagImage?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                            .offset(x = (-55).dp, y = 0.dp)

                    )
                }
                println(flagImage)

                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Expand/Collapse",
                    tint = if (expanded) Color.Black else Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }


            var snackbarVisible by remember { mutableStateOf(false) }
            if (expanded) {

                Button(
                    onClick = { snackbarVisible = true }, // Встановлюємо значення видимості Snackbar на true при натисканні на кнопку
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "More Info")
                }
            }
            if (snackbarVisible) {
                Snackbar(
                    action = {
                        Button(
                            onClick = { snackbarVisible = false }, // Закриття Snackbar при натисканні на кнопку "Close"
                        ) {
                            Text(text = "Close")
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .height(150.dp)
                ) {
                    // Додаткова інформація про країну
                    Column(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "State language: ${country.languages}",
                            style = MaterialTheme.typography.body1,
                        )
                        Text(
                            text = "Area: ${country.area} sq. km",
                            style = MaterialTheme.typography.body1,
                        )
                        Text(
                            text = "Population: ${country.population}",
                            style = MaterialTheme.typography.body1,
                        )
                    }
                }
            }

        }
    }
}

fun loadImage(url: String): ImageBitmap {
    return ImageIO.read(URL("$url")).toComposeImageBitmap()
}
