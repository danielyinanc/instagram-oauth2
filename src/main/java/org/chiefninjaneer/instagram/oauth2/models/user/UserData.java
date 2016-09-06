package org.chiefninjaneer.instagram.oauth2.models.user;

import lombok.Data;

@Data
public class UserData {
    private String username;
    private String bio;
    private String website;
    private String profile_picture;
    private String full_name;
    private UserCount counts;
    private String id;
}
