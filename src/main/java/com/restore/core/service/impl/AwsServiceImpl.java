package com.restore.core.service.impl;

import com.restore.core.exception.RestoreSkillsException;
import com.restore.core.service.AppService;
import com.restore.core.service.AwsService;
import com.restore.core.config.TenantIdentifierResolver;
import com.restore.core.dto.app.enums.ReferenceType;
import com.restore.core.dto.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

@Service
@Slf4j
public class AwsServiceImpl extends AppService implements AwsService {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private TenantIdentifierResolver tenantIdentifierResolver;
    @Autowired
    private S3Presigner s3Presigner;

    private String uploadToS3AndGetKey(String bucketName, String avtarKey, byte[] decodedBytes) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(avtarKey)
                .build();
        PutObjectResponse response = s3Client.putObject(request, RequestBody.fromBytes(decodedBytes));
        if (response != null && response.sdkHttpResponse().isSuccessful()) {
            return avtarKey;
        }
        return null;
    }

    @Override
    public String uploadProviderGroupsProfilePhoto(String providerGroupName, String base64ProfilePhoto,
            String bucketName, String avatarKey) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64ProfilePhoto);
        String profilePhotoKey;
        if (Objects.nonNull(avatarKey)) {
            profilePhotoKey = avatarKey;
        } else {
            profilePhotoKey = "ProviderGroup/" + providerGroupName + "/avatar/" + generateUniqueKey();
        }
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(profilePhotoKey)
                .build();
        PutObjectResponse response = s3Client.putObject(request, RequestBody.fromBytes(decodedBytes));
        if (response != null && response.sdkHttpResponse().isSuccessful()) {
            return profilePhotoKey;
        }
        throw new IOException("Failed to upload profile image to S3");
    }

    @Override
    public Set<String> uploadFiles(MultipartFile[] multipartFiles, ReferenceType referenceType, UUID uuid) throws RestoreSkillsException, IOException {
        Set<String> keys = new HashSet<>();
        for(MultipartFile file: multipartFiles){
            if (!file.isEmpty()) {
                String key = buildPath(referenceType,uuid);
                PutObjectRequest request = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build();
                PutObjectResponse response = s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getInputStream().available()));
                if (response != null && response.sdkHttpResponse().isSuccessful()) {
                    keys.add(key);
                }else{
                    throw new IOException("Failed to upload profile image to S3");
                }
            }
        }
        return keys;
    }

    @Override
    public String getProviderGroupsProfilePhoto(String profilePhotoKey, String bucketName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(profilePhotoKey)
                .build();
        ResponseBytes<GetObjectResponse> responseBytes = s3Client.getObjectAsBytes(getObjectRequest);
        byte[] imageData = responseBytes.asByteArray();
        return Base64.getEncoder().encodeToString(imageData);
    }

    @Override
    public String uploadProviderProfilePhoto(UUID providerId, String base64ProfilePhoto, String avtarKey,
            String bucketName) throws IOException, RestoreSkillsException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64ProfilePhoto);
        if (avtarKey == null) {
            avtarKey = buildPath(ReferenceType.PROVIDER_PROFILE, providerId);
        }
        return uploadToS3AndGetKey(bucketName, avtarKey, decodedBytes);
    }

    @Override
    public String uploadAdminProfilePhoto(String base64ProfilePhoto, String avtarKey) throws RestoreSkillsException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64ProfilePhoto);
        if (avtarKey == null) {
            avtarKey = buildPath(ReferenceType.ADMIN_PROFILE, null);
        }
        return uploadToS3AndGetKey(bucketName, avtarKey, decodedBytes);
    }

    @Override
    public String uploadLocationProfile(String base64) throws IOException, RestoreSkillsException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        String avtarKey = buildPath(ReferenceType.LOCATION_PROFILE, null);
        return uploadToS3AndGetKey(bucketName, avtarKey, decodedBytes);
    }

    @Override
    public String uploadStaffProfile(String base64) throws RestoreSkillsException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        String avtarKey = buildPath(ReferenceType.STAFF_PROFILE, null);
        return uploadToS3AndGetKey(bucketName, avtarKey, decodedBytes);
    }

    @Override
    public String deleteProfilePhoto(String profilePhotoKey, String bucketName) {
        if (Objects.nonNull(profilePhotoKey)) {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(profilePhotoKey)
                    .build();
            DeleteObjectResponse response = s3Client.deleteObject(deleteObjectRequest);
            if (response != null && response.sdkHttpResponse().isSuccessful()) {
                return profilePhotoKey;
            }
        }
        return null;
    }

    @Override
    public String generateUniqueKey() {
        return UUID.randomUUID().toString();
    }

    public String buildPath(ReferenceType referenceType, UUID uuid) throws RestoreSkillsException {
        String contextProviderGroup = tenantIdentifierResolver.resolveCurrentTenantIdentifier();
        switch (referenceType) {
            case LOCATION_PROFILE -> {
                if (contextProviderGroup == null || contextProviderGroup.isEmpty()
                        || contextProviderGroup.toLowerCase().equals("public"))
                    throwError(ResponseCode.BAD_REQUEST,
                            "Invalid context! can not upload provider without Provider group");
                return "ProviderGroup/" + contextProviderGroup + "/location/profile/" + UUID.randomUUID().toString();
            }

            case PROVIDER_PROFILE -> {
                return "ProviderGroup/" + contextProviderGroup + "/provider/" + uuid + "/profile/"
                        + UUID.randomUUID().toString();
            }
            case ADMIN_PROFILE -> {
                return "Admin/" + contextProviderGroup + "/admin/" + uuid + "/profile/"
                        + UUID.randomUUID().toString();
            }
            case PATIENT_INSURANCE_DOCUMENTS -> {
                return "ProviderGroup/" + contextProviderGroup + "/patient/insurance/docs" + uuid
                        + UUID.randomUUID().toString();
            }
            case STAFF_PROFILE -> {
                return "ProviderGroup/" + contextProviderGroup + "/staff/profile" + UUID.randomUUID().toString();
            }
        }
        throwError(ResponseCode.BAD_REQUEST, "Invalid context! no reference type found");
        return null;
    }

    @Override
    public String getPreSignedUrl(String key) throws IOException {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(15))
                    .getObjectRequest(getObjectRequest)
                    .build();

            PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest);
            return presignedGetObjectRequest.url().toString();
        } catch (S3Exception e) {
            throw new IOException("Error retrieving object from S3: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IOException("An error occurred: " + e.getMessage(), e);
        }
    }
}
