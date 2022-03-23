package com.example.springsecuritysystem.service;

import com.example.springsecuritysystem.entity.system.oauth.client.OAuthClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;


import java.util.*;
import java.util.stream.Collectors;

public class CustomOAuthClientDetails implements ClientDetails {

    private final OAuthClient OAuthClient;

    public CustomOAuthClientDetails(OAuthClient OAuthClient) {
        this.OAuthClient = OAuthClient;
    }

    @Override
    public String getClientId() {
        return OAuthClient.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return Arrays.stream(OAuthClient.getResourceIds().split(",")).collect(Collectors.toSet());
    }

    @Override
    public boolean isSecretRequired() {
        return OAuthClient.getIsSecretRequired();
    }

    @Override
    public String getClientSecret() {
        return OAuthClient.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return OAuthClient.getIsScoped();
    }

    @Override
    public Set<String> getScope() {
        return Arrays.stream(OAuthClient.getScope().split(",")).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return Arrays.stream(OAuthClient.getAuthorizedGrantTypes().split(",")).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Arrays.stream(OAuthClient.getRegisteredRedirectUri().split(",")).collect(Collectors.toSet());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.stream(OAuthClient.getAuthorities().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return OAuthClient.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return OAuthClient.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String s) {
        return OAuthClient.getIsAutoApprove();
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        try {
            return new ObjectMapper().readValue(OAuthClient.getAdditionalInformation(), new TypeReference<HashMap<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
