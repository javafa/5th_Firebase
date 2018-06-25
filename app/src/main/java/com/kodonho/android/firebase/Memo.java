package com.kodonho.android.firebase;

public class Memo {
    public String id;
    public String memo;
    public long timestamp;

    public Memo(){}

    public Memo(String id, String memo){
        this.id = id;
        this.memo = memo;
        this.timestamp = System.currentTimeMillis();
    }
}
