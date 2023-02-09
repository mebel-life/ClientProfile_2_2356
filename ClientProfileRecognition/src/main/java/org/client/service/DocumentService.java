package org.client.service;
import net.sourceforge.tess4j.TesseractException;
import org.client.dto.DocumentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface DocumentService {
    DocumentDto createDocumentDto(String documentType, MultipartFile scan) throws TesseractException, IOException;
}
