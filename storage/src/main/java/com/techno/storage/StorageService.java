// StorageService.java
package com.techno.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String saveFile(MultipartFile file, FileCategory fileCategory);
    Resource getFileAsResource(String fileUrl);
}
