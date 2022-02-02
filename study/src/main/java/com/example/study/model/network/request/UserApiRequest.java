package com.example.study.model.network.request;


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

// C 할 때 뿐만 아니라, U 할때도 쓰임!
public class UserApiRequest {
    private Long id;
    private String account;
    private String password; // 요청시 password 가 평문으로 들어오지만

    private UserStatus status;
    private String email;
    private String phoneNumber;

    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;
}
