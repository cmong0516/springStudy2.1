package com.example.demo;

public class OrderServiceimpl implements OrderService{
    // memberRepository 의 구현체는 new MemoryMemberRepository();
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // discountPolicy 의 구현체는 new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
