package grump.grumpster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Gunnar on 21-Oct-15.
 */
public class GroupActivity extends Activity {

    String [] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creategroup_screen);
        listView = (ListView)findViewById(R.id.listview);
        editText = (EditText)findViewById(R.id.txtsearch);
        initList();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(listItems.toString().equals("")) {
                    // reset listview
                    initList();
                } else {
                    // perform search
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void searchItem(String textToSearch) {
        for(String item: items) {
            if(!item.contains(textToSearch)) {
                listItems.remove(item);
            }
        }

        adapter.notifyDataSetChanged();
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




}



