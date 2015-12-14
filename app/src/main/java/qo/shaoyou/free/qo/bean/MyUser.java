package qo.shaoyou.free.qo.bean;

import org.json.JSONObject;

import java.util.List;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {

    /**
     *
     */

    private static final long serialVersionUID = 1L;


    private List<String> goodAt;
    private List<JSONObject> notice;


    public List<String> getGoodAt() {
        return goodAt;
    }

    public void setGoodAt(List<String> goodAt) {
        this.goodAt = goodAt;
    }

    public List<JSONObject> getNotice() {
        return notice;
    }

    public void setNotice(List<JSONObject> notice) {
        this.notice = notice;
    }

}
