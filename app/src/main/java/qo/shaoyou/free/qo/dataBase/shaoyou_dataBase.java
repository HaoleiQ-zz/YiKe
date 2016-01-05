package qo.shaoyou.free.qo.dataBase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 少游 on 2015/12/28.
 */
public class shaoyou_dataBase extends SQLiteOpenHelper {
    private static final String tag="DBSQLiteHelper";
    public static final String name= "shaoyou_data";
    private static  final String ID="shaoyou_Id";
    private static  final String title="title";
    private static  final String neirong="neirong";
    private static  final String shijian1="shijian1";
    private static  final String shijian2="shijian2";
    private static  final String shijian_yuji="shijian_yuji";
    private static  final String didian="didian";
    private static  final String jindu="jindu";
    private static final int version=1;
    private Context mContext;

    public static final String CREATE_BOOK = "create table shaoyou_data ("
            + "shaoyou_Id integer primary key autoincrement, "
            + "title text, "
            + "neirong text, "
            + "shijian1 text, "
            + "shijian2 text, "
            + "shijian_yuji text, "
            + "didian text, "
            + "nian integer, "
            + "yue integer, "
            + "ri integer, "
            + "seebar integer, "
            + "jindu text) ";


public static String create_data="";
    public shaoyou_dataBase(Context context){
        super(context,name,null,version);
        Log.d("shaoyou","我的数据库构造器");

    }
    public shaoyou_dataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        mContext = context;
    }

    public shaoyou_dataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
/*创建白表*/
         db.execSQL(CREATE_BOOK);
        Log.d("shaoyou", "创建表乘公共");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*更新表*/
//        db.execSQL( "DROP TABLE IF EXISTS " + TB_NAME );
        Log.d("shaoyou", "更新表");
        onCreate(db);
    }

    public void close(){
    }
}
