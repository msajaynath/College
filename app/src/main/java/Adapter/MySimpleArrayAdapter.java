package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.college.R;

import java.util.List;

import Entity.ListItem;

public class MySimpleArrayAdapter extends ArrayAdapter<ListItem> {
  private final Context context;
  private final List<ListItem>values;

  public MySimpleArrayAdapter(Context context,List<ListItem> values) {
    super(context, -1, values);
    this.context = context;
    this.values = values;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.list_item, parent, false);
    TextView name = (TextView) rowView.findViewById(R.id.name);
    TextView address = (TextView) rowView.findViewById(R.id.address);
    TextView courses = (TextView) rowView.findViewById(R.id.course);

    name.setText(values.get(position).name);
   // address.setText(values.get(position).address);
    courses.setText(values.get(position).course);

    return rowView;
  }
} 
