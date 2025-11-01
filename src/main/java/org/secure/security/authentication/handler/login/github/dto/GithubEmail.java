package org.secure.security.authentication.handler.login.github.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubEmail {
    private String email;
    private Boolean primary;
    private Boolean verified;
    private String visibility;
}