package com.englesoft.weatherinfo.presentation.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.englesoft.weatherinfo.R
import com.englesoft.weatherinfo.domain.model.WeatherInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToSearch: () -> Unit,
) {
    val state = viewModel.state

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Weather Info") },
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (state.weatherInfo != null) {
                HomeScreenContent(weatherInfo = state.weatherInfo, onNavigateToSearch)
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            state.error?.let { error ->
                Text(
                    text = error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun HomeScreenContent(weatherInfo: WeatherInfo, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Searchbar
        CitySearchBar(onClick)

        // Temperature and Weather Details
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "${weatherInfo.temperatureCelsius}°C",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${weatherInfo.city}, ${weatherInfo.country}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val iconUrl =
                    "https://openweathermap.org/img/wn/${weatherInfo.weatherIcon}@4x.png"
                Image(
                    painter = rememberAsyncImagePainter(model = iconUrl),
                    contentDescription = "Weather Icon",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = weatherInfo.weatherTitle,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        // Additional Weather Info
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            WeatherInfoRow(
                label = "Maximum Temperature",
                value = "${weatherInfo.temperatureMax}°C"
            )
            WeatherInfoRow(
                label = "Minimum Temperature",
                value = "${weatherInfo.temperatureMin}°C"
            )
            WeatherInfoRow(label = "Humidity", value = "${weatherInfo.humidity}%")
            WeatherInfoRow(label = "Pressure", value = "${weatherInfo.pressure} mBar")
            WeatherInfoRow(label = "Wind Speed", value = "${weatherInfo.windSpeed} KM")
            WeatherInfoRow(label = "Visibility", value = "${weatherInfo.visibility} KM")
        }

        // Sunrise and Sunset
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SunriseSunsetInfo(
                label = "Sunrise",
                time = weatherInfo.sunrise,
                icon = R.drawable.ic_sunrise
            )
            SunriseSunsetInfo(
                label = "Sunset",
                time = weatherInfo.sunset,
                icon = R.drawable.ic_sunset
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            "Last updated: ${weatherInfo.time}",
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun CitySearchBar(onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                shape = RoundedCornerShape(32.dp)
            )
            .padding(16.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = "Search for Cities",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun WeatherInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "$label: ", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SunriseSunsetInfo(label: String, time: String?, icon: Int) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(120.dp)
        )
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(
            text = time ?: "",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
