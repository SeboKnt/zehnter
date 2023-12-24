package com.function;
import com.microsoft.azure.functions.*;

public class FunctionTest {
    public void test() {
        ExecutionContext context = null;

        context.getLogger().info("Java Test Trigger");
    }
}