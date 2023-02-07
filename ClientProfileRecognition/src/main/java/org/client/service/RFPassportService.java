package org.client.service;

import org.client.Entity.RFPassport;

public interface RFPassportService {
    void FillSeriesAndNumber(RFPassport rfPassport, String stringFromScanForSeriesAndNumber);
    void FillGender(RFPassport rfPassport, String stringFromScanForRemainder);
    void FillReceiptDocDateBirthdateDepartmentDoc(RFPassport rfPassport, String stringFromScanForRemainder);
    void FillDivision(RFPassport rfPassport, String stringFromScanForRemainder);
    void FillSNP(RFPassport rfPassport, String stringFromScanForRemainder);
}
