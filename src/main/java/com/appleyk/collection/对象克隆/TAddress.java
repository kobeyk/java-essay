package com.appleyk.collection.对象克隆;

import java.io.Serializable;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:45 下午 2020/10/21
 */
public class TAddress implements Serializable {
    private String city;
    private String location;

    public TAddress(String city, String location) {
        this.city = city;
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "TAddress{" +
                "city='" + city + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
