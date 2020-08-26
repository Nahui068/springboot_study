package com.jojoldu.book.springboot;

import com.jojoldu.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// @RunWith(SpringRunner.class)
// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
// SpringRunner라는 스프링 실행자를 사용 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할
// @WebMvcTest(controllers = HelloController.class)
// Web(Spring MVC)에 집중할 수 있는 어노테이션
// 선언할 경우 @Controller, @ControllerAdvice등을 사용할 수 있음
// @Service, @Component, @Repository등은 사용 불가능
// 여기서는 Controller만 사용하기 때문에 선언
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입 받음
    private MockMvc mvc; // 웹 API를 테스트할 때 사용, 스프링 MVC 테스트의 시작점
    // 이 클래스 통해 HTTP GET,POST 등에 대한 API테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
        // 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언가능
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // mvc.perfo다rm의 결과 검증, HTTP Header의 Status를 검증
                // 200,400,500 등의 상태를 검증, 여기선 OK 즉, 200인지 아닌지 검증
                .andExpect(content().string(hello)); // mvc.perform의 결과를 검증, 응답 본문의 내용을 검증
        // Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증!!
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name",name) // API테스트할 때 사용될 요청 파라미터 설정 단, 값은 String만 허용!
                                          // 그래서 숫자/날짜 등 데이터 등록 시 문자열로 변경해야 가
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
                // jsonPath : json응답값을 필드별로 검증할 수 있는 메소드, $를 기준으로 필드명을 명시
    }


}
