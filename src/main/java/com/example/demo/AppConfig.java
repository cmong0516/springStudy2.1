package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceimpl(
                memberRepository(), discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}


// AppConfig 를 통해 DI 부분을 따로 분리하였다.
// 역할과  구현의 분리 .
// 하지만 2회차인 나는 어노테이션을 활용하여 이런 간단한 문제는 더 쉽게 해결할수 있는 방법을 알고있다 :)