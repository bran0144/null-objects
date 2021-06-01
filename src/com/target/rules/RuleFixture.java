package com.target.rules;

import com.target.states.DeviceStatus;
import com.target.Action;
import com.target.rules.ClaimingRule;
import com.target.states.OperationalStatus;

import java.util.Optional;
import java.util.function.Consumer;


public class RuleFixture<T extends DeviceStatus> implements ClaimingRule {
    private RootCondition<T> condition;
    private Consumer<T> action;

    public RuleFixture(RootCondition<T> condition, Consumer<T> action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public Optional<Action> applicableTo(DeviceStatus status) {
        return this.condition
                .applicableTo(status)
                .map(s -> new ReducedRule<>(s, this.action));
    }
}
