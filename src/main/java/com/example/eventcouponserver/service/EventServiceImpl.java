package com.example.eventcouponserver.service;

import com.example.eventcouponserver.dto.ResponseMessageDto;
import com.example.eventcouponserver.entity.Coupon;
import com.example.eventcouponserver.entity.Event;
import com.example.eventcouponserver.member.MemberDetails;
import com.example.eventcouponserver.repository.CouponRepository;
import com.example.eventcouponserver.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.example.eventcouponserver.entity.EventStatus.CLOSE;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "EventService")
public class EventServiceImpl implements EventService {

    private final CouponRepository couponRepository;
    private final EventRepository eventRepository;

    @Transactional
    @Override
    public ResponseMessageDto createCoupon(Long eventId, Long memberId) {

        Long couponCount = couponRepository.countByEventId(eventId);
        Event currentEvent = eventRepository.findById(eventId).orElseThrow();

        validateCreateCoupon(currentEvent, couponCount);

        Coupon coupon = Coupon.builder()
                .eventId(currentEvent.getId())
                .memberId(memberId)
                .discountRate(currentEvent.getDiscountRate())
                .build();

        couponRepository.save(coupon);
        currentEvent.updateCurrentMember();

        if (currentEvent.getMaxMemberCount().equals(currentEvent.getCurrentMemberCount())) {
            currentEvent.closeEventBeforeClosingTime(LocalDateTime.now());
        }

        return new ResponseMessageDto("쿠폰 발급 성공", HttpStatus.OK.value(), HttpStatus.OK.toString());
    }

    private void validateCreateCoupon(Event currentEvent, Long couponCount) {
        if (couponCount >= currentEvent.getMaxMemberCount()) {
            throw new IllegalArgumentException("You have exceeded the number of participants.");
        }
//        if (couponCount != currentEvent.getCurrentMemberCount()){
//            throw new IllegalArgumentException("The number of coupons currently issued does not match the number of people.");
//        }
        if (currentEvent.getEventStatus().equals(CLOSE)) {
            throw new IllegalArgumentException("Event Close");
        }
    }
}
