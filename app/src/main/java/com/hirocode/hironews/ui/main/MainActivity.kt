package com.hirocode.hironews.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.hirocode.hironews.R
import com.hirocode.hironews.ui.theme.HiroNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiroNewsTheme {
                HiroNewsApp()
            }
        }
    }
}

@Composable
fun HiroNewsApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            MainScreen()
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HiroNewsAppPreview() {
    HiroNewsTheme {
        HiroNewsApp()
    }
}