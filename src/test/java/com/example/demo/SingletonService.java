package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {


    // static 영역에 SingletonServie 객체 instance 생성
    private static final SingletonService instance = new SingletonService();

    // 이 객체의 instance 를 조회하려면 getInstance 메서드 사용
    public static SingletonService getInstance() {
        return instance;
    }

    // 1개의 인스턴스만 생성되어야 하므로 생성자를 private.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }


}
