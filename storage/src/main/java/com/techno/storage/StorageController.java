package com.techno.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/storage")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class StorageController {

    private final FileStorageService storageService;

    @GetMapping("/files/{filePath:.+}")

    public ResponseEntity<byte[]> getFile(@PathVariable String filePath) {
        try {
            Resource resource = storageService.getFileAsResource(filePath);

            // Read the file content into a byte array
            byte[] fileContent = Files.readAllBytes(resource.getFile().toPath());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(fileContent);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Failed to read file content: " + e.getMessage()).getBytes(StandardCharsets.UTF_8));
        }
    }

    @PostMapping("/files")
    public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile file,
                                           @RequestParam("fileCategory") FileCategory fileCategory) {
        try {
            // Log the parameters
            System.out.println("Received fileCategory: " + fileCategory);
            if (file!=null){
                String  filePath = storageService.saveFile(file, fileCategory);
                return ResponseEntity.ok(filePath);
            }
            else{
                return ResponseEntity.status(404).body("Failed to save file: ");
            }
        } catch (FileStorageException e) {
            return ResponseEntity.status(404).body("Failed to save file: " + e.getMessage());
        }
    }
    @PostMapping("/document-files")
    public ResponseEntity<Map<String, String>> saveFiles(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("fileCategories") List<FileCategory> fileCategories
    ) {
        try {
            Map<String, String> fileCategoryPathMap = new HashMap<>();

            // Assuming both files and fileCategories lists have the same size
            for (int i = 0; i < files.size(); i++) {
                MultipartFile file = files.get(i);
                if (file != null)
                {
                    FileCategory fileCategory = fileCategories.get(i);
                    String filePath = storageService.saveFile(file, fileCategory);
                    String originalFilename = file.getOriginalFilename();
                    if (originalFilename != null) {
                        int lastDotIndex = originalFilename.lastIndexOf('.');
                        if (lastDotIndex != -1) {
                            originalFilename = originalFilename.substring(0, lastDotIndex);
                        }
                    }
                    fileCategoryPathMap.put(originalFilename, filePath);
                }
            }
            return ResponseEntity.ok(fileCategoryPathMap);
        } catch (FileStorageException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyMap());
        }
    }
}
