package com.example.eventcouponserver.service;

import com.example.eventcouponserver.dto.ResponseMessageDto;
import com.example.eventcouponserver.member.MemberDetails;

public interface EventService {
    ResponseMessageDto createCoupon(Long evenId, MemberDetails memberDetails);
}
