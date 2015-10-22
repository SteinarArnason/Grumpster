package grump.grumpster;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends MainActivity {
    private EditText user;
    private EditText password;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        user = (EditText) findViewById( R.id.username );
        password = (EditText) findViewById( R.id.password );
        email = (EditText) findViewById( R.id.email );
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void signupHttp(final View view){
        String uName = user.getText().toString();
        String pWord = password.getText().toString();
        String eMail = password.getText().toString();

        JSONObject myobj = new JSONObject();
        try {
            myobj.put("username", uName);
            myobj.put("password", pWord);
            myobj.put("email", eMail);
        } catch (JSONException e) {
            System.out.println("Error: User object error (Signup).");
            e.printStackTrace();
        }


        JsonObjectRequest req = new JsonObjectRequest(APIprefix + "users/", myobj,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    login(view);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Signup Failed");
                    //error.networkResponse.statusCode
                }
            });
        Volley.newRequestQueue(this).add(req);
    }
}
