#CustomLoadDexFile

**It works on a pure Java lib. It do NOT guarantee that it will work in all situation**

This project is a sample Android project  to load compiled dex file.

Instead of dividing code in project, in my situation is code from third party Java library.

#Example

- Supposed `https://github.com/liratanak/hello-gradle` as the third party library
- `hello-gradle.jar` should be found in folder `build/libs`
- Compile `hello-gradle.jar` to `hello.dex` via this command `/path/to/sdk/build-tools/20.0.0/dx --dex --output="/path/to/CustomLoadDexFile/assets/hello.dex" "/path/to/hello-gradle/build/libs/hello-gradle.jar"`
- The dex file should be in `assets` folder because the sample code is using that folder [_Remember_: Dex file could also be loaded from external resource such as SD card or even from network stream]


#Reference
- Custom Class Loading in Dalvik | http://android-developers.blogspot.com/2011/07/custom-class-loading-in-dalvik.html
- Dex.java source code | https://android.googlesource.com/platform/libcore/+/master/dex/src/main/java/com/android/dex/Dex.java