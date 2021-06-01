package com.target;

import java.util.Optional;

public interface ClaimingRule {
    Optional<Action> applicableTo(DeviceStatus status);

    default ClaimingRule orElse(ClaimingRule next) {
        return new ChainedRule(this, next);
    }
}
