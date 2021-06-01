package com.target.rules;

import com.target.states.DeviceStatus;
import com.target.states.OperationalStatus;

import java.util.Optional;

public class OperationalCondition<T extends DeviceStatus> implements ExtendingCondition<T, T>{
    private OperationalStatus pattern;
    public OperationalCondition(OperationalStatus pattern) {
        this.pattern = pattern;
    }

    @Override
    public Optional<T> applicableTo(T status) {
        return status.matches(this.pattern).map(s -> status);
    }
}
