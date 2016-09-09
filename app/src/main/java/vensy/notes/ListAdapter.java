package vensy.notes;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by vensy on 07.09.2016.
 */
public class ListAdapter extends BaseAdapter {
    private ArrayList<String> notes;

    public ListAdapter(ArrayList<String> notes) {
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = view;
        if (rootView == null) rootView = View.inflate(viewGroup.getContext(), R.layout.list_item, null);
        TextView name = (TextView) rootView.findViewById(R.id.note_name);
        name.setText(notes.get(i));
        return rootView;
    }
}
