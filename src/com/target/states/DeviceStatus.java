package com.target.states;

import java.time.LocalDate;
import java.util.Optional;

public class DeviceStatus {
    private OperationalStatus state;

    protected DeviceStatus(OperationalStatus state) { this.state = state;}

    public static DeviceStatus allFine() {
        return new DeviceStatus(OperationalStatus.allFine());
    }
    public static DeviceStatus notOperational() {
        return new DeviceStatus(OperationalStatus.notOperational());
    }
    public static DeviceStatus visiblyDamaged() {
        return new DeviceStatus(OperationalStatus.visiblyDamaged());
    }
    public static DeviceStatus sensorFailed(LocalDate detectedOn) {
        return new SensorFailedStatus(detectedOn, OperationalStatus.allFine());
    }

    public DeviceStatus andNotOperational() {
        return new DeviceStatus(this.state.andNotOperational());
    }
    public DeviceStatus andVisiblyDamaged() {
        return new DeviceStatus(this.state.andVisiblyDamaged());
    }
    public DeviceStatus andSensorFailed(LocalDate detectedOn) {
        return new SensorFailedStatus(detectedOn, this.state);
    }

   public Optional<DeviceStatus> matches(OperationalStatus pattern) {
        return this.state.equals(pattern)
                ? Optional.of(this)
                : Optional.empty();
    }

    public Optional<DeviceStatus> isSupersetOf(OperationalStatus pattern) {
        return this.state.isSupersetOf(pattern)
                ? Optional.of(this)
                : Optional.empty();
    }
    @Override
    public String toString() {return this.state.toString();}
}
