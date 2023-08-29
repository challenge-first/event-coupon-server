package com.example.eventcouponserver.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCouponDto {

    private Long eventId;
    private Long memberId;

}
