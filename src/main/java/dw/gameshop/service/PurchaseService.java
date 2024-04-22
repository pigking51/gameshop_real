package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.exception.userPurchaseNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.repository.PurchaseRepository;
import dw.gameshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PurchaseService {

    PurchaseRepository purchaseRepository;

    UserRepository userRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }

    public Purchase savePurchase(Purchase purchase){
        // 구매확정 배로 직전, 현재시간을 저장함
        purchase.setPurchaseTime(LocalDateTime.now());
       return purchaseRepository.save(purchase);
    }
    public List<Purchase> getAllPurchase(){
        return purchaseRepository.findAll();
    }

    public List<Purchase> getPurchaseListByUser(String userId){
        // 유저아이디로 유저객체 찾기
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if(userOptional.isPresent()){
            return purchaseRepository.findByUser(userOptional.get());
        } else{
            throw new ResourceNotFoundException("User", "ID", userId);
        }
    }

    // 유저 이름으로 구매한 게임 찾기
//    public List<Purchase> getPurchaseGameListByUerId(User userId){
//        List<Purchase> purchaseOptional2 = purchaseRepository.findByUser(userId);
//
//
//    }

    // 유저 이름으로 구매한 게임 찾기(해답)
    public List<Purchase> getPurchaseListByUerName(String userName){
        // 유저 이름으로 유저 객체를 찾기
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if(userOptional.isEmpty()){
            throw new userPurchaseNotFoundException("User","Name", userName);
        }
        // ↓ 기존 int나 String 뿐만 아니라 객체로도 받을 수 있음
        return purchaseRepository.findByUser(userOptional.get());
    }


}
