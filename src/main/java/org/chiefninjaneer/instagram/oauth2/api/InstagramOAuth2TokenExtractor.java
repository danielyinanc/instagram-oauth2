package org.chiefninjaneer.instagram.oauth2.api;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.utils.OAuthEncoder;
import com.github.scribejava.core.utils.Preconditions;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstagramOAuth2TokenExtractor implements TokenExtractor<OAuth2AccessToken> {
    ObjectMapper mapper = new ObjectMapper();

    protected InstagramOAuth2TokenExtractor() {
    }

    private static class InstanceHolder {

        private static final InstagramOAuth2TokenExtractor INSTANCE = new InstagramOAuth2TokenExtractor();
    }

    public static InstagramOAuth2TokenExtractor instance() {
        return InstagramOAuth2TokenExtractor.InstanceHolder.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OAuth2AccessToken extract(String response) {
        String accessToken = null;
        String tokenType= null;
        Integer expiresIn = null;
        String refreshToken = null;
        String scope = null;

        Preconditions.checkEmptyString(response,
                "Response body is incorrect. Can't extract a token from an empty string");
        try {
            JsonNode actualObj = mapper.readTree(response);
            accessToken = actualObj.get("access_token").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OAuth2AccessToken(accessToken, tokenType, expiresIn, refreshToken, scope, response);
    }

}
