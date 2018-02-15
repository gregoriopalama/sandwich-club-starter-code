package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private final static String SANDWICH_NAME = "name";
    private final static String SANDWICH_MAIN_NAME = "mainName";
    private final static String SANDWICH_ALSO_KNOWN_AS = "alsoKnownAs";
    private final static String SANDWICH_PLACE_OF_ORIGIN = "placeOfOrigin";
    private final static String SANDWICH_DESCRIPTION = "description";
    private final static String SANDWICH_IMAGE = "image";
    private final static String SANDWICH_INGREDIENTS = "ingredients";

    /**
     * It parses a json representing a Sandwich and returns a Sandwich object.
     * If the json is not well formed, this will return a null value.
     *
     * @param json  The json to parse
     * @return      The parsed Sandwich
     * @author Gregorio Palamà
     */
    public static Sandwich parseSandwichJson(String json) {
        JSONObject jsonSandwich;
        Sandwich sandwich;
        try {
            jsonSandwich = new JSONObject(json);

            sandwich = new Sandwich(
                    jsonSandwich.getJSONObject(SANDWICH_NAME).getString(SANDWICH_MAIN_NAME),
                    parseJsonStringArray(jsonSandwich.getJSONObject(SANDWICH_NAME)
                            .getJSONArray(SANDWICH_ALSO_KNOWN_AS)),
                    jsonSandwich.getString(SANDWICH_PLACE_OF_ORIGIN),
                    jsonSandwich.getString(SANDWICH_DESCRIPTION),
                    jsonSandwich.getString(SANDWICH_IMAGE),
                    parseJsonStringArray(jsonSandwich.getJSONArray(SANDWICH_INGREDIENTS))
                    );
        } catch (JSONException e) {
            // Error while trying to parse the json.
            // The returned Sandwich will be null and nothing will be shown
            return null;
        }

        return sandwich;
    }

    /**
     * It parses a json representing an array of Strings, transforming it into a List
     *
     * @param array  The json array to parse as List of Strings
     * @return      The parsed List of Strings
     * @author Gregorio Palamà
     */
    private static List<String> parseJsonStringArray(JSONArray array) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getString(i));
        }
        return list;
    }
}
