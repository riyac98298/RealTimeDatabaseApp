package com.example.riyac.realtimedatabase;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    EditText editText;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    MediaPlayer mediaPlayer;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editText);
        relativeLayout=(RelativeLayout)findViewById(R.id.activity_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=editText.getText().toString();
                myRef.setValue(a);
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                textView.setText(value);
                if (value.equals("play"))
                {
                    Toast.makeText(getApplicationContext(), "text", Toast.LENGTH_LONG).show();
                    mediaPlayer=MediaPlayer.create(getApplicationContext(), R.raw.song);
                   mediaPlayer.start();
                }
                else if(value.equals("red"))
                {

                    relativeLayout.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
}
