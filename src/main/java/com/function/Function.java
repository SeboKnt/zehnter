package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.time.*;
import java.util.*;

public class Function {

    @FunctionName("Timer")
    public void timerHandler(
        @TimerTrigger(name = "timerInfo", schedule = "0 */5 * * * *") String timerInfo,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Timer trigger function executed at: " + LocalDateTime.now());
    }

    @FunctionName("Html")
    public HttpResponseMessage runHtml(
            @HttpTrigger(
                name = "req", 
                methods = {HttpMethod.GET, HttpMethod.POST}, 
                authLevel = AuthorizationLevel.ANONYMOUS,
                route = "html") 
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
    
        context.getLogger().info("Java HTTP trigger processed a request.");
    
        // Erstelle den HTML-Inhalt
        String htmlContent = "<html><body><h1>Hallo von Azure Functions!</h1><p>Dies ist eine Beispiel-Webseite.</p></body></html>";
    
        // Erstelle die HTTP-Antwort mit dem HTML-Inhalt
        return request.createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "text/html")
                .body(htmlContent)
                .build();
    }
}
