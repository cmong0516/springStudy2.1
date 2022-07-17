package com.example.demo;

// 할인정책 1
public class FixDiscountPolicy implements DiscountPolicy{
    // vip 면 1000원 할인.
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
