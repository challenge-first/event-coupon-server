package com.example.eventcouponserver.adapter;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CouponCreateMessage {

    private Long eventId;

    private Long memberId;
}
