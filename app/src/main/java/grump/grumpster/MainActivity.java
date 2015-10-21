package grump.grumpster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends LoginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("loggedIn", true);
        editor.commit();

        if(!sp.getBoolean("loggedIn", false)) {
            System.out.println("You are not logged in.");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else {
            System.out.println("You are logged in.");
        }
    }

    public void settings(View view) {
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
    }

}
