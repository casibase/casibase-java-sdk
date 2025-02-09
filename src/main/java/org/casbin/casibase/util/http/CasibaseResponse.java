package org.casbin.casibase.util.http;

public class CasibaseResponse<T1, T2> {
    private String status;
    private String msg;
    private T1 data;
    private T2 data2;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T1 getData() {
        return data;
    }

    public void setData(T1 data) {
        this.data = data;
    }

    public T2 getData2() {
        return data2;
    }

    public void setData2(T2 data2) {
        this.data2 = data2;
    }

}
