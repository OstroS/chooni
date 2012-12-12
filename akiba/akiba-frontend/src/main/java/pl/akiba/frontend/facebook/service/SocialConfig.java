package pl.akiba.frontend.facebook.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

/**
 * Have no idea what the hell is this class for, but it was in manual :) :)
 * @author OstroS
 */
@Configuration
public class SocialConfig {
    
    /**
     * @TODO
     * All of such parameters should be moved to properties
     */
    private final String facebookClientId = "142100775940517";
    private final String facebookClientSecret = "c7b1cef987324e69b9ad9fe6ebf05633";
    
    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory(
            facebookClientId,
            facebookClientSecret));
        return registry;
    }

}