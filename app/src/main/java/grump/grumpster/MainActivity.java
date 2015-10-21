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

    // checks if user has changed the icon in preference screen
    private void checkUserIcon() {
        ImageView image = (ImageView) findViewById(R.id.prefsButton);
        boolean girl = sp.getBoolean("userIcon", true);

        if (girl) {
            image.setImageResource(R.drawable.person2);
        } else {
            image.setImageResource(R.drawable.person);
        }
    }

    // monitors clicks in activity_main layout
    public void menuClick(View view) {
        if (view.getId() == R.id.prefsButton) {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.addFriends) {
            Intent intent = new Intent(this, FriendActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.addGroup) {
            Intent intent = new Intent(this, GroupActivity.class);
            startActivity(intent);
        }
    }
}
