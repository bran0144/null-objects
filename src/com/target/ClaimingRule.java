package com.target;

import java.util.Optional;

public interface ClaimingRule {
    Optional<Action> applicableTo(DeviceStatus status);
}
