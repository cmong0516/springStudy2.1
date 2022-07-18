package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // private 으로 생성자를 막아두었음.
//        new SingletonService(); 에러

        // 싱글톤 서비스 1 인스턴스와
        SingletonService singletonService1 = SingletonService.getInstance();
        // 싱글톤 서비스 2 의 인스턴스가
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // 같다 .

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        singletonService1.logic();
    }

    // 싱글톤을 사용하는 이유
    // 요청이 올때마다 객체를 생성하는것이 아니라 이미 만들어진 객체를 공유해서 효쥴적으로 사용하기 위함.
    // 싱글톤의 문제점
    // 싱글톤 패턴을 구현하는 코드가 길다.
    // 의존관계상 클라이언트가 구체 클래스를 의존한다. (DIP 위반 , OCP 위반가능성이 높다)
    // 테스트 하기 어렵다.
    // 내부 속성을 변경하거나 초기화가 어렵다.
    // 자식 클래스를 만들기 어렵다.

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);

        System.out.println("memberService2 = " + memberService2);

//        memberService1 = com.example.demo.MemberServiceImpl@1e392345
//        memberService2 = com.example.demo.MemberServiceImpl@1e392345

        // 같은 객체가 나온다.

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}

