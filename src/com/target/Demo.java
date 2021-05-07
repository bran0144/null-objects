package com.target;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
        LocalDate sellingDate = LocalDate.now().minus(40, ChronoUnit.DAYS);
        Warranty moneyback1 = new TimeLimitedWarranty(sellingDate, Duration.ofDays(30));
        Warranty warranty1 = new TimeLimitedWarranty(sellingDate, Duration.ofDays(365));

        Article item1 = new Article(moneyback1, warranty1);
        this.claimWarranty(item1);

        Article item2 = new Article(Warranty.VOID, Warranty.VOID);
        this.claimWarranty(item2);
    }
}
