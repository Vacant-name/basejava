package com.webapp.util;

public class LazySingleton {
    private static final LazySingleton INSTANCE = new LazySingleton();

    public LazySingleton() {
    }

    public static LazySingleton getInstance() {
        return INSTANCE;
    }
}
