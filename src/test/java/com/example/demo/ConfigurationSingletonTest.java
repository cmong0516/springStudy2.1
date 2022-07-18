package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceimpl orderService = ac.getBean("orderService", OrderServiceimpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }


}

// AppConfig 에서 작성한 @Configuration 어노테이션은 등록한 빈들을 CGLIB 기술을 사용하여 싱글톤으로 사용하게 해준다.
// AppConfig 를 @Configuration 없이 등록할시 위의 코드에서 memberRepository 는 세번 호출될것이다.
// 스프링 설정 정보는 항상 @Configuration 을 사용하자.