package com.target;

import java.time.LocalDate;

public class VoidWarranty implements Warranty{

    @Override
    public void claim(Runnable action) {}

    @Override
    public Warranty on(LocalDate date) {
        return this;
    }

}
