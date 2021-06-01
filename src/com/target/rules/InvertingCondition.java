package com.target.rules;

import com.target.states.DeviceStatus;

import java.util.Optional;

public class InvertingCondition implements RootCondition<DeviceStatus> {
    private RootCondition<DeviceStatus> target;

    public InvertingCondition(RootCondition<DeviceStatus> target) {
        this.target = target;
    }
    @Override
    public Optional<DeviceStatus> applicableTo(DeviceStatus status) {
        return this.target.applicableTo(status)
                .map(s -> Optional.<DeviceStatus>empty())
                .orElse(Optional.of(status));
    }
}
