# Introduction
The following document provides general documentation applicable across all programming languages for which the Prosperas SDK may be used.

# Requirements - Android
The Prosperas SDK requires the following Android permissions:
-	android.permission.INTERNET
-	android.permission.READ_EXTERNAL_STORAGE
-	android.permission.BLUETOOTH

# Project Setup - Android
Clone project using git.

`git clone https://github.com/ProsperasTeam/sdk-dist.git`

Copy sdk-dist/android/MarketplaceSDK folder to your root project directory

![image](https://github.com/ProsperasTeam/sdk-dist/assets/125609244/c808ca6c-3840-446f-8c9f-79a055720446)

![image](https://github.com/ProsperasTeam/sdk-dist/assets/125609244/991172ce-75c0-4e2b-8d94-61cca1ee9a00)

Open top-level settings.gradle add `include ':MarketplaceSDK'` to bottom of file

![image](https://github.com/ProsperasTeam/sdk-dist/assets/125609244/78c1b909-02d8-49b2-aa50-bb18d260e826)

Add `implementation project(path: ':MarketplaceSDK')` to app-level build.gradle, in the "dependencies" section

![image](https://github.com/ProsperasTeam/sdk-dist/assets/125609244/9746a125-f150-4bec-83eb-94c4e8cffdeb)

Add `import com.prosperas.marketplace.CreditoSDK` to app-level MainActivity.kt, or wherever you wish to instantiate the library

![image](https://github.com/ProsperasTeam/sdk-dist/assets/125609244/aac0bb0e-df50-4bbe-bc0c-c35484811ea5)

Call the SDK as illustrated by the following block of code in MainActivity.kt, StartCredit takes up to five variables:
* Locale - Localization string such as en-rUS
* SessionID - SessionID for your user
* API Key - API key issues to you by Prosperas
* URL - URL of the Progressive Web App Instance given to you by Prosperas
* Native Button Color - Hex code for the coloring of native text buttons. There are two buttons; one which controls returning to your app and one for returning to the beginning of the survey flow. Their color is configurable through this argument


        package com.prosperas.myapplication
    
        import android.os.Bundle
        import androidx.activity.ComponentActivity
        import com.prosperas.marketplace.CreditoSDK
    
        class MainActivity : ComponentActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                var new_locale = "es-rMX"
                var new_sessionId = "d/fvIRn....SFf2gQs"
                var new_apikey = "AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEEE" 
                var new_url = "https://creditos-web.prosperas.com"
                var new_color = "#00FF00"
                CreditoSDK.init(applicationContext)

                CreditoSDK.startCredit(this,
                    new_sessionId,
                    new_apikey,
                    new_locale,
                    new_url,
                    new_color)
            }
        }

# Example Application
An example application and reference implementation is available at: https://github.com/ProsperasTeam/WebViewAndroidBootstrapp
