package com.github.hitzseb.sec.util;

/**
 * Utility class for formatting and validating SEC Central Index Keys (CIK).
 */
public final class CikUtils {

    private CikUtils() {}

    /**
     * Normalize a CIK number to the SEC's official 10 dígits format.
     * E.g.: 320193 -> "0000320193"
     */
    public static String formatCik(long cik) {
        if (cik <= 0) {
            throw new IllegalArgumentException("CIK must be a positive number: " + cik);
        }
        return String.format("%010d", cik);
    }

    /**
     * Sanitize and normalize a CIK string.
     * E.g.: " 320193 " -> "0000320193"
     */
    public static String formatCik(String rawCik) {
        if (rawCik == null || rawCik.isBlank()) {
            throw new IllegalArgumentException("CIK cannot be null or blank");
        }

        // Remove any non-digit character
        String clean = rawCik.replaceAll("\\D", "");

        if (clean.isEmpty()) {
            throw new IllegalArgumentException("Invalid CIK format: " + rawCik);
        }

        long numericCik = Long.parseLong(clean);
        return formatCik(numericCik);
    }
}
