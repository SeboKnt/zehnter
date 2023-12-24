package com.function;
import com.microsoft.azure.functions.*;
import java.util.*;

public class FunctionTest {
    public void test() {
        // Erstellen Sie hier einen Kontext
        ExecutionContext context = null; // Sie müssen hier einen echten Kontext erstellen

        context.getLogger().info("Java Test Trigger");
    }
}