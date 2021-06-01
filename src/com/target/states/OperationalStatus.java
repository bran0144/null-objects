package com.target.states;

public class OperationalStatus {
    public static OperationalStatus allFine() { return new OperationalStatus(0);}
    public static OperationalStatus notOperational() {return new OperationalStatus(1);}
    public static OperationalStatus visiblyDamaged() {return new OperationalStatus(2);}
    public static OperationalStatus sensorFailed() {return new OperationalStatus(4);}

    private final int representation;

    private OperationalStatus(int represenation) {this.representation = represenation;}

    public OperationalStatus andNotOperational() {return this.add(notOperational());}
    public OperationalStatus andVisiblyDamaged() {return this.add(visiblyDamaged());}
    public OperationalStatus andSensorFailed() {return this.add(sensorFailed());}

    public OperationalStatus add(OperationalStatus status) {
        return new OperationalStatus(this.representation | status.representation);
    }
    private boolean isSupersetOf(OperationalStatus other) {
        return (this.representation & other.representation) == other.representation;
    }
    @Override

    public boolean equals(Object other) {
        return other instanceof OperationalStatus && this.equals((OperationalStatus) other);
    }
    private boolean equals(OperationalStatus other) {
        return this.representation == other.representation;
    }
    @Override
    public int hashCode() { return this.representation;}

    @Override
    public String toString() {
        String result = "";
        String separator = "";

        if (this.isSupersetOf(notOperational())) {
            result += separator + "Not operational";
            separator = " + ";
        }
        if (this.isSupersetOf(visiblyDamaged())) {
            result += separator + "Damaged";
            separator = " + ";
        }
        if (this.isSupersetOf(sensorFailed())){
            result += separator + "Sensfor failed";
            separator = " + ";
        }
        return result;
    }
}
