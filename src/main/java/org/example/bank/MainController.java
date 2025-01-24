package org.example.bank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class MainController {
    DatabaseConnection db;

    public MainController(DatabaseConnection db) {
        this.db = db;
    }

    @PostMapping("/api/payments")
    public int payment(@RequestBody Payment payment){
        if(db.searchCard(payment)){
            LocalDate date = LocalDate.parse(payment.getExpirationDate());
            LocalDate currentDate = LocalDate.now();
            if (currentDate.isBefore(date)){
                if (payment.getAmount() <= db.getMoney(payment) && payment.getAmount() <= db.getLimit(payment)){
                    db.updateMoney(payment);
                    System.out.println("[Bank] Dokonano płatności.");
                    return 200;
                }
                else {
                    System.out.println("[Bank] Nie masz pieniędzy lub przekroczyłeś limit.");
                }
            }
            else {
                System.out.println("[Bank] Upłynął termin ważności karty.");
            }
        }
        else{
            System.out.println("[Bank] Nie znaleziono karty.");
        }

        return 400;
    }
}
