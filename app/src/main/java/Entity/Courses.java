package Entity;

import com.orm.SugarRecord;

/**
 * Created by msajaynath on 19/03/16.
 */public class Courses extends SugarRecord {
    public String name;
    public College college;

    // You must provide an empty constructor
    public Courses() {}
    public Courses(String name,College college) {
        this.name=name;
        this.college=college;

    }


}
