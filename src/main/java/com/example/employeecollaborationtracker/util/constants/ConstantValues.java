package com.example.employeecollaborationtracker.util.constants;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class ConstantValues {
    public static final int CORRECT_SEPARATED_VALUES_COUNT = 4;
    private static List<String> DATE_FORMATS() {
        Set<String> dateFormats = new LinkedHashSet<>(Arrays.asList(
                // Common Date Formats
                "EEE MMM dd HH:mm:ss zzz yyyy",
                "yyyy-MM-dd",
                "MM/dd/yyyy",
                "dd-MM-yyyy",
                "yyyyMMdd",

                // Date and Time Formats
                "yyyy-MM-dd HH:mm:ss",
                "MM/dd/yyyy HH:mm:ss",
                "dd-MM-yyyy HH:mm:ss",
                "yyyyMMddHHmmss",

                // ISO 8601 Formats
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",

                // Additional Formats
                "yyyy-MM",
                "yyyy/MM/dd",
                "dd.MM.yyyy",
                "yyyy-MM-dd'T'HH:mm:ss",
                "dd/MM/yyyy HH:mm:ss",
                "yyyyMMdd'T'HH:mm:ss.SSSZ",
                "EEE, dd MMM yyyy HH:mm:ss zzz",

                // Additional Locale-Specific Formats
                DateTimeFormatter.ofPattern("d MMMM yyyy").toString(),  // Example: 4 December 2019
                DateTimeFormatter.ofPattern("MMMM d, yyyy").toString(),  // Example: December 4, 2019

                // Additional ISO 8601 Formats
                "yyyy-MM-dd'T'HH:mm:ssZ",
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
                "yyyy-MM-dd'T'HH:mm:ss.SSS",
                "yyyy-MM-dd'T'HH:mm:ss.SS",
                "yyyy-MM-dd'T'HH:mm:ss.S",
                "yyyy-MM-dd'T'HH:mm:ssX",
                "yyyy-MM-dd'T'HH:mm:ss.SSSX",
                "yyyy-MM-dd'T'HH:mm:ss.SSSSSSX",
                "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSX",
                "yyyyMMdd'T'HH:mm:ssZ",
                "yyyyMMdd'T'HH:mm:ss.SSSXXX",
                "yyyyMMdd'T'HH:mm:ss.SSSSSS",
                "yyyyMMdd'T'HH:mm:ss.SSS",
                "yyyyMMdd'T'HH:mm:ss.SS",
                "yyyyMMdd'T'HH:mm:ss.S",
                "yyyyMMdd'T'HH:mm:ssX",
                "yyyyMMdd'T'HH:mm:ss.SSSX",
                "yyyyMMdd'T'HH:mm:ss.SSSSSSX",
                "yyyyMMdd'T'HH:mm:ss.SSSSSSSX",

                // Oracle Database Timestamp Formats
                "yyyy-MM-dd HH:mm:ss.SSSSSS",
                "yyyy-MM-dd HH:mm:ss.SSS",
                "yyyy-MM-dd HH:mm:ss",

                // US Timestamp Formats
                "MM-dd-yyyy",
                "MM/dd/yyyy",
                "MMddyyyy",
                "MMM dd, yyyy",
                "MMMM dd, yyyy",
                "MMM dd, yyyy hh:mm:ss a",
                "MMMM dd, yyyy hh:mm:ss a",

                // European Timestamp Formats
                "dd-MM-yyyy",
                "dd/MM/yyyy",
                "ddMMyyyy",
                "dd MMM yyyy",
                "dd MMMM yyyy",
                "dd MMM yyyy HH:mm:ss",
                "dd MMMM yyyy HH:mm:ss",

                //more date formats
                "yy/MM/dd",
                "yyMMdd",
                "MM-dd-yy",
                "MM/dd/yy",
                "dd-MM-yy",
                "dd/MM/yy"
        ));

        return new ArrayList<>(dateFormats);
    }
    public static List<String> getDateFormats() {
        return DATE_FORMATS();
    }
}
