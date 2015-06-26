package edu.guet.jjhome.indexsp.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import edu.guet.jjhome.indexsp.R;
import edu.guet.jjhome.indexsp.util.AppConstants;
import edu.guet.jjhome.indexsp.util.WebService;

public class FunctionActivity extends ActionBarActivity {

    private ImageButton btn_index;
    private ImageButton btn_report;
    private ImageButton btn_policy;
    private ImageButton btn_answer;
    private ImageButton btn_marco;
    private ImageButton btn_trend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        processView();
    }

    private void processView() {
        btn_index = (ImageButton) findViewById(R.id.img_btn_index);
        btn_report = (ImageButton) findViewById(R.id.img_btn_report);
        btn_policy = (ImageButton) findViewById(R.id.img_btn_policy);
        btn_answer = (ImageButton) findViewById(R.id.img_btn_answer);
        btn_marco = (ImageButton) findViewById(R.id.img_btn_marco);
        btn_trend = (ImageButton) findViewById(R.id.img_btn_trend);

        btn_index.setOnClickListener(mButtonListener);
        btn_report.setOnClickListener(mButtonListener);
        btn_policy.setOnClickListener(mButtonListener);
        btn_answer.setOnClickListener(mButtonListener);
        btn_marco.setOnClickListener(mButtonListener);
        btn_trend.setOnClickListener(mButtonListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_function, menu);
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

    View.OnClickListener mButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.img_btn_index:
                    intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("index_category", AppConstants.INDEX_CLIMATE);
                    startActivity(intent);
                    break;
                case R.id.img_btn_report:
                    intent = new Intent(getBaseContext(), NewsActivity.class);
                    intent.putExtra("msg_type", AppConstants.WEB_REPORT);
                    startActivity(intent);
                    break;
                case R.id.img_btn_marco:
                    intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("index_category", AppConstants.INDEX_PREDICT);
                    startActivity(intent);
                    break;
                case R.id.img_btn_trend:
                    intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("index_category", AppConstants.PREDICT_TREND);
                    startActivity(intent);
                    break;
                case R.id.img_btn_policy:
                    intent = new Intent(getBaseContext(), NewsActivity.class);
                    intent.putExtra("msg_type", AppConstants.WEB_POLICY);
                    startActivity(intent);
                    break;
                case R.id.img_btn_answer:
                    intent = new Intent(getBaseContext(), NewsActivity.class);
                    intent.putExtra("msg_type", AppConstants.WEB_ANSWER);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
}
