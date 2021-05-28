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
    public void claimWarranty(Article article, DeviceStatus status) {
        LocalDate today = LocalDate.now();

        switch (status) {
            case ALL_FINE:
                article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);
                break;
            case NOT_OPERATIONAL:
                article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);
                article.getExpressWarranty().on(today).claim(this::offerRepair);
                break;
            case VISIBLY_DAMAGED:
                break;
            case SENSOR_FAILED:
                article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);
                article.getExtendedWarranty().on(today).claim(this::offerSensorRepair);
                break;
            case NOT_OPERATIONAL_DAMAGED:
                article.getExpressWarranty().on(today).claim(this::offerRepair);
                break;
            case NOT_OPERATIONAL_SENSOR_FAILED:
                article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);
                article.getExpressWarranty().on(today).claim(this::offerRepair);
                article.getExtendedWarranty().on(today).claim(this::offerSensorRepair);
                break;
            case DAMAGED_SENSOR_FAILED:
                article.getExtendedWarranty().on(today).claim(this::offerSensorRepair);
                break;
            case NOT_OPERATIONAL_DAMAGED_SENSOR_FAILED:
                article.getExpressWarranty().on(today).claim(this::offerRepair);
                article.getExtendedWarranty().on(today).claim(this::offerSensorRepair);
                break;
//    if (status == DeviceStatus.NOT_OPERATIONAL) {
//        article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);
//        article.getExpressWarranty().on(today).claim(this::offerRepair);
//    }else if (status == DeviceStatus.VISIBLY_DAMAGED) {
//    }else if (status == DeviceStatus.SENSOR_FAILED){
//        article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);
//        article.getExtendedWarranty().on(today).claim(this::offerSensorRepair);
//    }else {
//        article.getMoneyBackGuarantee().on(today).claim(this::offerMoneyBack);
//    }
        }
    }

    public void run() {
        LocalDate sellingDate = LocalDate.now().minus(40, ChronoUnit.DAYS);
        Warranty moneyback1 = new TimeLimitedWarranty(sellingDate, Duration.ofDays(60));
        Warranty warranty1 = new TimeLimitedWarranty(sellingDate, Duration.ofDays(365));

        Part sensor = new Part(sellingDate);
        Warranty sensorWarranty = new TimeLimitedWarranty(sellingDate, Duration.ofDays(90));
        Article item1 = new Article(moneyback1, warranty1).install(sensor, sensorWarranty);

        this.claimWarranty(item1, DeviceStatus.ALL_FINE);
        this.claimWarranty(item1, DeviceStatus.VISIBLY_DAMAGED);
        this.claimWarranty(item1, DeviceStatus.NOT_OPERATIONAL);
        this.claimWarranty(item1, DeviceStatus.SENSOR_FAILED);


    }
}
