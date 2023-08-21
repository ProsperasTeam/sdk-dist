package com.prosperas.marketplace

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.prosperas.marketplace.databinding.ActivitySdkBinding

private const val CAMERA_PERMISSION_REQUEST_CODE = 100
private const val LOCATION_PERMISSION_REQUEST_CODE = 101

class ProsperasSDK : Activity() {
    private var fileChooserCallback: ValueCallback<Array<Uri>>? = null
    private lateinit var binding: ActivitySdkBinding
    var locale = ""
    var sessionId = ""
    var newApiKey = ""
    var url = ""
    var colorNumber: Int = 0

    private fun setNativeColor()
    {
        var colorString = intent.extras?.getString("nativeButtons").toString()
        try {
            colorNumber = Color.parseColor(colorString)
        }
        catch (e: IllegalArgumentException)
        {
            colorNumber = Color.RED
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySdkBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        locale = intent.extras?.getString("locale").toString()
        newApiKey = intent.extras?.getString("apiKey2").toString()
        sessionId = intent.extras?.getString("sessionId").toString()
        url = intent.extras?.getString("url").toString()+
                "?sessionid=$sessionId&apikey=$newApiKey&locale=$locale"

        GenerateWebView(url)

        val btnRegresar = view.findViewById<Button>(R.id.botonRegresar)
        val btnExit = view.findViewById<Button>(R.id.botonSalida)
        setNativeColor()
        btnExit.setTextColor(colorNumber)
        btnRegresar.setTextColor(colorNumber)
        if(locale == "en-rUS"){
            btnRegresar.text = "Back"
            btnExit.text = "Exit"
        }

        btnRegresar.setOnClickListener{
            GenerateWebView(url)
        }
        btnExit.setOnClickListener{
            val returnIntent = Intent()
            returnIntent.putExtra("result", "Cancelado por el usuario")
            this.finishActivity(RESULT_CANCELED)
            this.finish()
        }

        /*// Check camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Request camera permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            // Camera permission already granted, you can proceed with camera-related tasks
            // ...
        }

        // Check location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Request location permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Location permission already granted, you can proceed with location-related tasks
            // ...
        }*/
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission granted, you can proceed with camera-related tasks
                // ...
            } else {
                // Camera permission denied, handle the scenario where the user denied the permission
                // ...
            }
        }

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted, you can proceed with location-related tasks
                // ...
            } else {
                // Location permission denied, handle the scenario where the user denied the permission
                // ...
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }
    fun openWebURL(url: String?) {
        var url = url ?: return
        if (!URLUtil.isValidUrl(url)) {
            if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://$url"
        }
        try {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(myIntent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }
    private fun GenerateWebView(uriString: String)
    {
        val home = Uri.parse(uriString)

        val view = binding.eazulWebview


        val settings = view.settings
        settings.allowContentAccess = true
        settings.allowFileAccess = true
        settings.databaseEnabled = true
        settings.domStorageEnabled = true
        settings.setGeolocationEnabled(true)
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.javaScriptEnabled = true
        settings.loadsImagesAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = false
        settings.setSupportMultipleWindows(true)
        //settings.userAgentString = "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.5735.57 Mobile Safari/537.36"
        view.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                vw: WebView,
                request: WebResourceRequest
            ): Boolean {
                if (request.url.toString().contains(home.host!!)) {
                    vw.loadUrl(request.url.toString())
                } else {
                    vw.loadUrl(request.url.toString())
                }
                return true
            }
        }
        view.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                request.grant(request.resources)
            }

            override fun onShowFileChooser(
                vw: WebView, filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                if (fileChooserCallback != null) {
                    fileChooserCallback!!.onReceiveValue(null)
                }
                fileChooserCallback = filePathCallback
                val selectionIntent = Intent(Intent.ACTION_GET_CONTENT)
                selectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
                selectionIntent.type = "*/*"
                val chooserIntent = Intent(Intent.ACTION_CHOOSER)
                chooserIntent.putExtra(Intent.EXTRA_INTENT, selectionIntent)
                startActivityForResult(chooserIntent, 0)
                return true
            }

            override fun onGeolocationPermissionsShowPrompt(
                origin: String?,
                callback: GeolocationPermissions.Callback?
            ) {
                // Grant geolocation permission
                callback?.invoke(origin, true, false)
            }
        }
        view.setOnKeyListener { v: View, keyCode: Int, event: KeyEvent ->
            val vw = v as WebView
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK && vw.canGoBack()) {
                vw.goBack()
                return@setOnKeyListener true
            }
            false
        }
        view.setDownloadListener { uri: String?, userAgent: String?, contentDisposition: String?, mimetype: String?, contentLength: Long ->
            handleURI(
                uri
            )
        }
        view.setOnLongClickListener { v: View ->
            handleURI((v as WebView).hitTestResult.extra)
            true
        }

        view.loadUrl(home.toString())
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (intent != null) {
            fileChooserCallback!!.onReceiveValue(arrayOf(Uri.parse(intent.dataString)))
        }

        if(resultCode != Activity.RESULT_OK)
        {
            fileChooserCallback!!.onReceiveValue(arrayOf<Uri>())
            fileChooserCallback = null
        }

        fileChooserCallback = null
    }

    private fun handleURI(uri: String?) {
        if (uri != null) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(uri.replaceFirst("^blob:".toRegex(), ""))
            startActivity(i)
        }
    }
}

