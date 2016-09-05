package org.chiefninjaneer.instagram.oauth2.controller;

import com.github.scribejava.core.builder.ServiceBuilder;

import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth.OAuthService;
import org.chiefninjaneer.instagram.oauth2.api.InstagramApi20;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import java.io.IOException;

@RestController
public class CallbackUrl {
    private static String accessToken;

        @Value("${instagram.api.key}")
    private String INSTAGRAM_API_KEY;

    @Value("${instagram.api.secret}")
    private String INSTAGRAM_API_SECRET;

    @Value("${instagram.api.callback}")
    private String INSTAGRAM_API_CALLBACK;

    @Value("${instagram.api.authorizationUrl}")
    private String INSTAGRAM_AUTHORIZATION_URL;

    private OAuth20Service service;

    @PostConstruct
    private void initiateInstagramService(){
        service = new ServiceBuilder()
                .apiKey(INSTAGRAM_API_KEY)
                .apiSecret(INSTAGRAM_API_SECRET)
                .callback(INSTAGRAM_API_CALLBACK)
                .build(InstagramApi20.instance());
    }

    @RequestMapping(value="/callbackUrl")
    public void callbackAnswer(@RequestParam("code") String code) throws IOException {
        accessToken = service.getAccessToken(code).getAccessToken();
    }

    public static String getAccessToken(){
        return accessToken;
    }
}
