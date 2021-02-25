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

class DataFactory {
    companion object {
        lateinit var dogData: MutableList<DogData>
        fun init() {
            val data = mutableListOf<DogData>()
            data.add(
                DogData(
                    "Bailey", "Alaskan Malamute", R.drawable.dog_0,
                    "male", 8, 43f
                )
            )
            data.add(
                DogData(
                    "Lucy", "Bichon Frise", R.drawable.dog_1,
                    "female", 4, 4.3f
                )
            )
            data.add(
                DogData(
                    "Max", "dachshund", R.drawable.dog_2,
                    "male", 6, 12f
                )
            )
            dogData = data
        }
    }
}
