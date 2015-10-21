package grump.grumpster;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {
    /**
     * Public/Global variables
     * */
    public SharedPreferences sp;
    final public String APIprefix = "http://ilovemetech.com/api/";

    /**
     * Private/protected variables
     * */
    private EditText user;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        System.out.println("Login Screen creation");
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        user = (EditText) findViewById( R.id.username );
        password = (EditText) findViewById( R.id.password );
    }

    @Override
    protected void onResume() {
        System.out.println("Login Screen Resume");
        super.onResume();

    }

    public void login(View view) {
        //TODO: set cookie
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("loggedIn", true);
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void loginHttp(final View view){
        String uName = user.getText().toString();
        String pWord = password.getText().toString();
        System.out.println("Function: loginHTTP");
        System.out.println("Username: " + uName + " -> Password: " + pWord);

        JSONObject myobj = new JSONObject();
        try {
            myobj.put("password", pWord);
        } catch (JSONException e) {
            System.out.println("Error: Password object error (Login).");
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest(APIprefix + "users/" + uName, myobj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Response is : ");
                        System.out.println(response.toString());
                        System.out.println("Login successful");
                        login(view);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Login Failed");
                        //error.networkResponse.statusCode
                    }
                });
        Volley.newRequestQueue(this).add(req);
    }

    public void signupHttp(View view){
        String uName = user.getText().toString();
        String pWord = password.getText().toString();
        String URL = "http://ilovemetech.com/api/users";


        JSONObject myobj = new JSONObject();
        try {
            myobj.put("username", uName);
            myobj.put("password", pWord);
            System.out.println("Signup jsonObject: \n" + myobj.toString());
        } catch (JSONException e) {
            System.out.println("Error: User object error (Signup).");
            e.printStackTrace();
        }


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

