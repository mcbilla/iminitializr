package com.mcb.iminitializr.config;

@FunctionalInterface
public interface IConfigBuilder<T> {
    T build();
}