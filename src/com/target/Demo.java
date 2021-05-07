package com.target;

import java.time.LocalDate;

public class Demo {
    public void claimWarranty(Article article) {
        LocalDate today = LocalDate.now();

        if (article.getMoneyBackGuarantee().isValidOn(today)) {
            System.out.println("Offer money back");
        }
        if (article.getExpressWarranty().isValidOn(today)) {
            System.out.println("Offer repair");
        }
    }

    public void run() {

    }
}
