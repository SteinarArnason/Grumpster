package grump.grumpster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
        checkUserIcon();

    }

    private void checkUserIcon() {
        ImageView image = (ImageView) findViewById(R.id.prefsButton);
        boolean girl = sp.getBoolean("userIcon", true);

        if (girl) {
            System.out.println("Its a girl!");
            image.setImageResource(R.drawable.person2);
        } else {
            image.setImageResource(R.drawable.person);
        }
    }

    public void settings(View view) {
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
    }

}
