package com.bloomnote.jpa.base

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @CreatedDate
    @Column(nullable = false, columnDefinition = "datetime(3)")
    var createdDateTime: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "datetime(3)")
    var modifiedDateTime: LocalDateTime = LocalDateTime.now()
}