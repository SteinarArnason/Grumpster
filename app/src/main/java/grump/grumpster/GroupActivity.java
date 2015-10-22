package grump.grumpster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupActivity extends Activity {

    String [] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText myFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creategroup_screen);
        listView = (ListView)findViewById(R.id.listview);
        myFilter = (EditText)findViewById(R.id.txtsearch);

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new ItemList());    //listens for click in userlist
        initList();

        myFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void initList() {
        items = new String[] {"Matti Gunnars", "Jonja Sóns", "Júlli Kristins", "Jr Steindi"};
        listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitems, listItems);
        listView.setAdapter(adapter);
    }

    // monitors clicks in activity_main layout
    public void groupButtons(View view) {
        if (view.getId() == R.id.createGroup) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.cancelGroup) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    class ItemList implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ViewGroup vg = (ViewGroup)view;
            TextView tv = (TextView)vg.findViewById(R.id.txtitems);
            Toast.makeText(GroupActivity.this, tv.getText().toString(), Toast.LENGTH_SHORT).show(); // here should we add the user to group...
        }
    }
}



