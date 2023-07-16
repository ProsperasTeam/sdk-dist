package com.prosperas.marketplace

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

//import com.prosperas. .creditosdk.activity.MainActivityCredo
//import com.prosperas.marketplace.SharedPrefs


open class CreditoSDK {
    companion object{

        lateinit var context: Context
        private lateinit var sharedpre: SharedPrefs
        public fun init(c: Context) {
            context = c

        }

        public fun startCredit(activity: Activity,sessionId: String, apiKey: String, locale : String){
            if(apiKey.isNullOrBlank()){
                Toast.makeText(context, "ERROR: No APIKEY proporcionada", Toast.LENGTH_LONG).show()
            }
            else{
                var locale_2 = ""
                if(locale == "en-rUS" || locale == "es-rMX" || locale == "es-rPE"){
                    locale_2 = locale
                }
                else{
                    locale_2 = "es-rMX"
                }

                val intent = Intent(context, ProsperasSDK::class.java)
                intent.putExtra("sessionId", sessionId)
                intent.putExtra("apiKey2", apiKey)
                intent.putExtra("locale", locale_2)
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