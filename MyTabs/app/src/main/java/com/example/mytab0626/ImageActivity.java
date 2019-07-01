package com.example.mytab0626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {
TextView name;
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_phonenumber);

        name = findViewById(R.id.listdata);
        image = findViewById(R.id.imageView);

        Intent intent = getIntent();

        name.setText(intent.getStringExtra("name"));
        image.setImageResource(intent.getIntExtra("image", 0));

    }
}
