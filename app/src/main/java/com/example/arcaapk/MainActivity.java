package com.example.arcaapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

   Button ActivitySidel;
   Button ActivityKrones;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    ActivityKrones = findViewById(R.id.btnKrones);
    ActivitySidel = findViewById(R.id.btnSidel);

    ActivitySidel.setOnClickListener(new AdapterView.OnClickListener() {
      @Override
      public void onClick(View view) {
        String NameEtiq = "Sidel";
        Intent ActivityElements = new Intent(MainActivity.this, activity_view_elements.class);
        ActivityElements.putExtra("Etiq", NameEtiq);
        startActivity(ActivityElements);
      }
    });

    ActivityKrones.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String NameEtiq = "Krones";
        Intent ActivityElements = new Intent(MainActivity.this, activity_view_elements.class);
        ActivityElements.putExtra("Etiq", NameEtiq);
        startActivity(ActivityElements);
      }
    });
  }


}