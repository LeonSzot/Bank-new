package org.example.bank;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class MainController {
    DatabaseConnection db;

    public MainController(DatabaseConnection db) {
        this.db = db;
    }

    @PostMapping("/api/payments")
    public boolean payment(@RequestBody Payment payment){


        if(db.searchCard(payment)){
            return true;
        }else{
            return false;
        }
    }
}
