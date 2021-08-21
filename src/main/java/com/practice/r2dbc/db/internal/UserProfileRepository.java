
package com.practice.r2dbc.db.internal;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserProfileRepository extends ReactiveCrudRepository<UserProfile, UUID> {

}
