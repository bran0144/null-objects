package com.target;

import com.target.rules.ClaimingRule;
import com.target.states.DeviceStatus;
import com.target.states.SensorFailedStatus;

import java.util.function.Consumer;

public interface ClaimingRulesBuilder {
    ClaimingRulesBuilder onMoneyBack(Consumer<DeviceStatus> action);
    ClaimingRulesBuilder onClaimExpress(Consumer<DeviceStatus> action);
    ClaimingRulesBuilder onClaimExtended(Consumer<SensorFailedStatus> action);
    ClaimingRule build();
}
