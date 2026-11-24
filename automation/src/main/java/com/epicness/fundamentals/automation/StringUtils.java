package com.epicness.fundamentals.automation;

final class StringUtils {

    private StringUtils() {
    }

    static String capitalizeFirst(String target) {
        return target.substring(0, 1).toUpperCase() + target.substring(1);
    }
}