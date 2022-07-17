package com.example.demo;

import org.springframework.stereotype.Repository;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
