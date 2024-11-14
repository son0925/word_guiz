package com.example.word.common.domain.user.db;

import com.example.word.common.domain.user.model.UserEntity;
import com.example.word.common.domain.user.model.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUserIdAndStatus(String id, UserStatus status);

}
