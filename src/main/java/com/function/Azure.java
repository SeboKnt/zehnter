////import com.azure.core.management.AzureEnvironment;
////import com.azure.identity.DefaultAzureCredentialBuilder;
////import com.azure.resourcemanager.AzureResourceManager;
//
//public class Azure {
//    
//    private final String funcName;
//    private final String tokenName;
//    private final String functionAppName;
//    private final String resourceGroupName;
//    private final String subscriptionId;
//
//    public Azure(){
//        this.funcName = "funcName";
//        this.tokenName = "tokenName";
//    }
//
//    public String getFunctionToken(){
//        // Authentifizieren Sie sich mit Azure
//        AzureResourceManager azureResourceManager = AzureResourceManager.configure()
//            .withLogLevel(HttpLogDetailLevel.BASIC)
//            .authenticate(new DefaultAzureCredentialBuilder().build(), new AzureProfile(AzureEnvironment.AZURE))
//            .withSubscription(subscriptionId);
//
//        FunctionApp functionApp = azureResourceManager.functionApps().getByResourceGroup(resourceGroupName, functionAppName);
//        String functionKey = functionApp.listHostKeys().functionKeys().get(this.tokenName);
//
//        return functionKey;
//    } 
//}
//
//
//// das wird noch irgendwan. Hier war zuerst geplant den Token direkt aus der Azure Function zu holen
//// In Zukunft soll das eine Anbindung an den Vault und die CosmosDB werden 
//// -> datenbank.java wird dann eine function hier aufrufen