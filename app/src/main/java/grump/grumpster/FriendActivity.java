package grump.grumpster;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gunnar on 21-Oct-15.
 */
public class FriendActivity extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriend_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("asdfasdfasdf");
        getAllUsers();
    }

    protected void getAllUsers() {
        JsonArrayRequest jreq=new JsonArrayRequest(APIprefix + "users", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("Got response : "  + response.toString());
                String string = new String(response.toString());
                try {
                    JSONArray arr = new JSONArray(string);

                    JSONObject jObj = arr.getJSONObject(0);
                    String user1 = jObj.getString("username");
                    System.out.println("user1: " + user1);
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
}
