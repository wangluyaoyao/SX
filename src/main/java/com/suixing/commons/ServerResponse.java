package com.suixing.commons;

import lombok.Data;

@Data
public class ServerResponse {
    private int resultcode;
    private String reason;
    private Object data;
    public ServerResponse(){}
    public ServerResponse(int resultcode, String reason, Object data) {
        this.resultcode = resultcode;
        this.reason = reason;
        this.data = data;
    }

    public static ServerResponse saveSuccess(Object data){
        return new ServerResponse(200,"添加成功",data);
    }
    public static ServerResponse saveFail(Object data){
        return new ServerResponse(201,"添加失败",data);
    }
    public static ServerResponse updateSuccess(Object data){
        return new ServerResponse(200,"修改成功",data);
    }
    public static ServerResponse updateFail(Object data){
        return new ServerResponse(201,"修改失败",data);
    }
    public static ServerResponse deleteSuccess(Object data){
        return new ServerResponse(200,"删除成功",data);
    }
    public static ServerResponse deleteFail(Object data){
        return new ServerResponse(201,"删除失败",data);
    }
    public static ServerResponse getSuccess(Object data){
        return new ServerResponse(200,"查询成功",data);
    }
    public static ServerResponse getFail(Object data){
        return new ServerResponse(201,"查询失败",data);
    }

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "resultcode=" + resultcode +
                ", reason='" + reason + '\'' +
                ", data=" + data +
                '}';
    }
}
