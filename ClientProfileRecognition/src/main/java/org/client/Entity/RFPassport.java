package org.client.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RFPassport {

    private String series;
    private String number;
    private String issued;
    private String departmentDoc;
    private String receiptDocDate;
    private String validateDateDoc;
    private String name;
    private String patronymic;
    private String surname;
    private String gender;
    private String birthdate;
    private String birthplace;
    private String issuedBy;
    private String division;
    private String invalidityReason;
    private String message;
    private String legalForce;
    private String passportStatus;
}
