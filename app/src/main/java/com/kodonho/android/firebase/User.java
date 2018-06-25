package com.kodonho.android.firebase;

public class User {
    public String id;
    public String username;
    public String email;
    public String password;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password; // 나중에는 암호화해서 입력
    }
}