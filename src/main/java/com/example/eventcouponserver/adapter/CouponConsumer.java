package com.example.eventcouponserver.adapter;

import com.example.eventcouponserver.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CouponConsumer {

    private final EventService eventService;
    private final ObjectMapper objectMapper;


    @KafkaListener(topics = "${kafka.topic.coupon}", containerFactory = "couponKafkaListenerContainerFactory")
    public void listener(String message) throws JsonProcessingException {
        log.info("couponConsumer running!");
        log.info("message = {}", message);
        CouponCreateMessage eventConsumeMessage = objectMapper.readValue(message, CouponCreateMessage.class);
        try {
            eventService.createCoupon(eventConsumeMessage.getEventId(), eventConsumeMessage.getMemberId());
        } catch (RuntimeException e) {
            log.error("createCoupon Error {}", e);
        }
    }
}
