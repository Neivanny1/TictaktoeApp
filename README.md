# TiktactoeApp
## BUILDING THE APP

### Option 1: Using the Build Menu
* Open the Build menu in Android Studio.
* Click Make Project to ensure your project builds successfully.
* Then, select Build APK(s) or Build Module(s), depending on the version of Android Studio.
* After the build process completes, the APK will be located in the following path:
```
app/build/outputs/apk/debug/app-debug.apk
```

### Option 2: Build APK Using Command Line

You can build the APK using Gradle from the command line.
#### Step 1: Open the Terminal
1. Open a terminal in Android Studio (View > Tool Windows > Terminal) or use your system terminal.
2. Navigate to the root directory of your project.

#### Step 2: Run the Gradle Build Command

Execute the following command to build the APK:
```
./gradlew assembleDebug
```
For a release APK:
```
./gradlew assembleRelease
```
#### Step 3: Locate the APK
1. The generated APK will be located in:
2. Debug APK: app/build/outputs/apk/debug/app-debug.apk
3. Release APK: app/build/outputs/apk/release/app-release.apk

### Option 3: Use Gradle Tool in Android Studio

* Open View > Tool Windows > Gradle.
* In the Gradle window, expand your project and locate the assembleDebug or assembleRelease task under :app > Tasks > build.
* Double-click the task to execute it.
* Once completed, locate the APK in the same path as above.

> Notes: If you're building a release APK, make sure to sign it with a keystore using the appropriate Gradle configuration.

## To install your APK onto your phone using adb (Android Debug Bridge), follow these steps:
#### Step 1: Enable Developer Options on Your Phone
* Go to Settings > About Phone.
* Tap on Build Number seven times to enable Developer Options.
* Go back to Settings > Developer Options, and enable USB Debugging.

#### Step 2: Connect Your Phone to Your Computer
*  Use a USB cable to connect your phone to your computer.
* Ensure your phone is set to File Transfer (MTP) mode, not Charging Only.
* Grant permission to allow USB debugging if prompted on your phone.

#### Step 3: Verify Connection with ADB
* Open a terminal/command prompt on your computer.
* Run the following command to verify that your device is recognized:
```
adb devices
```
You should see your device listed. If not, troubleshoot:

Ensure drivers for your phone are installed.
Restart the adb server:
```
adb kill-server
adb start-server
```
#### Step 4: Install the APK
Locate your APK file. If you built it using the debug command, it should be in:

```
app/build/outputs/apk/debug/app-debug.apk
```

Use the following adb command to install the APK:
```
adb install path/to/app-debug.apk
```
Replace path/to/app-debug.apk with the actual path to your APK file.

#### Step 5: Launch the App
After installation completes, look for the app icon on your phone's home screen or app drawer.

Tap to launch the app.

Notes

> If you encounter an error like "INSTALL_FAILED_UPDATE_INCOMPATIBLE," uninstall any previous versions of the app:
```
adb uninstall com.example.yourpackagename
```
Then reinstall the APK.
