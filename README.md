# Introduction
The following document provides general documentation applicable across all programming languages for which the Prosperas SDK may be used.

# Application flow
Prosperas offers two application flows, depending on the integration choice, iOS and Android

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

Open top-level settings.gradle add include `:MarketplaceSDK` to bottom of file

![image](https://github.com/ProsperasTeam/sdk-dist/assets/125609244/78c1b909-02d8-49b2-aa50-bb18d260e826)

Add `implementation project(path: ':MarketplaceSDK')` to app-level build.gradle, in the "dependencies" section

![image](https://github.com/ProsperasTeam/sdk-dist/assets/125609244/9746a125-f150-4bec-83eb-94c4e8cffdeb)

Add `import com.prosperas.marketplace.CreditoSDK` to app-level MainActivity.kt, or wherever you wish to instantiate the library

![image](https://github.com/ProsperasTeam/sdk-dist/assets/125609244/aac0bb0e-df50-4bbe-bc0c-c35484811ea5)


# Requirements - iOS
The Prosperas SDK requires that you add the following permissions and descriptions to your Info.plist:
- NSPhotoLibraryUsageDescription = "$(PRODUCT_NAME) uses photo library access as part of its metadata analysis."


# Include the Library


## iOS
1. Drag the ProsperasSDKiOS.xcframework folder from Finder where you downloaded it to the Frameworks folder in your XCode project.
2. Add `import ProsperasSDKiOS` at the top of the .swift file where you will be calling the Prosperas SDK.
3. Where you want to initiate metdata analysis, call one of the following functions depending on your needs:
   a. `let sdk = await ProsperasSDKiOS.scoreRefOnly(Prosperasapikey: "", Prosperasurl: "")`
   b. `let sdk = await ProsperasSDKiOS.score(Prosperasapikey: "", Prosperasurl: "")`
4. Set the Prosperasapikey to the Prosperas API Key that was provided to you by your Prosperas contact.  Leave the Prosperas URL empty unless your Prosperas contact provides a different URL.

## ProsperasSDK
Initializes the ProsperasSDK with base parameters for the mobile app using the SDK and the particular mobile device.
### Parameters:
-	Authorization Key: Unique key provided to your organization to allow you to access the Prosperas Platform.
-	API URL:  Optional URL string specifying the location of the Prosperas API.
### Returns:
-	Initialized ProsperasSDK object.
