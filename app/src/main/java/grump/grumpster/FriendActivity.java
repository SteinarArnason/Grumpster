package grump.grumpster;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Gunnar on 21-Oct-15.
 */
public class FriendActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriend_screen);
    }
}
