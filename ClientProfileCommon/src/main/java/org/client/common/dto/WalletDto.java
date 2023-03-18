package org.client.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.intellij.lang.annotations.Pattern;

@Schema(description = "Модель, описывающая баланс пользоватяле в ЛК банка")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletDto {

    public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    @Schema(example = "4800c301-50a5-46f9-8c5f-6d6b3fbc55nf", description = "Идентификатор кошелька по стандарту RFC4122")
    @Pattern(value = UUID_PATTERN)
    @JsonProperty(Fields.UUID)
    private String uuid;

    @Schema(example = "13356", description = "Идентификатор Клиентского профиля")
    @JsonProperty(Fields.INDIVIDUAL_ICP)
    private String individualIcp;

    @Schema(example = "30101810645250000416 ", description = "Рублевый счет клиента")
    @JsonProperty(Fields.RUB_WALLET)
    private String rubWallet;

    @Schema(example = "30101810645250000416123378 ", description = "Валютный счет клиента (евро)")
    @JsonProperty(Fields.EUR_WALLET)
    private String euroWallet;

    @Schema(example = "30101810645250000416123378 ", description = "Валютный счет клиента (доллар США)")
    @JsonProperty(Fields.USD_WALLET)
    private String usdWallet;

    public static class Fields {
        public static final String UUID = "uuid";

        public static final String RUB_WALLET = "rubWallet";

        public static final String EUR_WALLET = "eurWallet";

        public static final String USD_WALLET = "usdWallet";

        public static final String INDIVIDUAL_ICP = "icp";
    }
}
