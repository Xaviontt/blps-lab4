package com.itmo.blps.dao;

import com.itmo.blps.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query("update User u set u.remainingGrades = 100")
    int updateRemainingGradesBy100();
    User findByEmail(String email);
    User findById(long id);

    Boolean existsUserByEmail(String email);
}
