package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);


    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

        // 클라이언트빈은 싱글톤 , 프로토타입 빈은 프로토타입빈.
        // 클라이언트 빈이 생성될때 프로토타입 빈이 주입된다.
        // 위의 결과를 보면 클라이언트 빈에 있는 프로토타입 빈의 count 가 요청할때 초기화가 되지 않고 유지되고 있다.
        // 우리가 원하는 사용방법은 주로 이런것이 아닐것이다.
    }


    @Scope("singleton")
    static class ClientBean {
        // ObjectProvider 는 ObjectFactory 를 상속받아 기능을 추가하여 만듬. => 쓰기 더 좋다.
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }


//    //implementation 'javax.inject:javax.inject:1' gradle 추가 필수 @Autowired
//    private Provider<PrototypeBean> provider;
//    public int logic() {
//        PrototypeBean prototypeBean = provider.get();
//        prototypeBean.addCount();
//        int count = prototypeBean.getCount();
//        return count;
//    }


}

// 정리
// 프로토타입 빈을 사용할 경우 : 매번 사용할 때마다 의존관계 주입이 완료된 새로운 객체가 필요할때 .
// 실무에선 싱글톤 빈으로 대부분의 상황을 해결 가능하기에 잘 사용하지는 않는다.
// 방법 1. 객체를 생성할 때마다 프로토타입 빈을 주입받는다.
//     2. ObjectProvider 를 사용한다.
//     3. Provider (JSR330) 을 사용한다.
// 스프링 컨테이너에서 사용할 경우 ObjectProvider 외의 다른 컨테이너에서 사용이 필요할 경우 JSR330 을 이용한 Provider 사용.