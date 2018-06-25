package com.kodonho.android.firebase;

import android.os.PersistableBundle;
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
    DatabaseReference memoRef;
    @BindView(R.id.editMemo) EditText editMemo;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 화면을 바인딩
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        memoRef = database.getReference("memo");

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setListener();
    }
    public void addMemo(View view){

        writeNewMemo();
    }
    private void writeNewMemo() {
        String key = memoRef.push().getKey(); // 레퍼런스 밑에 키를 자동으로 생성
        String text = editMemo.getText().toString();
        Memo memo = new Memo(key, text);

        memoRef.child(key).setValue(memo);
    }

    private void setListener(){
        memoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Memo> data = new ArrayList<>();
                // 레퍼런스 밑의 목록 가져오기
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    // 값 꺼내기
                    Memo memo = child.getValue(Memo.class);
                    data.add(memo);
                }
                // 아답터에 꺼낸 데이터 세팅하기
                adapter.setDataAndRefresh(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
