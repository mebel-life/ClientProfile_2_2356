package org.client.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.client.Entity.RFPassport;
import org.client.dto.DocumentDto;
import org.client.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import util.DataInfoHandler;

import java.io.*;
import java.util.Arrays;

@RestController
@RequestMapping("/document")
@AllArgsConstructor

public class DocumentController {

    private DocumentService documentService;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping()
    public ResponseEntity<?> createDocumentDto2(@Parameter String documentType, @RequestParam("scan") MultipartFile file) throws TesseractException, IOException {
        DocumentDto docDto = documentService.createDocumentDto(documentType, file);
        return ResponseEntity.ok(docDto);
    }

    @PutMapping("/{icp}")
    public ResponseEntity<DataInfoHandler> updateProduct(@PathVariable("icp") String icp, @RequestBody RFPassport rfPassport) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<RFPassport> entity = new HttpEntity<RFPassport>(rfPassport, headers);
        restTemplate.exchange(
                "http://localhost:8080/documents/" + icp, HttpMethod.PUT, entity, String.class).getBody();

        return new ResponseEntity<>(new DataInfoHandler("Passport was added to individual"), HttpStatus.OK);
    }

}
