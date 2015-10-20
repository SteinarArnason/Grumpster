package grump.grumpster;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}
