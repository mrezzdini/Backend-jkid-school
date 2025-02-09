package com.techno.storage;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FileStorageService implements StorageService {
    private ResourceLoader resourceLoader;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public FileStorageService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public String saveFile(MultipartFile file, FileCategory fileCategory) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        // Check if the file name is null or empty
        if ( fileName.isEmpty()) {
            throw new FileStorageException("Invalid file name");
        }
        // Append current time in milliseconds to the file name
        String timestamp = String.valueOf(System.currentTimeMillis());
        fileName = timestamp + "_" + fileName;

        Path filePath = getFilePath( fileCategory, fileName);

        try {
            // Check if the folder exists, and create it if not
            Path parentDir = filePath.getParent();
            if (!Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return formatFilePath(filePath.toString());
        } catch (IOException e) {
            throw new FileStorageException("Failed to store file " + fileName, e);
        }
    }

    @Override
    public Resource getFileAsResource(String fileUrl) {
        try {
            Path filePath = Paths.get(fileUrl).normalize();
            Resource resource = resourceLoader.getResource("file:" + filePath.toString());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new FileStorageException("Could not read file: " + fileUrl);
            }
        } catch (Exception ex) {
            throw new FileStorageException("Could not read file: " + fileUrl, ex);
        }
    }
    private Path getFilePath( FileCategory fileCategory, String fileName) {
        String folder = switch (fileCategory) {
            case PROFILES -> Paths.get("profiles").toString();
            case GALLERY -> Paths.get("gallery").toString();
            case PARENT_ACTIVITIES -> Paths.get("parentActivities").toString();
            case DOCUMENTS -> Paths.get("documents").toString();
            default -> throw new IllegalArgumentException("Unsupported file category");
        };
        return Paths.get(uploadDir, folder, fileName);
    }
    public static String formatFilePath(String inputPath) {
        // Replace backslashes with forward slashes
        String formattedPath = inputPath.replace("\\", "/");

        // Remove leading dot and slash if present
        formattedPath = formattedPath.replaceFirst("^\\./", "");
        formattedPath = "http://localhost:5001/api/v1/storage/files/"+formattedPath;
        return formattedPath;
    }
}
