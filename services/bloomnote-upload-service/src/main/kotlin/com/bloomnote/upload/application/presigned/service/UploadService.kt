package com.bloomnote.upload.application.presigned.service

import com.bloomnote.upload.application.presigned.mapper.UploadApiMapper
import com.bloomnote.upload.application.presigned.usecase.GetUploadQuery
import com.bloomnote.upload.application.presigned.usecase.PresignedIssuedResult
import com.bloomnote.upload.application.presigned.usecase.UploadUseCase
import com.bloomnote.upload.config.properties.MinioProperties
import io.minio.GetPresignedObjectUrlArgs
import io.minio.MinioClient
import io.minio.http.Method
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UploadService(
    private val minioClient: MinioClient,
    private val minioProperties: MinioProperties
) : UploadUseCase {
    val EXPIRATION_TIME = 60 * 10

    override fun execute(getUploadQuery: GetUploadQuery): PresignedIssuedResult {
        val uuid = UUID.randomUUID().toString()
        val objectKey = "${getUploadQuery.uploadType.value}/$uuid.${getUploadQuery.ext}"

        val presignedUrl = minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.PUT)
                .bucket(minioProperties.bucketName)
                .`object`(objectKey)
                .expiry(EXPIRATION_TIME)
                .build()
        )

        return UploadApiMapper.toResult(
            presignedUrl = presignedUrl,
            objectKey = objectKey
        )
    }
}