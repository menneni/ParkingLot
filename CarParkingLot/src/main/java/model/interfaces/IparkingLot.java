package model.interfaces;

import model.Slot;
import model.entity.Car;
import model.enums.Color;
import service.Booking;

import java.util.List;
import java.util.Map;

/**
 * Created by thej on 3/10/16.
 */
public interface IparkingLot {
    public boolean isFull();
    public Slot getNextAvailableSlot();
    public int getCapacity();
    public int setCapacity();
    public int createParkingLot(int capacity);
    public Slot parkVehicle(Car car);
    public Slot leaveSlot(int slotNo);
    public List<Booking> getStatus();
    public void printStatus();
    public List<Map<String, String>> getRegNosByColor(Color color);
    public List<Map<String, String>> getSlotNosByColor(Color color);
    public int getSlotNoByRegNo(String regNo);
}
