package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 스프링 컨테이너가 인식하여 MemberController 객체를 생성하여 들고있음 = 스프링 빈 관리
public class MemberController {

    @GetMapping("member/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }

//    private final MemberService memberService = new MemberService();
//    이렇게 new 로 만들어 쓰면, -> MemberController 외 다른 컨트롤러에서도 MemberService 를 쓸 때, 다 각자 새로운 객체를 만들어서 씀, 여러 개 생성해서 쓸 필요 없이 하나를 만들어서 공유하면 됨

    // 2. 필드 주입 -> 스프링이 처음 뜰 때 등록되어 중간에 바꿔줄 수 있는 방법이 없기 때문에 비추
    // @Autowired private MemberService memberService;

    // 1. 생성자 주입 -> 스프링 뜰 때, 한 번에 조립되고 끝-!
    // 의존 관계가 실행 중에 동적으로 변하는 경우는 거의 없음 => 생성자 주입 권장
    private final MemberService memberService;

    @Autowired  // MemberController 를 만들 때, 스프링 컨테이너가 MemberService 를 가져다가 연결해줌 = 의존성 주입(DI)
    public MemberController(MemberService memberService) {
        // memberService 에 오류 표시 = MemberController 생성을 위해 MemberService 를 찾는데, 스프링 컨테이너에 등록되어있지 않아서
        // MemberService 파일에 @Service 어노테이션을 붙여서 스프링 컨테이너가 인식할 수 있도록 해야함
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());
        // memberService = class hello.hellospring.service.MemberService$$EnhancerBySpringCGLIB$$8a3b881b -> CGLIB, 프록시를 만들어서 AOP 실행 후 진짜 memberService 실행
    }

    // 3. setter 주입 -> 어디선가 MemberController 를 호출했을 때, setter 로 의존성 주입해주어야 하기 때문에 setter 가 public 으로 열려있어야 함. 중간에 값이 잘못 바뀌면 문제가 생김
    // memberService. 했을 때 아무 개발자나 setMemberService 를 실행 시킬 수 있는 상황, 호출하지 않아도 될 메서드는 실행되지 않도록 막아야 함! 안전하지 않음
//    private MemberService memberService;
//
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
}
