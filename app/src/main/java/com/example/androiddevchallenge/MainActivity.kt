/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.app.Activity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataFactory.init()
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    var dogClicked by remember { mutableStateOf<DogData?>(null) }
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current
    val context = LocalContext.current
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (dogClicked != null) {
                    dogClicked = null
                } else {
                    (context as? Activity)?.finish()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Dogs")
                }
            )
        }
    ) {
        val scrollState = rememberLazyListState()
        LazyColumn(state = scrollState) {
            items(DataFactory.dogData.size) {
                DogListItem(it) {
                    dogClicked = it
                }
            }
        }
    }

    DisposableEffect(key1 = backDispatcher) {
        backDispatcher.onBackPressedDispatcher.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }

    Surface(color = MaterialTheme.colors.background) {
        dogClicked?.let {
            DogDetails(dogData = it)
        }
    }
}

@Composable
private fun DogListItem(index: Int, onClick: (DogData) -> Unit) {
    val dogData = DataFactory.dogData[index]
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                // openDogDetails(dogData)
                onClick(dogData)
            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 0.dp, vertical = 4.dp)) {
            Spacer(Modifier.width(4.dp))
            Image(
                painter = painterResource(dogData.img),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(4.dp))
            )
            Spacer(Modifier.width(10.dp))
            Column() {
                Text(dogData.name, style = MaterialTheme.typography.subtitle1)
                Text(dogData.breed, style = MaterialTheme.typography.subtitle2)
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
