package org.client.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.client.dto.DocumentDto;
import org.client.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/document")
@AllArgsConstructor

public class DocumentController {

    private DocumentService documentService;

    @PostMapping()
    public ResponseEntity<?> createDocumentDto2(@Parameter String documentType, @RequestParam("scan") MultipartFile file) throws TesseractException, IOException {
        DocumentDto docDto = documentService.createDocumentDto(documentType, file);
        return ResponseEntity.ok(docDto);
    }

}
