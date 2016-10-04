import model.Slot;
import model.entity.Car;
import org.junit.Test;
import service.CarParkingLot;
import static org.junit.Assert.*;

/**
 * Created by thej on 4/10/16.
 */
public class TestParkingLot {

    int capacity = 3;
    CarParkingLot carParkingLot = new CarParkingLot();
    Car car;
    Slot slot;

    @Test
    public void testCreateParkingLot(){
        carParkingLot.createParkingLot(capacity);
        assertEquals(capacity, carParkingLot.getAvailableSlots());
    }

    @Test
    public void testParkVehicle(){
        carParkingLot.createParkingLot(capacity);
        car = new Car("KA­01­HH­7777", "Red");
        carParkingLot.parkVehicle(car);
        assertEquals(capacity-1, carParkingLot.getAvailableSlots());
    }

    @Test
    public void testLeaveSlot(){
        carParkingLot.createParkingLot(capacity);
        car = new Car("KA­01­HH­7777", "Red");
        Slot parkedSlot = carParkingLot.parkVehicle(car);

        // success case
        assertEquals(parkedSlot.getSlotNo(), 1);
        assertEquals(carParkingLot.getCapacity(), carParkingLot.getAvailableSlots()+1);

        Slot slot = carParkingLot.leaveSlot(carParkingLot.getSlotNoByRegNo(car.getRegNo()));
        assertEquals(slot.getSlotNo(), 1);
        assertEquals(carParkingLot.getCapacity(), carParkingLot.getAvailableSlots());

        // failure case
        assertEquals(carParkingLot.getSlotNoByRegNo(car.getRegNo()), -1);
    }

}
