/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 */
package io.helidon.reproducer.svm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class to test logging.
 */
public class TestClass {
    private static final Logger LOGGER = Logger.getLogger(TestClass.class.getName());

    private static final List<String> EXPECTED_LINES = new ArrayList<>();

    static {
        EXPECTED_LINES.add("Finest static");
        EXPECTED_LINES.add("Fine static");
        EXPECTED_LINES.add("Info static");
        EXPECTED_LINES.add("Severe static");
        EXPECTED_LINES.add("Info other");
        EXPECTED_LINES.add("Severe other");
    }

    static void log() {
        Logger theOtherLogger = Logger.getLogger("io.helidon.other");

        logIt(LOGGER, "static");
        logIt(theOtherLogger, "other");
    }

    static void test() throws IOException {
        List<String> linesWritten = Files.readAllLines(Paths.get("log-output.txt"));

        for (int i = 0; i < linesWritten.size(); i++) {
            String actualLine = linesWritten.get(i);

            if (EXPECTED_LINES.size() <= i) {
                System.err.println("Line " + (i + 1) + " invalid. Too long output, got: \"" + actualLine + "\"");
            } else {
                String expectedLine = EXPECTED_LINES.get(i);

                if (!expectedLine.equals(actualLine)) {
                    System.err
                            .println("Line " + (i + 1) + " invalid. Expected \"" + expectedLine + "\", got \"" + actualLine +
                                             "\"");
                }
            }
        }
    }

    private static void logIt(Logger logger, String name) {
        logger.finest("Finest " + name);
        logger.fine("Fine " + name);
        logger.info("Info " + name);
        logger.severe("Severe " + name);
    }
}
