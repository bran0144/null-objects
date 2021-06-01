package com.target;

import java.util.Arrays;
import java.util.stream.Stream;

public class DeviceStatus {
    public static final DeviceStatus ALL_FINE = new DeviceStatus(0);
    public static final DeviceStatus NOT_OPERATIONAL = new DeviceStatus(1);
    public static final DeviceStatus VISIBLY_DAMAGED = new DeviceStatus(2);
    public static final DeviceStatus SENSOR_FAILED= new DeviceStatus(4);
    public static final DeviceStatus NOT_OPERATIONAL_DAMAGED = combine(NOT_OPERATIONAL,  VISIBLY_DAMAGED);
    public static final DeviceStatus NOT_OPERATIONAL_SENSOR_FAILED = combine(NOT_OPERATIONAL, SENSOR_FAILED);
    public static final DeviceStatus DAMAGED_SENSOR_FAILED = combine(VISIBLY_DAMAGED, SENSOR_FAILED);
    public static final DeviceStatus NOT_OPERATIONAL_DAMAGED_SENSOR_FAILED = combine(NOT_OPERATIONAL, VISIBLY_DAMAGED, SENSOR_FAILED);

    private static DeviceStatus combine(DeviceStatus...statuses) {
        return new DeviceStatus(Arrays.stream(statuses)
        .mapToInt(status -> status.id)
        .reduce(0, (a,b) -> a | b));
    }
    private final int id;
    private DeviceStatus (int id) {
        this.id = id;
    }

    public DeviceStatus add (DeviceStatus status) {
        return Stream.of(
                    ALL_FINE, NOT_OPERATIONAL, VISIBLY_DAMAGED, SENSOR_FAILED,
                    NOT_OPERATIONAL_DAMAGED, NOT_OPERATIONAL_SENSOR_FAILED,
                    DAMAGED_SENSOR_FAILED, NOT_OPERATIONAL_DAMAGED_SENSOR_FAILED)
                .filter(val -> val.id == (this.id | status.id))
                .findFirst()
                .get();
    }
}
