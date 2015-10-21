package grump.grumpster;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends LoginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
