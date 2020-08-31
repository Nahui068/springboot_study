package com.jojoldu.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
// @SpringBootApplication이 있는 위치부터 서정을 읽어가기 때문에 항상 프로젝트의 최상단에 위치!!
public class Application { // 프로젝트의 메인 클래스
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); // 내장 WAS를 실행(내장 WAS : 별도로 외부에 WAS를 두지 않고 애플리케이션 실행 시
                                                        // 내부에서 WAS를 실행 -> 항상 서버에 톰캣 설치 필요x)
    }
}
