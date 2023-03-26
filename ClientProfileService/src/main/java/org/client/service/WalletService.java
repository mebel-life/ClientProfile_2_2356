package org.client.service;

import org.client.common.dto.AddressDto;
import org.client.common.dto.IndividualDto;
import org.client.common.dto.WalletDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletService {

    void addWalletForClient(String euroWallet, String rubWallet, String usdWallet, String individualIcp);

    List<WalletDto> getAll();

    List<WalletDto> getWalletByIcp(String icp);

    void editWallet(String uuid, String individualUuid, String rubWallet, String euroWallet, String usdWallet);

    void deleteWallet(String uuid);
}
