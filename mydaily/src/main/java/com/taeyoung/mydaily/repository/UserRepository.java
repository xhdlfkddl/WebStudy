package com.taeyoung.mydaily.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taeyoung.mydaily.entity.UserEntity;

// 해당 클래스를 Repository로 사용하겠다는 말
// @Component가 포함되어있어서 의존성을 외부에서 주입할 수 있음
@Repository
// <Entity, Id의 type>
public interface UserRepository extends JpaRepository<UserEntity, String> {
    
}
