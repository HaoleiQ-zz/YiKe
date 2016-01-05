package qo.shaoyou.free.qo.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import qo.shaoyou.free.qo.R;
import qo.shaoyou.free.qo.dataBase.shaoyou_dataBase;
import qo.shaoyou.free.qo.other2.GestureListener;

/**
 * Created by 少游 on 2015/12/17.
 */
public class Add_ extends Activity implements View.OnClickListener {
    private Calendar c = null;
    private TextView edit;
    private TextView edit2, textView19;
    private RelativeLayout more_relativeLayout, more_relativeLayout2;
    private Switch aSwitch;
    private static boolean isExit = false;
    private TextView yincangde_Edittext,
            data_textView15, data_textView16, fenxiang_editText6;
    private shaoyou_dataBase dbHelper;
    private EditText biaoti_editText2, nei_rong_editText3, yu_ji_tian_shu_textView17, didian_text, nei_rong_editText4;
    private int year1, year2, yue1, yue2, ri1, ri2;
    private int getShijiannian1 = 0, getShijiannian2 = 0, getShijiannian13 = 0;
    private String jindu_zhuangtai;
    private SQLiteDatabase db;
    private boolean bianjipanduan = false;
    private SeekBar seekBar2;
    private EditText sdfasd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_layout);
        dbHelper = new shaoyou_dataBase(this, "shaoyou_data.db", null, 1);
        db = dbHelper.getWritableDatabase();

        Godie();

    }

    private void Godie() {

        zhuce();
        nong();

    }

    private void nong() {
        Intent ss = getIntent();
        String status = ss.getStringExtra("status");
        String title_mie = ss.getStringExtra("title_mie");
        if (status.equals("edit")) {
//            biaoti_editText2.getText().toString();
//            nei_rong_editText3.getText().toString();
//            edit.getText().toString();
//            edit2.getText().toString();
//            yu_ji_tian_shu_textView17.getText().toString();
//            didian_text.getText().toString();

//            try {
//数据库操作
//                查询数据库 单挑数据

            Cursor cursor = db.query("shaoyou_data", null, null, null, null, null, null);
            boolean nimabi = true;
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("title"));
                    if (name.equals(title_mie)) {
                        String neirong = cursor.getString(cursor.getColumnIndex("neirong"));
                        String shijian1 = cursor.getString(cursor.getColumnIndex("shijian1"));
                        String shijian2 = cursor.getString(cursor.getColumnIndex("shijian2"));
                        String yuji = cursor.getString(cursor.getColumnIndex("shijian_yuji"));
                        String didian = cursor.getString(cursor.getColumnIndex("didian"));
                        String jindu = cursor.getString(cursor.getColumnIndex("jindu"));

                        int nianss = cursor.getInt(cursor.getColumnIndex("nian"));
                        int yuess = cursor.getInt(cursor.getColumnIndex("yue"));
                        int riss = cursor.getInt(cursor.getColumnIndex("ri"));
                        int seebajindu = cursor.getInt(cursor.getColumnIndex("seebar"));


                        edit.getText().toString();

//                        int nian = !nianss.equals("") ? 0 : Integer.valueOf(nianss).intValue();
//                        int yue = !nianss.equals("") ? 0 : Integer.valueOf(yuess).intValue();
//                        int ri = !nianss.equals("") ? 0 : Integer.valueOf(riss).intValue();

                        //
                        if (nianss > 0) {
                            Calendar c = Calendar.getInstance();
                            int danqianyear = c.get(Calendar.YEAR);
                            int danqianmonth = c.get(Calendar.MONTH);
                            int danqianday = c.get(Calendar.DAY_OF_MONTH);

                            int a1 = danqianyear - nianss;
                            int a2 = danqianmonth - yuess;
                            int a3 = danqianday - riss;
                            int yeess = (a1 * 365) + (a2 * 30) + a3;
                            TextView jinxing = (TextView) findViewById(R.id.textView45);

                            jinxing.setVisibility(View.VISIBLE);

                            jinxing.setText(" 已进行: " + yeess + "天");

                        } else {


                            TextView jinxing = (TextView) findViewById(R.id.textView45);

                            jinxing.setVisibility(View.VISIBLE);

                            jinxing.setText(" 已进行: " + " ~ 天");

                        }
                        //这里总是报空指针异常
                        if (jindu == null) {
//
                        } else {
                            if (jindu.equals("yes")) {
                                aSwitch.setChecked(true);
                            } else if (jindu.equals("no")) {
                                aSwitch.setChecked(false);
                            }
                        }
                        TextView
                                SDFt = (TextView) findViewById(R.id.textView14);
                        SDFt.setText(seebajindu + "%");
                        biaoti_editText2.setText(title_mie);
                        nei_rong_editText3.setText(neirong);
                        sdfasd.setText(neirong);
                        edit.setText(shijian1);
                        edit2.setText(shijian2);
                        yu_ji_tian_shu_textView17.setText(yuji);
                        didian_text.setText(didian);
                        seekBar2.setProgress(seebajindu);


                    }
                } while (cursor.moveToNext() && nimabi);
            }
            cursor.close();

//            } catch (Exception e) {
//                Toast.makeText(Add_.this, "查询数据库失败" + e.toString(), Toast.LENGTH_SHORT).show();
//            }


            ///
            textView19.setVisibility(View.VISIBLE);
            aSwitch.setVisibility(View.VISIBLE);
            yincangde_Edittext.setVisibility(View.INVISIBLE);
            more_relativeLayout2.setVisibility(View.VISIBLE);
            TextView fsdfasd14 = (TextView) findViewById(R.id.textView14);
            fsdfasd14.setVisibility(View.VISIBLE);
            SeekBar sss = (SeekBar) findViewById(R.id.seekBar2);
            sss.setVisibility(View.VISIBLE);

        } else if (status.equals("new")) {
            bianjipanduan = true;
        } else {
            Toast.makeText(Add_.this, "啊有偶尅?", Toast.LENGTH_SHORT).show();
        }
    }

    private void zhuce() {
        nei_rong_editText4 = (EditText) findViewById(R.id.editText6);
        sdfasd = (EditText) findViewById(R.id.editText6);
        TextView tessdfasdfas = (TextView) findViewById(R.id.textView49);
        tessdfasdfas.setOnClickListener(this);

        SeekBar seekBar234234 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar234234.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView SDFt = (TextView) findViewById(R.id.textView14);
                SDFt.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //需要监听左右滑动事件的view
        RelativeLayout view = (RelativeLayout) this.findViewById(R.id.add_eddidiidid);

        //setLongClickable是必须的
        view.setLongClickable(true);
        view.setOnTouchListener(new MyGestureListener(this));

        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        ImageView bianji_add = (ImageView) findViewById(R.id.bianji_add);
        bianji_add.setOnClickListener(this);
        ImageView aaafemxiang = (ImageView) findViewById(R.id.imageView6);
        aaafemxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss1 = biaoti_editText2.getText().toString();
                String ss2 = nei_rong_editText3.getText().toString();
                String ss3 = edit.getText().toString();
                String ss4 = edit2.getText().toString();
                String ss5 = yu_ji_tian_shu_textView17.getText().toString();
                String ss6 = didian_text.getText().toString();
                //分享 计划  分享计划
                Intent intent = new Intent(Intent.ACTION_SEND);


                intent.setType("text/plain");


                //      intent.setPackage("com.sina.weibo");


                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");

                String Appdizhi = "\nhttps://github.com/Harry-Qin/_YiKe/blob/master/%E4%B8%80%E5%88%BB.apk?raw=true";
                intent.putExtra(Intent.EXTRA_TEXT, "你好,我正在使用《一刻》\n这是我的计划清单\n" +
                        "标题: " + ss1 + "\n" +
                        "" + ss2 + "\n" +
                        "" + ss3 + "\n" +
                        "" + ss4 + "\n" +
                        "地点:" + ss6 + "\n" +
                        "预计时间:" + ss5 + "\n" + "《一刻》下载地址:" + Appdizhi);


                intent.putExtra(Intent.EXTRA_TITLE, "我是标题");


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                startActivity(Intent.createChooser(intent, "分享给好友:"));

            }
        });

        ImageView add_quxiao = (ImageView) findViewById(R.id.add_quxiao);

        ImageView add_ok = (ImageView) findViewById(R.id.add_ok);
        add_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_ok.setOnClickListener(this);


        more_relativeLayout2 = (RelativeLayout) findViewById(R.id.relativeLayout9);

        textView19 = (TextView) findViewById(R.id.textView19);
        aSwitch = (Switch) findViewById(R.id.switch3);
        yincangde_Edittext = (TextView) findViewById(R.id.editText4);
        yincangde_Edittext.setOnClickListener(this);


        biaoti_editText2 = (EditText) findViewById(R.id.editText2);
        nei_rong_editText3 = (EditText) findViewById(R.id.editText3);

        //开始日期
        edit = (TextView) findViewById(R.id.textView15);
//结束日期
        edit2 = (TextView) findViewById(R.id.textView16);
        edit2.setOnClickListener(this);
        edit.setOnClickListener(this);
        yu_ji_tian_shu_textView17 = (EditText) findViewById(R.id.textView17);

        didian_text = (EditText) findViewById(R.id.textView199);
        fenxiang_editText6 = (TextView) findViewById(R.id.fenxiang_editText6);
        fenxiang_editText6.setOnClickListener(this);

        Intent sss = getIntent();
        if (sss.getStringExtra("status").equals("new")) {

        } else if (sss.getStringExtra("status").equals("edit")) {

        }

    }

    /**
     * 创建日期及时间选择对话框
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case 0:
                c = Calendar.getInstance();
                dialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker dp, int year,
                                                  int month, int dayOfMonth) {
                                year2 = year;
                                yue2 = month;
                                ri2 = dayOfMonth;
                                if (year2 > 0) {
                                    edit.setText("开始" + year + "."
                                            + (month + 1) + "." + dayOfMonth);
                                }

                                getShijiannian1 = year;
                                getShijiannian2 = month;
                                getShijiannian13 = dayOfMonth;
                            }
                        }, c.get(Calendar.YEAR), // 传入年份
                        c.get(Calendar.MONTH), // 传入月份
                        c.get(Calendar.DAY_OF_MONTH) // 传入天数
                );
                break;
            case 2:
                c = Calendar.getInstance();
                dialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker dp, int year,
                                                  int month, int dayOfMonth) {
                                year1 = year;
                                yue1 = month;
                                ri1 = dayOfMonth;
                                edit2.setText("结束" + year + "."
                                        + (month + 1) + "." + dayOfMonth);
                            }
                        }, c.get(Calendar.YEAR), // 传入年份
                        c.get(Calendar.MONTH), // 传入月份
                        c.get(Calendar.DAY_OF_MONTH) // 传入天数
                );
                break;
            case 1:
                c = Calendar.getInstance();
                dialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {


                                edit2.setText("结束时间：" + hourOfDay + "时"
                                        + minute + "分");
                            }
                        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                        false);
                break;
        }
        yu_ji_tian_shu_textView17.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (year1 <= 0) {
                    Toast.makeText(Add_.this, "请选择结束时间", Toast.LENGTH_SHORT).show();
                } else if (year2 <= 0) {
                    Toast.makeText(Add_.this, "请选择开始时间", Toast.LENGTH_SHORT).show();
                } else if (year1 > year2 && year1 >= 0 && year2 >= 0) {
                    int nian = (year1 - year2) * 365;
                    int yue = (yue1 - yue2) * 30;
                    int ri = ri1 - ri2 + nian + yue;
                    String nim = "  " + ri + " 天";
                    yu_ji_tian_shu_textView17.setText(nim);
                } else {
                    int nian = (year2 - year1) * 365;
                    int yue = (yue2 - yue1) * 30;
                    int ri = ri2 - ri1 + nian + yue;
                    String nim = "  " + ri + " 天";
                    yu_ji_tian_shu_textView17.setText(nim);
                }


                return false;
            }
        });
        return dialog;
    }

    private void shu_ju_chun_chu() {
        Intent s = getIntent();

        String zhenBiaoti = s.getStringExtra("title_mie");
        if (s.getStringExtra("status").equals("new")) {


            String biao_ti = biaoti_editText2.getText().toString();
            String nei_rong = nei_rong_editText3.getText().toString();
            String kaishi_shijian = edit.getText().toString();
            String jieshu_shijian = edit2.getText().toString();
            String yuji_tianshu = yu_ji_tian_shu_textView17.getText().toString();
            String didian_textString = didian_text.getText().toString();
            int seejindu = seekBar2.getProgress();
            if (aSwitch.isChecked()) {
                jindu_zhuangtai = "yes";
            } else {
                jindu_zhuangtai = "no";

            }
            ;
            boolean nidaye = true;
            try {
                //                查询数据库 单挑数据
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("shaoyou_data", null, null, null, null, null, null);
                boolean nimabi = true;

                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("title"));
                        if (name.equals(biao_ti)) {
                            Toast.makeText(Add_.this, "您的清单中已经有一毛一样的计划了", Toast.LENGTH_LONG).show();
                            nimabi = false;
                            nidaye = false;
                        }
                    } while (cursor.moveToNext() && nimabi);
                }
                cursor.close();

            } catch (Exception e) {
                Toast.makeText(Add_.this, "失败" + e.toString(), Toast.LENGTH_SHORT).show();
            }


            if (!biao_ti.equals("") && nidaye) {
                //////////////////////////////
                //搞数据
                ///////////////////////////


                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("title", biao_ti);
                values.put("neirong", nei_rong);
                values.put("shijian1", kaishi_shijian);
                values.put("shijian2", jieshu_shijian);
                values.put("shijian_yuji", yuji_tianshu);
                values.put("didian", didian_textString);
                values.put("jindu", jindu_zhuangtai);


                values.put("nian", getShijiannian1);
                values.put("yue", getShijiannian2);
                values.put("ri", getShijiannian13);
                values.put("seebar", seejindu);

                db.insert("shaoyou_data", null, values);
                values.clear();

                finish();
//                Toast.makeText(Add_.this, "创建成功", Toast.LENGTH_SHORT).show();
            } else if (!nidaye) {
//            Toast.makeText(Add_.this, "请输入标题", Toast.LENGTH_SHORT).show();
            }/////////////////////////////////////////////////
            else {
                Toast.makeText(Add_.this, "请输入标题", Toast.LENGTH_SHORT).show();
            }

        } else if (s.getStringExtra("status").equals("edit")) {

            //咩咩

            String nei_rong2 = nei_rong_editText4.getText().toString();
            String biao_ti = biaoti_editText2.getText().toString() + "";
            String nei_rong = nei_rong_editText3.getText().toString();
            String kaishi_shijian = edit.getText().toString();
            String jieshu_shijian = edit2.getText().toString();
            String yuji_tianshu = yu_ji_tian_shu_textView17.getText().toString();
            String didian_textString = didian_text.getText().toString();
            int nimammaajindu = seekBar2.getProgress();
            if (aSwitch.isChecked()) {
                jindu_zhuangtai = "yes";
            } else {
                jindu_zhuangtai = "no";

            }
            ;
            boolean nidaye = true;
//            try {
            //                查询数据库 单挑数据
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query("shaoyou_data", null, null, null, null, null, null);
            boolean nimabi = true;

            if (!biao_ti.equals("") && nidaye) {
                //////////////////////////////
                //搞数据
                ///////////////////////////
                if (aSwitch.isChecked()) {
                    jindu_zhuangtai = "yes";
                } else {
                    jindu_zhuangtai = "no";

                }
                ContentValues values = new ContentValues();
                values.put("title", biao_ti);

                if (nei_rong_editText3.getVisibility() == View.VISIBLE) {
                    values.put("neirong", nei_rong);
                } else {
                    values.put("neirong", nei_rong2);
                }

                values.put("shijian1", kaishi_shijian);
                values.put("shijian2", jieshu_shijian);
                values.put("shijian_yuji", yuji_tianshu);
                values.put("didian", didian_textString);
                values.put("jindu", jindu_zhuangtai);

                TextView textView50 = (TextView) findViewById(R.id.textView50);

                TextView textView51 = (TextView) findViewById(R.id.textView51);

                TextView textView52 = (TextView) findViewById(R.id.textView52);


                values.put("nian", getShijiannian1);
                values.put("yue", getShijiannian2);
                values.put("ri", getShijiannian13);
                values.put("seebar", nimammaajindu);


                db.update("shaoyou_data", values, "title = ?", new String[]{zhenBiaoti});
                finish();
//                Toast.makeText(Add_.this, "创建成功", Toast.LENGTH_SHORT).show();
            } else if (!nidaye) {
//            Toast.makeText(Add_.this, "请输入标题", Toast.LENGTH_SHORT).show();
            }/////////////////////////////////////////////////
            else {
                Toast.makeText(Add_.this, "请输入标题", Toast.LENGTH_SHORT).show();
            }


            ///////////////////////////


        }
    }

//左滑

    /**
     * 继承GestureListener，重写left和right方法
     */
    private class MyGestureListener extends GestureListener {
        public MyGestureListener(Context context) {
            super(context);
        }

        @Override
        public boolean left() {
            Log.e("test", "向左滑");
            Toast.makeText(Add_.this, "zuohua", Toast.LENGTH_SHORT).show();
            return super.left();
        }

        @Override
        public boolean right() {
            Log.e("test", "向右滑");
            finish();
            return super.right();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView15:
                showDialog(0);
                break;
            case R.id.textView16:
                showDialog(2);
                break;
            case R.id.editText4:
                textView19.setVisibility(View.VISIBLE);
                aSwitch.setVisibility(View.VISIBLE);
                yincangde_Edittext.setVisibility(View.INVISIBLE);
                more_relativeLayout2.setVisibility(View.VISIBLE);
                SeekBar sss = (SeekBar) findViewById(R.id.seekBar2);
                sss.setVisibility(View.VISIBLE);

                TextView fsdfasd14 = (TextView) findViewById(R.id.textView14);
                fsdfasd14.setVisibility(View.VISIBLE);
                break;
            case R.id.add_ok:

                shu_ju_chun_chu();


                break;
            case R.id.bianji_add:
                bianjipanduan = true;


                break;
            case R.id.textView49:
                if (sdfasd.getVisibility() == View.VISIBLE) {
                    nei_rong_editText3.setVisibility(View.VISIBLE);

                    sdfasd.setVisibility(View.GONE);

                } else if (sdfasd.getVisibility() == View.GONE) {
                    nei_rong_editText3.setVisibility(View.GONE);

                    sdfasd.setVisibility(View.VISIBLE);

                }
                break;
            case R.id.fenxiang_editText6:
                String ss1 = biaoti_editText2.getText().toString();
                String ss2 = nei_rong_editText3.getText().toString();
                String ss3 = edit.getText().toString();
                String ss4 = edit2.getText().toString();
                String ss5 = yu_ji_tian_shu_textView17.getText().toString();
                String ss6 = didian_text.getText().toString();
                //分享 计划  分享计划
                Intent intent = new Intent(Intent.ACTION_SEND);


                intent.setType("text/plain");


                //      intent.setPackage("com.sina.weibo");


                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");

                String Appdizhi = "\nhttps://github.com/Harry-Qin/_YiKe/blob/master/%E4%B8%80%E5%88%BB.apk?raw=true";
                intent.putExtra(Intent.EXTRA_TEXT, "你好,我正在使用《一刻》\n这是我的计划清单\n" +
                        "标题: " + ss1 + "\n" +
                        "" + ss2 + "\n" +
                        "" + ss3 + "\n" +
                        "" + ss4 + "\n" +
                        "评价:" + ss6 + "\n" +
                        "预计时间:" + ss5 + "\n" + "《一刻》下载地址:" + Appdizhi);


                intent.putExtra(Intent.EXTRA_TITLE, "我是标题");


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                startActivity(Intent.createChooser(intent, "分享给好友:"));

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {


        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息

        }
    }
}
