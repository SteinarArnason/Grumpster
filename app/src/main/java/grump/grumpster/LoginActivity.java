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

public class LoginActivity extends MainActivity {
    private EditText user;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        user = (EditText) findViewById( R.id.username );
        password = (EditText) findViewById( R.id.password );
    }

    @Override
    protected void onResume() {

        if(sp.getBoolean("loggedIn", false)) {
            System.out.println("You are logged in.");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void signup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void loginHttp(final View view){
        final String uName = user.getText().toString();
        String pWord = password.getText().toString();

        /**Harðkóðuð gögn til að auto logga sig inn (Þarf samt að ýta á login)*/
        //uName = "steinar";
        //pWord = "adidas";

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
                    login(view);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", uName);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Login Failed");
                }
            });
        //error.networkResponse.statusCode
        Volley.newRequestQueue(this).add(req);
    }
}

