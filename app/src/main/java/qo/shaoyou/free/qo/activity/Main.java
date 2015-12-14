package qo.shaoyou.free.qo.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yydcdut.sdlv.SlideAndDragListView;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;
import qo.shaoyou.free.qo.R;
import qo.shaoyou.free.qo.bean.Mybean;

//import com.yydcdut.sdlv.MenuItem;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DialogInterface.OnClickListener, View.OnClickListener, SlideAndDragListView.OnListItemLongClickListener,
        SlideAndDragListView.OnDragListener, SlideAndDragListView.OnSlideListener,
        SlideAndDragListView.OnListItemClickListener, SlideAndDragListView.OnMenuItemClickListener {
    private Toolbar toolbar;
    private TextView ijd;
    private View include1, include9, include10, include2, include3, include4, include5, include6, include7, include8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zhucenimabi();
        Bmob.initialize(this, "55b874f54664e160a67c36a9872d81be");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        yemian_1();
//        toolbar.setTitleTextAppearance(this, R.style.nimabi);
//toolbar.setLogo(R.drawable.ic_tab_love_pressed);
//        toolbar.setNavigationIcon(R.drawable.ic_tab_love_pressed);


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
    }

    private void yemian_1() {

        

    }

    private void addShuju() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                "diyicidenglu", Context.MODE_PRIVATE); // 私有数据
        SharedPreferences sharedPreferencesw = getSharedPreferences(
                "www", Context.MODE_PRIVATE); // 私有数据

        String yonghuming2 = sharedPreferencesw.getString("yonghuming", "");

        final Mybean p2 = new Mybean();
        p2.setName(yonghuming2);
        EditText editText = (EditText) findViewById(R.id.editText);

        p2.setAddress(editText.getText().toString());
        p2.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                // toast("添加数据成功，返回objectId为："+p2.getObjectId());
                include1.setVisibility(View.VISIBLE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);
                include9.setVisibility(View.GONE);
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
    }

    private void zhucenimabi() {

        include1 = findViewById(R.id.include1);
        include2 = findViewById(R.id.include2);
        include3 = findViewById(R.id.include3);
        include4 = findViewById(R.id.include4);
        include5 = findViewById(R.id.include5);
        include6 = findViewById(R.id.include6);
        include7 = findViewById(R.id.include7);
        include8 = findViewById(R.id.include8);
        include9 = findViewById(R.id.include9);
        include10 = findViewById(R.id.include10);


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


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

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
                String zuobian = "|关于";
//        toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
                toolbar.setTitle(yonghuming2);
                toolbar.setSubtitle(zuobian);
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);
                include9.setVisibility(View.GONE);
                include10.setVisibility(View.VISIBLE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);


                toolbar.setTitle(yonghuming2 + "   ");
                toolbar.setSubtitle(zuobian);
                break;
            case R.id.yaoqing:
//                Toast.makeText(Main.this,"s",Toast.LENGTH_SHORT);
//        SharedPreferences sharedPreferences = getSharedPreferences(
//                "diyicidenglu", Context.MODE_PRIVATE); // 私有数据
                SharedPreferences sharedPreferenceswa = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据


                String yonghuming2a = sharedPreferenceswa.getString("yonghuming", "");

//        ijd.setText(yonghuming2);
//        toolbar.setTitleTextAppearance(this, R.style.nimabi);
                String zuobiana = "|邀请";
//        toolbar.setTitle(zuobiana + yonghuming2 + " 的 " + tian + " 天");

                toolbar.setTitle(yonghuming2a);
                toolbar.setSubtitle(zuobiana);
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);
                include9.setVisibility(View.GONE);
                include10.setVisibility(View.VISIBLE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);

                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);
                include9.setVisibility(View.VISIBLE);
                include10.setVisibility(View.GONE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);
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
                //  Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);
//                toolbar.setTitle("< 上传/备份 >");
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.VISIBLE);
                include9.setVisibility(View.GONE);
                include10.setVisibility(View.GONE);
                toolbar = (Toolbar) findViewById(R.id.toolbar);

                SharedPreferences sharedPreferences = getSharedPreferences(
                        "diyicidenglu", Context.MODE_PRIVATE); // 私有数据
                SharedPreferences sharedPreferencesw = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据
                String yonghuming = sharedPreferences.getString("yonghuming", "");

                String yonghuming2 = sharedPreferencesw.getString("yonghuming", "");

                String nimabi = "0";
                String nimabi2 = "0";

                String tian = "0";
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                String zuobian = "|上传/备份";
//                toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
//        View view = View.inflate(this, R.layout.nav_header_main, null);
                toolbar.setTitle(yonghuming2 + "   ");
                toolbar.setSubtitle(zuobian);

                break;
            case R.id.nav_send:
                //   Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include9.setVisibility(View.GONE);
                include10.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.VISIBLE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);
                toolbar = (Toolbar) findViewById(R.id.toolbar);

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
                //  Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);

                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include9.setVisibility(View.GONE);
                include10.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);
                include5.setVisibility(View.VISIBLE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);
                toolbar = (Toolbar) findViewById(R.id.toolbar);

                SharedPreferences sharedPreferencesww = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据

                String yonghumingw2 = sharedPreferencesww.getString("yonghuming", "");


                toolbar = (Toolbar) findViewById(R.id.toolbar);
                String zuobianw = "|社区";
//                toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
//        View view = View.inflate(this, R.layout.nav_header_main, null);
                toolbar.setTitle(yonghumingw2);
                toolbar.setSubtitle(zuobianw);
                break;
            case R.id.nav_manage:
                //  Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);
                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include9.setVisibility(View.GONE);
                include10.setVisibility(View.GONE);
                include4.setVisibility(View.VISIBLE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);

                toolbar = (Toolbar) findViewById(R.id.toolbar);

                SharedPreferences sharedPreferenceswww = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据


                String yonghuming2ww = sharedPreferenceswww.getString("yonghuming", "");

                toolbar = (Toolbar) findViewById(R.id.toolbar);
                String zuobianww = "|设置";
//                toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
//        View view = View.inflate(this, R.layout.nav_header_main, null);
                toolbar.setTitle(yonghuming2ww);
                toolbar.setSubtitle(zuobianww);
                break;

            case R.id.nav_slideshow:
                //   Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);

                include1.setVisibility(View.GONE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.VISIBLE);
                include4.setVisibility(View.GONE);
                include9.setVisibility(View.GONE);
                include10.setVisibility(View.GONE);
                include5.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                SharedPreferences sharedPreferenceswwww = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据

                String yonghuming2www = sharedPreferenceswwww.getString("yonghuming", "");


                toolbar = (Toolbar) findViewById(R.id.toolbar);
                String zuobianwww = "|总结";
//                toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
//        View view = View.inflate(this, R.layout.nav_header_main, null);
                toolbar.setTitle(yonghuming2www);
                toolbar.setSubtitle(zuobianwww);
                break;
            case R.id.nav_gallery:
                //          Toast.makeText(Main.this, "s", Toast.LENGTH_LONG);

                include1.setVisibility(View.GONE);
                include2.setVisibility(View.VISIBLE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);
                include5.setVisibility(View.GONE);
                include9.setVisibility(View.GONE);
                include10.setVisibility(View.GONE);
                include6.setVisibility(View.GONE);
                include7.setVisibility(View.GONE);
                include8.setVisibility(View.GONE);
                toolbar = (Toolbar) findViewById(R.id.toolbar);
//                toolbar.setTitle("< 提醒 >");
                SharedPreferences sharedPreferenceswwwww = getSharedPreferences(
                        "www", Context.MODE_PRIVATE); // 私有数据

                String yonghuming2wwww = sharedPreferenceswwwww.getString("yonghuming", "");


                toolbar = (Toolbar) findViewById(R.id.toolbar);
                String zuobianwwww = "|提醒";
//                toolbar.setTitle(zuobian + yonghuming2 + " 的 " + tian + " 天");
//        View view = View.inflate(this, R.layout.nav_header_main, null);
                toolbar.setTitle(yonghuming2wwww);
                toolbar.setSubtitle(zuobianwwww);
                break;
            case R.id.Denglu2:

                include1.setVisibility(View.VISIBLE);
                include2.setVisibility(View.GONE);
                include3.setVisibility(View.GONE);
                include4.setVisibility(View.GONE);
                include9.setVisibility(View.GONE);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }

    }

    @Override
    public void onDragViewStart(int position) {

    }

    @Override
    public void onDragViewMoving(int position) {

    }

    @Override
    public void onDragViewDown(int position) {

    }

    @Override
    public void onListItemClick(View v, int position) {

    }

    @Override
    public void onListItemLongClick(View view, int position) {

    }

    @Override
    public boolean onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
        return false;
    }

    @Override
    public void onSlideOpen(View view, View parentView, int position, int direction) {

    }

    @Override
    public void onSlideClose(View view, View parentView, int position, int direction) {

    }
}
