package com.example.study.model.network.response;

import com.example.study.model.enumclass.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApiResponse {
    private Long id; // DB id 를 내려줄 것이고,
    private String account;
    private String password; // 응답시에는 password 가 암호화되어!

    private UserStatus status;
    private String email;
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;

}
