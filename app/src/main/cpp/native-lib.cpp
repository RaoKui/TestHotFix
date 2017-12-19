#include <jni.h>
#include <string>
#include "art_method.h"

extern "C"
JNIEXPORT void JNICALL
Java_com_raokui_testhotfix_DexManager_replace(JNIEnv *env, jclass type, jobject wrongMethod,
                                              jobject rightMethod) {

    // 做一些替换操作
    art::mirror::ArtMethod *smeth = (art::mirror::ArtMethod *) env->FromReflectedField(wrongMethod);

    art::mirror::ArtMethod *dmeth = (art::mirror::ArtMethod *) env->FromReflectedField(rightMethod);

    smeth->declaring_class_ = dmeth->declaring_class_;
    smeth->dex_cache_resolved_methods_ = dmeth->dex_cache_resolved_methods_;
    smeth->access_flags_ = dmeth->access_flags_;
    smeth->dex_cache_resolved_types_ = dmeth->dex_cache_resolved_types_;
    smeth->dex_code_item_offset_=dmeth->dex_code_item_offset_;
    smeth->method_index_=dmeth->method_index_;
    smeth->dex_method_index_= dmeth->dex_method_index_;


}