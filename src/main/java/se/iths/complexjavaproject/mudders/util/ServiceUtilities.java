package se.iths.complexjavaproject.mudders.util;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class ServiceUtilities {

    public static double randomNumberGenerator(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+ min;
        return x;

    }

    public static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }

    public static ResponseEntity runPostEndpointWithBody(String url, JSONPObject jsonpObject) {
        try {
            URL object = new URL(url);
            HttpURLConnection con = (HttpURLConnection) object.openConnection();

            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(jsonpObject.toString());
            wr.flush();

//            display what the POST request returns

            StringBuilder sb = new StringBuilder();
            int HttpResult = con.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_CREATED) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                return ResponseEntity.ok(sb.toString());
            } else {
                // TODO: Fix response with another inputstream.
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getErrorStream(), "utf-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                return ResponseEntity.status(HttpResult).body(sb.toString());
            }
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getStackTrace());
        }
    }
}
