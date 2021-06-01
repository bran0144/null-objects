package com.target;

import java.time.LocalDate;
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
    public void claimWarranty(Article article, DeviceStatus status, Optional<LocalDate> sensorFailureDate) {
        LocalDate today = LocalDate.now();

        StatusEqualityRule.match(
                    DeviceStatus.allFine(),
                    () -> this.claimMoneyBack(article, today))
                .applicableTo(status)
                .ifPresent(action -> action.apply());


        Runnable allFineAction = () ->
                this.claimMoneyBack(article, today);
        Runnable notOperationalAction = () ->
                this.claimMoneyBack(article, today);
        this.claimExpress(article, today);
    };
        } else if (status.equals(DeviceStatus.visiblyDamaged())) {
         }else  if (status.equals(DeviceStatus.sensorFailed())) {
            this.claimMoneyBack(article, today);
            this.claimExtended(article, today, sensorFailureDate);
        } else if (status.equals(DeviceStatus.notOperational().add(DeviceStatus.visiblyDamaged()))) {
            this.claimExpress(article, today);
        }else if (status.equals(DeviceStatus.notOperational().add(DeviceStatus.sensorFailed()))) {
            this.claimMoneyBack(article, today);
            this.claimExpress(article, today);
            this.claimExtended(article, today, sensorFailureDate);
        } else if (status.equals(DeviceStatus.visiblyDamaged().add(DeviceStatus.sensorFailed()))) {
            this.claimExtended(article, today, sensorFailureDate);
        } else {
            this.claimExpress(article, today);
            this.claimExtended(article, today, sensorFailureDate);

        }
    }
    private void claimExtended(Article article, LocalDate today, Optional<LocalDate> sensorFailureDate) {
        sensorFailureDate
                .flatMap(date -> article.getExtendedWarranty().filter(date))
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

        this.claimWarranty(item1, DeviceStatus.ALL_FINE, Optional.empty());
        this.claimWarranty(item1, DeviceStatus.VISIBLY_DAMAGED, Optional.empty());
        this.claimWarranty(item1, DeviceStatus.NOT_OPERATIONAL, Optional.empty());
        this.claimWarranty(item1, DeviceStatus.NOT_OPERATIONAL.add(DeviceStatus.VISIBLY_DAMAGED), Optional.empty());

        LocalDate sensorExamined = LocalDate.now().minus(2, ChronoUnit.DAYS);
        this.claimWarranty(item1, DeviceStatus.SENSOR_FAILED, Optional.of(sensorExamined));


    }
}
