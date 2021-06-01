package com.target;

import java.util.Optional;

public class StatusEqualityRule implements ClaimingRule, Action{
    private DeviceStatus pattern;
    private Runnable action;

    private StatusEqualityRule(DeviceStatus pattern, Runnable action) {
        this.pattern = pattern;
        this.action = action;
    }
    public static ClaimingRule match(DeviceStatus pattern, Runnable action) {
        return new StatusEqualityRule(pattern, action);
    }

    @Override
    public Optional<Action> applicableTo(DeviceStatus status) {
        return this.pattern.equals(status)
                ? Optional.of(this)
                : Optional.empty();
    }
    @Override
    public void apply() {
        this.action.run();
    }
}
