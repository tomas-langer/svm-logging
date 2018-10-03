/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 */
package io.helidon.svm;

import java.util.logging.FileHandler;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

/**
 * Substitutions needed.
 */
public class SvmSubstitutions {
    @TargetClass(FileHandler.class)
    static final class FileHandlerSvmExtension {
        @Substitute
        private static boolean isSetUID() {
            return false;
        }
    }
}
