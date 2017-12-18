#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_raokui_testhotfix_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_raokui_testhotfix_DexManager_replace(JNIEnv *env, jclass type, jobject wrongMethod,
                                              jobject rightMethod) {

    // 做一些替换操作


}