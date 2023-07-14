package com.restore.core.service;

import com.restore.core.exception.RestoreSkillsException;
import com.restore.core.dto.app.enums.ReferenceType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public interface AwsService {
    String uploadProviderGroupsProfilePhoto(String providerGroupName, String base64ProfilePhoto, String bucketName,
            String avatarKey) throws IOException;

    String getProviderGroupsProfilePhoto(String profilePhotoKey, String bucketName);

    String uploadProviderProfilePhoto(UUID providerId, String base64ProfilePhoto, String avtarKey, String bucketName)
            throws IOException, RestoreSkillsException;

    String uploadLocationProfile(String base64) throws IOException, RestoreSkillsException;

    String deleteProfilePhoto(String profilePhotoKey, String bucketName);

    String generateUniqueKey();

    String getPreSignedUrl(String Key) throws IOException;

    String uploadAdminProfilePhoto(String base64ProfilePhoto, String avtarKey) throws RestoreSkillsException;
    Set<String> uploadFiles(MultipartFile[] multipartFiles, ReferenceType referenceType, UUID uuid) throws RestoreSkillsException, IOException;

    String uploadStaffProfile(String avatar) throws RestoreSkillsException;
}
