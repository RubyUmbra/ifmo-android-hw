package ru.ifmo.ctddev.gromov.vkphotosx

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Post(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var text: String = "",
    var isFave: Boolean = false,
    var url: String? = null
) : Parcelable