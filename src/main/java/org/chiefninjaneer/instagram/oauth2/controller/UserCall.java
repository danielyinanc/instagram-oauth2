package org.chiefninjaneer.instagram.oauth2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.chiefninjaneer.instagram.oauth2.models.user.InstagramUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value="/user")
public class UserCall {
    private static final String INSTAGRAM_USER_ENDPOINT ="https://api.instagram.com/v1/users/";

    private static final String SELF_SUFFIX ="self/";
    private static final String ACCESS_TOKEN_SUFFIX="?access_token=";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value="/self", produces = { "application/json", "text/json" })
    public InstagramUser callbackAnswer() throws IOException, ExecutionException, InterruptedException {
        String uriToCall = INSTAGRAM_USER_ENDPOINT + SELF_SUFFIX+
                ACCESS_TOKEN_SUFFIX + CallbackUrl.getAccessToken();
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        Future<Response> f = asyncHttpClient.prepareGet(uriToCall).execute();
        Response r = f.get();
        InstagramUser instagramUser = objectMapper.readValue(r.getResponseBody(), InstagramUser.class);
        return instagramUser;
    }

    @RequestMapping(value="/{id}")
    public InstagramUser callbackAnswer(@PathVariable("id") String id) throws IOException, ExecutionException, InterruptedException {
        String uriToCall = INSTAGRAM_USER_ENDPOINT+id+"/" +ACCESS_TOKEN_SUFFIX+CallbackUrl.getAccessToken();
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        Future<Response> f = asyncHttpClient.prepareGet(uriToCall).execute();
        Response r = f.get();
        InstagramUser instagramUser = objectMapper.readValue(r.getResponseBody(), InstagramUser.class);
        return instagramUser;
    }
}
