package com.target;

import java.time.LocalDate;
import java.util.Optional;

public class Article {
    private Warranty moneyBackGuarantee;
    private Warranty expressWarranty;
    private Warranty effectiveExpressWarranty;
    private Optional<Part> sensor;
    private Warranty extendedWarranty;

    public Article(Warranty moneyBackGuarantee, Warranty expressWarranty) {
        this(moneyBackGuarantee, expressWarranty, Warranty.VOID, Optional.empty(), Warranty.VOID);
    }
    private Article(
            Warranty moneyBackGuarantee,
            Warranty expressWarranty,
            Warranty effectiveExpressWarranty,
            Optional<Part> sensor, Warranty extendedWarranty) {
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
        return this.sensor.map(part -> part.apply(this.extendedWarranty)).orElse(Warranty.VOID);
    }
    public Article withVisibleDamage() {
        return new Article(Warranty.VOID, this.expressWarranty, this.effectiveExpressWarranty, this.sensor, this.extendedWarranty);
    }
    public Article nonOperational() {
        return new Article(this.moneyBackGuarantee, this.expressWarranty, this.effectiveExpressWarranty, this.sensor, this.extendedWarranty);
    }
    public Article install(Part sensor, Warranty extendedWarranty) {
        return new Article(this.moneyBackGuarantee, this.expressWarranty, this.effectiveExpressWarranty, Optional.of(sensor), extendedWarranty);
    }
    public Article sensorNotOperational(LocalDate detectedOn) {
        return this.sensor.map(part -> part.defective(detectedOn)).map(defective -> this.install(defective, this.extendedWarranty))
                .orElse(this);
    }
}
