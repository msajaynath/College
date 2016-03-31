package Entity;

import com.orm.SugarRecord;

/**
 * Created by msajaynath on 19/03/16.
 */public class ListItem  {
    public String name,address,course;
    public long id;
    public int rank;


    // You must provide an empty constructor
    public ListItem(String name, String address,String course,long id,int rank) {
        this.name=name;
        this.address=address;
        this.course=course;
        this.id=id;
        this.rank=rank;

    }


}
