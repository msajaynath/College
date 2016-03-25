package Entity;

import com.orm.SugarRecord;

/**
 * Created by msajaynath on 19/03/16.
 */public class College extends SugarRecord {
    public String name,colfacilities,hostelfacilities,fees,routemap,city,address;
    public String latitude,longitude;

    // You must provide an empty constructor
    public College() {}

    public College(String name, String colfacilities,String hostelfacilities,String address, String city,String latitude,String longitude)
    {
        this.name=name;
        this.colfacilities=colfacilities;
        this.hostelfacilities=hostelfacilities;
        this.address=address;
        this.city=city;
        this.latitude=latitude;
        this.longitude=longitude;

    }

}
