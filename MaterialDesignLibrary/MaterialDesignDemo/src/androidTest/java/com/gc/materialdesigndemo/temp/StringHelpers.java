package com.gc.materialdesigndemo.temp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class StringHelpers {

    public static String abbreviate(@Nullable String str, int len) {
        return str != null && str.length() > len ? str.substring(0, len) + "\u2026" : str;
    }

    public static boolean isBlank(@Nullable String str) {
        return str == null || str.trim().equals("");
    }

    @Nullable
    public static String charSequenceToString(@Nullable CharSequence input, boolean replaceNull) {
        return input == null ? (replaceNull ? "" : null) : input.toString();
    }

    @Nullable
    public static String charSequenceToNullableString(@Nullable CharSequence input) {
        return charSequenceToString(input, false);
    }

    @NonNull
    public static String charSequenceToNonNullString(@Nullable CharSequence input) {
        return charSequenceToString(input, true);
    }

}