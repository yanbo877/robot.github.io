package com.bigdata.coin.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类.
 */
public final class StringUtils {

    public static final String EMPTY_STRING = "";
    public static final Pattern COMPILE = Pattern.compile("^[-\\+]?[\\d]+[.[\\d]+]*$");

    private static final Pattern phonePattern1 = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
    private static final Pattern phonePattern2 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");
    private static final Pattern phonePattern3 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");

    public static boolean isEmpty(Object str) {
        return str == null || EMPTY_STRING.equals(str);
    }

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    private StringUtils() {}

    /**
     * 判断两个字符串是否相等，包括null的情况.
     */
    public static boolean isSame(String str1, String str2) {
        boolean onlyOneNull = (null == str1 && null != str2) || (null != str1 && null == str2);
        if (onlyOneNull) {
            return false;
        }
        if (null == str1 && null == str2) {
            return true;
        }
        return str1.compareTo(str2) == 0;
    }

    /**
     * 判断是否为数字，包括整数、浮点数.
     */
    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }

        return COMPILE.matcher(str).matches();
    }

    /**
     * 转成小写.
     */
    public static String toLowerCase(String str) {
        if (null == str) {
            return null;
        }
        return str.toLowerCase();
    }

    /**
     * 转成大写.
     */
    public static String toUpperCase(String str) {
        if (null == str) {
            return null;
        }
        return str.toUpperCase();
    }

    /**
     * 字符串转二进制.
     **/
    public static String strToBinary(String str) {
        StringBuilder result = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char character : chars) {
            result.append(Integer.toBinaryString(character)).append(" ");
        }
        return result.toString().trim();
    }

    /**
     * 二制制转字符串.
     *
     * @param binary  二进制字符串
     * @param spliter 二进制分割符
     * @return 返回转换后字符串.
     */
    public static String binaryToStr(String binary, String spliter) {
        if (binary == null || binary.length() == 0) {
            return binary;
        }

        if (spliter == null) {
            spliter = " ";
        }

        String[] binarys = binary.split(spliter);
        char[] chars = new char[binarys.length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = binaryToChar(binarys[i]);
        }
        return String.valueOf(chars);
    }

    /**
     * 将二进制字符串转换成int数组.
     */
    private static int[] binaryToIntArray(String binary) {
        char[] temp = binary.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }

    /**
     * 将二进制转换成字符.
     */
    private static char binaryToChar(String binStr) {
        int[] temp = binaryToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    public static boolean checkPhoneFormat(String phone) {
        Matcher matcher = phonePattern1.matcher(phone);
        boolean result = matcher.matches();
        if (!result) {
            if (phone.length() > 9) {
                matcher = phonePattern2.matcher(phone);
                result = matcher.matches();
            } else {
                matcher = phonePattern3.matcher(phone);
                result = matcher.matches();
            }
        }
        return result;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * replace % - in string for query.
     */
    public static String replaceSpecialChar(String in) {
        if (StringUtils.isEmpty(in)) {
            return in;
        }
        if (in.contains("\\")) {
            in = in.replace("\\", "\\\\");
        }
        if (in.contains("%")) {
            in = in.replace("%", "\\%");
        }
        if (in.contains("_")) {
            in = in.replace("_", "\\_");
        }
        return in;
    }

    public static String concat(String ... sub) {
        String result = null;
        if (sub.length > 0) {
            StringBuilder builder = new StringBuilder();
            for (String s : sub) {
                if (s != null) {
                    builder.append(s);
                }
            }
            result = builder.toString();
        }
        return result;
    }
}
