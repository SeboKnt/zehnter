package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.time.*;
import java.util.*;

public class Function {
    String cmd;

    public Function(String cmd){
        this.cmd = cmd;
    }

    public void main() {
        String response = this.cmd;
        Telegram dm = new Telegram(response);
        dm.sendMessage();
    }
}
