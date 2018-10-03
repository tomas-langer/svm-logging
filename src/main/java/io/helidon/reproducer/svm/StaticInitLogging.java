/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 */
package io.helidon.reproducer.svm;

import java.io.IOException;
import java.util.logging.LogManager;

/**
 * Static initialization of logging.
 */
public final class StaticInitLogging {
    static {
        try {
            LogManager.getLogManager().readConfiguration(StaticInitLogging.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Failed to init configuration");
            e.printStackTrace();
        }
    }

    private StaticInitLogging() {
    }

    public static void main(String[] args) throws IOException {
        TestClass.log();
        TestClass.test();
    }
}
