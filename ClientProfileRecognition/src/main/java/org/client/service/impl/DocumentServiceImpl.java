package org.client.service.impl;

import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.client.Entity.RFPassport;
import org.client.dto.DocumentDto;
import org.client.service.DocumentService;
import org.client.service.RFPassportService;
import org.hibernate.cfg.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@AllArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {

    static final String DATAPATH = "C:/proj/ClientProfileRecognition/src/main/resources/tessdata";
    static final String LANG = "rus";
    static private String stringFromScanForSeriesAndNumber;
    static private String stringFromScanForRemainder;

    private final RFPassportService rfPassportService;
    private final RFPassport rfPassport;

    public DocumentDto createDocumentDto(String documentType, MultipartFile scan) throws TesseractException, NullPointerException, IOException {

        Map<String, RFPassport> docDtoMap = new HashMap<>();


        stringFromScanForSeriesAndNumber = GetInfoFromScan(convert(scan), 5).toUpperCase(); // 5 - определяет серию и номер
        stringFromScanForRemainder = GetInfoFromScan(convert(scan), 4).toUpperCase();       // 4 - для остальной части документа

        FillRFPassport();

        docDtoMap.put(documentType, rfPassport);

        return new DocumentDto(docDtoMap);
    }

    private void FillRFPassport() {
        rfPassportService.FillSeriesAndNumber(rfPassport,stringFromScanForSeriesAndNumber);
        rfPassportService.FillGender(rfPassport,stringFromScanForRemainder);
        rfPassportService.FillReceiptDocDateBirthdateDepartmentDoc(rfPassport,stringFromScanForRemainder);
        rfPassportService.FillDivision(rfPassport,stringFromScanForRemainder);
        rfPassportService.FillSNP(rfPassport,stringFromScanForRemainder);
    }

    private String GetInfoFromScan(File image, int PageSegMode) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(DATAPATH);
        tesseract.setLanguage(LANG);
        tesseract.setPageSegMode(PageSegMode);
        return tesseract.doOCR(image);
    }
    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
