package com.example.springsecuritysystem.repository;



import com.example.springsecuritysystem.entity.system.oauth.client.OAuthClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthClientRepository extends JpaRepository<OAuthClient, String> {
}
