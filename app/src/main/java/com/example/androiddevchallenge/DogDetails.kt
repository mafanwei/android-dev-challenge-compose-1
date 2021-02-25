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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun DogDetails(dogData: DogData) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = dogData.name)
                }
            )
        }
    ) {
        Column {
            Image(
                painter = painterResource(id = dogData.img),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    // .offset(y = imageOffset)
                    .fillMaxWidth()
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                TwoLineText("Breed", dogData.breed)
                TwoLineText("Weight️", dogData.weight.toString() + " kg")
                TwoLineText("Sex", detail = dogData.sex)
                TwoLineText("Age", detail = dogData.age.toString())
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = dogData.desc, modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
private fun TwoLineText(heading: String, detail: String, modifier: Modifier = Modifier) {
    Column(
        modifier.padding(horizontal = 0.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(heading, style = MaterialTheme.typography.subtitle2)
        Text(detail, style = MaterialTheme.typography.body1)
    }
}

@Preview
@Composable
private fun PuppyPreview() {
    MyTheme {
        DogDetails(dogData = DataFactory.dogData[1])
    }
}
