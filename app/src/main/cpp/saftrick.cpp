#include <jni.h>
#include <string>
#include <unistd.h>

extern "C" JNIEXPORT jlong JNICALL
Java_io_github_kotoriproject_saftrick_MainActivity_hello2(JNIEnv* env, jobject thisObject,jint fd){
    char arr[30];
    int len=snprintf(arr,30,"hello,pid: %d",getpid());
    return write(fd,arr,len);
}

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("saftrick");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("saftrick")
//      }
//    }