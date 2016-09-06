package org.chiefninjaneer.instagram.oauth2.models.user;

import lombok.Data;

@Data
public class UserCount {
    private Integer media;
    private Integer followed_by;
    private Integer follows;
}

