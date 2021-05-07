package com.target;

import java.time.LocalDate;

public class VoidWarranty implements Warranty{
    @Override
    public boolean isValidOn(LocalDate date) {
        return false;
    }
}
