package org.client.service.impl;

import lombok.AllArgsConstructor;
import org.client.common.dto.AddressDto;
import org.client.common.dto.IndividualDto;
import org.client.common.dto.WalletDto;
import org.client.common.entity.Address;
import org.client.common.entity.Individual;
import org.client.common.entity.Wallet;
import org.client.repo.AddressRepo;
import org.client.repo.IndividualRepo;
import org.client.repo.WalletRepo;
import org.client.service.AddressService;
import org.client.service.IndividualService;
import org.client.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final IndividualService individualService;

    @Autowired
    WalletRepo walletRepo;

    @Autowired
    IndividualRepo individualRepo;

    public WalletServiceImpl(IndividualService individualService) {
        this.individualService = individualService;
    }

    @Override // создать новый кошелек
    public void addWalletForClient(String euroWallet, String rubWallet, String usdWallet, String individualIcp) {
        Individual individual = individualRepo.findIndividualByIcp(individualIcp).orElse(new Individual()); // чтобы найти uuid клиента по его icp

        walletRepo.insertToWalletTable(UUID.randomUUID().toString(), euroWallet, rubWallet, usdWallet, individual.getUuid());
    }

    @Override //получить все кошельки
    public List<WalletDto> getAll(){
        List<Wallet> walletList= walletRepo.findAll();
        List<WalletDto> walletDtoList = new ArrayList<>();

        //для каждого элемента walletList создадим объект типа WalletDto, и присвоим ему значения из элемента walletList.
        // Потом  - поместим этот объект в лист walletDtoList
        for(Wallet w: walletList){
            WalletDto walletDto = WalletDto.builder().uuid(w.getUuid()).individualIcp(walletRepo.findClientByWalletId(w.getUuid()).getIcp()).
            rubWallet(w.getRubWallet()).euroWallet(w.getEuroWallet()).usdWallet(w.getUsdWallet()).build();
            walletDtoList.add(walletDto);
        }
        return walletDtoList;
    }

    @Override //найти кошельки клиента по его icp
    public List<WalletDto> getWalletByIcp(String icp) {
        List<Wallet> walletList= walletRepo.findByIcp(icp);
        List<WalletDto> walletDtoList = new ArrayList<>();

        //для каждого элемента walletList создадим объект типа WalletDto, и присвоим ему значения из элемента walletList.
        // Потом  - поместим этот объект в лист walletDtoList
        for(Wallet w: walletList){
            WalletDto walletDto = WalletDto.builder().uuid(w.getUuid()).individualIcp(walletRepo.findClientByWalletId(w.getUuid()).getIcp()).
                    rubWallet(w.getRubWallet()).euroWallet(w.getEuroWallet()).usdWallet(w.getUsdWallet()).build();
            walletDtoList.add(walletDto);
        }
        return walletDtoList;
    }

    @Transactional
    @Override  // редактировать wallet.
    public void editWallet(String uuid,String individualUuid, String rubWallet, String euroWallet, String usdWallet) {

        Wallet editWallet = Wallet.builder().uuid(uuid).rubWallet(rubWallet).euroWallet(euroWallet).usdWallet(usdWallet).build();
        editWallet.setIndividual(walletRepo.findClientByWalletId(uuid)); // добавляем отдельно, так как поле individual приватное
        walletRepo.save(editWallet);
    }

    @Override //удалить кошелек по uuid
    public void deleteWallet(String uuid) {

        walletRepo.deleteById(uuid);
    }


}
