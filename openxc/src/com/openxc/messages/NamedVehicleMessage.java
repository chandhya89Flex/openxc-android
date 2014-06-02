package com.openxc.messages;

import java.util.Map;

import android.os.Parcel;

import com.google.common.base.Objects;

import com.openxc.measurements.UnrecognizedMeasurementTypeException;

public class NamedVehicleMessage extends VehicleMessage {

    private String mName;

    public NamedVehicleMessage(String name) {
        this(name, null);
    }

    public NamedVehicleMessage(Map<String, Object> values) {
        super(values);
        mName = (String) getValuesMap().remove(NAME_KEY);
    }

    public NamedVehicleMessage(String name, Map<String, Object> values) {
        super(values);
        mName = name;
    }

    public NamedVehicleMessage(Long timestamp, String name, Map<String, Object> values) {
        super(timestamp, values);
        mName = name;
    }

    public String getName() {
        return mName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !super.equals(obj)) {
            return false;
        }

        final NamedVehicleMessage other = (NamedVehicleMessage) obj;
        return super.equals(other) && mName.equals(other.mName);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("timestamp", getTimestamp())
            .add("name", getName())
            .add("values", getValuesMap())
            .toString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeString(getName());
    }

    @Override
    protected void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        mName = in.readString();
    }

    protected NamedVehicleMessage(Parcel in)
            throws UnrecognizedMeasurementTypeException {
        this();
        readFromParcel(in);
    }

    protected NamedVehicleMessage() { }
}