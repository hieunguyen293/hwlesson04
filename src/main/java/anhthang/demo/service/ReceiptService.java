package anhthang.demo.service;

import anhthang.demo.dto.ProductReqDTO;
import anhthang.demo.dto.ReceiptRequestDTO;
import anhthang.demo.repository.AccountRepository;
import anhthang.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import anhthang.demo.repository.ReceiptRepository;

import java.security.PublicKey;
import java.util.List;
import java.util.UUID;

@Service
public class ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductRepository productRepository;

    public Boolean addReceipt(ReceiptRequestDTO receiptRequestDTO){
        String email = "aaaaaaaaaa"; // phai sua lai de tim theo Email, neu khong co thi tao 1 account cho khach hang
        String accountID = accountRepository.getAccountByEmail(email).getAccountID();
        Integer total_money = 0;
        List<ProductReqDTO> listProductReqDTO = receiptRequestDTO.getReceiptRequestDTO();

        // Tinh tong tien
        for (ProductReqDTO productReqDTO : listProductReqDTO){
            total_money = total_money + productRepository.getProductById(productReqDTO.getProductID()).getPriceOut()*productReqDTO.getAmount();
        }

        String status = "chua thanh toan";
        String receiptID = UUID.randomUUID().toString();

        if (receiptRepository.addReceipt(receiptID, accountID,total_money,status)==0){
            return false;
        }

        for (ProductReqDTO productReqDTO : listProductReqDTO){
            productRepository.checkAmountOfProduct(productReqDTO.getProductID(), productReqDTO.getAmount());
            if (receiptRepository.addReceiptDetail(receiptID, productReqDTO.getProductID(), productReqDTO.getAmount())==0){
                return false;
            }
        }
        return true;
    }






}
