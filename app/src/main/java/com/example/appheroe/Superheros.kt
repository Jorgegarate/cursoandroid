package com.example.appheroe

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Superheros(val name: String, val alterEgo: String, val bio: String, val power: Float) :
    Parcelable