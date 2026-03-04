import java.util.*;

class ParkingSpot {

    String licensePlate;
    long entryTime;

    ParkingSpot(String plate) {
        licensePlate = plate;
        entryTime = System.currentTimeMillis();
    }

}

public class ParkingLotSystem {

    static int SIZE = 500;

    static ParkingSpot[] table = new ParkingSpot[SIZE];

    // hash function
    static int hash(String plate) {
        return Math.abs(plate.hashCode()) % SIZE;
    }

    // park vehicle
    static void parkVehicle(String plate) {

        int index = hash(plate);
        int probes = 0;

        while (table[index] != null) {

            index = (index + 1) % SIZE;
            probes++;

        }

        table[index] = new ParkingSpot(plate);

        System.out.println("Vehicle " + plate +
                " parked at spot #" + index +
                " (" + probes + " probes)");

    }

    // exit vehicle
    static void exitVehicle(String plate) {

        int index = hash(plate);

        while (table[index] != null) {

            if (table[index].licensePlate.equals(plate)) {

                long duration =
                        (System.currentTimeMillis() - table[index].entryTime) / 1000;

                double fee = duration * 0.05;

                System.out.println("Vehicle " + plate +
                        " exited from spot #" + index +
                        " Duration: " + duration +
                        " sec Fee: $" + fee);

                table[index] = null;
                return;

            }

            index = (index + 1) % SIZE;

        }

        System.out.println("Vehicle not found");

    }

    public static void main(String[] args) {

        parkVehicle("ABC1234");
        parkVehicle("ABC1235");
        parkVehicle("XYZ9999");

        exitVehicle("ABC1234");

    }

}