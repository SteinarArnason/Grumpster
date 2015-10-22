package grump.grumpster;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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

    protected void getAllUsers(){
        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.GET, APIprefix+"users", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //mTxtDisplay.setText("Response: " + response.toString());
                        System.out.println("Response : " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println("Error message" + error.getMessage());
                    }
                });

        //error.networkResponse.statusCode
        Volley.newRequestQueue(this).add(req);
    }
}
