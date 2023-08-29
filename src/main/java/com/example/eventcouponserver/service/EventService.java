package com.example.eventcouponserver.service;

import com.example.eventcouponserver.dto.ResponseMessageDto;

public interface EventService {
    ResponseMessageDto createCoupon(Long evenId, Long memberId);
}
