package qo.shaoyou.free.qo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;
import qo.shaoyou.free.qo.R;
import qo.shaoyou.free.qo.bean.GameScore;
import qo.shaoyou.free.qo.bean.MyUser;


public class ZhuCe extends Activity implements OnClickListener {
    private AutoCompleteTextView autoTextView;
    private EditText yonghuming;
    private EditText mima;
    private EditText mima2;
    private Button zhuce;
    private TextView textView4222;
    private String youxiang;
    private String yonghuming2;
    private String mima222;
    private String mima3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhu_ce);

        zhuce();
    }

    private void zhuce() {
        Bmob.initialize(this, "55b874f54664e160a67c36a9872d81be");
        autoTextView = (AutoCompleteTextView) findViewById(R.id.yonghuming22);
        yonghuming = (EditText) findViewById(R.id.yonghuming1);
        mima = (EditText) findViewById(R.id.mima22);
        mima2 = (EditText) findViewById(R.id.EditText01);
        zhuce = (Button) findViewById(R.id.login222);
        zhuce.setOnClickListener(this);

        String[] arr = {"@qq.com", "@163.com", "@gmail.com", "@hotmail.com",
                "@yahoo.com", "@126.com", "@tom.com", "@outlook.com"};
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr);
        autoTextView.setAdapter(arrayAdapter);
        autoTextView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
//				autoTextView.showDropDown();
                return false;
            }
        });

        autoTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                autoTextView.showDropDown();

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

    }

    /**
     * 注册用户
     */
    private void testSignUp() {
        youxiang = autoTextView.getText().toString();
        yonghuming2 = yonghuming.getText().toString();
        mima222 = mima.getText().toString();
        mima3 = mima2.getText().toString();
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

        if (mima222.equals(mima3) && !youxiang.equals("")
                && !yonghuming.equals("") && !mima222.equals("")
                && !mima3.equals("") && youxiang.matches(regex)) {

            final MyUser myUser = new MyUser();
            myUser.setUsername(yonghuming2);
            myUser.setPassword(mima222);

            myUser.setEmail(youxiang);


            myUser.signUp(this, new SaveListener() {

                @Override
                public void onSuccess() {

                    // Toast.makeText(
                    // ZhuCe.this,
                    // "注册成功:" + myUser.getUsername() + "-"
                    // + myUser.getObjectId() + "-"
                    // + myUser.getCreatedAt() + "-"
                    // + myUser.getSessionToken() + ",是否验证："
                    // + myUser.getEmailVerified(), 0).show();

                    SharedPreferences sharedPreferences = getSharedPreferences(
                            "ming", Context.MODE_PRIVATE); // 私有数据
                    Editor editor = sharedPreferences.edit();// 获取编辑器
                    editor.putString("name", youxiang);
                    editor.putString("id", yonghuming2);

                    editor.commit();// 提交修改
                    Toast.makeText(ZhuCe.this, "注册成功,请登录", Toast.LENGTH_SHORT).show();
                    Intent intentt = new Intent(ZhuCe.this, DengLu.class);
                    intentt.putExtra("extra", yonghuming2);
                    startActivityForResult(intentt, 1);
                    GameScore gameScore = new GameScore();
//注意：不能调用gameScore.setObjectId("")方法
                    gameScore.setPlayerName(yonghuming2);
                    gameScore.setMail(youxiang);
                    gameScore.setMima(mima222);
                    gameScore.setScore(89);
                    gameScore.setIsPay(false);
                    gameScore.save(ZhuCe.this, new SaveListener() {

                        @Override
                        public void onSuccess() {
                            // TODO Auto-generated method stub
//                toast("添加数据成功，返回objectId为：" + gameScore.getObjectId() + ”, 数据在服务端的创建时间为：“ + gameScore.getCreatedAt())
                            ;
//                Toast.makeText(ZhuCe.this, "成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int code, String arg0) {
                            // TODO Auto-generated method stub
                            // 添加失败
                            Toast.makeText(ZhuCe.this, "失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    finish();

                }


                @Override
                public void onFailure(int code, String msg) {
                    // Toast.makeText(ZhuCe.this, "服务器异常,注册失败", 0).show();
                    Toast.makeText(ZhuCe.this, msg, Toast.LENGTH_SHORT).show();
                    Toast.makeText(ZhuCe.this, "服务器罢工啦，请尝试游客登录", Toast.LENGTH_SHORT).show();

                }
            });
        } else if (!mima222.equals(mima3)) {

            Toast.makeText(ZhuCe.this, "您两次输入的密码不一致,请重新输入", Toast.LENGTH_SHORT).show();
            mima.setText("");
            mima2.setText("");
        } else if (youxiang.equals("") || yonghuming.equals("")
                || mima222.equals("") || mima3.equals("")) {
            Toast.makeText(ZhuCe.this, "您还有未填写的内容", Toast.LENGTH_SHORT).show();
        } else if (!youxiang.matches(regex)) {
            Toast.makeText(ZhuCe.this, "您的邮箱填写错误,请填写正确的邮箱", Toast.LENGTH_SHORT).show();
            autoTextView.setText("");
        }


    }

    private void mie() {
        autoTextView = (AutoCompleteTextView) findViewById(R.id.yonghuming22);
        // autoTextView = (AutoCompleteTextView)
        // findViewById(R.id.autoCompleteTextView1);
        // 从string中获取array数据
        String[] contries = new String[]{"@qq.com", "@163.com",
                "@hotmail.com", "@gmail.com", "@126.com"};
        // 定义一个适配器
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, contries);

        // autoTextView.setAdapter(adapter);

        // yonghuming = (EditText) findViewById(R.id.yonghuming1);
        // mima = (EditText) findViewById(R.id.mima22);
        // mima2 = (EditText) findViewById(R.id.EditText01);
        // zhuce = (Button) findViewById(R.id.login22);
        // textView4222 = (TextView) findViewById(R.id.textView422);

        // autocomText.setOnClickListener(this);
        // yonghuming.setOnClickListener(this);
        // mima.setOnClickListener(this);
        // mima2.setOnClickListener(this);
        // zhuce.setOnClickListener(this);
        // textView4222.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                Toast.makeText(ZhuCe.this, "s", Toast.LENGTH_SHORT).show();
                // if (textView4222.getText().equals("English")) {
                // autocomText.setText("Enter your Email");
                // yonghuming.setText("Enter your Username");
                // mima.setText("Enter your Password");
                // mima2.setText("Enter your Password again");
                // zhuce.setText("Register");
                // textView4222.setText("中文");
                // } else if (textView4222.getText().equals("中文")) {
                // autocomText.setText("请输入邮箱l");
                // yonghuming.setText("请输入用户名");
                // mima.setText("密码");
                // mima2.setText("重复密码");
                // zhuce.setText("注 册");
                // textView4222.setText("English");
                //
                // }

                break;
            case R.id.login222:
                // Toast.makeText(ZhuCe.this, "失败", 0).show();

                testSignUp();
                break;
            default:
                break;
        }
    }
}
