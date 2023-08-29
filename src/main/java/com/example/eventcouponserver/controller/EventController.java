package com.example.eventcouponserver.controller;

import com.example.eventcouponserver.dto.ResponseMessageDto;
import com.example.eventcouponserver.member.LoginMember;
import com.example.eventcouponserver.member.MemberDetails;
import com.example.eventcouponserver.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

//    @PostMapping("/{eventId}/coupons")
//    public ResponseEntity<ResponseMessageDto> createCoupon(@PathVariable Long eventId, @LoginMember MemberDetails memberDetails) {
//        ResponseMessageDto responseDataDto = eventService.createCoupon(eventId, memberDetails);
//
//        return ResponseEntity.status(OK).body(responseDataDto);
//    }
}
