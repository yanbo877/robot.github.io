package com.bigdata.coin.utils;

import com.google.common.base.Preconditions;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类.
 */
public class ThrowableUtils {
    private ThrowableUtils() {}

    /**
     * 异常栈信息转成字符串.
     */
    public static String extractStackTrace(Throwable throwable) {
        Preconditions.checkNotNull(throwable);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    /**
     * 判断是否是主键、唯一键冲突异常.
     */
    public static boolean isDuplicate(Throwable throwable) {
        Preconditions.checkNotNull(throwable);
        return throwable.getMessage().toLowerCase().contains("duplicate");
    }

    /**
     * 使用运行时异常包装编译异常.
     * @param throwable 异常类
     */
    public static RuntimeException wrapRuntime(Throwable throwable) {
        return wrapRuntime(null, throwable);
    }

    /**
     * 使用运行时异常包装编译异常.
     * @param message 自定义的错误信息
     * @param throwable 异常类
     */
    public static RuntimeException wrapRuntime(String message, Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            return (RuntimeException) throwable;
        }
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }
        return new RuntimeException(message, throwable);
    }
}
