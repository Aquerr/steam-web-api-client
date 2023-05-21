package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Class representing the result of <a href="https://partner.steamgames.com/doc/webapi/IPlayerService#GetCommunityBadgeProgress">https://partner.steamgames.com/doc/webapi/IPlayerService#GetCommunityBadgeProgress</a>
 */
@Data
public class CommunityBadgeProgressResponse implements SteamWebApiResponse {

    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {
        /**
         * List of quests for specific badge.
         */
        @JsonProperty("quests")
        private List<Quest> quests;

        @Data
        public static class Quest {
            /**
             * Quest id.
             */
            @JsonProperty("questid")
            private long questId;
            /**
             * Is quest completed.
             */
            @JsonProperty("completed")
            private boolean completed;
        }
    }
}
