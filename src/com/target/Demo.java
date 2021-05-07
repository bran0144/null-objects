package com.target;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Demo {
    private void offerMoneyBack() {
        System.out.println("Offer money back.");
    }
    public void offerRepair() {
        System.out.println("Offer repair.");
    }
    public void claimWarranty(Article article) {
        LocalDate today = LocalDate.now();

        article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);
        article.getExpressWarranty().on(today).claim(this::offerRepair);
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
