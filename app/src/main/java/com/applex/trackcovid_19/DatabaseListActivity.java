package com.applex.trackcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DatabaseListActivity extends AppCompatActivity {
    TextView database1, database2, database3, database4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_list);

        database1=findViewById(R.id.database1);
        database2=findViewById(R.id.database2);
        database3=findViewById(R.id.database3);
        database4=findViewById(R.id.database4);



    }
}
