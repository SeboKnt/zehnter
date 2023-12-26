package com.function;

import java.net.*;
import java.io.*;


// reference https://www.youtube.com/watch?v=gw0tlbpCx5U

public class Telegram {
    private final String token = System.getenv("telegram_token");
    private final String chatId = System.getenv("telegram_chat_id");
    private final String api = "https://api.telegram.org/bot";
    String message;
    
    public Telegram(String message){
        this.message = message;
    }

    private int setWebhook(){
        try{
            URL url = new URL(this.api + this.token + "/setWebhook?url=" + System.getenv("webhook"));       
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

    private void deleteWebhook(){
        
    }

    public int sendMessage(){
        try{
            String msg = this.api + this.token + "/sendMessage?chat_id=" + this.chatId + "&text=" + this.message;

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
