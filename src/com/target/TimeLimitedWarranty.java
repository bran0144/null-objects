package com.target;

import java.time.Duration;
import java.time.LocalDate;

public class TimeLimitedWarranty implements Warranty{
    private LocalDate dateIssued;
    private Duration validFor;

    public TimeLimitedWarranty(LocalDate dateIssued, Duration validFor) {
        this.dateIssued = dateIssued;
        this.validFor = validFor;
    }

    @Override
    public boolean isValidOn(LocalDate date) {
        return this.dateIssued.compareTo(date) <= 0 && this.getExpiredDate().compareTo(date) > 0;
    }
    private LocalDate getExpiredDate() {
        return this.dateIssued.plusDays(this.getValidForDays());
    }
    private long getValidForDays() {
        return this.validFor.toDays();
    }
}
