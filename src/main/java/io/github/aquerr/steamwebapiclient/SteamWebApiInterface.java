package io.github.aquerr.steamwebapiclient;

/**
 * Enum representing steam web api interfaces.
 */
public enum SteamWebApiInterface {

    I_WORKSHOP_SERVICE("IWorkshopService", new Method[]{
        Method.SET_ITEM_PAYMENT_RULES,
        Method.GET_FINALIZED_CONTRIBUTORS,
        Method.GET_ITEM_DAILY_REVENUE,
        Method.GET_FINALIZED_CONTRIBUTORS
    }),
    I_PUBLISHED_FILE_SERVICE("IPublishedFileService", new Method[]{
            Method.QUERY_FILES,
            Method.SET_DEVELOPER_METADATA,
            Method.UPDATE_APP_UGC_BAN,
            Method.UPDATE_BAN_STATUS,
            Method.UPDATE_INCOMPATIBLE_STATUS,
            Method.UPDATE_TAGS
    });


    private final String interfaceName;
    private final Method[] methods;

    SteamWebApiInterface(String interfaceName, Method[] methods) {
        this.interfaceName = interfaceName;
        this.methods = methods;
    }

    public String getInterfaceName()
    {
        return interfaceName;
    }

    public Method[] getMethods()
    {
        return methods;
    }

    /**
     * Enum representing steam web api interfaces' methods.
     */
    public enum Method {
        SET_ITEM_PAYMENT_RULES("SetItemPaymentRules"),
        GET_FINALIZED_CONTRIBUTORS("GetFinalizedContributors"),
        GET_ITEM_DAILY_REVENUE("GetItemDailyRevenue"),
        POPULATE_ITEM_DESCRIPTIONS("PopulateItemDescriptions"),
        QUERY_FILES("QueryFiles"),
        SET_DEVELOPER_METADATA("SetDeveloperMetadata"),
        UPDATE_APP_UGC_BAN("UpdateAppUGCBan"),
        UPDATE_BAN_STATUS("UpdateBanStatus"),
        UPDATE_INCOMPATIBLE_STATUS("UpdateIncompatibleStatus"),
        UPDATE_TAGS("UpdateTags")
        ;

        private final String methodName;

        Method(String methodName) {
            this.methodName = methodName;
        }

        public String getMethodName()
        {
            return methodName;
        }
    }
}
