package vensy.notes;



import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Intent intent;
    ListView lv;
    ListAdapter listAdapter;
    ArrayList<String> notes = new ArrayList<>();
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("vensy.notes.pref", MODE_PRIVATE);
        for (int i = 0; i < preferences.getAll().size(); i++) {
            notes.add(preferences.getString(Integer.toString(i), "empty"));
        }
        if (getIntent().hasExtra("name")) {
            notes.add(getIntent().getStringExtra("name"));
        }
        if (getIntent().hasExtra("delete")) {
            notes.remove(notes.indexOf(getIntent().getStringExtra("delete")));
        }
        lv = (ListView) findViewById(R.id.lv);
        listAdapter = new ListAdapter(notes);
        lv.setAdapter(listAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getApplicationContext(), NoteActivity.class);
                intent.putExtra("NewOrEdit", "Edit");
                intent.putExtra("EditName", notes.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (int i = 0; i < notes.size(); i++) {
            preferences.edit().putString(Integer.toString(i), notes.get(i)).apply();
        }
    }

    public void onBtnClick(View view) {
        intent = new Intent(this, NoteActivity.class);
        intent.putExtra("NewOrEdit", "New");
        startActivity(intent);
    }
}