package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.time.*;

public class Timer {

    @FunctionName("Timer")
    public void timerHandler(
        @TimerTrigger(name = "timerInfo", schedule = "0 */5 * * * *") String timerInfo,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Timer trigger function executed at: " + LocalDateTime.now());
    }
}