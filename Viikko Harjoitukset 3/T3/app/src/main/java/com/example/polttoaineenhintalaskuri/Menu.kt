package com.example.polttoaineenhintalaskuri

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(modifier: Modifier = Modifier) {
    var fuelPrice by remember { mutableStateOf("") }
    var fuelConsumption by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var totalCost by remember { mutableStateOf(0.0) }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.Title),
            style = MaterialTheme.typography.headlineMedium.copy(
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = fuelPrice,
            onValueChange = { fuelPrice = it },
            label = { Text(stringResource(R.string.Price)) },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            ),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = fuelConsumption,
            onValueChange = { fuelConsumption = it },
            label = { Text(stringResource(R.string.Consumption)) },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            ),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = distance,
            onValueChange = { distance = it },
            label = { Text(stringResource(R.string.Distance)) },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            val price = fuelPrice.toDoubleOrNull() ?: 0.0
            val consumption = fuelConsumption.toDoubleOrNull() ?: 0.0
            val distanceValue = distance.toDoubleOrNull() ?: 0.0

            // Calculate the total cost
            totalCost = (distanceValue * consumption / 100) * price
        }) {
            Text(stringResource(R.string.Button))
        }

        Text(
            text = stringResource(R.string.Total, String.format("%.2f", totalCost)),
            style = MaterialTheme.typography.titleLarge
        )
    }
}
