package org.secure.security.authentication.handler.login.github.dto;

import lombok.Data;

@Data
public class GitHubUserProfile {
    private Long id;
    private String login;
    private String nodeId;
    private String avatarUrl;
    private String gravatarId;
    private String url;
    private String htmlUrl;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String type;
    private Boolean siteAdmin;

    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private Boolean hireable;
    private String bio;
    private String twitterUsername;

    private Integer publicRepos;
    private Integer publicGists;
    private Integer followers;
    private Integer following;

    private String createdAt;
    private String updatedAt;

    private Integer privateGists;
    private Integer totalPrivateRepos;
    private Integer ownedPrivateRepos;
    private Integer diskUsage;
    private Integer collaborators;
    private Boolean twoFactorAuthentication;

    private Plan plan;

    @Data
    public static class Plan {
        private String name;
        private Integer space;
        private Integer privateRepos;
        private Integer collaborators;
    }
}
