package com.jasper.learn.dto.response;

import lombok.*;

import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResponseDto {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
