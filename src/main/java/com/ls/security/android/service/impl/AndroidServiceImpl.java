package com.ls.security.android.service.impl;


import com.ls.security.android.service.AndroidService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class AndroidServiceImpl implements AndroidService {
    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:9000")
            .build();

    @Override
    public ResponseEntity<byte[]> requestPdfFromIntraServer(Integer digitalApprovalId) {
        try {
            ResponseEntity<byte[]> response = webClient.get()
                    .uri("/api/v1/lighting_solutions/digital/approval/getPdfForSecureServer/{digitalApprovalId}", digitalApprovalId)
                    .retrieve()
                    .toEntity(byte[].class)
                    .block();

            // pdf bytes 반환
            byte[] pdfBytes = response.getBody();

            // 파일 저장 기능
            if (pdfBytes != null) {
                // Save the PDF to the resources/test directory
                String fileName = "signed" + digitalApprovalId + ".pdf";
                Path targetPath = Paths.get("src/main/resources/test/" + fileName);

                // Ensure the directories exist
                Files.createDirectories(targetPath.getParent());

                // Write the PDF bytes to the file
                Files.write(targetPath, pdfBytes);

                // Log the saved file path and contents for debugging
                System.out.println("Saved PDF file to: " + targetPath.toAbsolutePath());

                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                        .body(pdfBytes);
            } else {
                System.err.println("Failed to retrieve PDF bytes: response body is null");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (IOException e) {
            System.err.println("Error occurred while saving the PDF file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
