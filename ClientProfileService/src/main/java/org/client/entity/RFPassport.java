package org.client.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

/**
 * RF passport entity
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rfPassports")
public class RFPassport {
    @Id
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор клиента по стандарту RFC4122")
    private UUID uuid;
    @Schema(example = "11 04", description = "Серия паспорта, первые 4 цифры")
    private String series;
    @Schema(example = "123456", description = "6-ти значный номер паспорта")
    private String number;
    private String issued;
    private String departmentDoc;
    private String receiptDocDate;
    private String validateDateDoc;
    @Schema(example = "Иван", description = "Имя согласно паспорту")
    private String name;
    @Schema(example = "Иванов", description = "Фамилия согласно паспорту")
    private String surname;
    @Schema(example = "Иванович", description = "Отчество согласно паспорту")
    private String patronymic;
    @Schema(example = "Муж.", description = "Пол согласно паспорту")
    private String gender;
    @Schema(example = "12.12.2000", description = "Дата рождения согласно паспорту")
    private String birthdate;
    @Schema(example = "Москва", description = "Место рождения согласно паспорту")
    private String birthplace;
    @Schema(example = "Отделом внутренних дел", description = "Кем выдан согласно паспорту")
    private String issuedBy;
    @Schema(example = "123-123", description = "Код подразделения согласно паспорту")
    private String division;
    private String invalidityReason;
    private String message;
    private String legalForce;
    private String passportStatus;

    @OneToOne(mappedBy = "rfPassport")
    private Individual individual;
}