package qo.shaoyou.free.qo.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.AlarmClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.yydcdut.sdlv.SlideAndDragListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;
import qo.shaoyou.free.qo.R;
import qo.shaoyou.free.qo.bean.Mybean;

import qo.shaoyou.free.qo.ohter.ListViewCompat;
import qo.shaoyou.free.qo.ohter.SlideView;

//import com.yydcdut.sdlv.MenuItem;

public class Main extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        DialogInterface.OnClickListener,
        View.OnClickListener,
        AdapterView.OnItemClickListener,
        SlideView.OnSlideListener,
        ListViewCompat.OnRefreshListener,
        ListViewCompat.OnLoadListener {


    //////////////////////////////
    private static final String TAG = "MainActivity";
    private TextView texttime;
    private ListViewCompat mListView;
    private ScrollView scrollview;

    private List<Main.MessageItem> mMessageItems = new ArrayList<Main.MessageItem>();

    private SlideView mLastSlideViewWithStatusOn;

    private SlideAdapter adapter;
    private SlideAdapter2 adapter2;
    private int allCount = 40;

    /////////////////////////////
    private Toolbar toolbar;
    private TextView ijd;
    private View include1, include10, include2, include3, include4, include5, include6, include7, include8;

    // 只是用来模拟异步获取数据
    private Handler handler2;
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private Button imageView3;
    private Button imageView4;
    private ImageView imageView;
    private TextView textView12;
    private TextView textView13;
    private Button button3;
    private ProgressBar progressBar;

    private static String Appdizhi = "\n咩咩 我还没想好";
    private static String Appfenxiangjihua = "\n咩咩 我还没想好";
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zhucenimabi();
        Bmob.initialize(this, "55b874f54664e160a67c36a9872d81be");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ////////////////////////////
        initView();
        loadData0(ListViewCompat.REFRESH);
        ///////////////////////////////
        yemian_1();
        texttime = (TextView) findViewById(R.id.time);
//        shangchuan();
//        toolbar.setTitleTextAppearance(this, R.style.nimabi);
//toolbar.setLogo(R.drawable.ic_tab_love_pressed);
//        toolbar.setNavigationIcon(R.drawable.ic_tab_love_pressed);

//        toolbar.setLogo(R.drawable.q20151212092630);
        SharedPreferences sharedPreferences = getSharedPreferences(
                "diyicidenglu", Context.MODE_PRIVATE); // 私有数据
        SharedPreferences sharedPreferencesw = getSharedPreferences(
                "www", Context.MODE_PRIVATE); // 私有数据
        String yonghuming = sharedPreferences.getString("yonghuming", "");

        String yonghuming2 = sharedPreferencesw.getString("yonghuming", "");

        String nimabi = "0";
        String nimabi2 = "0";

        String tian = "0";
//        ijd.setText(yonghuming2);
//        toolbar.setTitle(" " + yonghuming2 + "的" + tian + "天" + ": 成功 " + nimabi + " 次" + "|" + "失败 " + nimabi2 + " 次");
        String zuobian = "|计划";
        toolbar.setTitle(yonghuming2);
        toolbar.setSubtitle(zuobian);
//        toolbar.setTitle(yonghuming2 + zuobian);
//        View view = View.inflate(this, R.layout.nav_header_main, null);
//        ijd = (TextView) findViewById(R.id.yizhixian);
//        ijd.setText("  成功 " + nimabi + " 次" + " | " + "失败 " + nimabi2 + " 次");

        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        jiance();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);


    }


    private void listview_zongjie() {
        ListView zongjielist = (ListView) findViewById(R.id.xListView2);
//        zongjielist.setAdapter(new ArrayAdapter<>(this, R.layout.listview_zongjie, listview_1_getData()));

        SimpleAdapter simpleAdapter = new
                SimpleAdapter(this, listview_1_getData(), R.layout.listview_zongjie,
                new String[]{"tv1", "tv2"}, new int[]{R.id.atext1, R.id.atext2});
        zongjielist.setAdapter(simpleAdapter);


        zongjielist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//                ImageView aa = (ImageView) findViewById(R.id.imageView_shanchu);
//
//                switch (position) {
//                    case 3:
//                    aa.setVisibility(View.VISIBLE);
//
//
//
//
//                }

                return false;
            }
        });
    }

    private List<Map<String, String>> listview_1_getData() {
//        List<String> data = new ArrayList<>();
//        data.add("那天我来到一家二向箔");
//        data.add("广泛的速度");
//        data.add("sdf的分公司的告诉对方告诉对方sdf");
//        data.add("你在南方的艳阳里");
//        data.add("大学飞鸟撒旦发射");

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();


        Map<String, String> map = new HashMap<String, String>();

        map.put("tv1", "2015年一月工作总结");
        map.put("tv2", "2015.1.2");
        list.add(map);


        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("tv1", "2015年记账单");
        map2.put("tv2", "2015.12.2");
        list.add(map2);

        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("tv1", "2015年6月生活消费");
        map3.put("tv2", "2015.6.12");
        list.add(map3);

        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("tv1", "2015年7月二向箔");
        map4.put("tv2", "2015.7.12");
        list.add(map4);

        Map<String, String> map5 = new HashMap<String, String>();
        map5.put("tv1", "2015年8月歌者毁灭计划");
        map5.put("tv2", "2015.8.12");
        list.add(map5);

        Map<String, String> map6 = new HashMap<String, String>();
        map6.put("tv1", "2015年9月三体入侵计划");
        map6.put("tv2", "2015.9.12");
        list.add(map6);

        Map<String, String> map7 = new HashMap<String, String>();
        map7.put("tv1", "2015年10月火星之旅");
        map7.put("tv2", "2015.10.12");
        list.add(map7);

        Map<String, String> map8 = new HashMap<String, String>();
        map8.put("tv1", "2015年11月砌筑素描");
        map8.put("tv2", "2015.11.12");
        list.add(map8);

        Map<String, String> map9 = new HashMap<String, String>();
        map9.put("tv1", "2015年12月咩米咩");
        map9.put("tv2", "2015.12.12");
        list.add(map9);

        Map<String, String> map10 = new HashMap<String, String>();
        map10.put("tv1", "2015年33月瓜瓜");
        map10.put("tv2", "2015.12.12");
        list.add(map10);


        Map<String, String> map11 = new HashMap<String, String>();
        map11.put("tv1", "2015年33月瓜瓜");
        map11.put("tv2", "2015.12.12");
        list.add(map11);

        Map<String, String> map12 = new HashMap<String, String>();
        map12.put("tv1", "2015年33月瓜瓜");
        map12.put("tv2", "2015.12.12");
        list.add(map12);

        Map<String, String> map13 = new HashMap<String, String>();
        map13.put("tv1", "撒旦法撒旦法搜索");
        map13.put("tv2", "2015.12.12");
        list.add(map13);

        Map<String, String> map15 = new HashMap<String, String>();
        map15.put("tv1", "粉红粉红的风格");
        map15.put("tv2", "2015.12.12");
        list.add(map15);

        return list;
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData0(ListViewCompat.REFRESH);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadData0(ListViewCompat.REFRESH);


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData0(ListViewCompat.REFRESH);
    }

    private void yemian_1() {

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Main.this, "咩咩1,你点了一下标题栏", Toast.LENGTH_SHORT).show();

            }
        });

        toolbar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

//                Toast.makeText(Main.this, "咩咩1,你长按了一下标题栏", Toast.LENGTH_SHORT).show();

                return false;
            }
        });


        ImageView imageView4 = (ImageView) findViewById(R.id.quxiao);

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImageView imageView3 = (ImageView) findViewById(R.id.shanchu);
//                imageView3.setVisibility(View.INVISIBLE);
//                ImageView imageView1 = (ImageView) findViewById(R.id.add);
//                imageView1.setVisibility(View.VISIBLE);
//
//
//                ImageView imageView4 = (ImageView) findViewById(R.id.quxiao);
//                imageView4.setVisibility(View.INVISIBLE);
//                ImageView imageViewshanchuzhende = (ImageView) findViewById(R.id.shanchuzhende);
//                imageViewshanchuzhende.setVisibility(View.INVISIBLE);

            }
        });
    }

    private void addShuju() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                "diyicidenglu", Context.MODE_PRIVATE); // 私有数据
        SharedPreferences sharedPreferencesw = getSharedPreferences(
                "www", Context.MODE_PRIVATE); // 私有数据

        String yonghuming2 = sharedPreferencesw.getString("yonghuming", "");
        EditText editText = (EditText) findViewById(R.id.editText);
        if (editText.getText().length() > 0) {


            final Mybean p2 = new Mybean();
            p2.setName(yonghuming2);


            p2.setAddress(editText.getText().toString());
            p2.save(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    // TODO Auto-generated method stub
                    loadData0(ListViewCompat.REFRESH);
                    // toast("添加数据成功，返回objectId为："+p2.getObjectId());
                    include1.setVisibility(View.VISIBLE);
                    include2.setVisibility(View.GONE);
                    include3.setVisibility(View.GONE);
                    include4.setVisibility(View.GONE);

                    include10.setVisibility(View.GONE);
                    include5.setVisibility(View.GONE);
                    include6.setVisibility(View.GONE);
                    include7.setVisibility(View.GONE);
                    include8.setVisibility(View.GONE);

                    Toast.makeText(Main.this, "发送成功,谢谢", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int code, String msg) {
                    // TODO Auto-generated method stub
                    Toast.makeText(Main.this, "对不起发送失败，请检查网络后重新发送", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Toast.makeText(Main.this, "你输入的是什么，我怎么看不到？", Toast.LENGTH_SHORT).show();

        }
    }

    private void zhucenimabi() {


        ImageView imageView222222222222222222222 = (ImageView) findViewById(R.id.add);
        imageView222222222222222222222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Main.this, Add_.class);
                startActivity(in);

            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        include1 = findViewById(R.id.include1);
        include2 = findViewById(R.id.include2);
        include3 = findViewById(R.id.include3);
        include4 = findViewById(R.id.include4);
        include5 = findViewById(R.id.include5);
        include6 = findViewById(R.id.include6);
        include7 = findViewById(R.id.include7);
        include8 = findViewById(R.id.include8);

        include10 = findViewById(R.id.include10);

        imageView3 = (Button) findViewById(R.id.imageView3);
        imageView4 = (Button) findViewById(R.id.imageView4);
        textView12 = (TextView) findViewById(R.id.textView8);
        textView13 = (TextView) findViewById(R.id.textView9);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        textView13.setText(str);
        imageView3.setOnClickListener(this);
        textView12.setOnClickListener(this);
        textView13.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.imageViewaaaaaa);
        imageView.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        RelativeLayout sdfs = (RelativeLayout) findViewById(R.id.relativeLayout);
        sdfs.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShuju();


            }
        });


    }

    private void jiance() {
        SharedPreferences a = getSharedPreferences("diyicidenglu", Context.MODE_PRIVATE);
        String nima = a.getString("name", "");
        if (!nima.equals("yes")) {
            Intent ini = new Intent(Main.this, DengLu.class);
            startActivity(ini);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(
                "diyicidenglu", Context.MODE_PRIVATE); // 私有数据
        SharedPreferences sharedPreferencesw = getSharedPreferences(
                "www", Context.MODE_PRIVATE); // 私有数据
        String yonghuming = sharedPreferences.getString("yonghuming", "");

        String yonghuming2 = sharedPreferencesw.getString("yonghuming", "");

        String nimabi = "0";
        String nimabi2 = "0";

        String tian = "0";
//        ijd.setText(yonghuming2);
//        toolbar.setTitleTextAppearance(this, R.style.nimabi);
        String zuobian = "|计划";
//        toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
        toolbar.setTitle(yonghuming2);
        toolbar.setSubtitle(zuobian);
//        View view = View.inflate(this, R.layout.nav_header_main, null);
//        ijd = (TextView) findViewById(R.id.yizhixian);
//        ijd.setText("  成功 " + nimabi + " 次" + " | " + "失败 " + nimabi2 + " 次");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        toolbar.showContextMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.jianyi:
//        SharedPreferences sharedPreferences = getSharedPreferences(
//                "diyicidenglu", Context.MODE_PRIVATE); // 私有数据
                SharedPreferences sharedPreferenceswf = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据


                String yonghuming2f = sharedPreferenceswf.getString("yonghuming", "");

                String zuobian = "|建议";
//        toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
                toolbar.setTitle(yonghuming2f);
                toolbar.setSubtitle(zuobian);
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);

                include10.setVisibility(View.GONE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.VISIBLE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);


                toolbar.setTitle(yonghuming2f + "   ");
                toolbar.setSubtitle(zuobian);
                break;
            case R.id.about:
//        SharedPreferences sharedPreferences = getSharedPreferences(
//                "diyicidenglu", Context.MODE_PRIVATE); // 私有数据
                SharedPreferences sharedPreferencesw = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据


                String yonghuming2 = sharedPreferencesw.getString("yonghuming", "");

                String nimabi = "0";
                String nimabi2 = "0";

                String tian = "0";
//        ijd.setText(yonghuming2);
//        toolbar.setTitleTextAppearance(this, R.style.nimabi);
                String zuobiana = "|关于";
//        toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
                toolbar.setTitle(yonghuming2);
                toolbar.setSubtitle(zuobiana);
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);

                include10.setVisibility(View.VISIBLE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);


                toolbar.setTitle(yonghuming2 + "   ");
                toolbar.setSubtitle(zuobiana);
                break;
            case R.id.yaoqing:
                SharedPreferences sharedPreferencesw1111 = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据


                String yonghuming21111 = sharedPreferencesw1111.getString("yonghuming", "");
                Intent intent = new Intent(Intent.ACTION_SEND);


                intent.setType("text/plain");


                //      intent.setPackage("com.sina.weibo");


                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");


                intent.putExtra(Intent.EXTRA_TEXT, "你好我是《一刻》的用户" + yonghuming21111 + "\n邀请你使用《一刻》App" + Appdizhi);


                intent.putExtra(Intent.EXTRA_TITLE, "我是标题");


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                startActivity(Intent.createChooser(intent, "邀请好友来使用《一刻》"));


                break;

        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {


            case R.id.Denglu:
                //   Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);
                //   Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);
                Intent nn = new Intent(Main.this, DengLu.class);
                startActivity(nn);

                toolbar = (Toolbar) findViewById(R.id.toolbar);
                break;
            case R.id.beifen:

                include8.setVisibility(View.GONE);
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);
                include5.setVisibility(View.VISIBLE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include10.setVisibility(View.GONE);


                SharedPreferences sharedPreferences = getSharedPreferences(
                        "diyicidenglu", Context.MODE_PRIVATE); // 私有数据
                SharedPreferences sharedPreferencesw = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据


                String yonghuming2 = sharedPreferencesw.getString("yonghuming", "");


                String zuobian = "|我的数据";
                toolbar.setTitle(yonghuming2);
                toolbar.setSubtitle(zuobian);


                break;
            case R.id.nav_send:
                //   Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);

                include10.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.VISIBLE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);


                SharedPreferences sharedPreferences1 = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据

                String yonghuming23 = sharedPreferences1.getString("yonghuming", "");


                toolbar = (Toolbar) findViewById(R.id.toolbar);
                String zuobian2 = "|意见";
//                toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
//        View view = View.inflate(this, R.layout.nav_header_main, null);
                toolbar.setTitle(yonghuming23);
                toolbar.setSubtitle(zuobian2);
                break;
            case R.id.nav_share:
                SharedPreferences sharedPreferencesw1111 = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据


                String yonghuming21111 = sharedPreferencesw1111.getString("yonghuming", "");
                Intent intent = new Intent(Intent.ACTION_SEND);


                intent.setType("text/plain");


                //      intent.setPackage("com.sina.weibo");


                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");


                intent.putExtra(Intent.EXTRA_TEXT, "你好我是《一刻》的用户" + yonghuming21111 + "\n这是我的计划清单" + Appfenxiangjihua);


                intent.putExtra(Intent.EXTRA_TITLE, "我是标题");


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                startActivity(Intent.createChooser(intent, "邀请好友来使用《一刻》"));
//                //  Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);
//
//                include1.setVisibility(View.GONE);
//                include2.setVisibility(View.GONE);
//                include3.setVisibility(View.GONE);
//
//                include10.setVisibility(View.GONE);
//                include4.setVisibility(View.GONE);
//                include5.setVisibility(View.VISIBLE);
//                include6.setVisibility(View.GONE);
//                include7.setVisibility(View.GONE);
//                include8.setVisibility(View.GONE);
//
//                SharedPreferences sharedPreferencesww = getSharedPreferences(
//                        "www", Context.MODE_PRIVATE); // 私有数据
//
//                String yonghumingw2 = sharedPreferencesww.getString("yonghuming", "");
//
//
//                String zuobianw = "|分享";
////                toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
////        View view = View.inflate(this, R.layout.nav_header_main, null);
//                toolbar.setTitle(yonghumingw2);
//                toolbar.setSubtitle(zuobianw);


                break;
            case R.id.nav_manage:
                //  Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);

                include10.setVisibility(View.GONE);
                include4.setVisibility(View.VISIBLE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);


                SharedPreferences sharedPreferenceswww = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据


                String yonghuming2ww = sharedPreferenceswww.getString("yonghuming", "");

                toolbar = (Toolbar) findViewById(R.id.toolbar);
                String zuobianww = "|同步";
//                toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
//        View view = View.inflate(this, R.layout.nav_header_main, null);
                toolbar.setTitle(yonghuming2ww);
                toolbar.setSubtitle(zuobianww);
                break;

            case R.id.nav_slideshow:
                //   Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);

                loadData022(ListViewCompat.REFRESH);

                include1.setVisibility(View.VISIBLE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);

                include10.setVisibility(View.GONE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);
                SharedPreferences sharedPreferenceswwww = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据

                String yonghuming2www = sharedPreferenceswwww.getString("yonghuming", "");


                toolbar = (Toolbar) findViewById(R.id.toolbar);
                String zuobianwww = "|日志";
//                toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
//        View view = View.inflate(this, R.layout.nav_header_main, null);
                toolbar.setTitle(yonghuming2www);
                toolbar.setSubtitle(zuobianwww);
                toolbar.setVisibility(View.VISIBLE);

                break;
            case R.id.nav_gallery:

                Intent alarmas = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                startActivity(alarmas);
//                //          Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);
//
//                include1.setVisibility(View.GONE);
//                include2.setVisibility(View.VISIBLE);
//                include3.setVisibility(View.GONE);
//                include4.setVisibility(View.GONE);
//                include5.setVisibility(View.GONE);
//
//                include10.setVisibility(View.GONE);
//                include6.setVisibility(View.GONE);
//                include7.setVisibility(View.GONE);
//                include8.setVisibility(View.GONE);
//                toolbar = (Toolbar) findViewById(R.id.toolbar);
////                toolbar.setTitle("< 提醒 >");
//                SharedPreferences sharedPreferenceswwwww = getSharedPreferences(
//                        "www", Context.MODE_PRIVATE); // 私有数据
//
//                String yonghuming2wwww = sharedPreferenceswwwww.getString("yonghuming", "");
//
//
//                String zuobianwwww = "|提醒";
//                toolbar.setTitle(yonghuming2wwww);
//                toolbar.setSubtitle(zuobianwwww);
                break;
            case R.id.Denglu2:
                loadData0(ListViewCompat.REFRESH);
                include1.setVisibility(View.VISIBLE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);

                include10.setVisibility(View.GONE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);
                SharedPreferences sharedPreferenceswa = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据

                String yonghuminga2 = sharedPreferenceswa.getString("yonghuming", "");


                toolbar = (Toolbar) findViewById(R.id.toolbar);
                String zuobiana = "|计划";

                toolbar.setTitle(yonghuminga2);

                toolbar.setSubtitle(zuobiana);


                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

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

        boolean aa = toolbar.getSubtitle().equals("|意见");

        if (!isExit && !aa) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else if (aa) {
            loadData0(ListViewCompat.REFRESH);
            include1.setVisibility(View.VISIBLE);
            include2.setVisibility(View.GONE);
            include3.setVisibility(View.GONE);
            include4.setVisibility(View.GONE);

            include10.setVisibility(View.GONE);
            include5.setVisibility(View.GONE);
            include6.setVisibility(View.GONE);
            include7.setVisibility(View.GONE);
            include8.setVisibility(View.GONE);
            SharedPreferences sharedPreferenceswa = getSharedPreferences(
                    "www", Context.MODE_PRIVATE); // 私有数据

            String yonghuminga2 = sharedPreferenceswa.getString("yonghuming", "");


            toolbar = (Toolbar) findViewById(R.id.toolbar);
            String zuobiana = "|计划";
//                toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
//        View view = View.inflate(this, R.layout.nav_header_main, null);
            toolbar.setTitle(yonghuminga2);
//                ijd = (TextView) findViewById(R.id.yizhixian);
            toolbar.setSubtitle(zuobiana);

        } else {
            finish();
            System.exit(0);
        }
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
//

    //////////////////////////////////////////////////////


    public void initView() {
        mListView = (ListViewCompat) findViewById(R.id.list);
        adapter = new SlideAdapter();


        adapter2 = new SlideAdapter2();
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
//        mListView.setOnRefreshListener(this);
//        mListView.setOnLoadListener(this);

    }

    public void initView2() {
        mListView = (ListViewCompat) findViewById(R.id.list);


        adapter2 = new SlideAdapter2();
        mListView.setAdapter(adapter2);
        mListView.setOnItemClickListener(this);
//        mListView.setOnRefreshListener(this);
//        mListView.setOnLoadListener(this);

    }

    private void loadData(final int what) {
        // 这里模拟从服务器获取数据
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1699);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                msg.what = what;
                msg.obj = getData();
                handler.sendMessage(msg);
            }
        }).start();
    }


    //miemie
    private void loadData0(final int what) {
        // 这里模拟从服务器获取数据
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                msg.what = what;
                msg.obj = getData();
                handler.sendMessage(msg);
            }
        }).start();
    }


    //miemie
    private void loadData022(final int what) {
        // 这里模拟从服务器获取数据
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                msg.what = what;
                msg.obj = getData2();
                handler.sendMessage(msg);
            }
        }).start();
    }


    public Map<Integer, String> getData_add() {
        int data_int = 1;
        String data_1 = "data" + data_int;

        SharedPreferences s_data1 = getSharedPreferences(data_1, MODE_PRIVATE);


        Map<Integer, String> maplist = new HashMap<Integer, String>();
        int a = 111;
        maplist.put(a, s_data1.getString("1", ""));

        Toast.makeText(Main.this, maplist.get(111), Toast.LENGTH_SHORT).show();
        return maplist;
    }

    private String getZongDate() {
        int data_int = 1;
        String data_1 = "data" + data_int;

        SharedPreferences s_data1 = getSharedPreferences(data_1, MODE_PRIVATE);

        String title = s_data1.getString("biaoti", "");
        return title;
    }

    // 测试数据
    public List<MessageItem> getData() {
        List<MessageItem> result = new ArrayList<MessageItem>();


        for (int i = 0; i < 15; i++) {
            MessageItem item = new MessageItem();


            if (i == 0) {
                item.iconRes = R.drawable.delete_wechat_icon;
                //只显示msg
                item.msg = "★欢迎使用《一刻》";
                if (!getZongDate().equals("")) {
                    item.msg = getZongDate();

                }
                item.time = "《一刻》在这里等你很久啦";
                item.title = "12.27.2015";

                result.add(item);
            }


            if (i == 1) {
                item.iconRes = R.drawable.delete_wechat_icon;

                //只显示msg
                item.msg = "★这里是清单标题";
                item.time = "这里是详细内容预览";
                item.title = "12.28.2015";

                result.add(item);
            }
            if (i == 2) {
                item.iconRes = R.drawable.delete_wechat_icon;
                item.msg = "★向左滑动可编辑/删除计划清单?";
                item.time = "《一刻》在这里等你很久啦";
                item.title = "12.27.2015";

                result.add(item);
            }
            if (i == 3) {
                item.iconRes = R.drawable.delete_wechat_icon;
                item.msg = "★单击下方添加按钮可添加清单?";
                item.time = "《一刻》在这里等你很久啦";
                item.title = "12.27.2015";

                result.add(item);
            }
            if (i == 4) {
                item.iconRes = R.drawable.delete_wechat_icon;
                item.msg = "★单击计划清单进入编辑页面";
                item.time = "《一刻》在这里等你很久啦";
                item.title = "12.27.2015";

                result.add(item);
            }
            if (i == 6) {
                item.iconRes = R.drawable.delete_wechat_icon;
                item.msg = "风卷江湖雨暗村，四山声作海涛翻";
                item.time = "似此星辰非昨夜，为谁风露立中宵";
                item.title = "12.27.2015";

                result.add(item);
            }
            if (i == 7) {
                item.iconRes = R.drawable.delete_wechat_icon;
                item.msg = "凄凉别后两应同";
                item.time = "天不老，情难绝.心似双丝网，中有千千结.";
                item.title = "12.27.2015";

                result.add(item);
            }

            if (i == 8) {
                item.iconRes = R.drawable.delete_wechat_icon;
                item.msg = "最是不胜清怨月明中";
                item.time = "我欲与君相知，长命无绝衰";
                item.title = "12.27.2015";

                result.add(item);
            }
            if (i == 9) {
                item.iconRes = R.drawable.delete_wechat_icon;
                item.msg = "滴不尽相思血泪抛红豆";
                item.time = "风卷江湖雨暗村，四山声作海涛翻";
                item.title = "12.27.2015";

                result.add(item);
            }
            if (i == 10
                    ) {
                item.iconRes = R.drawable.delete_wechat_icon;
                item.msg = "开不完春柳春花满画楼";
                item.time = "风卷江湖雨暗村，四山声作海涛翻";
                item.title = "12.27.2015";

                result.add(item);
            }
            if (i == 11) {
                item.iconRes = R.drawable.delete_wechat_icon;
                item.msg = "滴不尽相思血泪抛红豆";
                item.time = "风卷江湖雨暗村，四山声作海涛翻";
                item.title = "12.27.2015";

                result.add(item);
            }
            if (i == 12) {
                item.iconRes = R.drawable.delete_wechat_icon;
                item.msg = "开不完春柳春花满画楼";
                item.time = "风卷江湖雨暗村，四山声作海涛翻";
                item.title = "12.27.2015";

                result.add(item);
            }


        }
        return result;
    }


    //测试数据2
    // 测试数据
    public List<MessageItem> getData2() {
        List<MessageItem> result = new ArrayList<MessageItem>();
        for (int i = 0; i < 1; i++) {
            MessageItem item = new MessageItem();
//            texttime.setVisibility(View.VISIBLE);

            if (i == 0) {
                item.iconRes = R.drawable.delete_wechat_icon;
                item.msg = "单击下方添加按钮可添加日志";
                item.time = "《一刻》在这里等你很久啦";
                item.title = "12.27.2015";

                result.add(item);
            }


        }
        return result;
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<MessageItem> result = (List<MessageItem>) msg.obj;
            switch (msg.what) {
                case ListViewCompat.REFRESH:
//                    mListView.onRefreshComplete();
                    mMessageItems.clear();
                    mMessageItems.addAll(result);
                    break;
                case ListViewCompat.LOAD:
//                    mListView.onLoadComplete();
                    mMessageItems.addAll(result);
                    break;
                case 2:
//                    mListView.onLoadComplete();
                    Toast.makeText(Main.this, "已加载全部！", Toast.LENGTH_SHORT).show();
                    break;
            }
            mListView.setResultSize(result.size());
            adapter.notifyDataSetChanged();
        }

        ;
    };

    private class SlideAdapter2 extends BaseAdapter {

        private LayoutInflater mInflater;

        SlideAdapter2() {
            super();
            mInflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mMessageItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mMessageItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            SlideView slideView = (SlideView) convertView;
            if (slideView == null) {
                View itemView = mInflater.inflate(R.layout.item_listview_delete, null);

                slideView = new SlideView(Main.this);
                slideView.setContentView(itemView);

                holder = new ViewHolder(slideView);
                slideView.setOnSlideListener(Main.this);
                slideView.setTag(holder);
            } else {
                holder = (ViewHolder) slideView.getTag();
            }
            MessageItem item = mMessageItems.get(position);
            item.slideView = slideView;
            item.slideView.shrink();

            holder.icon.setImageResource(item.iconRes);
            holder.title.setText(item.title);
            holder.msg.setText(item.msg);
            holder.time.setText(item.time);
            holder.deleteHolder.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mMessageItems.remove(position);
                    adapter.notifyDataSetChanged();
//                          Toast.makeText(Main.this, "删除" + position+"个条", Toast.LENGTH_SHORT).show();
                }
            });
            holder.edit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    adapter.notifyDataSetChanged();
//                          Toast.makeText(Main.this, "编辑" + position+"个条", Toast.LENGTH_SHORT).show();

                    Intent in89 =
                            new Intent(Main.this, Add_.class);
                    in89.putExtra("jihua_key", position);

                }
            });

            return slideView;
        }

    }


    private class SlideAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        SlideAdapter() {
            super();
            mInflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mMessageItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mMessageItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            SlideView slideView = (SlideView) convertView;
            if (slideView == null) {
                View itemView = mInflater.inflate(R.layout.item_listview_delete, null);

                slideView = new SlideView(Main.this);
                slideView.setContentView(itemView);

                holder = new ViewHolder(slideView);
                slideView.setOnSlideListener(Main.this);
                slideView.setTag(holder);
            } else {
                holder = (ViewHolder) slideView.getTag();
            }
            MessageItem item = mMessageItems.get(position);
            item.slideView = slideView;
            item.slideView.shrink();

            holder.icon.setImageResource(item.iconRes);
            holder.title.setText(item.title);
            holder.msg.setText(item.msg);
            holder.time.setText(item.time);
            holder.deleteHolder.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mMessageItems.remove(position);
                    adapter.notifyDataSetChanged();
//                          Toast.makeText(Main.this, "删除" + position+"个条", Toast.LENGTH_SHORT).show();
                }
            });
            holder.edit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    adapter.notifyDataSetChanged();
//                          Toast.makeText(Main.this, "编辑" + position+"个条", Toast.LENGTH_SHORT).show();

                    Intent in89 =
                            new Intent(Main.this, Add_.class);
                    in89.putExtra("rizhi_key", position);

                }
            });

            return slideView;
        }

    }


    public class MessageItem {
        public int iconRes;
        public String title;
        public String msg;
        public String time;
        public SlideView slideView;
    }

    private static class ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView msg;
        public TextView time;
        public TextView deleteHolder;
        public TextView edit;

        ViewHolder(View view) {
            icon = (ImageView) view.findViewById(R.id.icon);
            title = (TextView) view.findViewById(R.id.title);
            msg = (TextView) view.findViewById(R.id.msg);
            time = (TextView) view.findViewById(R.id.time);
            deleteHolder = (TextView) view.findViewById(R.id.delete);
            edit = (TextView) view.findViewById(R.id.edit);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
//              Toast.makeText(this, "onItemClick position=" + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSlide(View view, int status) {
        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
            mLastSlideViewWithStatusOn.shrink();
        }

        if (status == SLIDE_STATUS_ON) {
            mLastSlideViewWithStatusOn = (SlideView) view;
        }
    }


    @Override
    public void onLoad() {
        // TODO Auto-generated method stub
        if (adapter.getCount() < allCount) {
            loadData(ListViewCompat.LOAD);
        } else {
            Message msg = handler.obtainMessage();
            msg.what = 2;
            msg.obj = mMessageItems;
            handler.sendMessage(msg);

        }
    }

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        loadData(ListViewCompat.REFRESH);
    }

    ///////////////////////////////////////////////////////////

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewaaaaaa:
//                Toast.makeText(Main.this, "sssss", Toast.LENGTH_SHORT).show();

                break;

            case R.id.imageView3:


                textView12.setVisibility(View.INVISIBLE);

                textView13.setText("正在同步...");

                progressBar.setVisibility(View.VISIBLE);
//                Log.d("nnn", "imagadfasdfasdfasdfeviewsss3");
                imageView.setVisibility(View.INVISIBLE);

                xianchengzuduan();
                break;
            case R.id.imageView4:
                textView12.setVisibility(View.INVISIBLE);
                textView13.setText("正在下载...");

                progressBar.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                xianchengzuduan2();
                break;
            case R.id.relativeLayout:

                break;
            case R.id.button3:
                loadData0(ListViewCompat.REFRESH);
                include1.setVisibility(View.VISIBLE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);

                include10.setVisibility(View.GONE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);
                SharedPreferences sharedPreferenceswa = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据

                String yonghuminga2 = sharedPreferenceswa.getString("yonghuming", "");


                toolbar = (Toolbar) findViewById(R.id.toolbar);
                String zuobiana = "|计划";
//                toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
//        View view = View.inflate(this, R.layout.nav_header_main, null);
                toolbar.setTitle(yonghuminga2);
//                ijd = (TextView) findViewById(R.id.yizhixian);
                toolbar.setSubtitle(zuobiana);


                break;
        }

    }

    private void xianchengzuduan() {


        new Handler().postDelayed(new Runnable() {
            public void run() {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String str = formatter.format(curDate);
                textView12.setVisibility(View.VISIBLE);
                textView13.setText(str);
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);

                Toast.makeText(Main.this, "上传成功", Toast.LENGTH_SHORT).show();
            }
        }, 2199);


    }

    private void xianchengzuduan2() {


        new Handler().postDelayed(new Runnable() {
            public void run() {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String str = formatter.format(curDate);
                textView12.setVisibility(View.VISIBLE);
                textView13.setText(str);
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);

                Toast.makeText(Main.this, "下载成功", Toast.LENGTH_SHORT).show();

            }
        }, 2899);


    }
}
