package com.kodonho.android.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference userRef;
    @BindView(R.id.editId) EditText editId;
    @BindView(R.id.editPassword) EditText editPassword;
    @BindView(R.id.editEmail) EditText editEmail;
    @BindView(R.id.editName) EditText editName;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 화면을 바인딩
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setListener();
    }
    public void addUser(View view){
        String id = editId.getText().toString();
        String password = editPassword.getText().toString();
        String email = editEmail.getText().toString();
        String name = editName.getText().toString();
        writeNewUser(id, name, email, password);
    }
    private void writeNewUser(String userId, String name, String email, String password) {
        User user = new User(userId, name, email, password);
        userRef.child(userId).setValue(user);
    }

    private void setListener(){
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> data = new ArrayList<>();
                // 레퍼런스 밑의 목록 가져오기
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    // 키 꺼내기
                    String userId = child.getKey();
                    // 값 꺼내기
                    User user = child.getValue(User.class);
                    data.add(user);
                }
                // 아답터에 꺼낸 데이터 세팅하기
                adapter.setDataAndRefresh(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
