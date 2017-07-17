package mvote.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wahyuade on 16/07/17.
 */
@Entity
@Table(name="calon")
public class CalonModel {
    @Id
    private String id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "visi")
    private String visi;

    @Column(name = "misi")
    private String misi;

    @Column(name = "foto")
    private String foto;

    public CalonModel(String id, String nama, String visi, String misi, String foto) {
        this.id = id;
        this.nama = nama;
        this.visi = visi;
        this.misi = misi;
        this.foto = foto;
    }

    public CalonModel() {
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getVisi() {
        return visi;
    }

    public String getMisi() {
        return misi;
    }

    public String getFoto() {
        return foto;
    }
}
