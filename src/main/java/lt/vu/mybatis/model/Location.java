package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class Location {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.LOCATION.ID
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.LOCATION.LATITUDE
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    private BigDecimal latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.LOCATION.LONGITUDE
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    private BigDecimal longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.LOCATION.NAME
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.LOCATION.STREET
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    private String street;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.LOCATION.CITY_ID
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    private Integer cityId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.LOCATION.ID
     *
     * @return the value of PUBLIC.LOCATION.ID
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.LOCATION.ID
     *
     * @param id the value for PUBLIC.LOCATION.ID
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.LOCATION.LATITUDE
     *
     * @return the value of PUBLIC.LOCATION.LATITUDE
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.LOCATION.LATITUDE
     *
     * @param latitude the value for PUBLIC.LOCATION.LATITUDE
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.LOCATION.LONGITUDE
     *
     * @return the value of PUBLIC.LOCATION.LONGITUDE
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.LOCATION.LONGITUDE
     *
     * @param longitude the value for PUBLIC.LOCATION.LONGITUDE
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.LOCATION.NAME
     *
     * @return the value of PUBLIC.LOCATION.NAME
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.LOCATION.NAME
     *
     * @param name the value for PUBLIC.LOCATION.NAME
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.LOCATION.STREET
     *
     * @return the value of PUBLIC.LOCATION.STREET
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public String getStreet() {
        return street;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.LOCATION.STREET
     *
     * @param street the value for PUBLIC.LOCATION.STREET
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.LOCATION.CITY_ID
     *
     * @return the value of PUBLIC.LOCATION.CITY_ID
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.LOCATION.CITY_ID
     *
     * @param cityId the value for PUBLIC.LOCATION.CITY_ID
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    // Added manually
    @Getter @Setter
    private City city;
}