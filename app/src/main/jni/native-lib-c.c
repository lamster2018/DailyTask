#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_lahm_dailytask_NDKActivity_cFunc(JNIEnv *env, jobject instance, jint n) {

    // TODO


    return (*env)->NewStringUTF(env, "aaa");
}