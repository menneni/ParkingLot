package service;

import model.Slot;
import model.entity.Car;
import model.enums.BookingStatus;
import model.enums.TransactionType;
import java.util.Date;

/**
 * Created by thej on 3/10/16.
 */
public class Booking implements Ibooking {
    long bookingId;
    BookingStatus status;
    TransactionType type;
    Date bookingTime;
    String vehicleRegNo;
    int slotNo;

    public Booking() {
    }

    public Booking(BookingStatus status, TransactionType type, Date bookingTime, String vehicleRegNo, int slotNo) {
        this.bookingId = System.currentTimeMillis();
        this.status = status;
        this.type = type;
        this.bookingTime = bookingTime;
        this.vehicleRegNo = vehicleRegNo;
        this.slotNo = slotNo;
    }

    public Booking bookSlot(Car car, Slot slot) {
        return new Booking(BookingStatus.booked, TransactionType.entry,
                new Date(), car.getRegNo(), slot.getSlotNo());
    }



    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public void setVehicleRegNo(String vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Booking leaveSlot(long bookingNo) {
        return null;
    }
}
