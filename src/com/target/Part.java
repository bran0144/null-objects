package com.target;

import java.time.LocalDate;
import java.util.Optional;

public class Part {
    private LocalDate installmentDate;
    private Optional<LocalDate> defectDetectedOn;

    public Part(LocalDate installmentDate) {
        this(installmentDate, Optional.empty());
    }
    private Part(LocalDate installmentDate, Optional<LocalDate> defectDetectedOn) {
        this.installmentDate = installmentDate;
        this.defectDetectedOn = defectDetectedOn;
    }

    public Part defective(LocalDate detectedOn) {
        return new Part(this.installmentDate, Optional.of(detectedOn));
    }
    public Warranty apply(Warranty partWarranty) {
        return this.defectDetectedOn.flatMap(date -> partWarranty.filter(date).map(warranty -> Warranty.lifetime(date)))
                .orElse(Warranty.VOID);
    }
}
