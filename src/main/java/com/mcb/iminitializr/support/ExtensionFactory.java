package com.mcb.iminitializr.support;

import java.util.List;

public interface ExtensionFactory<T> {
    T getExtension(String name);

    List<T> getExtensions();
}
