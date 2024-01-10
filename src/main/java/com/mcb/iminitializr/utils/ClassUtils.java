package com.mcb.iminitializr.utils;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassUtils {
    /**
     * 获取一个类的所有子类
     *
     * @param target
     * @return
     */
    public static List<Class<?>> getInterfaceImpls(@NotNull Class<?> target) {
        List<Class<?>> rs = new ArrayList<>();
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources("");
            List<String> classNames = new ArrayList<>();
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                Files.walk(Paths.get(url.toURI())).filter(Files::isRegularFile).forEach(path -> {
                    String pathStr = path.toString();
                    if (pathStr.endsWith(".class")) {
                        // 获取完整类名
                        String className = pathStr
                                .substring(pathStr.indexOf("classes") + 8, pathStr.length() - 6)
                                .replace("/", ".").replace("\\", ".");
                        classNames.add(className);
                    }
                });
                // 获取所有类，然后判断是否是 target 接口的实现类
                for (String classpath : classNames) {
                    Class<?> classObject = Class.forName(classpath);
                    if (target.isAssignableFrom(classObject) && target != classObject) {
                        rs.add(classObject);
                    }
                }
                classNames.clear();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return rs;
    }
}
