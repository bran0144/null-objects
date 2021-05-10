package com.target;

import java.time.LocalDate;
import java.util.Optional;

public class VoidWarranty implements Warranty{

    @Override
    public Warranty on(LocalDate date) {
        return this;
    }
    @Override
    public Optional<Warranty> filter(LocalDate date) { return Optional.empty();}

    @Override
    public void claim(Runnable action) {}
}
