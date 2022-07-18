package com.example.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class))
// 기본에 AppConfig 에서는 모든 빈을 하나하나 등록해줘야 사용이 가능했다.
// 이런 반복을 피하려고 @ComponentScan 를 만들었다.
// @Component 가 붙은 클래스를 스프링 빈으로 등록해준다.
// @ComponentScan 을 효율적으로 사용하기 위해선 AppConfig 파일을 루트 최상단에 위치하여 하위 프로젝트에 대해서만 탐색하도록 만든다.

public class AutoAppConfig {
}
