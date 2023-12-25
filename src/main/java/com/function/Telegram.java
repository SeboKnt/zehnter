package com.function;

import java.net.*;
import java.io.*;

public class Telegram {
    private final String token = System.getenv("telegram_token");
    private final String chatId = System.getenv("telegram_chat_id");
    private final String api = "https://api.telegram.org/bot";
    String subject;
    String message;
    
    public Telegram(String s, String m){
        this.subject = s;
        this.message = m;
    }

    public int sendMessage(String a, String s, String c, String t, String m){
        try{
            String msg = a + s + "/sendMessage?chat_id=" + c + "&text=" + t + ": " + m;

            URL url = new URL(msg);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            
            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.flush();
            out.close();
            
            // check response code
            int responseCode = conn.getResponseCode();
            
            conn.disconnect();

            return responseCode;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
