package com.example.demo;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
