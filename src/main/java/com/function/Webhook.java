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

//  curl "https://zehnter.azurewebsites.net/api/sendMsgOnCurl?msg=HelloWord"

public class Webhook {
    @FunctionName("sendMsgOnCurl")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        final String query = request.getQueryParameters().get("msg");
        final String message = request.getBody().orElse(query);

        Telegram dm = new Telegram(message);
        dm.sendMessage();

        if (message == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Message darf nicht leer sein").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body("Telegram Respone: " + message).build();
        }
    }
}