//add the libraries to your gradle dependencies
implementation files('libs/scorelib.aar')
implementation files('libs/scssdk.aar')


//import the SCS SDK
import com.scs.sdk.SCSSDK


//create an array of permissions to verify
val arrPerms = arrayOf(
Manifest.permission.WRITE_EXTERNAL_STORAGE,
Manifest.permission.READ_EXTERNAL_STORAGE,
Manifest.permission.READ_CONTACTS,
Manifest.permission.READ_CALENDAR,
Manifest.permission.GET_ACCOUNTS,
Manifest.permission.BLUETOOTH,
Manifest.permission.ACCESS_WIFI_STATE,
Manifest.permission.ACCESS_NETWORK_STATE,
Manifest.permission.USE_FINGERPRINT,
Manifest.permission.QUERY_ALL_PACKAGES
)
//Verify that the user has given the necessary permissions – if they haven’t, request the permission.
ActivityCompat.requestPermissions(this@MainActivity, permission, requestCode)
//collect the user’s email address as a unique identifier for the user
val email = “test2002@test.com”
//retrieve the authorization key from settings or wherever you securely store it
val authKey = PROVIDED_BY_SCS
//initialize the SCSSDK
var scs = SCSSDK(authKey)
//call the score function passing 
val score = scs.score(applicationContext, email=email).toString()
