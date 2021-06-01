package com.target.rules;

import com.target.states.DeviceStatus;

import java.util.Optional;

public class StatusTypeCondition <T extends DeviceStatus> implements RootCondition<T>,
        ExtendingCondition<DeviceStatus, T>{
    private Class<T> statusType;

    public StatusTypeCondition(Class<T> statusType) {
        this.statusType = statusType;
    }

    @Override
    public Optional<T> applicableTo(DeviceStatus status) {
        return this.statusType.isAssignableFrom(status.getClass())
                ? Optional.of((T)status)
                : Optional.empty();
    }
}
