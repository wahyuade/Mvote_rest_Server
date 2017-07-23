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
    private String id;

    @Column(name = "nrp")
    private String nrp;

    @Column(name = "token")
    private String token;

    @Column(name = "v")
    private String v;

    @Column(name = "n")
    private String n;

    @Column(name = "x")
    private String x;

    @Column(name = "waktu_login")
    private Long waktuLogin;

    public Long getWaktuLogin() {
        return waktuLogin;
    }

    public UserModel() {
    }

    public String getNrp() {
        return nrp;
    }

    public String getToken() {
        return token;
    }

    public String getV() {
        return v;
    }

    public String getN() {
        return n;
    }

    public String getId() {
        return id;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getX() {
        return x;
    }

    public void setWaktuLogin(Long waktuLogin) {
        this.waktuLogin = waktuLogin;
    }
}
