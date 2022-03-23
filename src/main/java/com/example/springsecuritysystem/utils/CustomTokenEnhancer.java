package com.example.springsecuritysystem.utils;


import com.example.springsecuritysystem.entity.system.user.ApiKeys;
import com.example.springsecuritysystem.entity.system.user.Role;
import com.example.springsecuritysystem.entity.system.user.Users;
import com.example.springsecuritysystem.enumeration.KeyType;
import com.example.springsecuritysystem.repository.ApiKeyRepository;
import com.example.springsecuritysystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    /**
     * Adding departments as additional information for an user
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        Optional<Users> optionalUsers = usersRepository.findByUserNameIgnoreCase(oAuth2Authentication.getUserAuthentication().getName());
       // List<ApiKeys> mapApiKeys = apiKeyRepository.findAllByKeyTypeAndIsEnable(KeyType.GOOGLE_MAP, true);
       // ApiKeys randomApiKey = mapApiKeys.get(new Random().nextInt(mapApiKeys.size()));

        Map<String, Object> additionalInformation = new HashMap<>();
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            if (users.getTeritory() != null) {
                additionalInformation.put("territoryName", optionalUsers.get().getTeritory().getTeritoryName());
                additionalInformation.put("territoryId", optionalUsers.get().getTeritory().getId());
            } else { // using roleName instead of territoryName cause mis/admin user does not have territory
                Role role = users.getRoles().stream().findFirst()
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found with user name:"+ users.getUserName()));
                additionalInformation.put("territoryName", role.getRole());
                additionalInformation.put("territoryId", 0);
            }
            //additionalInformation.put("googleMapKey", randomApiKey.getKeyStr());

        } else {
            additionalInformation.put("territoryName", Optional.empty());
        }

        long expire_date = System.currentTimeMillis() + (oAuth2AccessToken.getExpiresIn() * 1000L);

        additionalInformation.put("expire_date", expire_date);

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInformation);

        return oAuth2AccessToken;
    }


}
