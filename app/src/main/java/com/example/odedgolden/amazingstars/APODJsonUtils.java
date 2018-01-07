package com.example.odedgolden.amazingstars;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by odedgolden on 07/01/2018.
 */

public final class APODJsonUtils {

    public static APODObject getAPODObjectFromJson(Context context, String APODJsonString)
            throws JSONException {

        JSONObject APODJson = new JSONObject(APODJsonString);

        APODObject object = new APODObject();

        object.date = APODJson.getString("date");
        object.explanation = APODJson.getString("explanation");
        object.hdurl = APODJson.getString("hdurl");
        object.media_type = APODJson.getString("media_type");
        object.service_version = APODJson.getString("service_version");
        object.title = APODJson.getString("title");
        object.url = APODJson.getString("url");

        return object;
    }
}
