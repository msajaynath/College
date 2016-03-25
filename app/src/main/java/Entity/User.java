package Entity;

import com.orm.SugarRecord;

/**
 * Created by msajaynath on 19/03/16.
 */public class User extends SugarRecord {
    public String username,name,email,address,mobno,city;
    public String password;


    // You must provide an empty constructor
    public User() {}
public  User(String username,String name,String password)
{
    this.username=username;
    this.name=name;
    this.password=password;


}
}
