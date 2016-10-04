package service;

import model.Slot;
import model.entity.Car;
import model.enums.Color;
import model.interfaces.IparkingLot;
import service.Booking;

import java.util.*;

/**
 * Created by thej on 3/10/16.
 */
public class CarParkingLot implements IparkingLot {
    private int capacity;
    private Slot[] slots;
    private Set<Car> cars;
    private int availableSlots;
    private Map<String, Set<Car>> regNosByColorMap;
    private Map<String, Slot> slotsByRegNoMap;
    private Map<Integer, Car> carsBySlotNoMap;


    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public boolean isFull() {
        return availableSlots == 0;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Map<String, Set<Car>> getRegNosByColorMap() {
        return regNosByColorMap;
    }

    public void setRegNosByColorMap(Map<String, Set<Car>> regNosByColorMap) {
        this.regNosByColorMap = regNosByColorMap;
    }

    public Map<String, Slot> getSlotsByRegNoMap() {
        return slotsByRegNoMap;
    }

    public void setSlotsByRegNoMap(Map<String, Slot> slotsByRegNoMap) {
        this.slotsByRegNoMap = slotsByRegNoMap;
    }

    public Map<Integer, Car> getCarsBySlotNoMap() {
        return carsBySlotNoMap;
    }

    public void setCarsBySlotNoMap(Map<Integer, Car> carsBySlotNoMap) {
        this.carsBySlotNoMap = carsBySlotNoMap;
    }

    public Slot getNextAvailableSlot() {
        if(!isFull()){
            for (int i = 1; i <= capacity; i++) {
                if (!slots[i].isOccupied()){
                    return slots[i];
                }
            }
        }
        return null;
    }

    public int getCapacity() {
        return capacity;
    }

    public int setCapacity() {
        return 0;
    }

    public int createParkingLot(int capacity) {
        init(capacity);
        return capacity;
    }

    private void init(int capacity) {
        slots = new Slot[capacity+1];
        for (int i = 0, len = slots.length; i < len; i++){
            Slot unoccupiedSlot = new Slot(i, false);
            slots[i] = unoccupiedSlot;
        }
        cars = new HashSet<Car>(capacity);
        this.capacity = capacity;
        regNosByColorMap = new HashMap<String, Set<Car>>();
        slotsByRegNoMap = new HashMap<String, Slot>();
        carsBySlotNoMap = new HashMap<Integer, Car>();
        this.availableSlots = capacity;
    }

    public Slot parkVehicle(Car car) {
        Set<Car> availableCars = null;
        List<Slot> availableSlotsList = null;

        Slot slot = getNextAvailableSlot();
        if(slot != null){

            slot.setIsOccupied(true);
            availableSlots--;

            // add car to available list of cars
            // put car into map by color
            if(this.regNosByColorMap.isEmpty() || this.regNosByColorMap.get(car.getColor()) == null){
                availableCars = new HashSet<Car>();
            }
            else {
                availableCars = this.regNosByColorMap.get(car.getColor());

            }
            availableCars.add(car);
            this.regNosByColorMap.put(car.getColor(), availableCars);

            this.slotsByRegNoMap.put(car.getRegNo(), slot);
            this.carsBySlotNoMap.put(slot.getSlotNo(), car);
        }

        return slot;
    }

    public Slot leaveSlot(int slotNo) {
        Slot oldSlot = slots[slotNo];
        if(slotNo < capacity && oldSlot.isOccupied()){
            Car car = carsBySlotNoMap.get(slotNo);
            carsBySlotNoMap.remove(slotNo);
            slotsByRegNoMap.remove(car.getRegNo());
            Set<Car> carsByColor = regNosByColorMap.get(car.getColor());
            carsByColor.remove(car.getRegNo());
            availableSlots++;
            return markSlotAsAvailable(slotNo);
        }
        return null;
    }

    private Slot markSlotAsAvailable(int slotNo) {
        Slot oldSlot = slots[slotNo];
        oldSlot.setIsOccupied(false);
        return oldSlot;
    }

    public List<Booking> getStatus() {
        for (Map.Entry<String, Slot> entry : slotsByRegNoMap.entrySet()){
            System.out.println(entry.getValue().getSlotNo()+"/t"
                    +entry.getKey()+"/t"+carsBySlotNoMap.get(entry.getKey()).getColor());
        }
        return null;
    }

    public void printStatus() {
        System.out.println("Slot No."+"\t"+"Registration No"+"\t"+"Colour");

        for (int i = 1; i <= capacity ; i++) {
            if(this.slots[i].isOccupied()){
                System.out.println(i+"\t"+this.carsBySlotNoMap.get(i).getRegNo()+"\t"
                        +this.carsBySlotNoMap.get(i).getColor());
            }
        }
    }

    public List<Map<String, String>> getRegNosByColor(Color color) {
        if(color != null){
            String separator = "";
            Set<Car> cars = this.regNosByColorMap.get(color.name());
            if(cars != null){
                for (Car car: cars){
                    System.out.print(separator+car.getRegNo());
                    separator = ", ";
                }
                System.out.println();
            }
            else{
                System.out.println("Not found");
            }
        }
        return null;
    }

    public List<Map<String, String>> getSlotNosByColor(Color color) {
        if(color != null){
            String separator = "";
            Set<Car> cars = this.regNosByColorMap.get(color.name());
            if (cars != null) {
                for (Car car: cars){
                    if(car != null && this.slotsByRegNoMap.get(car.getRegNo()) != null){
                        System.out.print(separator + this.slotsByRegNoMap.get(car.getRegNo()).getSlotNo());
                        separator = ", ";
                    }
                }
                System.out.println();
            }
            else{
                System.out.println("Not found");
            }
        }
        return null;
    }

    public int getSlotNoByRegNo(String regNo) {
        if(regNo != null && this.slotsByRegNoMap.get(regNo)!= null){
            System.out.println(this.slotsByRegNoMap.get(regNo).getSlotNo());
            return this.slotsByRegNoMap.get(regNo).getSlotNo();
        }
        else{
            System.out.println("Not found");
        }
        return -1;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }
}
