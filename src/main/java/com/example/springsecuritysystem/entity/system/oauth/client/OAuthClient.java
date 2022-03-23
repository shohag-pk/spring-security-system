package com.example.springsecuritysystem.entity.system.oauth.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Touhid Hossain
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OAUTH_CLIENT")
public class OAuthClient implements Serializable {
    @Id
    @Column(name = "CLIENT_ID", nullable = false)
    private String clientId;

    @Column(name = "RESOURCE_IDS", length = 1024)
    private String resourceIds;

    @Column(name = "IS_SECRET_REQUIRED")
    private Boolean isSecretRequired;

    @Column(name = "CLIENT_SECRET", nullable = false)
    private String clientSecret;

    @Column(name = "IS_SCOPED")
    private Boolean isScoped;

    @Column(name = "SCOPE")
    private String scope;

    @Column(name = "AUTHORIZED_GRANT_TYPES", length = 1024)
    private String authorizedGrantTypes;

    @Column(name = "REGISTERED_REDIRECT_URI", length = 2048)
    private String registeredRedirectUri;

    @Column(name = "AUTHORITIES", length = 1024)
    private String authorities;

    @Column(name = "ACCESS_TOKEN_VALIDITY_SECONDS")
    private Integer accessTokenValiditySeconds;

    @Column(name = "REFRESH_TOKEN_VALIDITY_SECONDS")
    private Integer refreshTokenValiditySeconds;

    @Column(name = "IS_AUTO_APPROVE")
    private Boolean isAutoApprove;

    @Column(name = "ADDITIONAL_INFORMATION", length = 4096)
    private String additionalInformation;
}
