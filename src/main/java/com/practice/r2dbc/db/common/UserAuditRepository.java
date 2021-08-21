package com.practice.r2dbc.db.common;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuditRepository extends ReactiveCrudRepository<UserAudit, Long> {
    //    UserAudit findByUserName(String userName);
    List<UserAudit> findAllByUserName(String userName);
}
