package org.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.client.common.dto.WalletDto;
import org.client.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService){this.walletService = walletService;}

    @PostMapping("/create")
    @Operation(summary = "Создание нового кошелька и его привязка к пользователю по icp пользователя")
    public void createAddress(@RequestBody WalletDto dto) {
        walletService.addWalletForClient(dto.getEuroWallet(), dto.getRubWallet(), dto.getUsdWallet(),
                dto.getIndividualIcp());
    }

    @GetMapping("/getAll")
    @Operation(summary = "находим все кошельки")
    public ResponseEntity<List<WalletDto>> getAllWallets() {

        return new ResponseEntity<>(walletService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getWalletByClientIcp/{icp}")
    @Operation(summary = "Информация о кошельке по icp клиента")
    public ResponseEntity<List<WalletDto>> getWalletByClientIcp(@Parameter(description = "icp") String Icp,
                                                              @PathVariable(value="icp") String icp) {
        return new ResponseEntity<>(walletService.getWalletByIcp(icp), HttpStatus.OK);
    }

    @PutMapping("/edit")
    @Operation(summary = "редактирование кошелька по его uuid")
    public ResponseEntity<Void> editWalletByUuid(@RequestBody WalletDto dto) {

        walletService.editWallet(dto.getUuid(),dto.getIndividualUuid(), dto.getRubWallet(), dto.getEuroWallet(), dto.getUsdWallet());
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/delete")  //post запрос с walletuuid в  теле
    @Operation(summary = "удаление кошелька по walletuuid")
    public ResponseEntity<Void> deleteWalletByUuid(@RequestBody WalletDto dto) {

        walletService.deleteWallet(dto.getUuid());
        return ResponseEntity.status(200).build();
    }

}
