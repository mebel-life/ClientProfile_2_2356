package org.client.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.client.common.entity.Contacts.Email;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="wallet_uuid")
    private String uuid;

    private String rubWallet;

    private String euroWallet;

    private String usdWallet;

    //двусторонняя связь
    @ManyToOne(fetch = FetchType.LAZY)
    private Individual individual;

}
