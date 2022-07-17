package com.example.demo;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}

// sout 를 통해 member 의 name 값과 findMember 의 name 값을 비교할수도 있지만 테스트 코드를 작성하는것이 더 좋다.

//
//public class MemberServiceTest {
//
//    MemberService memberService = new MemberServiceImpl();
//
//    @Test
//    void join() {
//        Member member = new Member(1L, "memberA", Grade.VIP);
//
//        memberService.join(member);
//        Member findMember = memberService.findMember(1L);
//
//        Assertions.assertThat(member).isEqualTo(findMember);
//
//
//    }
//}
