package com.maternity.muaiwork.model;

import com.baidu.location.Poi;

import java.util.List;

/**
 * Created by apple on 2017/8/17.
 */

public class LocationModel extends BaseModel {
    public String locationTime;
    public String locationId;
    public int locationType;
    public double locationLatitude; //  public String location
    public double locationLongitude;
    public float locationRadius;
    public String locationAddrStr;
    public String locationCountry;
    public String locationCountryCode;
    public String locationCity;
    public String locationCityCode;
    public String locationDistrict;
    public String locationStreet;
    public String locationStreetNumber;
    public String locationLocationDescribe;
    public List<Poi> locationPoiList;
    public String locationBuildingID;
    public String locationBuildingName;
    public String locationFloor;

    public LocationModel() {
        super();
    }
}
