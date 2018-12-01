package com.example.ibrah.easyrotate

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout

class RotationService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val extras: Bundle?
        if (intent.extras==null){
        extras = intent.extras
        if (extras != null) {
            val indicator = extras.getInt("indicator", -1)

            if (indicator > -1) {
                val orientationChanger = LinearLayout(this)
                val layoutParams = WindowManager.LayoutParams(
                    WindowManager.LayoutParams.TYPE_APPLICATION_PANEL,
                    0,
                    PixelFormat.RGBA_8888
                )
                layoutParams.screenOrientation = indicator

                val windowManager = getSystemService(Service.WINDOW_SERVICE) as WindowManager

                if (windowManager != null) {
                    windowManager.addView(orientationChanger, layoutParams)
                    orientationChanger.visibility = View.VISIBLE
                }
            }        }

        }

        return Service.START_STICKY
    }

}
