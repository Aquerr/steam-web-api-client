package io.github.aquerr.steamwebapiclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SupportedApiListResponse implements SteamWebApiResponse {

    @JsonProperty("apilist")
    private SupportedApiList supportedApiList;

    @Data
    public static class SupportedApiList {

        @JsonProperty("interfaces")
        private List<SupportedInterface> interfaces = new ArrayList<>();

        @Data
        public static class SupportedInterface {

            @JsonProperty("name")
            private String name;

            @JsonProperty("methods")
            private List<Method> methods;

            @Data
            public static class Method {

                @JsonProperty("name")
                private String name;

                @JsonProperty("version")
                private int version;

                @JsonProperty("httpmethod")
                private String httpMethod;

                @JsonProperty("parameters")
                private List<Parameter> parameters = new ArrayList<>();

                @Data
                public static class Parameter {

                    @JsonProperty("name")
                    private String name;

                    @JsonProperty("type")
                    private String type;

                    @JsonProperty("optional")
                    private boolean optional;

                    @JsonProperty("description")
                    private String description;
                }
            }
        }
    }
}
