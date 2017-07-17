package mvote.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wahyuade on 16/07/17.
 */
@Entity
@Table(name = "admin")
public class AdminModel {
    @Id
    private String id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "statusLogin")
    private String statusLogin;

    public AdminModel() {
    }

    public String getStatusLogin() {
        return statusLogin;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatusLogin(String statusLogin) {
        this.statusLogin = statusLogin;
    }

    public AdminModel(String id, String nama, String username, String password, String statusLogin) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.statusLogin = statusLogin;
    }
}
