package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Entity클래스와 기본 Entity Repository는 함께 위치!(둘은 밀접한 관계)
// Entity클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없음
public interface PostRepository extends JpaRepository<Posts,Long> {
}
