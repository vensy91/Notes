package vensy.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by vensy on 07.09.2016.
 */
public class NoteActivity extends AppCompatActivity {

    EditText editName, editText;
    Button btnSave, btnDelete;
    File file;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity);

        editName = (EditText) findViewById(R.id.edit_name);
        editText = (EditText) findViewById(R.id.edit_text);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        switch (getIntent().getStringExtra("NewOrEdit")) {
            case ("New"):
                break;
            case ("Edit"):
                file = new File(getFilesDir(), getIntent().getStringExtra("EditName"));
                editName.setText(getIntent().getStringExtra("EditName"));
                int length = (int) file.length();
                byte[] bytes = new byte[length];
                try {
                    FileInputStream in = new FileInputStream(file);
                    in.read(bytes);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                editText.setText(new String(bytes));
                break;
        }
    }

    public void onBtnClick(View view) {
        switch (view.getId()) {
            case (R.id.btn_save):
                file = new File(getFilesDir(), editName.getText().toString());
                try {
                    FileOutputStream out = new FileOutputStream(file, false);
                    out.write(editText.getText().toString().getBytes());
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent = new Intent(this, MainActivity.class);
                if (getIntent().getStringExtra("NewOrEdit").equals("New")) {
                    intent.putExtra("name", editName.getText().toString());
                }
                startActivity(intent);
                break;
            case (R.id.btn_delete):
                this.deleteFile(editName.getText().toString());
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("delete", editName.getText().toString());
                startActivity(intent);
        }
    }
}
