package grump.grumpster;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;

import java.net.URISyntaxException;

public class MainActivity extends Activity {
    SharedPreferences sp;
    final String APIprefix = "http://ilovemetech.com/api/";
    private Socket mSocket;
    {
        try {
            System.out.println("trying to connect socket to server");
            mSocket = IO.socket("http://ilovemetech.com");
            System.out.println("msocket is : " + mSocket.toString());
        } catch (URISyntaxException e) {
            System.out.println("failed to create socket: ");
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        System.out.println("Should create socket");
        mSocket.connect();
    }
    
    @Override
    protected void onResume() {
        super.onResume();

        //Crashar einhverra hluta vegna, laga seinna
        /*if(sp.getBoolean("loggedIn", false)) {
            checkUserIcon();
        }*/
    }

    public void login(View view) {
        //TODO: set cookie
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("loggedIn", true);
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        //Used to clear the activity stack
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    public void logout(View view) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("loggedIn", false);
        editor.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        //Used to clear the activity stack
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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
