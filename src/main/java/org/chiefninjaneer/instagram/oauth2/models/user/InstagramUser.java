package org.chiefninjaneer.instagram.oauth2.models.user;


import lombok.Data;

@Data
public class InstagramUser {
    private UserMeta meta;
    private UserData data;
}
