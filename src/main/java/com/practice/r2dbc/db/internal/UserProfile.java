package com.practice.r2dbc.db.internal;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;


//@Profile({"JWT", "OAuth2Github"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {
    String name;

    String authority;

    String password;

    String clientId;

    String oAuth2userName;

    private Long uuid;

}
