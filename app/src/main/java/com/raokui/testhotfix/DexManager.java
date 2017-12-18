package com.raokui.testhotfix;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

/**
 * Created by 饶魁 on 2017/12/18.
 */

public class DexManager {
    private Context context;

    public DexManager(Context context) {
        this.context = context;
    }

    public void load(File file) {
        try {
            DexFile dexFile = DexFile.loadDex(file.getAbsolutePath(),
                    new File(context.getCacheDir(), "opt").getAbsolutePath(),
                    Context.MODE_PRIVATE);
            // 当前的dex里面的class
            Enumeration<String> entry = dexFile.entries();
            while (entry.hasMoreElements()) {
                // 得到dex里面的class的全类名
                String className = entry.nextElement();
                Class realClazz = dexFile.loadClass(className, context.getClassLoader());
                if (realClazz != null) {
                    fixClazz(realClazz);
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fixClazz(Class realClazz) {

        Method[] methods = realClazz.getMethods();
        for (Method rightMethod : methods) {
            // 要知道目的地
            Replace replace = rightMethod.getAnnotation(Replace.class);
            if (replace == null) {
                continue;
            }
            String clazzName = replace.clazz();
            String methodName = replace.method();

            try {
                Class wrongClazz = Class.forName(clazzName);
                Method wrongMethod = wrongClazz.getDeclaredMethod(methodName, rightMethod.getParameterTypes());
                replace(wrongMethod, rightMethod);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }


    }

    public native static void replace(Method wrongMethod, Method rightMethod) ;
}
