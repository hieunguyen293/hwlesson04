package anhthang.demo.controller;

import anhthang.demo.dto.ReceiptRequestDTO;
import anhthang.demo.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    ReceiptService receiptService;

    @PostMapping("/add")
    public Boolean addReceipt(@RequestBody ReceiptRequestDTO receiptRequestDTO){
        return receiptService.addReceipt(receiptRequestDTO);
    }

}
