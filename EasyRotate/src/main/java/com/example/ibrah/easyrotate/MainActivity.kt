package com.example.ibrah.easyrotate
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.View
import android.view.WindowManager
import android.os.Build
import android.util.SparseIntArray




class MainActivity : AppCompatActivity() {
    private var orientationMap: SparseIntArray? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        orientationMap = SparseIntArray(4)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val port by bind<View>(R.id.portView)
        val land by bind<View>(R.id.landScape)
        val portR by bind<View>(R.id.portViewR)
        val landR by bind<View>(R.id.landScapeR)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        port.setOnClickListener {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            startRotate(3)

        }
        land.setOnClickListener {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            startRotate(1)

        }
        portR.setOnClickListener {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
            startRotate(4)

        }
        landR.setOnClickListener {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
            startRotate(2)
        }
    }
    fun startRotate(id: Int){
        val rotationService = Intent(this, RotationService::class.java)
        rotationService.putExtra("indicator", id)
        startService(rotationService)
    }
    //safe binding
    fun <T : View> Activity.bind(@IdRes idRes: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return unsafeLazy { findViewById<T>(idRes) }
    }
    fun <T : View> View.bind(@IdRes idRes: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return unsafeLazy { findViewById<T>(idRes) }
    }
    private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
    //safe binding

}
