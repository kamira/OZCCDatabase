package tw.delta4studio.www.ozccdatabase;

import tw.delta4studio.www.ozccdatabase.tools.*;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.google.android.gms.common.api.GoogleApiClient;


/**
 * Created by kamira on 2016/08/27.
 */
public class Loading extends Activity {
    public static SQLiteDatabase db;
    static String dbpath = "";
    static String url = "https://spreadsheets.google.com/tq?key=";
    static String key = "";

    public static final int len1 = 14;
    public static final int len2 = 4;

    public static String[] runesBase = {"no", "name", "minStar", "maxStar", "red", "green", "blue", "yellow"};
    public static String[] runesItem = {"no", "star", "color", "ATK", "MATK", "HP", "DEF", "MDEF"};
    public static String[] runesPics = {"no", "icon", "standing"};
    public static String[] runesSkill = {"no", "color", "active", "coolDown", "effect", "positive"};

    public static int width, height;
    int count = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    // 資料庫建立
    private void openDB(){
        try {
            db = SQLiteDatabase.openDatabase(
                    "data/data/tw.delta4studio.www.ozccdatabase/db",
                    null,
                    SQLiteDatabase.CREATE_IF_NECESSARY);

            db.beginTransaction();;
            db.execSQL("DROP table if exists runesbase");
            db.execSQL("DROP table if exists runesitem");
            db.execSQL("DROP table if exists runespics");
            db.execSQL("DROP table if exists runesskill");

            // 符文基本資料
            db.execSQL("CREATE table if not exists runesbase (" +
                       " no           int, " +
                       " name        text, " +
                       " minStar      int, " +
                       " maxStar      int, " +
                       " red      boolean, " +
                       " blue     boolean, " +
                       " green    boolean, " +
                       " blue     boolean);");
            // 符文能力資料
            db.execSQL("CREATE table if not exists runesitem ("
                     + " no           int, "
                     + " star         int, "
                     + " color        int, "
                     + " ATK          int, "
                     + " MATK         int, "
                     + " HP           int, "
                     + " DEF          int, "
                     + " MDEF         int);");
            // 符文圖片資料
            db.execSQL("CREATE table if not exists runespics ("
                     + " no           int, "
                     + " icon        text, "
                     + " standing    text);");
            // 符文技能資料
            db.execSQL("CREATE table if not exists runesskill ("
                     + " no           int, "
                     + " active      text, "
                     + " cooldown     int, "
                     + " effect      text, "
                     + " positive    text);");

            db.setTransactionSuccessful();
        } catch(SQLException e) {

        } finally {
            db.endTransaction();
        }
    }

    private void getScreenSize(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width   = dm.widthPixels;
        height  = dm.heightPixels;
    }
    public class DownloadData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void...voids){
            return null;
        }

        @Override
        protected void onPostExecute(Void voids){

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        openDB();
        getScreenSize();
        new DownloadData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    private void doIntent(){
        Intent intent = new Intent();
        intent.setClass(Loading.this, DownloadLogo.class);
        startActivity(intent);
        finish();
    }
}
