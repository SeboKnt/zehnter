package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;
import org.json.JSONObject;

// https://api.telegram.org/bot<TOKEN>/setWebhook?url=https://zehnter.azurewebsites.net/api/reqest

public class Webhook {
    @FunctionName("reqest")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.FUNCTION)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        
        // return statement for azure function
        context.getLogger().info("Java HTTP trigger processed a request.");

        final String body = request.getBody().orElse(null);

        if (body == null) {
            Error err = new Error("Bad_REQUEST");
            Telegram dm = new Telegram(err.toString());
            dm.sendMessage();

            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Bad_REQUEST").build();

        } else {
            JSONObject json = new JSONObject(body);
            String message = json.getJSONObject("message").getString("text");

            Telegram dm = new Telegram(message);
            dm.sendMessage();

            return request.createResponseBuilder(HttpStatus.OK).body("OK").build();
        }
    }
}