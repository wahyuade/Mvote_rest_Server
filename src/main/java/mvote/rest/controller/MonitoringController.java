package mvote.rest.controller;

import mvote.rest.database_service.DeviceService;
import mvote.rest.model.DeviceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by wahyuade on 28/07/17.
 */
@RestController
public class MonitoringController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/monitoring")
    public ArrayList<DeviceModel> monitoring(){
        return deviceService.findAll();
    }
}
