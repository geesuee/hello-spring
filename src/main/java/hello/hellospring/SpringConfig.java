package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 자바 코드로 직접 스프링 빈을 등록하는 방식
public class SpringConfig {

//    private DataSource dataSource;
//    private EntityManager em;
    private final MemberRepository memberRepository;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
    // 다형성으로 인터페이스 구현체를 바꿔 끼움, 스프링 컨테이너가 객체 지향 설계를 지원해줌
    // -> MemberService 어플리케이션 관련 코드 수정 없이, 설정 코드만 수정

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
