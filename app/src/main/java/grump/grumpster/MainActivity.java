package grump.grumpster;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class MainActivity extends Activity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean li = sp.getBoolean("loggedIn", false);
        if(!li) {
            System.out.println("You are not logged in.");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else {
            System.out.println("You are logged in.");
        }
    }

}
