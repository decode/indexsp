package edu.guet.jjhome.indexsp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.cookie.Cookie;

import java.util.List;

import edu.guet.jjhome.indexsp.model.ClimateIndex;
import edu.guet.jjhome.indexsp.model.IndexData;
import edu.guet.jjhome.indexsp.model.PredictIndex;

public class AppUtils {
    /**
     * Retrieve the account stored for the application.
     *
     * @param context used to access the preferences (usually the Activity)
     * @return stored account name or empty string
     */
    public static String getStoredAccount(Context context) {
        return getStoredProperty(context, AppConstants.PREF_SELECTED_ACCOUNT_EMAIL);
    }

    /**
     * Set the account stored for the application.
     */
    public static void setStoredAccount(Context context, String account) {
        SharedPreferences preferences = context.getSharedPreferences(AppConstants.APP_PREF_NM,
                Context.MODE_PRIVATE);
        preferences.edit().putString(AppConstants.PREF_SELECTED_ACCOUNT_EMAIL, account).commit();
    }

    /**
     * Utility method for retrieving stored properties.
     */
    private static String getStoredProperty(Context context, String propertyName) {
        SharedPreferences preferences = context.getSharedPreferences(AppConstants.APP_PREF_NM,
                Context.MODE_PRIVATE);
        if (preferences == null) {
            return "";
        }
        return preferences.getString(propertyName, "");
    }

    public static void syncCookies(Context context) {

        PersistentCookieStore myCookieStore = new PersistentCookieStore(context);

        List<Cookie> cookies = myCookieStore.getCookies();

        CookieManager cookieManager = CookieManager.getInstance();

        for (int i = 0; i < cookies.size(); i++) {
            Cookie eachCookie = cookies.get(i);
            String cookieString = eachCookie.getName() + "=" + eachCookie.getValue();
            cookieManager.setCookie("http://guetw5.myclub2.com", cookieString);
            Log.i(">>>>>", "cookie : " + cookieString);
        }

        CookieSyncManager.getInstance().sync();
    }

    public static IndexData[] getIndexDataByType(String index_type) {
        IndexData[] indexes;
        switch (index_type) {
            case AppConstants.PREDICT_PRODUCT_PMI:
            case AppConstants.PREDICT_NON_PRODUCT_PMI:
            case AppConstants.PREDICT_CPI:
            case AppConstants.PREDICT_PPI:
            case AppConstants.PREDICT_ALL:
            case AppConstants.PREDICT_ENVIRONMENT:
            case AppConstants.PREDICT_ENTERPRISE:
            case AppConstants.PREDICT_TREND:
                indexes = PredictIndex.getIndexByParams(index_type);
                break;
            case AppConstants.CLIMATE_TRAVEL:
            case AppConstants.CLIMATE_LOGISTICS:
                indexes = ClimateIndex.getIndexByParams("全国", index_type);
                break;
            default:
                indexes = ClimateIndex.getIndexByParams("全国", AppConstants.CLIMATE_ALL);
        }
        return indexes;
    }



}
