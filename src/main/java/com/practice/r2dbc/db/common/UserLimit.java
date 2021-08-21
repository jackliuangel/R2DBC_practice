//package com.practice.r2dbc.db.common;
//
//
//
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Version;
//import org.springframework.data.relational.core.mapping.Table;
//
//import java.util.UUID;
//
//
//@Table("public.user_limit")
//public class UserLimit {
//    @Override
//    public String toString() {
//        return "UserLimit{" +
//                "id=" + id +
//                ", userProfileId=" + userProfileId +
//                ", limitYear=" + limitYear +
//                ", transactionLimit=" + transactionLimit +
//                ", transactionCount=" + transactionCount +
//                ", version=" + version +
//                '}';
//    }
//
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public UUID getUserProfileId() {
//        return userProfileId;
//    }
//
//    public void setUserProfileId(UUID userProfileId) {
//        this.userProfileId = userProfileId;
//    }
//
//    public int getLimitYear() {
//        return limitYear;
//    }
//
//    public void setLimitYear(int limitYear) {
//        this.limitYear = limitYear;
//    }
//
//    public int getTransactionLimit() {
//        return transactionLimit;
//    }
//
//    public void setTransactionLimit(int transactionLimit) {
//        this.transactionLimit = transactionLimit;
//    }
//
//    public int getTransactionCount() {
//        return transactionCount;
//    }
//
//    public void setTransactionCount(int transactionCount) {
//        this.transactionCount = transactionCount;
//    }
//
//    public int getVersion() {
//        return version;
//    }
//
//    public void setVersion(int version) {
//        this.version = version;
//    }
//
//    @Id
//    private UUID id;
//
//    private UUID userProfileId;
//
//    private int limitYear;
//
//    private int transactionLimit;
//
//    private int transactionCount;
//
//    @Version
//    private int version;
//}