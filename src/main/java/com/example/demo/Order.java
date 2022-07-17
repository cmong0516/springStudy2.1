package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

// 강의에선 게터세터를 직접 만듬
@Data
// 강의에선 생성자를 직접 만듬
@AllArgsConstructor
// 강의에선 toString 메서드를 직접 재정의함
@ToString
public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public int calculatePrice() {
        return itemPrice - discountPrice;
    }
}
