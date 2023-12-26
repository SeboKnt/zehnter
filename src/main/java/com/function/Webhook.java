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

        final String contents = request.getQueryParameters().get("msg");

        if (contents == null) {
            Error err = new Error("Bad_REQUEST");
            Telegram dm = new Telegram(err.toString());
            dm.sendMessage();

            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Bad_REQUEST").build();
        } else {
            Telegram dm = new Telegram(contents);
            dm.sendMessage();

            return request.createResponseBuilder(HttpStatus.OK).body("Telegram Message: " + contents).build();
        }
    }
}