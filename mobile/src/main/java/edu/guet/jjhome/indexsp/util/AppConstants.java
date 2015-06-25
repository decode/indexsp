package edu.guet.jjhome.indexsp.util;

import org.joda.time.DateTimeZone;

import java.util.TimeZone;

public class AppConstants {
    public static final String MY_ACCOUNT_TYPE = "edu.guet.accounts";

    public static final String PREF_SELECTED_ACCOUNT_EMAIL = "AccountEmail";
    public static final String APP_PREF_NM = "AccountPreferences";

    public static final String DATE_FORMAT_SOURCE = "yyyy/M/d H:mm";
    public static final String DATE_FORMAT_DEST = "yyyy/MM/dd HH:mm";
    public static final String DATE_FORMAT_DEST_SIMPLE = "M月d日";
    public static final int DAY_BEFORE = 4;
    public static final DateTimeZone TIME_ZONE = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

    public static final int NOTICE_PUBLIC = 1;
    public static final int NOTICE_ALL = 2;

    public static final int STAGE_LOGIN = 0;
    public static final int STAGE_GET_PAGE = 1;
    public static final int STAGE_GET_ERROR = 2;
    public static final int STAGE_GET_SUCCESS = 3;
    public static final int STAGE_NOT_LOGIN = 5;
    public static final int STAGE_USERNAME_ERROR = 6;
    public static final int STAGE_PASSWORD_ERROR = 7;
    public static final int STAGE_LOGIN_FAILED = 8;
    public static final int STAGE_LOGOUT = 9;
    public static final int STAGE_POST_FAILED = 10;
    public static final int STAGE_POST_SUCCESS = 11;
    public static final int STAGE_GET_MESSAGE_ID = 12;
    public static final int STAGE_UNDO_MESSAGE_SUCCESS = 13;

    public static final String STAGE_GET_ERROR_KEY = "ERROR_MSG";
    public static final String STAGE_GET_MESSAGE_ID_KEY = "MESSAGE_ID";

    public static final String MSG_ALL = "all";
    public static final String MSG_PUBLIC = "public";
    public static final String MSG_SENT = "sent";

    public static final String MSG_LIST = "MESSAGES";

    public static final String PREF_AUTOLOGIN = "pref_auto_login";
    public static final String PREF_TAIL = "pref_tail";
    public static final String PREF_MESSAGE_TAIL = "pref_message_tail";

    public static final String MSG_STATUS_READ = "已读";
    public static final String MSG_STATUS_UNREAD = "未读";

    public static final String MES_TAIL_TEMPLATE = " [SENDER]";

    public static final long SNACKBAR_DURATION = 6000;


    public static final String INDEX_CLIMATE = "climate";
    public static final String INDEX_PREDICT = "predict";
    public static final String INDEX_MACRO = "macro";

    public static final String CLIMATE_ALL = "全行业";
    public static final String CLIMATE_TRAVEL = "旅游行业";
    public static final String CLIMATE_LOGISTICS = "物流业";

    // 桂林服务业发展指数

    // 桂林服务业综合发展指数
    public static final String PREDICT_ALL = "20";
    // 服务业发展环境指数
    public static final String PREDICT_ENVIRONMENT = "21";
    // 服务企业经营指数
    public static final String PREDICT_ENTERPRISE = "22";
    // 服务业发展趋势调查
    public static final String PREDICT_TREND = "23";

    // 宏观经济指数

    // 制造业采购经理指数(PMI)
    public static final String PREDICT_PRODUCT_PMI = "24";
    // 非制造业PMI
    public static final String PREDICT_NON_PRODUCT_PMI = "25";
    // 居民消费者价格指数CPI
    public static final String PREDICT_CPI = "26";
    // 生产价格指数
    public static final String PREDICT_PPI = "27";
}
