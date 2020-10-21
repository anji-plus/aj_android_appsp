package com.anji.appsp.sdk;

import android.util.Log;

/**
 * 日志输出
 *
 * @author chenhailong
 * Create Date:2020.9.3
 */
public class AppSpLog {
    private static final String Tag = "APP-SP";
    private static boolean Debug = true;

    public static void setDebugged(boolean debug) {
        Debug = debug;
    }

    public static void i(Object txt) {
        if (Debug && txt != null) {
            Log.i(Tag, txt.toString());
        }
    }

    public static void d(Object txt) {
        if (Debug && txt != null) {
            Log.d(Tag, txt.toString());
        }
    }

    public static void w(Object txt) {
        if (Debug && txt != null) {
            Log.w(Tag, txt.toString());
        }
    }

    public static void e(Object txt) {
        if (Debug && txt != null) {
            Log.e(Tag, txt.toString());
        }
    }
}
