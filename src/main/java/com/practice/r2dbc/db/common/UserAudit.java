package com.practice.r2dbc.db.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAudit {

    String userName;

    //    @CreationTimestamp vs @CreatedDate
    //    不用hibernate的audit，用spring data
    @CreatedDate
    private LocalDateTime lastUpdated; //it is created and then should not be updated again


    private String action;

    @Id
    private Long uuid;

    @Column
    String comments;

}
