#include <jni.h>
#include <stdio.h>

JNIEXPORT jlong JNICALL
Java_com_example_lahm_dailytask_NDKActivity_cFunc(JNIEnv *env, jobject instance,
                                                  jint n, jint count) {

    // TODO

    int s = 0;
    int t = 0;
    int i;
    i = 1;
    while (i <= count) {
        t = t * 10 + n;
        s = s + t;
        i++;
    }
    printf("%d", s);
    return s;
}