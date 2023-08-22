package com.example.eventcouponserver.repository;

import com.example.eventcouponserver.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Long countByEventId(Long eventId);
}
