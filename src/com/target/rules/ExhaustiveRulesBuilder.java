package com.target.rules;

import com.target.ClaimingRulesBuilder;
import com.target.states.DeviceStatus;
import com.target.states.OperationalStatus;
import com.target.states.SensorFailedStatus;

import java.util.function.Consumer;

public class ExhaustiveRulesBuilder implements ClaimingRulesBuilder {
    private Consumer<DeviceStatus> moneyBackAction = s -> {};
    private Consumer<DeviceStatus> expressAction = s -> {};
    private Consumer<SensorFailedStatus> extendedAction = s -> {};

    @Override
    public ClaimingRulesBuilder onMoneyBack(Consumer<DeviceStatus> action) {
        this.moneyBackAction = action;
        return this;
    }

    @Override
    public ClaimingRulesBuilder onClaimExpress(Consumer<DeviceStatus> action) {
        this.expressAction = action;
        return this;
    }
    @
    Override
    public ClaimingRulesBuilder onClaimExtended(Consumer<SensorFailedStatus> action) {
        this.extendedAction = action;
        return this;
    }
    @Override
    public ClaimingRule build() {
        return this.allFineRule()
                .orElse(this.notOperationalRule())
                .orElse(this.visiblyDamagedRule())
                .orElse(this.sensorFailedRule())
                .orElse(this.notOperationalDamagedRule())
                .orElse(this.notOperationalSensorFailedRule())
                .orElse(this.visiblyDamagedSensorFailedRule())
                .orElse(this.notOperationalDamagedSensorFailedRule());
    }
    private ClaimingRule allFineRule() {
        return State.matching(
                OperationalStatus.allFine()).applies(
                        this::moneyBack);
    }
    private ClaimingRule notOperationalRule() {
        return State.matching(
                OperationalStatus.notOperational()).applies(
                        s -> {
                            this.moneyBack(s);
                            this.express(s);
                        });
    }
    private ClaimingRule visiblyDamagedRule() {
        return State.matching(
                OperationalStatus.visiblyDamaged()).applies(
                        this::doNothing);
    }
    private ClaimingRule sensorFailedRule() {
        return State.matching(
                OperationalStatus.sensorFailed(),
                SensorFailedStatus.class).applies(
                        s -> {
                            this.moneyBack(s);
                            this.extended(s);
                        });
    }
    private ClaimingRule notOperationalDamagedRule() {
        return State.matching(
                OperationalStatus.notOperational().andVisiblyDamaged()).applies(
                        this::express);
    }
    private ClaimingRule notOperationalSensorFailedRule() {
        return State.matching(
                OperationalStatus.notOperational().andSensorFailed(), SensorFailedStatus.class).applies(
                        s -> {
                            this.moneyBack(s);
                            this.express(s);
                            this.extended(s);
                        });
    }
    private ClaimingRule visiblyDamagedSensorFailedRule() {
        return State.matching(
                OperationalStatus.visiblyDamaged().andSensorFailed(), SensorFailedStatus.class).applies(
                        this::extended);
    }
    private ClaimingRule notOperationalDamagedSensorFailedRule() {
        return State.matching(
                OperationalStatus.notOperational().andVisiblyDamaged().andSensorFailed(), SensorFailedStatus.class).applies(
                s -> {
                    this.express(s);
                    this.extended(s);
                });
    }
    private void moneyBack(DeviceStatus status) {
        this.moneyBackAction.accept(status);
    }
    private void express(DeviceStatus status) {
        this.expressAction.accept(status);
    }
    private void extended(SensorFailedStatus status) {
        this.extendedAction.accept(status);
    }
    private void doNothing(DeviceStatus status) {}
}
