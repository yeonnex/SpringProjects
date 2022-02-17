
package com.yeonnex.blog.model;


public class KakaoProfile {

    public Integer id;
    public String connectedAt;
    public Properties properties;
    public KakaoAccount kakaoAccount;

}

class KakaoAccount {

    public Boolean profileNicknameNeedsAgreement;
    public Boolean profileImageNeedsAgreement;
    public Profile profile;
    public Boolean hasEmail;
    public Boolean emailNeedsAgreement;
    public Boolean isEmailValid;
    public Boolean isEmailVerified;
    public String email;

}


class Profile {

    public String nickname;
    public String thumbnailImageUrl;
    public String profileImageUrl;
    public Boolean isDefaultImage;

}
class Properties {

    public String nickname;
    public String profileImage;
    public String thumbnailImage;

}