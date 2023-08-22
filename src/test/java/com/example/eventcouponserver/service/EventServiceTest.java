package com.example.eventcouponserver.service;

import com.example.eventcouponserver.dto.ResponseMessageDto;
import com.example.eventcouponserver.entity.Event;
import com.example.eventcouponserver.entity.EventStatus;
import com.example.eventcouponserver.member.MemberDetails;
import com.example.eventcouponserver.repository.CouponRepository;
import com.example.eventcouponserver.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    EventRepository eventRepository;

    @Mock
    CouponRepository couponRepository;

    @Mock
    MemberDetails memberDetails;

    @InjectMocks
    EventServiceImpl eventService;

    private Event event;

    @BeforeEach
    void beforeEach() {
        LocalDateTime now = LocalDateTime.now();
        event = Event.builder()
                .eventStatus(EventStatus.OPEN)
                .discountRate(0.1)
                .openingTime(now)
                .closingTime(now.plusHours(1))
                .currentMemberCount(0L)
                .maxMemberCount(2L)
                .build();
    }

    @Test
    public void createCouponTest() throws Exception {
        //given
        when(memberDetails.getId()).thenReturn(1L);
        when(couponRepository.countByEventId(any())).thenReturn(0L);
        when(eventRepository.findById(any())).thenReturn(Optional.of(event));


        //when
        ResponseMessageDto coupon = eventService.createCoupon(1L, memberDetails);

        //then
        assertThat(coupon.getStatusCode()).isEqualTo(200);
    }
    
    @Test
    public void createCouponExceptionTest() throws Exception {
        //given
        event.closeEventAtClosingTime();

        when(couponRepository.countByEventId(any())).thenReturn(0L);
        when(eventRepository.findById(any())).thenReturn(Optional.of(event));

        // when then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            eventService.createCoupon(1L, memberDetails);
        });
    }
}