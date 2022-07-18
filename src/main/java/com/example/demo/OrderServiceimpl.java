package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceimpl implements OrderService{
    // memberRepository 의 구현체는 new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    // discountPolicy 의 구현체는 new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 현재 할인 정책은 고정할인을 적용하는 FixDiscountPolicy 와 10% 할인을 적용하는 RateDiscountPolicy 두개가 있다.
    // 할인 정책이 변경될경우 위의 코드를 수정해주어야한다.
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceimpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
