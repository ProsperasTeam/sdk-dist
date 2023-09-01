package com.prosperas.marketplace

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

open class CreditoSDK {
    companion object{

        lateinit var context: Context
        private lateinit var sharedpre: SharedPrefs
        public fun init(c: Context) {
            context = c

        }

        public fun startCredit(activity: Activity,sessionId: String, apiKey: String, locale : String, url: String, nativeButtons: String){
            if(apiKey.isNullOrBlank()){
                Toast.makeText(context, "ERROR: No APIKEY proporcionada", Toast.LENGTH_LONG).show()
            }
            else if(sessionId.isNullOrBlank()){
                Toast.makeText(context, "ERROR: No SessionId proporcionado", Toast.LENGTH_LONG).show()
            }
            else if(locale.isNullOrBlank()){
                Toast.makeText(context, "ERROR: No Locale proporcionado", Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(context, ProsperasSDK::class.java)
                intent.putExtra("sessionId", sessionId)
                intent.putExtra("apiKey2", apiKey)
                intent.putExtra("locale", locale)
                intent.putExtra("url", url)
                intent.putExtra("nativeButtons", nativeButtons)
                sharedpre = SharedPrefs(context)
                sharedpre.setPrefString("X-Api-Key",apiKey)
                activity.startActivityForResult(
                    intent,
                    100
                )
            }
        }
    }
}