package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.exception.ParkingNotFoundException;
import one.digitalinnovation.cloudparking.model.Parking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    public List<Parking> findAll(){
        return new ArrayList<>(parkingMap.values());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public Parking findById(String id) {

        Parking parking = parkingMap.get(id);
        if (parking == null){
            throw new ParkingNotFoundException(id);
        }
        return parking;

    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parkingMap.replace(id, parking);
        return parking;

    }

    public Parking exit(String id) {
//        Parking parking = findById(id);
//        parking.setExitDate(LocalDateTime.now());
//        parking.setBill(parkingExit.getBill(parking));
//        return parkingRepository.save(parking);

        return null;
    }
}
