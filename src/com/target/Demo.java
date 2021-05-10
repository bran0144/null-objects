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
    public void offerSensorRepair() {
        System.out.println("Offer sensor replacement.");
    }
    public void claimWarranty(Article article) {
        LocalDate today = LocalDate.now();
        article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);
        article.getExpressWarranty().on(today).claim(this::offerRepair);
        article.getExtendedWarranty().on(today).claim(this::offerSensorRepair);
    }

    public void run() {
        LocalDate sellingDate = LocalDate.now().minus(40, ChronoUnit.DAYS);
        Warranty moneyback1 = new TimeLimitedWarranty(sellingDate, Duration.ofDays(60));
        Warranty warranty1 = new TimeLimitedWarranty(sellingDate, Duration.ofDays(365));

        Part sensor = new Part(sellingDate);
        Warranty sensorWarranty = new TimeLimitedWarranty(sellingDate, Duration.ofDays(90));
        Article item1 = new Article(moneyback1, warranty1).install(sensor, sensorWarranty);

        this.claimWarranty(item1);
        this.claimWarranty(item1.withVisibleDamage());
        this.claimWarranty(item1.nonOperational().withVisibleDamage());
        this.claimWarranty(item1.nonOperational());

        LocalDate sensorExamined = LocalDate.now().minus(2, ChronoUnit.DAYS);
        this.claimWarranty(item1.sensorNotOperational(sensorExamined));
        this.claimWarranty(item1.nonOperational().sensorNotOperational(sensorExamined));

    }
}
