package ru.ifmo.ctddev.gromov.vkphotos

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.image_detail.view.*

class ImageDetailFragment : Fragment() {
    private var image: ImagesContent.ImageItem? = null
    private lateinit var view: ImageView
    lateinit var receiver: ServiceReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiver = ServiceReceiver(Handler())
        receiver.setReceiver(object : ServiceReceiver.Receiver {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                if (resultCode == RESULT_OK) {
                    val res = resultData.getByteArray("RESULT_VALUE")
                    view.setImageBitmap(BitmapFactory.decodeByteArray(res, 0, res?.size ?: 0))
                    context?.openFileOutput(resultData.getString("FILENAME"), Context.MODE_PRIVATE)
                        .use { it?.write(res) }
                }
            }
        })
        arguments?.let { if (it.containsKey("item_id")) image = ImagesContent.IMAGE_MAP[it.getInt("item_id")] }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.image_detail, container, false)
        view = rootView.image_detail
        context?.startService(Intent(context, Loader::class.java).apply {
            putExtra("EXTRA_URL", image?.url ?: "")
            putExtra("RECEIVER", receiver)
        })
        return rootView
    }
}
