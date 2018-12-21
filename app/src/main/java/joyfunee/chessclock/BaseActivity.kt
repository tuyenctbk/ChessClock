package joyfunee.chessclock

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    lateinit var pref: SharedPreferences

    val neededPermissions = arrayOf(Manifest.permission.INTERNET, Manifest.permission.VIBRATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pref = PreferenceManager.getDefaultSharedPreferences(this)

        if (needRequestPermissions()) {
            requestNeededPermissions()
        } else {
            initAll()
        }
    }

    private val REQUEST_NEEDED_PERMISSION: Int = 100

    private fun requestNeededPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(neededPermissions, REQUEST_NEEDED_PERMISSION)
        }
    }

    private fun needRequestPermissions(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return false
        for (permission in neededPermissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return true
            }

        }
        return false
    }


    fun initUI() {

    }

    fun initAll() {
        initUI()
        loadPreferences()
    }

    private fun loadPreferences() {
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_NEEDED_PERMISSION) {

        }
    }
}
