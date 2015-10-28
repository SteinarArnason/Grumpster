package grump.grumpster;

import android.app.Activity;
import android.content.SharedPreferences;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Arrays;

public class FriendActivity extends MainActivity{

    ArrayList<String> usernames;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText myFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriend_screen);
        listView = (ListView)findViewById(R.id.getUsers);
        myFilter = (EditText)findViewById(R.id.friendUsername);

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new ItemList());
        getAllUsers();
        initlist();

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

    @Override
    protected void onResume() {
        super.onResume();
    }

    // Gets response from API of all users in system.
    protected void getAllUsers() {
        JsonArrayRequest jreq=new JsonArrayRequest(APIprefix + "users", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                String name;
                try {
                    JSONArray array = new JSONArray(response.toString());

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject row = array.getJSONObject(i);
                        name = row.getString("username");
                        usernames.add(name);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error message : " + error.toString());
            }
        });
        Volley.newRequestQueue(this).add(jreq);
    }

    // Populates list for this activity.
    public void initlist() {
        usernames = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.txtitems, usernames);
        listView.setAdapter(adapter);
    }

    protected void addFriend(String friendToAdd){
        //má ekki breyta "notfound"
        String myUsername = sp.getString("uName", "notfound");
        JSONObject myobj = new JSONObject();
        try {
            myobj.put("friend", friendToAdd);
        } catch (JSONException e) {
            System.out.println("Error: failed to create json object");
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest(APIprefix + "users/" + myUsername + "/friends", myobj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // hérna þarf að skipta um mynd
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error adding friend :  " +  error.getMessage());
                    }
                });
        Volley.newRequestQueue(this).add(req);
    }

    class ItemList implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ViewGroup vg = (ViewGroup)view;
           // TextView tv = (TextView)vg.findViewById(R.id.username);
           // Toast.makeText(FriendActivity.this, tv.getText().toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
