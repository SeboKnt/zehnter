package com.function;

import java.net.*;
import java.io.*;

// reference https://www.youtube.com/watch?v=gw0tlbpCx5U

public class Telegram {
    private final String telegram_token = System.getenv("telegram_token");
    private final String chatId = System.getenv("telegram_chat_id");
    private final String telegram_api = "https://api.telegram.org/bot" + this.telegram_token;
    private final String func_auth = System.getenv("azure_function_zehnter_request_default");
    private final String func_api = "https://zehnter.azurewebsites.net/api/reqest?code=" + this.func_auth;
    String message;
    
    public Telegram(String message){
        this.message = message;
    }

    private int curl(String addr){
        try{
            URL url = new URL(addr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            
            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.flush();
            out.close();
            
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

    public int sendMessage(){
        String url = this.telegram_api + "/sendMessage?chat_id=" + this.chatId + "&text=" + this.message;
        return this.curl(url);

    }

    public int renewWebhook(){
        String url = this.telegram_api + "/setWebhook?url=" + this.func_api;
        return this.curl(url);

        // das ist die Antwort die von Telegram zu erwarten ist.
        // die kann auch noch verarbeitet werden.
        //{
        //    "ok": true,
        //    "result": true,
        //    "description": "Webhook is already set"
        //}

    }
}
