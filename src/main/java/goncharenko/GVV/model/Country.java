package goncharenko.GVV.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Vitaliy on 31.10.2015.
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Country extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true, nullable = false)
    private String countryName;

    @Column(nullable = false)
    private String countryISOCode;

    @OneToMany
    private Set<City> cities;

    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryISOCode() {
        return countryISOCode;
    }

    public void setCountryISOCode(String countryISOCode) {
        this.countryISOCode = countryISOCode;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }
}
