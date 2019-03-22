package ru.ifmo.ctddev.gromov.vkphotosx.model.room

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import ru.ifmo.ctddev.gromov.vkphotosx.view.DetailActivity

@Entity
@Parcelize
data class Post(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var text: String = "",
    var isFave: Boolean = false,
    var url: String? = null
) : Parcelable

fun Post.onClick(context: Context): Unit =
    context.startActivity(Intent(context, DetailActivity::class.java).putExtra("POST", this))