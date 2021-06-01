package com.target;

import com.target.rules.ClaimingRule;
import com.target.states.DeviceStatus;
import com.target.states.OperationalStatus;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

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

   private void claimWarranty(Article article, OperationalStatus status) {
        LocalDate today = LocalDate.now();

    }

    private void claimExtended(Article article, LocalDate today, LocalDate sensorFailureDate) {
        article.getExtendedWarranty().filter(sensorFailureDate)
                .ifPresent(warranty -> warranty.on(today).claim(this::offerSensorRepair));
    }
    private void claimExpress(Article article, LocalDate today) {
        article.getExpressWarranty().on(today).claim(this::offerRepair);
    }
    private void claimMoneyBack(Article article, LocalDate today) {
        article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);
    }

    public void run() {
        LocalDate sellingDate = LocalDate.now().minus(40, ChronoUnit.DAYS);
        Warranty moneyback1 = new TimeLimitedWarranty(sellingDate, Duration.ofDays(60));
        Warranty warranty1 = new TimeLimitedWarranty(sellingDate, Duration.ofDays(365));

        Part sensor = new Part(sellingDate);
        Warranty sensorWarranty = new TimeLimitedWarranty(sellingDate, Duration.ofDays(90));
        Article item1 = new Article(moneyback1, warranty1).install(sensor, sensorWarranty);

        this.claimWarranty(item1, OperationalStatus.allFine(), Optional.empty());
        this.claimWarranty(item1, OperationalStatus.visiblyDamaged(), Optional.empty());
        this.claimWarranty(item1, OperationalStatus.notOperational(), Optional.empty());
        this.claimWarranty(item1, OperationalStatus.notOperational().andVisiblyDamaged(), Optional.empty());

        LocalDate sensorExamined = LocalDate.now().minus(2, ChronoUnit.DAYS);
        this.claimWarranty(item1, OperationalStatus.sensorFailed(), Optional.of(sensorExamined));
        this.claimWarranty(item1, OperationalStatus.notOperational().andSensorFailed(), Optional.of(sensorExamined));


    }
}
