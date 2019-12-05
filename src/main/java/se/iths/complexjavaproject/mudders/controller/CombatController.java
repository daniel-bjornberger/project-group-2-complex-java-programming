package se.iths.complexjavaproject.mudders.controller;

import lombok.NoArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@NoArgsConstructor
@RequestMapping("/combat")
public class CombatController {

    @GetMapping
    public ResponseEntity testRequest() {

        try {
            String url="http://localhost:8080/player/add";

            URL object = new URL(url);
            HttpURLConnection con = (HttpURLConnection) object.openConnection();

            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");

            JSONObject name = new JSONObject();
            name.put("characterName","test");

            OutputStreamWriter wr = null;
            wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(name.toString());
            wr.flush();

//            display what the POST request returns

            StringBuilder sb = new StringBuilder();
            int HttpResult = con.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_CREATED) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                return ResponseEntity.ok(sb.toString());
            } else {
                // TODO: Fix response with another inputstream.
                return ResponseEntity.status(HttpResult).body(con.getResponseMessage());
            }
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getStackTrace());
        }
    }
}
