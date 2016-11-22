# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/software/android-sdk-linux/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

###########################以下都是API里边的类，最好都要避免混淆###############
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.**
-keep public class com.android.vending.licensing.ILicensingService
###############以下都是API里边的类，最好都要避免混淆 结束#######################

###########################RxJava混淆#####################################
-dontwarn rx.internal.util.unsafe.*
###########################RxJava混淆结束##################################

###########################Gson混淆#######################################
-keep public class com.google.gson.**
-keep public class com.google.gson.** {public private protected *;}
-keepattributes Signature
-keepattributes *Annotation*
#-keep public class com.project.mocha_patient.login.SignResponseData { private *; }  #对SignResponseData.Java的所有private 对象不进行obfuscation
###########################Gson混淆 结束###################################

###########################retrofit2混淆##################################
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
###########################retrofit2混淆 结束##############################

###########################okHttp混淆#####################################
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}
-dontwarn okio.**
###########################okHttp混淆 结束#################################