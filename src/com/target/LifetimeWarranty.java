package com.target;

import java.time.LocalDate;
import java.util.Optional;

public class LifetimeWarranty implements Warranty{
    private LocalDate issuedOn;

    public LifetimeWarranty(LocalDate issuedOn) {
        this.issuedOn = issuedOn;
    }

    @Override
    public Warranty on(LocalDate date) {
        return date.compareTo(this.issuedOn) < 0 ? Warranty.VOID : this;
    }
    @Override
    public Optional<Warranty> filter(LocalDate date) {
        return date.compareTo(this.issuedOn) >= 0 ? Optional.of(this) : Optional.empty();
    }
}
