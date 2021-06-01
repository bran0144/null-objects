package com.target.rules;

import com.target.states.DeviceStatus;
import com.target.states.OperationalStatus;

public class State {
    public static RootCondition<DeviceStatus> matching(OperationalStatus status) {
        return matching(status, DeviceStatus.class);
    }

    public static <T extends DeviceStatus> RootCondition<T> matching (
            OperationalStatus pattern, Class<T> stateType) {
        return new AppendingCondition<>(
                new StatusTypeCondition<>(stateType),
                new OperationalCondition<>(pattern)
        );
    }
}
