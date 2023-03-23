package org.client.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.client.common.dto.WalletDto;
import org.client.repo.IndividualRepo;
import org.hibernate.annotations.GenericGenerator;
import org.intellij.lang.annotations.Pattern;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Individual individual;

    private String rubWallet;

    private String euroWallet;

    private String dollarWallet;
}
