package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.time.*;

public class Timer {
    @FunctionName("Timer")
    public void timerHandler(@TimerTrigger(name = "timer", schedule = "0 */5 * * * *") String timer) {

        final String message = "HelloWord";

        Telegram dm = new Telegram(message);
        dm.sendMessage();
    }
}