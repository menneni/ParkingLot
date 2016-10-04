import model.Slot;
import model.entity.Car;
import service.CarParkingLot;
import model.entity.Command;
import model.enums.Color;
import model.interfaces.IparkingLot;
import service.Booking;
import service.Ibooking;

import java.io.*;

/**
 * Created by thej on 3/10/16.
 */
public class Solution {

    static IparkingLot carParkingLot = new CarParkingLot();
    Ibooking ibooking = new Booking();

    public static void main(String[] args) throws FileNotFoundException {

        BufferedReader reader = null;
        File inFile = null;
        if (args.length == 0){
            reader = new BufferedReader(new InputStreamReader(System.in));
        }
        else{
            inFile = new File(args[0]);
            try {
                reader = new BufferedReader(new FileReader(inFile));
            } catch (FileNotFoundException e) {
                System.out.println("File not found exception"+e);
            }
        }


        String ip = null;
        int capacity = 0;


        while (true){
            try {
                ip = reader.readLine().trim();
                args = ip.split(" ");

                switch (Enum.valueOf(Command.class, args[0])){
                    case create_parking_lot:
                        if(args.length == 2){
                            capacity = Integer.valueOf(args[1]);
                            carParkingLot.createParkingLot(capacity);
                            System.out.println("Created a parking lot with "+capacity+"  slots");
                        }
                        else{
                            System.out.println("Invalid command, Missing capacity");
                        }
                        break;

                    case park:
                        if (args.length  == 3){
                            Slot slot = checkAndAllocateSlot(args);
                            if(slot != null){
                                System.out.println("Allocated slot number: "+slot.getSlotNo());
                            }
                            else {
                                System.out.println("Sorry, parking lot is full");
                            }
                        }
                        else{
                            System.out.println("Invalid details, Missing registration number or color");
                        }
                        break;

                    case leave:
                        if (args.length == 2){
                            Slot slot = retainSlot(args);
                            if (slot != null){
                                System.out.println("Slot number "+ slot.getSlotNo() +" is free");
                            }
                        }
                        else{
                            System.out.println("Invalid command, Missing slot no.");
                        }
                        break;
                    case status:
                        if (args.length == 1){
                            carParkingLot.printStatus();
                        }
                        else{
                            System.out.println("Invalid command, Do you mean 'status' ? ");
                        }
                        break;
                    case registration_numbers_for_cars_with_colour:
                        if (args.length == 2){
                            carParkingLot.getRegNosByColor(Enum.valueOf(Color.class, args[1]));
                        }
                        else{
                            System.out.println("Invalid command, Missing colour");
                        }
                        break;
                    case slot_numbers_for_cars_with_colour:
                        if (args.length == 2){
                            carParkingLot.getSlotNosByColor(Enum.valueOf(Color.class, args[1]));
                        }
                        else{
                            System.out.println("Invalid command, Missing color");
                        }
                        break;
                    case slot_number_for_registration_number:
                        if (args.length == 2){
                            carParkingLot.getSlotNoByRegNo(args[1]);
                        }
                        else{
                            System.out.println("Invalid command, Missing registration number");
                        }
                        break;
                    case exit:
                        System.exit(0);
                    default:
                        System.out.println("Invalid command, please try again !!");

                }
            } catch (IOException e) {
                System.out.println("Invalid input, please try again !!");
            }
        }
    }

    private static Slot retainSlot(String[] args) {
        String slotNo = args[1];
        Slot slot = carParkingLot.leaveSlot(Integer.parseInt(slotNo));
        return slot;
    }

    private static Slot checkAndAllocateSlot(String[] args) {
        String regNo = null;
        String color;
        regNo = args[1];
        color = args[2];
        Car car = new Car(regNo, color);
        Slot slot = carParkingLot.parkVehicle(car);
        return slot;
    }
}
