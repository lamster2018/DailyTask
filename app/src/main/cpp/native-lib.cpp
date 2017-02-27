//
// Created by lahm on 2017/2/27.
//

#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_example_lahm_dailytask_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++ world haha";
    return env->NewStringUTF(hello.c_str());
}