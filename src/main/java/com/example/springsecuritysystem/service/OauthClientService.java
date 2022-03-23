package com.example.springsecuritysystem.service;


import com.example.springsecuritysystem.entity.system.oauth.client.OAuthClient;
import com.example.springsecuritysystem.repository.OAuthClientRepository;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class OauthClientService implements ClientDetailsService {

    private final OAuthClientRepository authClientRepository;

    public OauthClientService(OAuthClientRepository OAuthClientRepository) {
        this.authClientRepository = OAuthClientRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        OAuthClient oauthClient = getOauthClientByClientId(s).orElseThrow(() -> new RuntimeException("Client not found with id " + s));
        return new CustomOAuthClientDetails(oauthClient);
    }

    public Optional<OAuthClient> getOauthClientByClientId(String clientId) {
        return authClientRepository.findById(clientId);
    }
}
