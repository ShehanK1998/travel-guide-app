package com.treklanka.version1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int driverId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "vehicle_type", length = 50)
    private String vehicleType;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "driver_img_url", length = 255)
    private String driverImgUrl;

    @Column(name = "vehicle_img_url", length = 255)
    private String vehicleImgUrl;

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getDistrictId() {
        return districtId;
    }
    public void setDistrictId(Integer districtId){
        this.districtId = districtId;
    }

    public String getDriverImgUrl() {
        return driverImgUrl;
    }

    public void setDriverImgUrl(String driverImgUrl) {
        this.driverImgUrl = driverImgUrl;
    }

    public String getVehicleImgUrl() {
        return vehicleImgUrl;
    }

    public void setVehicleImgUrl(String vehicleImgUrl) {
        this.vehicleImgUrl = vehicleImgUrl;
    }
}
