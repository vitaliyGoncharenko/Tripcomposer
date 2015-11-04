package goncharenko.GVV.model;

import javax.persistence.*;

/**
 * Created by Vitaliy on 31.10.2015.
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class City extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String cityName;

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
