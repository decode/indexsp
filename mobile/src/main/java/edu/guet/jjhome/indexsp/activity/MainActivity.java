package edu.guet.jjhome.indexsp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.activeandroid.query.Select;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import edu.guet.jjhome.indexsp.R;
import edu.guet.jjhome.indexsp.model.User;
import edu.guet.jjhome.indexsp.util.AppConstants;

public class MainActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    private AccountHeader.Result headerResult;
    private MessageFragment fragment;

    static int drawerPosition;
    private Drawer.Result drawerResult;
    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;
    private Bundle params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        params = getIntent().getExtras();

        processView();
    }

    private void processView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initDrawer();

//        if (User.currentUser().position_id == null || Contact.getAllContact().length == 0) {
//            Handler handler = new Handler(new MsgHandler());
//            WebService web = new WebService(getBaseContext(), handler);
//            web.makeCreatePage();
//
//            Log.d("all contact size:", String.valueOf(Contact.getAllContact().length));
//        }

        initTab();
    }

    private void initTab() {
        FragmentPagerItemAdapter adapter;
        Log.d("index_category:", params.getString("index_category"));
        switch (params.getString("index_category")) {
            case AppConstants.INDEX_PREDICT:
                adapter = new FragmentPagerItemAdapter(
                        getSupportFragmentManager(), FragmentPagerItems.with(this)
//                        .add(R.string.tab_product_pmi, ChartActivityFragment.class)
//                        .add(R.string.tab_non_product_pmi, ChartActivityFragment.class)
//                        .add(R.string.tab_cpi, ChartActivityFragment.class)
//                        .add(R.string.tab_ppi, ChartActivityFragment.class)
                        .add(R.string.tab_product_pmi, ChartActivityFragment.class, new Bundler().putString("index_type", AppConstants.PREDICT_PRODUCT_PMI).get())
                        .add(R.string.tab_non_product_pmi, ChartActivityFragment.class, new Bundler().putString("index_type", AppConstants.PREDICT_NON_PRODUCT_PMI).get())
                        .add(R.string.tab_cpi, ChartActivityFragment.class, new Bundler().putString("index_type", AppConstants.PREDICT_CPI).get())
                        .add(R.string.tab_ppi, ChartActivityFragment.class, new Bundler().putString("index_type", AppConstants.PREDICT_PPI).get())
                        .create());
                break;
            case AppConstants.PREDICT_TREND:
                adapter = new FragmentPagerItemAdapter(
                        getSupportFragmentManager(), FragmentPagerItems.with(this)
                        .add(R.string.tab_trend, ChartActivityFragment.class, new Bundler().putString("index_type", AppConstants.PREDICT_TREND).get())
                        .create());
                break;
            default:
                adapter = new FragmentPagerItemAdapter(
                        getSupportFragmentManager(), FragmentPagerItems.with(this)
                        .add(R.string.tab_overview, ChartActivityFragment.class, new Bundler().putString("index_type", AppConstants.CLIMATE_ALL).get())
                        .add(R.string.tab_travel, ChartActivityFragment.class, new Bundler().putString("index_type", AppConstants.CLIMATE_TRAVEL).get())
                        .add(R.string.tab_logistics, ChartActivityFragment.class, new Bundler().putString("index_type", AppConstants.CLIMATE_LOGISTICS).get())
                        .create());
                break;
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    private void initDrawer() {
        headerResult = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.logo)
                .withHeaderBackgroundScaleType(ImageView.ScaleType.FIT_CENTER)
//                .addProfiles(
//                        initAccount()
//                )
//                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
//                    @Override
//                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
//                        return false;
//                    }
//                })
                .build();


        drawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.nav_item_index),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.nav_item_report),
                        new SecondaryDrawerItem().withName(R.string.nav_item_policy),
                        new SecondaryDrawerItem().withName(R.string.nav_item_environment),
                        new SecondaryDrawerItem().withName(R.string.nav_item_business),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.nav_item_about)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        Intent intent;
                        switch (position) {
                            case 0:
                                backupPosition(position);
                                break;
                            case 1:
                                break;
                            case 2:
                                drawerResult.setSelection(0);
                                intent = new Intent(getBaseContext(), NewsActivity.class);
                                intent.putExtra("msg_type", getString(R.string.nav_item_report));
                                startActivity(intent);
                                break;
                            case 3:
                                drawerResult.setSelection(0);
                                intent = new Intent(getBaseContext(), NewsActivity.class);
                                intent.putExtra("msg_type", getString(R.string.nav_item_policy));
                                startActivity(intent);
                                break;
                            case 4:
                                drawerResult.setSelection(0);
                                intent = new Intent(getBaseContext(), NewsActivity.class);
                                intent.putExtra("msg_type", getString(R.string.nav_item_environment));
                                startActivity(intent);
                                break;
                            case 5:
                                drawerResult.setSelection(0);
                                intent = new Intent(getBaseContext(), NewsActivity.class);
                                intent.putExtra("msg_type", getString(R.string.nav_item_business));
                                startActivity(intent);
                                break;
                            case 7:
                                drawerResult.setSelection(0);
                                showAbout();
                                break;
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.container, fragment);
                            fragmentTransaction.commit();
                        }
                    }
                })
                .build();

//        drawerResult.setSelection(0, true);
        drawerResult.setSelection(0);
    }

    private ProfileDrawerItem initAccount() {
        User u = new Select().from(User.class).where("current=?", true).orderBy("ID ASC").executeSingle();
        ProfileDrawerItem account;
        if (u != null) {
            account = new ProfileDrawerItem().withName(u.username).withEmail(" ").withIcon(getResources().getDrawable(R.drawable.ic_profile));
        }
        else {
            account = new ProfileDrawerItem().withName("Not Login").withIcon(getResources().getDrawable(R.drawable.ic_profile));
        }
        return account;
    }

    private void backupPosition(int position) {
        drawerPosition = position;
    }

    private void restorePosition() {
        drawerResult.setSelection(drawerPosition, false);
    }

    public void showAbout() {
        new MaterialDialog.Builder(this)
                .title(R.string.dialog_about)
                .content(R.string.dialog_about_content)
                .positiveText(R.string.action_back)
                .show();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.global, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(getBaseContext(), SettingsActivity.class));
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private class MsgHandler implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case AppConstants.STAGE_LOGIN:
                    break;
                case AppConstants.STAGE_GET_PAGE:
                    break;
                case AppConstants.STAGE_GET_ERROR:
                    break;
                case AppConstants.STAGE_GET_SUCCESS:
                    break;
                case AppConstants.STAGE_NOT_LOGIN:
                    break;
                case AppConstants.STAGE_LOGOUT:
                    break;
            }
            return false;
        }
    }

    @Override
    public void onBackPressed(){
//        moveTaskToBack(true);
        this.finish();
    }
}
