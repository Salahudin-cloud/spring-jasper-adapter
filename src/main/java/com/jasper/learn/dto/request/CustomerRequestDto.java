package com.jasper.learn.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDto {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotBlank(message = "Phone cannot be empty")
    private String phone;

}
