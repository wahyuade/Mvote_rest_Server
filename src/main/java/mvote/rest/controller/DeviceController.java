package mvote.rest.controller;

import mvote.rest.database_service.DeviceService;
import mvote.rest.model.DeviceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by wahyuade on 26/07/17.
 */
@Controller
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @MessageMapping("/device_information")
    @SendTo("/server/data_voter")
    public DeviceModel greeting(DeviceModel deviceModel) throws Exception {
        DeviceModel voter = new DeviceModel(deviceModel.getUuid(), deviceModel.getStatus(), deviceModel.getLatitude(), deviceModel.getLongitude());
        deviceService.save(voter);
        return voter;
    }

}
