package com.target;

import java.util.Arrays;

public enum DeviceStatus {
    ALL_FINE,
    NOT_OPERATIONAL(1),
    VISIBLY_DAMAGED(2),
    SENSOR_FAILED(4),
    NOT_OPERATIONAL_DAMAGED(NOT_OPERATIONAL.id) | VISIBLY_DAMAGED.id),
    NOT_OPERATIONAL_SENSOR_FAILED(NOT_OPERATIONAL.id | SENSOR_FAILED.id),
    DAMAGED_SENSOR_FAILED(VISIBLY_DAMAGED.id | SENSOR_FAILED.id),
    NOT_OPERATIONAL_DAMAGED_SENSOR_FAILED(NOT_OPERATIONAL.id | VISIBLY_DAMAGED.id, SENSOR_FAILED.id);

    private final int id;
    private DeviceStatus (int id) {
        this.id = id;
    }

    public DeviceStatus add (DeviceStatus status) {
        return Arrays.stream(DeviceStatus.class.getEnumConstants())
                .filter(val -> val.id == (this.id | status.id))
                .findFirst()
                .get();
    }
}
