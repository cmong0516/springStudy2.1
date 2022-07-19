package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {
    // 호출 안됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = "+noBean1);
        }
    // null
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
            Assertions.assertThat(noBean2).isEqualTo(null);
        }
    // Optional.empty
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
            Assertions.assertThat(noBean3).isEqualTo(Optional.empty());
        }
    }
}

// 자동 주입 대상을 옵션으로 처리하는 방법
// 1. @Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
// 2. org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
// 3. Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.