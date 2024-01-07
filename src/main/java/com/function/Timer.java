package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.time.*;

public class Timer {
    @FunctionName("timeToUpload")
    // On every 10th of the month at 9:00 AM
    public void timerHandler(@TimerTrigger(name = "timer", schedule = "0 0 9 10 * *") String timer) {

        final String message = "Stundenzettel für den Monat ist fällig!";

        Telegram dm = new Telegram(message);
        dm.sendMessage();
    }

    @FunctionName("timeToRrenewTelegramWebhook")
    public void timerHandler2(@TimerTrigger(name = "timer", schedule = "0 0 3 * * *") String timer) {

        final String message = "erneuern!";
        Telegram dm = new Telegram(message);
        dm.sendMessage();
    }
}