package org.client.dto.shortIndividual;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RFPassportShortDto {
    @Schema(example = "11 04", description = "Серия паспорта, первые 4 цифры")
    private String series;
    @Schema(example = "123456", description = "6-ти значный номер паспорта")
    private String number;
    @Schema(example = "123-123", description = "Код подразделения согласно паспорту")
    private String division;


}
