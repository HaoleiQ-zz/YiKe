package qo.shaoyou.free.qo.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import qo.shaoyou.free.qo.R;

/**
 * Created by 少游 on 2015/12/17.
 */
public class Add_ extends Activity implements View.OnClickListener {
    private Calendar c = null;
    private TextView edit;
    private TextView edit2, textView19;
    private RelativeLayout more_relativeLayout, more_relativeLayout2;
    private Switch aSwitch;
    private TextView yincangde_Edittext,
            data_textView15, data_textView16, fenxiang_editText6;

    private EditText biaoti_editText2, nei_rong_editText3, yu_ji_tian_shu_textView17, didian_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_layout);
        Godie();

    }

    private void Godie() {
        zhuce();
    }

    private void zhuce() {
        Button add_quxiao = (Button) findViewById(R.id.add_quxiao);

        Button add_ok = (Button) findViewById(R.id.add_ok);
        add_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_ok.setOnClickListener(this);

        more_relativeLayout = (RelativeLayout) findViewById(R.id.rele11);

        more_relativeLayout2 = (RelativeLayout) findViewById(R.id.relativeLayout9);
        more_relativeLayout.setOnClickListener(this);
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
                                edit.setText("开始时间：" + year + "年"
                                        + (month + 1) + "月" + dayOfMonth + "日");
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
                                edit2.setText("结束时间：" + year + "年"
                                        + (month + 1) + "月" + dayOfMonth + "日");
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
        return dialog;
    }

    private void shu_ju_chun_chu() {
//我艹
        String biao_ti = biaoti_editText2.getText().toString();
        String nei_rong = nei_rong_editText3.getText().toString();
        String kaishi_shijian = edit.getText().toString();
        String jieshu_shijian = edit2.getText().toString();
        String yuji_tianshu = yu_ji_tian_shu_textView17.getText().toString();
        String didian_textString = didian_text.getText().toString();
        String jindu_zhuangtai;
        if (aSwitch.isChecked()) {
            jindu_zhuangtai = "yes";
        } else {
            jindu_zhuangtai = "no";

        }
        ;

        if (!biao_ti.equals("")) {
            //////////////////////////////
            //搞数据
            ///////////////////////////
            int data_int = 1;
            String data_1 = "data" + data_int;

            SharedPreferences s_data1 = getSharedPreferences(data_1, MODE_PRIVATE);

            SharedPreferences.Editor editor = s_data1.edit();


            editor.putString("biaoti", biao_ti);

            editor.putString("neirong", nei_rong);

            editor.putString("kaishi_shijian", kaishi_shijian);


            editor.putString("jieshu_shijian", jieshu_shijian);

            editor.putString("yuji_tianshu", yuji_tianshu);


            editor.putString("didian", didian_textString);

            editor.putString("jindu", jindu_zhuangtai);

            editor.commit();
            finish();
        } else {
            Toast.makeText(Add_.this, "请输入标题", Toast.LENGTH_SHORT).show();
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
                break;
            case R.id.add_ok:
                shu_ju_chun_chu();

                break;
            case R.id.fenxiang_editText6:

                //分享 计划  分享计划
                Intent intent = new Intent(Intent.ACTION_SEND);


                intent.setType("text/plain");


                //      intent.setPackage("com.sina.weibo");


                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");


                intent.putExtra(Intent.EXTRA_TEXT, "你好我是《一刻》的用户\n这是我的计划清单");


                intent.putExtra(Intent.EXTRA_TITLE, "我是标题");


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                startActivity(Intent.createChooser(intent, "邀请好友来使用《一刻》"));

                break;
        }
    }
}
