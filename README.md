# Introduction
The following document provides general documentation applicable across all programming languages for which the Prosperas SDK may be used.

# Application flow
Prosperas offers two application flows, depending on the integration choice, iOS and Android
# Full Integration
The Full Integration opens access to the full suite of features available on the Prosperas Platform.  Full Integration requires that each consumer be registered as a User using the RegisterUser function.  After registration, each User can log in with their email address and password.  If login is successful, the SDK will receive a token that will automatically be sent with all subsequent calls.  
After being logged in, a list of previously saved saved consents – if any -- can be retrieved for that User. The SDK can also save new consents for each User that each represent a date and time a particular type of consent was granted or denied.  
To collect the User’s data for assessment by the Prosperas platform, call the Score function, passing it the UserID received from the Login function.  The Score function will verify that the user has provided the necessary consent to the required permissions.  Score will then collect the necessary metadata, interact with the Prosperas platform, and return a score and probability of default.
# Requirements - Android
The Prosperas SDK requires the following Android permissions:
-	android.permission.INTERNET
-	android.permission.READ_EXTERNAL_STORAGE
-	android.permission.BLUETOOTH

# Project Setup - Android
Clone project using git.
Copy sdk-dist/android/MarketplaceSDK folder to your root project directory (Image of File Explorer)
![Alt text](image.png)
open top-level settings.gradle
add include ':MarketplaceSDK' to bottom of file
add implementation project(path: ':MarketplaceSDK') to app-level build.gradle
add import com.prosperas.marketplace.CreditoSDK to app-level MainActivity.kt, or wherever you wish to call it

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