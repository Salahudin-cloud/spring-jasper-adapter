package com.jasper.learn.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequestDto {

    private String name;
    private String address;
    private String phone;

}
