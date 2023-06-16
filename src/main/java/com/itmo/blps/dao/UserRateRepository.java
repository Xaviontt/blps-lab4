package com.itmo.blps.dao;

import com.itmo.blps.model.UserRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRateRepository extends JpaRepository<UserRate, String> {
    @Transactional
    @Modifying
    @Query("update UserRate u set u.remainingGrades = 100")
    void updateRemainingGradesBy100();
}
