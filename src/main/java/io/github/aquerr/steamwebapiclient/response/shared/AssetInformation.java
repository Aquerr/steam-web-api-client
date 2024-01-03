package io.github.aquerr.steamwebapiclient.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class AssetInformation {
    /**
     * The id of the app.
     */
    @JsonProperty("appid")
    private int appId;

    /**
     * The class id of the asset.
     */
    @JsonProperty("classid")
    private String classId;

    /**
     * The instance if of the asset.
     */
    @JsonProperty("instanceid")
    private String instanceId;

    /**
     * If currency.
     */
    @JsonProperty("currency")
    private boolean currency;

    /**
     * Background color.
     */
    @JsonProperty("background_color")
    private String backgroundColor;

    /**
     * Icon url.
     */
    @JsonProperty("icon_url")
    private String iconUrl;

    /**
     * Icon url large.
     */
    @JsonProperty("icon_url_large")
    private String iconUrlLarge;

    @JsonProperty("descriptions")
    private List<Description> descriptions = new ArrayList<>();

    /**
     * Determines if the asset/item is tradable.
     */
    @JsonProperty("tradable")
    private boolean tradable;

    /**
     * List of actions that can be performed on the asset/item.
     */
    @JsonProperty("actions")
    private List<Action> actions;

    /**
     * The name of the item.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The color of the name. (RGB hexadecimal value e.g. CF6A32)
     */
    @JsonProperty("name_color")
    private String nameColor;

    /**
     * The type of the asset/item.
     */
    @JsonProperty("type")
    private String type;

    /**
     * The market name of the asset/item.
     */
    @JsonProperty("market_name")
    private String marketName;

    /**
     * The market hash name of the asset/item.
     */
    @JsonProperty("market_hash_name")
    private String marketHashName;

    /**
     * The list of actions that performed on the asset/item.
     */
    @JsonProperty("market_actions")
    private List<MarketAction> marketActions;

    /**
     * If commodity.
     */
    @JsonProperty("commodity")
    private boolean commodity;

    /**
     * Market tradable restricion.
     */
    @JsonProperty("market_tradable_restriction")
    private int marketTradableRestriction;

    /**
     * If marketable.
     */
    @JsonProperty("marketable")
    private boolean marketable;

    /**
     * Tags.
     */
    @JsonProperty("tags")
    private List<Tag> tags = new ArrayList<>();

    @Data
    public static class Description {

        /**
         * The type of description. Usually "html".
         */
        @JsonProperty("type")
        private String type;

        /**
         * The value of description.
         */
        @JsonProperty("value")
        private String value;

        /**
         * The color of text. (RGB hexadecimal value e.g. 9da1a9)
         */
        @JsonProperty("color")
        private String color;
    }

    @Data
    public static class Action {

        /**
         * The name of the action.
         */
        @JsonProperty("name")
        private String name;

        /**
         * The action link.
         */
        @JsonProperty("link")
        private String link;
    }

    @Data
    public static class MarketAction {
        /**
         * The name of the action.
         */
        @JsonProperty("name")
        private String name;

        /**
         * The action link.
         */
        @JsonProperty("link")
        private String link;
    }

    @Data
    public static class Tag {
        /**
         * Category.
         */
        @JsonProperty("category")
        private String category;

        /**
         * Internal name.
         */
        @JsonProperty("internal_name")
        private String internalName;

        /**
         * Localized category name.
         */
        @JsonProperty("localized_category_name")
        private String localizedCategorName;

        /**
         * Localized tag name.
         */
        @JsonProperty("localized_tag_name")
        private String localizedTagName;
    }
}
