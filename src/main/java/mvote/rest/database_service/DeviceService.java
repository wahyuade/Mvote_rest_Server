package mvote.rest.database_service;

import mvote.rest.model.DeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

/**
 * Created by wahyuade on 27/07/17.
 */
public interface DeviceService extends JpaRepository<DeviceModel, Long> {
    ArrayList<DeviceModel> findAll();
}
