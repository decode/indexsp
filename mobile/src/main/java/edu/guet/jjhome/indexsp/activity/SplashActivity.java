package edu.guet.jjhome.indexsp.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import edu.guet.jjhome.indexsp.R;
import edu.guet.jjhome.indexsp.util.AppConstants;
import edu.guet.jjhome.indexsp.util.WebService;

public class SplashActivity extends ActionBarActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler(new MsgHandler());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                attemptFetch();
            }
        }, 1000);
    }

    private void attemptFetch() {
        WebService web = new WebService(getBaseContext(), handler);
        web.businessIndexData();
        web.complexIndexData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class MsgHandler implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case AppConstants.STAGE_GET_ERROR:
                    break;
                case AppConstants.STAGE_GET_SUCCESS:
                    startActivity(new Intent(SplashActivity.this, FunctionActivity.class));
                    SplashActivity.this.finish();
                    break;
            }
            return false;
        }
    }
}
