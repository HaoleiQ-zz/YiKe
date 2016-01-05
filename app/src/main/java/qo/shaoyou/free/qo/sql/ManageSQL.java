package qo.shaoyou.free.qo.sql;

import android.database.sqlite.SQLiteDatabase;


/**
 * �����ݿ�洢����ȡ����
 *
 * @author Administrator
 */
public class ManageSQL {

    //MySQLite mySQLite;

    /*public void creatSQL(){
        mySQLite=new MySQLite(this, "MyWeather.db3",1);
    }*/
    //insert the temperature and humidity datas
    public void insertT(SQLiteDatabase db, String data0, String data1, String data2, String data3, String data4, String data5
            , String data6) {

        db.execSQL("insert into shaoyou_data values (null , ? , ? , ? , ? , ? , ? , ?)", new String[]{data0, data1, data2, data3, data4, data5,
                data6});
    }

}
