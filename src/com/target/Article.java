package com.target;

import java.time.LocalDate;

public class Article {
    private Warranty moneyBackGuarantee;
    private Warranty expressWarranty;
    private Warranty effectiveExpressWarranty = Warranty.VOID;
    private Part sensor;
    private Warranty extendedWarranty;

    public Article(Warranty moneyBackGuarantee, Warranty expressWarranty) {
        this(moneyBackGuarantee, expressWarranty, Warranty.VOID, null, Warranty.VOID);
    }
    private Article(
            Warranty moneyBackGuarantee,
            Warranty expressWarranty,
            Warranty effectiveExpressWarranty,
            Part sensor, Warranty extendedWarranty) {
        this.moneyBackGuarantee = moneyBackGuarantee;
        this.expressWarranty = expressWarranty;
        this.effectiveExpressWarranty = effectiveExpressWarranty;
        this.sensor = sensor;
    }

    public Warranty getExpressWarranty() {
        return effectiveExpressWarranty;
    }
    public Warranty getMoneyBackGuarantee() {
        return moneyBackGuarantee;
    }
    public Warranty getExtendedWarranty() {
        if (sensor == null) return Warranty.VOID;
        LocalDate detectedOn = this.sensor.getDefectDetectedOn();
        if (detectedOn == null) return Warranty.VOID;
        return this.extendedWarranty.on(detectedOn);
    }
    public Article withVisibleDamage() {
        return new Article(Warranty.VOID, this.expressWarranty, this.effectiveExpressWarranty, this.sensor, this.extendedWarranty);
    }
    public Article nonOperational() {
        return new Article(this.moneyBackGuarantee, this.expressWarranty, this.effectiveExpressWarranty, this.sensor, this.extendedWarranty);
    }
    public Article install(Part sensor, Warranty extendedWarranty) {
        return new Article(this.moneyBackGuarantee, this.expressWarranty, this.effectiveExpressWarranty, sensor, extendedWarranty);
    }
    public Article sensorNotOperational(LocalDate detectedOn) {
        return this.install(this.sensor.defective(detectedOn), this.extendedWarranty);
    }
}
