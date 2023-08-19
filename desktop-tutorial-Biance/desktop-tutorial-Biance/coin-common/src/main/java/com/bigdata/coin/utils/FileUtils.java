package com.bigdata.coin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

public class FileUtils {

    private FileUtils() {}

    /**
     * 读取文本文件内容.
     *
     * @param filePath 文件绝对路径
     * @param charset 文件编码方式
     * @return 文件内容
     */
    public static String read(String filePath, Charset charset) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("Can't not find file " + filePath);
        }
        Long length = file.length();
        byte[] bytes = new byte[length.intValue()];
        try (FileInputStream fs = new FileInputStream(file)) {
            fs.read(bytes);
            return new String(bytes, charset);
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected exception on read file " + filePath);
        }
    }

    /**
     * 读取文本文件内容.
     *
     * @param filePath 文件绝对路径
     */
    public static String read(String filePath) {
        return read(filePath, Charset.forName("UTF-8"));
    }
}
