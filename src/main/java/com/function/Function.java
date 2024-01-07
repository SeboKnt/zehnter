package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.time.*;
import java.util.*;

public class Function {
    String cmd;
    String response;

    public Function(String cmd){
        this.cmd = cmd;
    }

    

    // Hier eigenlich main(String[] args) -> aber Azure Functions anders
    public void main() {
        response = this.cmd;
        Telegram dm = new Telegram(response);
        dm.sendMessage();
    }
}
