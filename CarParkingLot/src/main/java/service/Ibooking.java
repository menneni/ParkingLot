package service;

import model.Slot;
import model.entity.Car;
import model.enums.Color;

/**
 * Created by thej on 3/10/16.
 */
public interface Ibooking {

    public Booking bookSlot(Car car, Slot slot);
    public Booking leaveSlot(long bookingNo);

}
