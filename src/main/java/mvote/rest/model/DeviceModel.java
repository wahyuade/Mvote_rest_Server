package mvote.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wahyuade on 26/07/17.
 */
@Entity
@Table(name = "device")
public class DeviceModel {
    @Id
    private String uuid;

    @Column(name = "status")
    private String status;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public DeviceModel(String uuid, String status, Double latitude, Double longitude) {
        this.uuid = uuid;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public DeviceModel() {
    }
}
