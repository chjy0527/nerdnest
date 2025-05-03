package com.nn.nerdnest;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass// 부모 클래스의 필드가 자식 엔티티 테이블에 포함될 수 있도록 설정
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity { // 생성일, 수정일을 자동으로 관리하는 엔티티 클래스
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
