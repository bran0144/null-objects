package com.target.rules;

import com.target.states.DeviceStatus;

import java.util.Optional;

public class AppendingCondition <T extends DeviceStatus, T1 extends DeviceStatus> implements RootCondition<T1>{
    private RootCondition<T> first;
    private ExtendingCondition<T, T1> second;

    public AppendingCondition(RootCondition<T> first, ExtendingCondition<T, T1> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Optional<T1> applicableTo(DeviceStatus status) {
        return this.first.applicableTo(status)
                .map(this.second::applicableTo)
                .orElse(Optional.empty());
    }
}
