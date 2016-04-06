package Entity;

import com.orm.SugarRecord;

/**
 * Created by msajaynath on 19/03/16.
 */public class Review extends SugarRecord {
    public College college;
    public User user;
    public String review;

    // You must provide an empty constructor
    public Review() {}

    public Review(College college, User user,String review)
    {
        this.college=college;
        this.user=user;
        this.review=review;


    }

}
