package com.mcb.iminitializr.support.impl;

import com.mcb.iminitializr.extension.ExtensionHandler;
import com.mcb.iminitializr.support.ExtensionFactory;
import com.mcb.iminitializr.support.RuntimeFactory;
import com.mcb.iminitializr.extension.RuntimeFactoryAware;
import com.mcb.iminitializr.utils.ClassUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExtensionFactoryImpl implements ExtensionFactory<ExtensionHandler> {

    private RuntimeFactory runtimeFactory;

    private List<ExtensionHandler> extensionHandlerList = new ArrayList<>();

    private Map<String, ExtensionHandler> extensionHandlerMap = new HashMap<>();

    public ExtensionFactoryImpl(RuntimeFactory runtimeFactory) {
        this.runtimeFactory = runtimeFactory;
        this.extensionHandlerList = initExtensionHandlerList();
        this.extensionHandlerMap = initExtensionHandlerMap();
    }

    List<ExtensionHandler> initExtensionHandlerList() {
        List<Class<?>> candidateImpls = ClassUtils.getInterfaceImpls(ExtensionHandler.class);
        List<ExtensionHandler> extensionHandlers = candidateImpls.stream().map(impl -> {
            try {
                return (ExtensionHandler) impl.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).filter(ExtensionHandler::enable).collect(Collectors.toList());
        invokeAwareInterfaces(extensionHandlers);
        return extensionHandlers;
    }

    private void invokeAwareInterfaces(List<ExtensionHandler> extensionHandlers) {
        extensionHandlers.forEach(e -> {
            if (RuntimeFactoryAware.class.isAssignableFrom(e.getClass())) {
                ((RuntimeFactoryAware) e).setPathFactory(this.runtimeFactory);
            }
        });
    }

    private Map<String, ExtensionHandler> initExtensionHandlerMap() {
        return this.extensionHandlerList.stream()
                .collect(Collectors.toMap(
                        ExtensionHandler::getName,
                        Function.identity(),
                        (o1, o2) -> {
                            throw new IllegalArgumentException("扩展插件名重复: " + o1);
                        }
                ));
    }

    @Override
    public ExtensionHandler getExtension(String name) {
        return this.extensionHandlerMap.get(name);
    }

    @Override
    public List<ExtensionHandler> getExtensions() {
        return this.extensionHandlerList;
    }
}
