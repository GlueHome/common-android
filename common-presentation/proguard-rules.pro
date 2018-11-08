-keepattributes Signature
-keepattributes *Annotation*

-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

-keep class com.stripe.android.** { *; }



-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keep class org.parceler.Parceler$$Parcels

-dontwarn sun.misc.**
-dontwarn sun.misc.Unsafe
-dontwarn javax.annotation.**

-dontwarn kotlin.coroutines.**
-dontnote kotlin.coroutines.**

-dontwarn retrofit2.adapter.rxjava.CompletableHelper$**

-keep class retrofit.** { *; }
-dontwarn retrofit.**
-dontwarn retrofit2.Platform*

-dontwarn com.viewpagerindicator.**

-dontwarn okio.**

-keep class com.gluehome.backend.glue.** { *; }
-keepclassmembers class com.gluehome.backend.glue.** { *; }

# Fix weird issues when serializing Date objects
-keepclassmembers class * implements java.io.Serializable {
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Fix warnings for stuff in bonapputils
-dontwarn java.lang.invoke**
-dontwarn com.squareup.**


-dontwarn android.support.v8.renderscript.*
-keepclassmembers class android.support.v8.renderscript.RenderScript {
  native *** rsn*(...);
  native *** n*(...);
}

-keepattributes Signature
-keepattributes InnerClasses

-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
-keepattributes EnclosingMethod

-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

-printusage unused.txt

# Joda Library
-dontwarn org.joda.convert.**

-dontwarn rx.**
-dontnote rx.**
-dontwarn io.reactivex.**
-dontnote io.reactivex.**
-dontnote com.crashlytics.**

# NETWORK
-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class javax.inject.** { *; }
-keep class retrofit.** { *; }

-keep class com.gluehome.inhome.data.network.** { *; }
-keep class com.gluehome.inhome.data.features.** { *; }
-keep class com.gluehome.domain.user.** { *; }

-dontwarn com.instabug.**

-dontnote android.net.http.*
-dontnote org.apache.commons.codec.**
-dontnote org.apache.http.**

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# crashlytics
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
-printmapping mapping.txt

-keep public class com.google.android.gms.* { public *; }
-dontwarn com.google.android.gms.**


-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-dontwarn sun.misc.Unsafe
-dontwarn javax.annotation.**

-dontwarn javax.annotation.**

-dontwarn dagger.**
-dontwarn com.google.common.**
-dontwarn com.google.googlejavaformat.**

-dontnote com.google.android.gms.**

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# https://github.com/Kotlin/kotlinx.coroutines
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

-keep class com.stripe.android.** { *; }


-keepnames class kotlinx.** { *; }

-ignorewarnings
