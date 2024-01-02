package com.mcb.iminitializr.utils;

import com.mcb.iminitializr.constant.Constant;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static void forceMkdir(final File directory) throws IOException {
        if (directory.exists()) {
            if (!directory.isDirectory()) {
                final String message =
                        "File "
                                + directory
                                + " exists and is "
                                + "not a directory. Unable to create directory.";
                throw new IOException(message);
            }
        } else {
            if (!directory.mkdirs()) {
                // Double-check that some other thread or process hasn't made
                // the directory in the background
                if (!directory.isDirectory()) {
                    final String message =
                            "Unable to create directory " + directory;
                    throw new IOException(message);
                }
            }
        }
    }

    /**
     * 分隔符转驼峰式
     * @param source
     * @param separator
     * @return
     */
    public static String separatorToCamel(String source, String separator) {
        StringBuffer target = new StringBuffer();
        String[] split = source.split(separator);
        for (String s : split) {
            s = s.toLowerCase();
            target.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }
        return target.toString();
    }

    public static String separatorToCamel(String source, String separator, String suffix) {
        return separatorToCamel(source, separator) + suffix;
    }
}
