package com.npktech.ravi.newsapp;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;



public class NetworkUtils {


    public static String base_url = "newsapi.org";

    public static String apiKey = "3f046cba6bc544fbb24fc9da14a5a67c";

    public static String getResponseFromHttpUrl(URL url) throws IOException {

        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        try {
            InputStream inputStream = httpsURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            httpsURLConnection.disconnect();
        }
    }


    public static String buildURL(String mBaseUrl, String apiKey) {
        Uri.Builder builder = new Uri.Builder();

        builder.scheme("https")
                .authority(mBaseUrl)
                .appendPath("v1")
                .appendPath("articles")
                .appendQueryParameter("source", "the-next-web")
                .appendQueryParameter("sortBy", "latest")
                .appendQueryParameter("apiKey", apiKey);

        return builder.build().toString();
    }

}
