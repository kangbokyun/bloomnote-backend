package com.bloomnote.upload.infrastructure.album.entity

import com.bloomnote.jpa.base.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "album")
class Album(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val albumId: Long = 0,

    @Comment("업로드 OBJECT_KEY")
    val objectKey: String,

    @Comment("용량")
    val volume: Long,

    @Comment("메타데이터 생성 일자")
    val metaCreatedDateTime: LocalDateTime? = null,

    @Comment("유저 ID")
    val userId: Long,
) : BaseEntity() {
    override fun toString(): String {
        return "Album(albumId=$albumId, objectKey='$objectKey', volume=$volume, userId=$userId)"
    }
}