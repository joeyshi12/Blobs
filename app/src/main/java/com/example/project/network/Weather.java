package com.example.project.network;



import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.project.model.Observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Weather extends Subject {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void notifyObserver() throws IOException {
        for (Observer observer : observerList) {
            observer.update(getInfo());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String getInfo() throws IOException {
        BufferedReader br = null;
        try {
            String theURL = getURL();
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            return parse(sb);
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    private String getURL() {
        String apiKey = "52db5c03f7b5e2d2b6cacfafd627669c";
        String weatherQuery = "https://api.openweathermap.org/data/2.5/weather?q=Surrey,uk&APPID=";
        return weatherQuery + apiKey;
    }

    private String parse(StringBuilder string) {
        int startIndex = string.indexOf("main") + 7;
        int endIndex = string.indexOf("description") - 3;
        return string.substring(startIndex, endIndex);
    }
}
