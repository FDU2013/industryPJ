package cn.itcast.feign.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuthResponse {
    private Account account;

    private Long code;
    private String msg;
}
