package mvote.rest.model;

/**
 * Created by wahyuade on 16/07/17.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserModel {
    @Id
    @Column(name = "nrp")
    private String nrp;

    @Column(name = "token")
    private String token;

    @Column(name = "global")
    private String global;

    @Column(name = "local")
    private String local;

    @Column(name = "statustoken")
    private String statusToken;

    @Column(name = "statuspilih")
    private String statusPilih;

    @Column(name = "waktulogin")
    private Long waktuLogin;

    public String getNrp() {
        return nrp;
    }

    public String getToken() {
        return token;
    }

    public String getGlobal() {
        return global;
    }

    public String getLocal() {
        return local;
    }

    public String getStatusToken() {
        return statusToken;
    }

    public String getStatusPilih() {
        return statusPilih;
    }

    public Long getWaktuLogin() {
        return waktuLogin;
    }

    public void setStatusToken(String statusToken) {
        this.statusToken = statusToken;
    }

    public void setStatusPilih(String statusPilih) {
        this.statusPilih = statusPilih;
    }

    public void setWaktuLogin(Long waktuLogin) {
        this.waktuLogin = waktuLogin;
    }
}
