package dw.gameshop.controller;

import dw.gameshop.model.Purchase;
import dw.gameshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {
    PurchaseService purchaseService;
    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/products/purchase")
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.savePurchase(purchase),
                HttpStatus.OK);
    }

    @GetMapping("/products/purchase")
    public ResponseEntity<List<Purchase>>  getAllPurchase(){
        return new ResponseEntity<>(purchaseService.getAllPurchase(),
                HttpStatus.OK);
    }

    @GetMapping("/products/purchase/{userId}")
    public ResponseEntity<List<Purchase>> getPurchaseListById(@PathVariable String userId){
        return new ResponseEntity<>(purchaseService.getPurchaseListByUser(userId),
                HttpStatus.OK);
    }

    @GetMapping("/products/purchase/name/{userName}")
    public ResponseEntity<List<Purchase>> getPurchaseListByUserName
            (@PathVariable String userName){
        return new ResponseEntity<>(purchaseService.getPurchaseListByUerName(userName),
                HttpStatus.OK);
    }
}
