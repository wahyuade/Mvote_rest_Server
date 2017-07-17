package mvote.rest.model;

import javax.persistence.*;

/**
 * Created by wahyuade on 16/07/17.
 */
@Entity
@Table(name="suara")
public class SuaraModel {

    @Column(name = "idcalon")
    private String idCalon;

    @Id
    @Column(name = "uuid")
    private String uuid;

    public SuaraModel(String idCalon, String uuid) {
        this.idCalon = idCalon;
        this.uuid = uuid;
    }

    public SuaraModel() {
    }

    public String getIdCalon() {
        return idCalon;
    }

    public String getUuid() {
        return uuid;
    }
}
