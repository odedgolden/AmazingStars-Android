package com.example.odedgolden.amazingstars;

import android.net.Uri;
import android.util.Log;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by odedgolden on 07/01/2018.
 */


public final class NetworkUtils {

    /**
     * These utilities will be used to communicate with the weather servers.
     */

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String APOD_URL = "https://api.nasa.gov/planetary/apod";

    private static final String hd = "True";
    private static final String api_key = "DEMO_KEY";

    final static String DATE_PARAM = "date";
    final static String HD_PARAM = "hd";
    final static String API_KEY_PARAM = "api_key";

    public static URL buildUrl(String locationQuery) {
        // COMPLETED (1) Fix this method to return the URL used to query Open Weather Map's API
        Uri builtUri = Uri.parse(APOD_URL).buildUpon()
//                .appendQueryParameter(DATE_PARAM, locationQuery)
                .appendQueryParameter(HD_PARAM, hd)
                .appendQueryParameter(API_KEY_PARAM, api_key)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * Builds the URL used to talk to the weather server using latitude and longitude of a
     * location.
     *
     * @param lat The latitude of the location
     * @param lon The longitude of the location
     * @return The Url to use to query the weather server.
     */
    public static URL buildUrl(Double lat, Double lon) {
        /** This will be implemented in a future lesson **/
        return null;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
//                System.out.print(scanner.next());
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
