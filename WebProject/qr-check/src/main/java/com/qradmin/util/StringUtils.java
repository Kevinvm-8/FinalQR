package com.qradmin.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Strings.isNullOrEmpty;


public class StringUtils {

    public static boolean validMonth(String text) {
        try {
            if (isEmpty(text)) return false;

            int month = Integer.parseInt(text);
            if (month < 1 || month > 12) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static boolean validYear(String text) {
        try {
            if (isEmpty(text)) return false;

            Integer.parseInt(text);
            if (text.length() != 2) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static String getStringInJsonObject(JsonObject jsonObject, String key) {
        return (!jsonObject.has(key) || jsonObject.get(key).isJsonNull()) ? null : jsonObject.get(key).getAsString();
    }

    public static JsonArray getArrayInJsonObject(JsonObject jsonObject, String key) {
        return (!jsonObject.has(key) || !jsonObject.get(key).isJsonArray()) ? null : jsonObject.get(key).getAsJsonArray();
    }

    public static JsonObject getObjectInJsonObject(JsonObject jsonObject, String key) {
        return (!jsonObject.has(key) || !jsonObject.get(key).isJsonObject()) ? null : jsonObject.get(key).getAsJsonObject();
    }

    public static int getIntInJsonObject(JsonObject jsonObject, String key) {
        return (!jsonObject.has(key) || jsonObject.get(key).isJsonNull()) ? 0 : jsonObject.get(key).getAsInt();
    }

    public static double getDoubleInJsonObject(JsonObject jsonObject, String key) {
        return (!jsonObject.has(key) || jsonObject.get(key).isJsonNull()) ? 0 : jsonObject.get(key).getAsDouble();
    }

    public static long getLongInJsonObject(JsonObject jsonObject, String key) {
        return (!jsonObject.has(key) || jsonObject.get(key).isJsonNull()) ? 0 : jsonObject.get(key).getAsLong();
    }


    public static String getAutoNumber(int index) {
        String year = DateUtils.getCurrentDate("yyyy");
        int dayOfYear = DateUtils.getDayOfYear();
        String beginNumber;
        String indexStr;
        if (dayOfYear < 10) {
            beginNumber = "00" + dayOfYear;
            indexStr = getString(index, "00000", "0000", "000", "00", "0", "");
        } else if (dayOfYear < 100) {
            beginNumber = "0" + dayOfYear;
            indexStr = getString(index, "00000", "0000", "000", "00", "0", "");
        } else {
            beginNumber = String.valueOf(dayOfYear);
            indexStr = getString(index, "00000", "0000", "000", "00", "0", "");
        }
        return year + beginNumber + indexStr;
    }

    private static String getString(int index, String s, String s2, String s3, String s4, String s5, String s6) {
        String indexStr;
        if (index < 10) {
            indexStr = s + index;
        } else if (index < 100) {
            indexStr = s2 + index;
        } else if (index < 1000) {
            indexStr = s3 + index;
        } else if (index < 10000) {
            indexStr = s4 + index;
        } else if (index < 100000) {
            indexStr = s5 + index;
        } else {
            indexStr = s6 + index;
        }
        return indexStr;
    }

    public static String getAmount(double amount) {
        return new DecimalFormat("#.00").format(amount);
    }

    /**
     * @deprecated
     */
    public static Integer getAsInteger(String value) {
        try {
            if (isEmpty(value)) {
                return null;
            } else {
                return Integer.valueOf(value);
            }
        } catch (Exception ex) {
            return null;
        }
    }


    public static String createHashData(Map fields, String secret) throws UnsupportedEncodingException {
        // create a list and sort it
        fields.remove("mac_type");
        fields.remove("mac");
        List fieldNames = new ArrayList(fields.keySet());
        Iterator iterator = fieldNames.iterator();
        while (iterator.hasNext()) {
            String fieldName = String.valueOf(iterator.next());
            String fieldValue = String.valueOf(fields.get(fieldName));
            if ((isEmpty(fieldValue) || fieldValue.equals("null"))) {
                iterator.remove();
            }
        }
        Collections.sort(fieldNames);
        // create a buffer for the md5 input and add the secure secret first
        StringBuilder sb = new StringBuilder();
        sb.append(secret);
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = String.valueOf(itr.next());
            String fieldValue = String.valueOf(fields.get(fieldName));
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                sb.append(fieldName);
                sb.append("=");
                sb.append(URLDecoder.decode(fieldValue.replaceAll("\\+", "%2b"), "UTF-8"));
                if (itr.hasNext()) {
                    sb.append("&");
                }
            }
        }
        return sb.toString();
    }


}
