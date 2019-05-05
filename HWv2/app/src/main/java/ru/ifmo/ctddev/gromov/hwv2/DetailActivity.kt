package ru.ifmo.ctddev.gromov.hwv2

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*
import ru.ifmo.ctddev.gromov.hwv2.json.Item
import java.lang.ref.WeakReference

class DetailActivity : AppCompatActivity() {
    private lateinit var item: Item
    private var index: Int? = null
    var bm: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        index = intent?.extras?.get("item") as Int?
        if (index != null) {
            item = App.getApp().list?.response?.items!![index!!]
            if (App.getApp().images.containsKey(index!!)) {
                bm = App.getApp().images[index!!]
                updateContent()
            } else TakeImageTask(WeakReference(this)).execute(item.sizes?.last()?.url)
        } else updateContent()
    }

    fun updateContent() {
        progressBar.visibility = View.INVISIBLE
        if (bm == null) {
            detailText.text = getString(R.string.error_message)
            detailText.visibility = View.VISIBLE
        } else {
            image.setImageBitmap(bm)
            image.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (bm != null && index != null) App.getApp().images[index!!] = bm!!
    }
}
