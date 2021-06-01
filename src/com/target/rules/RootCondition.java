package com.target.rules;

import com.target.states.DeviceStatus;
import com.target.states.OperationalStatus;
import com.target.rules.ClaimingRule;
import java.util.Optional;
import java.util.function.Consumer;

public interface RootCondition<T extends DeviceStatus> {
    Optional<T> applicableTo(DeviceStatus status);

    default ClaimingRule applies(Consumer<T> action) {
        return new RuleFixture<> (this, action);
    }
}
