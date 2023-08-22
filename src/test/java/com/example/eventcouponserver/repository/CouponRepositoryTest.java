package com.example.eventcouponserver.repository;

import com.example.eventcouponserver.entity.Coupon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CouponRepositoryTest {

    @Autowired
    CouponRepository couponRepository;

    @Test
    public void countByEventIdTest_1() throws Exception {
        //given

        //when
        Long couponCount = couponRepository.countByEventId(1L);

        //then
        assertThat(couponCount).isEqualTo(0);
    }

    @Test
    @DisplayName("쿠폰이 있을 경우")
    public void countByEventIdTest_2() throws Exception {
        //given
        Coupon coupon = Coupon.builder()
                .eventId(1L)
                .discountRate(0.1)
                .memberId(1L)
                .build();

        couponRepository.save(coupon);

        //when
        Long couponCount = couponRepository.countByEventId(1L);

        //then
        assertThat(couponCount).isEqualTo(1);
    }

}