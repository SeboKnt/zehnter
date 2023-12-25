package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.*;

public class Html {

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
    
        String htmlContent = "<html><body><h1>Hallo von Azure Functions!</h1><p>Dies ist eine Beispiel-Webseite.</p></body></html>";
    
        return request.createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "text/html")
                .body(htmlContent)
                .build();
    }
}