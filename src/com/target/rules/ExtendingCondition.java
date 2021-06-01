package com.target.rules;

import com.target.states.DeviceStatus;
import java.util.Optional;

public interface ExtendingCondition <T1 extends DeviceStatus, T2 extends DeviceStatus> {
    Optional<T2> applicableTo(T1 status);
}
