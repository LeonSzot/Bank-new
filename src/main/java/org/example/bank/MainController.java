package org.example.bank;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class MainController {
    @PostMapping("/api/payments")
    public int payment(@RequestBody Payment payment) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        db.connect();

        //TODO
        //logika endpointa

        return 1;
    }
}
