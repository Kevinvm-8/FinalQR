package com.qradmin.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonUtil {
	public static String getAsString(JsonObject json, String keyName, String nullValue) {
		if (!json.has(keyName) || json.get(keyName).isJsonNull()) {
			return nullValue;
		}
		if(json.get(keyName).isJsonObject() || json.get(keyName).isJsonArray()){
			return json.get(keyName).toString();
		}
		return json.get(keyName).getAsString();
	}

	public static int getAsInt(JsonObject json, String keyName, int nullValue) {
		if (!json.has(keyName) || json.get(keyName).isJsonNull()) {
			return nullValue;
		}
		return json.get(keyName).getAsInt();
	}

	public static double getAsDouble(JsonObject json, String keyName, double nullValue) {
		if (!json.has(keyName) || json.get(keyName).isJsonNull()) {
			return nullValue;
		}
		return json.get(keyName).getAsDouble();
	}

	public static JsonArray getAsJsonArray(JsonObject json, String keyName, JsonArray nullValue) {
		if (!json.has(keyName) || !json.get(keyName).isJsonArray()) {
			return nullValue;
		}
		return json.get(keyName).getAsJsonArray();
	}

	public static JsonObject getAsJsonObject(JsonObject json, String keyName, JsonObject nullValue) {
		if (!json.has(keyName) || !json.get(keyName).isJsonObject()) {
			return nullValue;
		}
		return json.get(keyName).getAsJsonObject();
	}

	public static Boolean getAsBoolean(JsonObject json, String keyName, Boolean nullValue) {
		if (!json.has(keyName) || json.get(keyName).isJsonNull()) {
			return nullValue;
		}
		return json.get(keyName).getAsBoolean();
	}
}
