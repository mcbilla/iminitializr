package com.mcb.iminitializr.support.impl;

import com.mcb.iminitializr.extension.ExtensionHandler;
import com.mcb.iminitializr.support.ExtensionFactory;

import java.util.List;

public class ExtensionFactoryImpl implements ExtensionFactory<ExtensionHandler> {

    public ExtensionFactoryImpl() {
    }

    @Override
    public ExtensionHandler getExtension(String name) {
        return null;
    }

    @Override
    public List<ExtensionHandler> getExtensions() {
        return null;
    }
}
