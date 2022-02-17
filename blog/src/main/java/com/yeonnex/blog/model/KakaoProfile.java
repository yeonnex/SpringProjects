
package com.yeonnex.blog.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

// JSON 네이밍 전략
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class KakaoProfile {

    public Integer id;
    public String connected_at; // Unrecognized field "connected_at" 에러 해결. 오타 때문이었음
    public Properties properties;
    public KakaoAccount kakaoAccount;

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Data
    public class KakaoAccount {

        public Boolean profileNicknameNeedsAgreement;
        public Boolean profileImageNeedsAgreement;
        public Profile profile;
        public Boolean hasEmail;
        public Boolean emailNeedsAgreement;
        public Boolean isEmailValid;
        public Boolean isEmailVerified;
        public String email;

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        @Data
        public class Profile {

            public String nickname;
            public String thumbnailImageUrl;
            public String profileImageUrl;
            public Boolean isDefaultImage;

        }

    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Data
    public class Properties {

        public String nickname;
        public String profileImage;
        public String thumbnailImage;

    }

}





