package qo.shaoyou.free.qo.activity;

import android.app.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import qo.shaoyou.free.qo.R;

public class Add_rizhi extends Activity implements View.OnClickListener {

    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_rizhi);

        Go();
    }

    private void Go() {
        zhuce();
    }

    private void zhuce() {

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 1:


                break;

        }
    }
}
