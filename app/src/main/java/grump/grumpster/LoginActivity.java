package grump.grumpster;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        System.out.println("Login Screen creation");
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    }

    @Override
    protected void onResume() {
        System.out.println("Login Screen Resume");
        super.onResume();

    }

    public void login(View view) {
        System.out.println("Logging user in...");
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("loggedIn", true);
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loginHttp(View view){
        System.out.println("heheh http hehehe");
        String URL = "http://ilovemetech.com/api/users/kristinn";
        final JSONObject jsonBody;
        try {
            System.out.println("Trying to add password to body");
            jsonBody = new JSONObject().put("password", "api123123");
        } catch (JSONException e) {
            System.out.println("VILLLLLLAAA ATH ATH ATH");
            e.printStackTrace();
        }
        HashMap<String, String> params = new HashMap<String, String>();
        //params.put("password", "api123123");
        JSONObject myobj = null;
        try {
            myobj = new JSONObject().put("password","api123123");
            //System.out.println(myobj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(myobj.toString());
        JsonObjectRequest req = new JsonObjectRequest(URL, myobj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("Response is : ");
                            System.out.println(response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                VolleyLog.e(" !!! Error: ", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(req);
    }

    public void signupHttp(View view){
        String username = "steinar";
        String password = "adidas";
        String URL = "http://ilovemetech.com/api/users";


        JSONObject myobj = null;
        try {
            myobj = new JSONObject().put("username",username);
            myobj.put("password", password);
            System.out.println(myobj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(myobj.toString());


        JsonObjectRequest req = new JsonObjectRequest(URL, myobj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("Response is : ");
                            System.out.println(response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                VolleyLog.e(" !!! Error: ", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(req);

    }
}

