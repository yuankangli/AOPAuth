package com.liyk.AOPAuth.framework.common.result;

import com.alibaba.fastjson.JSONObject;

/**
 * @author liyk
 * @date 2018-04-05 下午 10:59
 */
public class Result {

    private boolean success;
    private int state;
    private JSONObject json;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public Result() {
        this.success = true;
        this.state = ResultState.SUCCESS;
    }

    public Result(boolean success, int state) {
        this.success = success;
        this.state = state;
    }

    public Result( Object data) {
        this.success = true;
        this.state = ResultState.SUCCESS;
        JSONObject json = new JSONObject();
        json.put("data",data);
        this.json = json;
    }
    public Result(boolean success, int state, Object data) {
        this.success = success;
        this.state = state;
        JSONObject json = new JSONObject();
        json.put("data",data);
        this.json = json;
    }
    
    public Result(boolean success, int state, JSONObject msg) {
        this.success = success;
        this.state = state;
        this.json = msg;
    }
}
