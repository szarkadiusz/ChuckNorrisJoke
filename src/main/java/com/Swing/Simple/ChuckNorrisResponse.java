package com.Swing.Simple;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChuckNorrisResponse {
    private String[] categories;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("icon_url")
    private String iconUrl;

    private String id;

    @JsonProperty("updated_at")
    private String updatedAt;

    private String url;

    private String value;
}
