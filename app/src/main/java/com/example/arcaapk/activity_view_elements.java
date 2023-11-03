package com.example.arcaapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Objects;
import java.util.jar.Attributes;

public class activity_view_elements extends AppCompatActivity {
  ListView listView;
  LinearLayout LinearLayout_1;
  ImageView ReturnMain1;
  TextView txtNameEtiq;
  String NameEtiq;

  private String elementsSidel[] = {"Alineador","Arrancador","Bobina","Bomba","Bornera","Boya","Cable","Cable-Encoder",
          "Cable-Servo","Cerradura","Chipcard","Conector","Contacto Auxiliar","Contactor","Diodo Supresor","Electroválvula",
          "Encoder","Fibra Óptica","Final de Carrera","Fuente","Fusible","Interruptor","JOG","Módulo B&R","Motor","Optoacoplador",
          "Pacdrive","Panel","Pilz", "PT100","Relé","Resistencia","Sensor","Servomotor","Tarjetas B&R","UPS","Variador",
          "Warner"};
  private String elementsKrones[] = {"ACOPOS","Bomba","Bornera","Cable","Cable - Encoder", "Cable - Servo","Cable - red",
          "Clavija", "Conector", "Electroválvula", "Encoder", "Manómetro","Módulo I/O","Motor","Panel","PT100","Relé","Resistencia",
          "Sensor","Servomotor","Tarjeta","Variador"};


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_elements);
    listView = findViewById(R.id.listView);
    ReturnMain1 = findViewById(R.id.returnMain1);
    LinearLayout_1 = findViewById(R.id.LinearLayout_1);
    txtNameEtiq = findViewById(R.id.txtNameEtiq);

    NameEtiq = getIntent().getStringExtra("Etiq");
    SetTypeActivity(NameEtiq);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String sendElement = listView.getItemAtPosition(i).toString();
        Intent nextActivity = new Intent(activity_view_elements.this,activity_spareparts.class);
        nextActivity.putExtra("element",sendElement);
        nextActivity.putExtra("Etiq",NameEtiq);//ENVÍA EL TIPO DE ETIQ PARA LUEGO REGRESARLO
        startActivity(nextActivity);
      }
    });
  }
  public void ReturnMainActivity(View view){
    Intent MainActivity = new Intent(this,com.example.arcaapk.MainActivity.class);
    startActivity(MainActivity);

  }
  private void SetTypeActivity(String s){
    txtNameEtiq.setText("Etiquetadora " + s);
    if(Objects.equals(s,"Krones")) {
      Arrays.sort(elementsKrones);
      ArrayAdapter<String> adapter =new ArrayAdapter<>(this,R.layout.element_view,elementsKrones);
      listView.setAdapter(adapter);
      LinearLayout_1.setBackgroundColor(android.graphics.Color.parseColor("#032995"));
      ReturnMain1.setBackgroundColor(android.graphics.Color.parseColor("#032995"));
    }
    else if(Objects.equals(s, "Sidel")){
      Arrays.sort(elementsSidel);
      ArrayAdapter<String> adapter =new ArrayAdapter<>(this,R.layout.element_view,elementsSidel);
      listView.setAdapter(adapter);
      LinearLayout_1.setBackgroundColor(android.graphics.Color.parseColor("#FF6B4E"));
      ReturnMain1.setBackgroundColor(android.graphics.Color.parseColor("#FF6B4E"));
    }
  }
}