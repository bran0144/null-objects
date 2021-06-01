package com.target.rules;

import com.target.Action;
import com.target.states.DeviceStatus;

import java.util.Optional;

public interface ClaimingRule {
    Optional<Action> applicableTo(DeviceStatus status);

    default ClaimingRule orElse(ClaimingRule next) {
        return new ChainedRule(this, next);
    }
}
