package com.target;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

public class TimeLimitedWarranty implements Warranty{
    private LocalDate dateIssued;
    private Duration validFor;

    public TimeLimitedWarranty(LocalDate dateIssued, Duration validFor) {
        this.dateIssued = dateIssued;
        this.validFor = validFor;
    }

    @Override
    public Warranty on(LocalDate date) {
        return date.compareTo(this.dateIssued) < 0 ? Warranty.VOID
                : date.compareTo(this.getExpiredDate()) > 0 ? Warranty.VOID
                : this;
    }
    @Override
    public Optional<Warranty> filter(LocalDate date) {
        return date.compareTo(this.dateIssued) >= 0 && date.compareTo(this.getExpiredDate()) <= 0
                ? Optional.of(this)
                : Optional.empty();
    }
    private LocalDate getExpiredDate() {
        return this.dateIssued.plusDays(this.getValidForDays());
    }
    private long getValidForDays() {
        return this.validFor.toDays();
    }
}
