package com.example.eventcouponserver.dto;

import lombok.*;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class ResponseMessageDto {

    private String message;
    private int statusCode;
    private String statusMessage;
}
