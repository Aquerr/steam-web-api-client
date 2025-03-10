package io.github.aquerr.steamwebapiclient;

/**
 * Enum representing steam web api interfaces.
 */
enum SteamWebApiInterfaceMethod
{

    I_WORKSHOP_SERVICE_SET_ITEM_PAYMENT_RULES("IWorkshopService", "SetItemPaymentRules"),
    I_WORKSHOP_SERVICE_GET_FINALIZED_CONTRIBUTORS("IWorkshopService", "GetFinalizedContributors"),
    I_WORKSHOP_SERVICE_GET_ITEM_DAILY_REVENUE("IWorkshopService", "GetItemDailyRevenue"),

    I_PUBLISHED_FILE_SERVICE_QUERY_FILES("IPublishedFileService", "QueryFiles"),
    I_PUBLISHED_FILE_SERVICE_SET_DEVELOPER_METADATA("IPublishedFileService", "SetDeveloperMetadata"),
    I_PUBLISHED_FILE_SERVICE_UPDATE_APP_UGC_BAN("IPublishedFileService", "UpdateAppUGCBan"),
    I_PUBLISHED_FILE_SERVICE_UPDATE_BAN_STATUS("IPublishedFileService", "UpdateBanStatus"),
    I_PUBLISHED_FILE_SERVICE_UPDATE_INCOMPATIBLE_STATUS("IPublishedFileService", "UpdateIncompatibleStatus"),
    I_PUBLISHED_FILE_SERVICE_UPDATE_TAGS("IPublishedFileService", "UpdateTags"),
    I_PUBLISHED_FILE_SERVICE_GET_DETAILS("IPublishedFileService", "GetDetails"),

    I_STEAM_REMOTE_STORAGE_GET_PUBLISHED_FILE_DETAILS("ISteamRemoteStorage", "GetPublishedFileDetails"),
    I_STEAM_REMOTE_STORAGE_GET_COLLECTION_DETAILS("ISteamRemoteStorage", "GetCollectionDetails"),

    I_STEAM_WEB_API_UTIL_GET_SERVER_INFO("ISteamWebAPIUtil", "GetServerInfo"),
    I_STEAM_WEB_API_UTIL_GET_SUPPORTED_API_LIST("ISteamWebAPIUtil", "GetSupportedAPIList"),

    I_PLAYER_SERVICE_GET_RECENTLY_PLAYED_GAMES("IPlayerService", "GetRecentlyPlayedGames"),
    I_PLAYER_SERVICE_GET_OWNED_GAMES("IPlayerService", "GetOwnedGames"),
    I_PLAYER_SERVICE_GET_STEAM_LEVEL("IPlayerService", "GetSteamLevel"),
    I_PLAYER_SERVICE_GET_BADGES("IPlayerService", "GetBadges"),
    I_PLAYER_SERVICE_GET_COMMUNITY_BADGE_PROGRESS("IPlayerService", "GetCommunityBadgeProgress"),

    I_STEAM_NEWS_GET_NEWS_FOR_APP("ISteamNews", "GetNewsForApp"),
    I_GAME_SERVERS_SERVICE_GET_ACCOUNT_LIST("IGameServersService", "GetAccountList"),
    I_GAME_SERVERS_SERVICE_CREATE_ACCOUNT("IGameServersService", "CreateAccount"),
    I_GAME_SERVERS_SERVICE_DELETE_ACCOUNT("IGameServersService", "DeleteAccount"),
    I_GAME_SERVERS_SERVICE_SET_MEMO("IGameServersService", "SetMemo"),
    I_GAME_SERVERS_SERVICE_RESET_LOGIN_TOKEN("IGameServersService", "ResetLoginToken"),
    I_GAME_SERVERS_SERVICE_GET_ACCOUNT_PUBLIC_INFO("IGameServersService", "GetAccountPublicInfo"),
    I_GAME_SERVERS_SERVICE_QUERY_LOGIN_TOKEN("IGameServersService", "QueryLoginToken"),

    I_GAME_SERVERS_SERVICE_GET_SERVER_IPS_BY_STEAM_ID("IGameServersService", "GetServerIPsBySteamID"),
    I_GAME_SERVERS_SERVICE_GET_SERVER_STEAM_IDS_BY_IP("IGameServersService", "GetServerSteamIDsByIP"),

    I_ECON_SERVICE_GET_TRADE_HISTORY("IEconService", "GetTradeHistory"),
    I_ECON_SERVICE_GET_TRADE_OFFERS("IEconService", "GetTradeOffers"),
    I_ECON_SERVICE_GET_TRADE_OFFER("IEconService", "GetTradeOffer"),
    I_ECON_SERVICE_GET_TRADE_OFFERS_SUMMARY("IEconService", "GetTradeOffersSummary");

    private final String interfaceName;
    private final String methodName;

    SteamWebApiInterfaceMethod(String interfaceName, String methodName)
    {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
    }

    public String getInterfaceName()
    {
        return interfaceName;
    }

    public String getMethodName()
    {
        return methodName;
    }
}
