/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 */
package io.helidon.reproducer.svm;

import java.io.IOException;
import java.util.logging.LogManager;

/**
 * In-process initialization of logging.
 */
public final class MainInitLogging {
    private MainInitLogging() {
    }

    public static void main(String[] args) throws IOException {
        LogManager.getLogManager().readConfiguration(MainInitLogging.class.getResourceAsStream("/logging.properties"));

        TestClass.log();
        TestClass.test();
    }
}
