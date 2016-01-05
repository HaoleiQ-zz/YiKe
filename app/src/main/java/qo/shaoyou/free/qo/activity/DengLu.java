package qo.shaoyou.free.qo.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import qo.shaoyou.free.qo.R;
import qo.shaoyou.free.qo.bean.GameScore;
import qo.shaoyou.free.qo.bean.MyUser;
import qo.shaoyou.free.qo.bean.Mybean;
import qo.shaoyou.free.qo.dataBase.shaoyou_dataBase;
import qo.shaoyou.free.qo.sql.ManageSQL;


public class DengLu extends Activity implements OnClickListener {
    private EditText yonghumingEditText;
    private EditText miamaEditText;
    private Button clearButton1;
    private ImageView ImageView1;
    private ImageView ImageView2;
    private ImageView ImageView11;
    private ImageView ImageView22;
    private TextView text4;
    private TextView text2;
    private TextView TextView01;
    private TextView text3;
    private Button dengluButton;
    private Button zhuce;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        jiance();

        yonghumingEditText = (EditText) findViewById(R.id.yonghuming);
        denglu();
        zhuce();
        Shuju();
        fangkedenglu();

    }

    private void fangkedenglu() {

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DengLu.this, ZhuCe.class);
                startActivity(in);

                finish();
            }
        });
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String yonghumingzi = "访客";
//                    Toast.makeText(DengLu.this,yonghumingzi,Toast.LENGTH_LONG).show();
                SharedPreferences abc = getSharedPreferences(
                        "shaoyou", Context.MODE_PRIVATE);
                ;
                ;
                ;
                ;
//                    Toast.makeText(DengLu.this, bu2.getEmail() + bu2.getEmailVerified()
//                            + bu2.getTableName() + bu2.getUsername(), Toast.LENGTH_LONG).show();
                // 获取编辑器
                Editor a = abc.edit();

                a.putString("name", "yes");
                a.putString("yonghuming", yonghumingzi);
                a.commit();
                finish();
            }
        });
    }

    private void jiance() {
        SharedPreferences a = getSharedPreferences("diyicidenglu", Context.MODE_PRIVATE);
        String nima = a.getString("name", "");
        if (!nima.equals("yes")) {


//
//            "凄凉别后两应同";
//            "天不老，情难绝.心似双丝网，中有千千结."
//            "最是不胜清怨月明中"
            ManageSQL manageSQL;
            shaoyou_dataBase mySQLite;
            mySQLite = new shaoyou_dataBase(this, shaoyou_dataBase.name+".db", null, 1);


//                    Toast.makeText(DengLu.this,yonghumingzi,Toast.LENGTH_LONG).show();
            SharedPreferences abc = getSharedPreferences(
                    "diyicidenglu", Context.MODE_PRIVATE);

//                    Toast.makeText(DengLu.this, bu2.getEmail() + bu2.getEmailVerified()
//                            + bu2.getTableName() + bu2.getUsername(), Toast.LENGTH_LONG).show();
            // 获取编辑器
            Editor sssa = abc.edit();

            sssa.putString("name", "yes");

            sssa.commit();
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("尊敬的用户您好，您是首次打开App\n" + "是否注册加入《一刻》?\n★登陆后可云端同步所有数据\n★随时上传/下载数据\n★让您的重要数据永不丢失~");
            dialog.setPositiveButton("暂不登陆,看看再说",
                    new android.content.DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            Intent ini = new Intent(DengLu.this, Main.class);
                            startActivity(ini);
                            String yonghumingzi = "访客";
//                    Toast.makeText(DengLu.this,yonghumingzi,Toast.LENGTH_LONG).show();
                            SharedPreferences abc = getSharedPreferences(
                                    "shaoyou", Context.MODE_PRIVATE);
                            ;
                            ;
                            ;
                            ;
//                    Toast.makeText(DengLu.this, bu2.getEmail() + bu2.getEmailVerified()
//                            + bu2.getTableName() + bu2.getUsername(), Toast.LENGTH_LONG).show();
                            // 获取编辑器
                            SharedPreferences.Editor a = abc.edit();

                            a.putString("name", "yes");
                            a.putString("yonghuming", yonghumingzi);
                            a.commit();
                            finish();

                        }
                    });
            dialog.setNeutralButton("马上登陆《一刻》",
                    new android.content.DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
            dialog.show();


        }
        SharedPreferences aaa = getSharedPreferences("diyicidenglu",
                Context.MODE_PRIVATE);
        String aaaaaa = aaa.getString("name", "");
        if (aaaaaa.equals("yes")) {

        }
        TextView textView1 = (TextView) findViewById(R.id.textView1);

        textView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                yonghumingEditText.setText("HarryQin");
                miamaEditText.setText("a550913732");
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // yonghumingEditText.setText(str);
        Intent intent2 = getIntent();
        String StringE = intent2.getStringExtra("extra");
        // Toast.makeText(DengLu.this, "接受" + StringE, 0).show();
        yonghumingEditText.setText(StringE);

    }

    private void login() {

        /**
         * 登陆用户
         */


        String user = yonghumingEditText.getText().toString();

        String pass = miamaEditText.getText().toString();
        if (!user.equals("") && !pass.equals("")) {

            final BmobUser bu2 = new BmobUser();
            bu2.setUsername(user);
            bu2.setPassword(pass);

            bu2.login(this, new SaveListener() {

                @Override
                public void onSuccess() {

                    chaxun();


                    Toast.makeText(
                            DengLu.this,
                            "尊敬的:" + bu2.getUsername() + "\n" + "\n" + "您好~"
                                    + "\n" + "\n" + "欢迎使用《一刻》", Toast.LENGTH_SHORT).show();

                    // testGetCurrentUser();

//                    SharedPreferences abca = getSharedPreferences(
//                            "shaoyou", Context.MODE_PRIVATE);
//                    ;
//                    ;
//                    ;
//                    ;
////                    Toast.makeText(DengLu.this, bu2.getEmail() + bu2.getEmailVerified()
////                            + bu2.getTableName() + bu2.getUsername(), Toast.LENGTH_LONG).show();
//                    // 获取编辑器
//                    Editor aa = abca.edit();
//
//                    aa.putString("name", "yes");
//                    aa.putString("yonghuming", yonghumingzi);
//                    aa.commit();
                    finish();

                }

                @Override
                public void onFailure(int code, String msg) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DengLu.this,
                            "对不起~登陆失败" + "\n" + "用户名或密码错误" + "\n" + msg, Toast.LENGTH_SHORT)
                            .show();
                }
            });

            //
        } else if (user.equals("")) {

            Toast.makeText(DengLu.this, "请输入用户名", Toast.LENGTH_SHORT).show();
        } else if (pass.equals("")) {

            Toast.makeText(DengLu.this, "请输入密码", Toast.LENGTH_SHORT).show();
        }


    }

    private void chaxun() {
        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
////查询playerName叫“比目”的数据
//        String pass = miamaEditText.getText().toString();
        String user = yonghumingEditText.getText().toString();
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        boolean aa = user.matches(regex);
        if (aa) {
            query.addWhereEqualTo("email", user);
////返回50条数据，如果不加上这条语句，默认返回10条数据
            query.setLimit(50);
////执行查询方法


            query.findObjects(this, new FindListener<MyUser>() {
                @Override
                public void onSuccess(List<MyUser> object) {
                    // TODO Auto-generated method stub
//                toast("查询成功：共" + object.size() + "条数据。");
//                String nimabi = object.get(0).getPlayerName();

//                    Toast.makeText(DengLu.this, object.get(0).getUsername(), Toast.LENGTH_SHORT).show();

                    SharedPreferences abc = getSharedPreferences(
                            "shaoyou", Context.MODE_PRIVATE);
                    ;
                    ;
                    ;
                    ;
//                    Toast.makeText(DengLu.this, bu2.getEmail() + bu2.getEmailVerified()
//                            + bu2.getTableName() + bu2.getUsername(), Toast.LENGTH_LONG).show();
                    // 获取编辑器
                    Editor a = abc.edit();


                    a.putString("yonghuming", object.get(0).getUsername());
                    a.commit();
                }

                @Override
                public void onError(int code, String msg) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DengLu.this, "查询失败：" + msg, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            SharedPreferences abc = getSharedPreferences(
                    "shaoyou", Context.MODE_PRIVATE);
            ;
            ;
            ;
            ;
//                    Toast.makeText(DengLu.this, bu2.getEmail() + bu2.getEmailVerified()
//                            + bu2.getTableName() + bu2.getUsername(), Toast.LENGTH_LONG).show();
            // 获取编辑器
            Editor a = abc.edit();


            a.putString("yonghuming", user);
            a.commit();
        }

    }

    private void Shuju() {
        Bmob.initialize(this, "55b874f54664e160a67c36a9872d81be");

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        // TODO Auto-generated method stub
        super.startActivityForResult(intent, requestCode);

        if (requestCode == 1) {

            SharedPreferences share = getSharedPreferences("ming",
                    Activity.MODE_WORLD_READABLE);

            String str = share.getString("name", "");
            // yonghumingEditText.setText(str);
            Intent intent2 = getIntent();
            String StringE = intent2.getStringExtra("extra");
            Toast.makeText(DengLu.this, "接受" + StringE, Toast.LENGTH_SHORT).show();

        }

    }

    private void addShuju() {
        final Mybean p2 = new Mybean();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                // toast("添加数据成功，返回objectId为："+p2.getObjectId());
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                // toast("创建数据失败：" + msg);
            }
        });
    }

    private void zhuce() {
        ImageView1 = (ImageView) findViewById(R.id.imageView1);
        ImageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView11 = (ImageView) findViewById(R.id.imageView11);
        ImageView22 = (ImageView) findViewById(R.id.imageView22);
        text4 = (TextView) findViewById(R.id.textView4);
        zhuce = (Button) findViewById(R.id.Button01);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4.setOnClickListener(this);
        dengluButton.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        TextView01 = (TextView) findViewById(R.id.TextView01);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
        TextView01.setOnClickListener(this);
    }

    private void puanduan() {

        Animation shake2 = AnimationUtils.loadAnimation(this, R.anim.shake);
        findViewById(R.id.yonghuming).startAnimation(shake2);
        Toast.makeText(DengLu.this, "请输入用户名~", Toast.LENGTH_SHORT).show();

        if (yonghumingEditText.getText().length() == 0) {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            findViewById(R.id.yonghuming).startAnimation(shake);
            Toast.makeText(DengLu.this, "请输入用户名~", Toast.LENGTH_SHORT).show();
        } else if (miamaEditText.getText().length() == 0) {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            findViewById(R.id.mima).startAnimation(shake);
            Toast.makeText(DengLu.this, "请输入密码~", Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    private void denglu() {
        nimabi1();

        miamaEditText = (EditText) findViewById(R.id.mima);
        clearButton1 = (Button) findViewById(R.id.clear);
        yonghumingEditText.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageView1.setVisibility(View.INVISIBLE);
                ImageView11.setVisibility(View.VISIBLE);
                ImageView2.setVisibility(View.VISIBLE);
                ImageView22.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        miamaEditText.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageView1.setVisibility(View.VISIBLE);
                ImageView11.setVisibility(View.INVISIBLE);
                ImageView2.setVisibility(View.INVISIBLE);
                ImageView22.setVisibility(View.VISIBLE);
                return false;
            }
        });
        yonghumingEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.length() > 0) {
                    clearButton1.setVisibility(View.VISIBLE);
                } else if (s.length() < 1) {
                    clearButton1.setVisibility(View.INVISIBLE);
                }
                ImageView1.setVisibility(View.INVISIBLE);
                ImageView11.setVisibility(View.VISIBLE);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        dengluButton = (Button) findViewById(R.id.login);
        dengluButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                puanduan();
            }
        });

        clearButton1.setOnClickListener(this);

    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//            if ((System.currentTimeMillis() - mExitTime) > 2000) {
//
//                Object mHelperUtils;
//
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//
//                mExitTime = System.currentTimeMillis();
//
//            } else {
//
//                finish();
//
//            }
//
//            return true;
//
//        }
//
//        return super.onKeyDown(keyCode, event);
//
//    }

    private void nimabi1() {
        SharedPreferences share = getSharedPreferences("wujay",
                Activity.MODE_WORLD_READABLE);

        String str = share.getString("name", "");

        if (!str.equals("yes")) {
//			Intent mainIntent = new Intent(DengLu.this, HuanYing.class);
//			DengLu.this.startActivity(mainIntent);
//			DengLu.this.finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                yonghumingEditText.setText("");
                break;

            case R.id.textView4:

                if (text4.getText().equals("English")) {
                    text4.setText("中文");
                    yonghumingEditText.setHint("Your account");
                    miamaEditText.setHint("******");
                    dengluButton.setText("Login");
                    zhuce.setText("Register");
                    text2.setText("Forgot password?");
                    text3.setText("Visitor login");
                    TextView01.setText("New to Trader+? Register here");
                } else if (text4.getText().equals("中文")) {
                    text4.setText("English");
                    yonghumingEditText.setHint("帐号");
                    miamaEditText.setHint("密码");
                    dengluButton.setText("登录");
                    zhuce.setText("注册");
                    text2.setText("忘记密码?");
                    text3.setText("访客登录");
                    TextView01.setText("还没有帐号? 快来注册一个吧！");
                }
                break;
            case R.id.TextView01:
                // TextView01.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); // 删除线
                if (text4.getText().equals("English")) {
                    Intent in = new Intent(DengLu.this, ZhuCe.class);
                    startActivity(in);
                    finish();
                } else if (text4.getText().equals("中文")) {
                    Intent in = new Intent(DengLu.this, ZhuCe2.class);
                    startActivity(in);
                    finish();
                }

                break;
            case R.id.login:
                // TextView01.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); // 删除线
                login();

                break;

            default:
                break;
        }

    }
}
