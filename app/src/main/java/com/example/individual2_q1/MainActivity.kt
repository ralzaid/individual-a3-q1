@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.individual2_q1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val AppColors = lightColorScheme(
    primary = Color(0xFF2F303D),
    secondary = Color(0xFF757575),
    surface = Color(0xFFFFFFFF),
    background = Color(0xFFF5F5F5),
    onPrimary = Color.White,
    onSurface = Color(0xFF212121)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = AppColors) {
                SettingsScreen()
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    var autoSync by rememberSaveable { mutableStateOf(false) }
    var volume by rememberSaveable { mutableFloatStateOf(0.6f) }

    Scaffold { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Column(Modifier.fillMaxWidth()) {
                    ListItem(
                        headlineContent = { Text("Preferences") },
                        supportingContent = { Text("Basic app settings") }
                    )
                    HorizontalDivider()

                    SettingRow(
                        title = "Sounds",
                        subtitle = "COntrols in-app sounds",
                        right = {
                            Switch(
                                checked = autoSync,
                                onCheckedChange = { autoSync = it }
                            )
                        },
                        onRowClick = { autoSync = !autoSync }
                    )

                    HorizontalDivider()

                    SettingRow(
                        title = "Volume",
                        subtitle = "Controls in-app sounds",
                        right = {
                            Slider(
                                modifier = Modifier.width(120.dp),
                                value = volume,
                                onValueChange = { volume = it }
                            )
                        },
                        onRowClick = {}
                    )

                    HorizontalDivider()

                    SettingRow(
                        title = "Delete Account",
                        subtitle = "This action cannot be undone",
                        right = {
                            Button(
                                onClick = {},
                                modifier = Modifier.heightIn(min = 40.dp)
                            ) { Text("Delete") }
                        },
                        onRowClick = {}
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingRow(
    title: String,
    subtitle: String,
    right: @Composable () -> Unit,
    onRowClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 72.dp)
            .clickable { onRowClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Box(modifier = Modifier.align(Alignment.CenterVertically)) {
            right()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSettings() {
    MaterialTheme(colorScheme = AppColors) {
        SettingsScreen()
    }
}