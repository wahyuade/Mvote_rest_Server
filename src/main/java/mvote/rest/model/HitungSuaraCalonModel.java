package mvote.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wahyuade on 16/07/17.
 */
public class HitungSuaraCalonModel {
    private String id;
    private String nama;
    private String foto;
    private Integer suara;

    public HitungSuaraCalonModel(String id, String nama, String foto) {
        this.id = id;
        this.nama = nama;
        this.foto = foto;
        this.suara = 0;
    }

    public HitungSuaraCalonModel() {
    }

    public void tambahSuara() {
        this.suara++;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getFoto() {
        return foto;
    }

    public Integer getSuara() {
        return suara;
    }
}
