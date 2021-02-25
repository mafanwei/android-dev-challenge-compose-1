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

data class DogData(
    var name: String,
    var breed: String,
    var img: Int,
    var sex: String = "male",
    var age: Int,
    var weight: Float,
    var desc: String = "For some reason, " +
        "I can't keep it anymore, " +
        "and I hope to find a suitable person to replace me to love it."
)
