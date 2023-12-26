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

        // Extrahieren Sie den Inhalt der HTTP-Anforderung
        final String contents = request.getBody().orElse(null);

        if (contents == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Message darf nicht leer sein").build();
        } else {
            // Erstellen Sie eine Nachricht und senden Sie sie
            final String message = "Beep boop bop, message received.";
            Telegram dm = new Telegram(message);
            dm.sendMessage();

            return request.createResponseBuilder(HttpStatus.OK).body("Telegram Response: " + message).build();
        }
    }
}