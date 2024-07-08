package it.mikeslab.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    private static final String API_URL = "https://vault.mikeslab.it/access/";

    public static int REQUEST_COUNT = 0;

    public static String getContent(String locale, String path, String licenseKey) {

        try {
            URL url = new URL(API_URL + locale + "/" + path);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("License-Key", licenseKey);

            int status = connection.getResponseCode();

            if(status == 403) {
                System.out.println("Invalid license key.");
                System.exit(1);
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;

            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();

            System.out.printf("#%d Response code: " + status + "\n", ++REQUEST_COUNT);

            return content.toString();
        } catch (Exception e) {
            System.out.println("An error occurred while fetching content from the API. Skipping...");
        }

        return null;
    }
}
