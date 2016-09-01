package org.chiefninjaneer.instagram.oauth2.api;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Verb;

public class InstagramApi20 extends DefaultApi20{

    protected InstagramApi20(){

    }

    private static class InstanceHolder{
        private static final InstagramApi20 INSTANCE = new InstagramApi20();
    }

    public static InstagramApi20 instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.instagram.com/oauth/access_token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://api.instagram.com/oauth/authorize/";
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return InstagramOAuth2TokenExtractor.instance();
    }

}
