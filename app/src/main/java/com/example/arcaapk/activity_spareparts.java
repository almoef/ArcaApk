package com.example.arcaapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.arcaapk.Adapter.Adapter;
import com.example.arcaapk.Model.Model;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public class activity_spareparts extends AppCompatActivity {
  ProgressBar progressBar;
  DatabaseReference myDatabase;
  Adapter mAdapter;
  private RecyclerView recyclerView;
  LinearLayout LinearLayout_2;
  private TextView nameElement;
  private ImageView returnActivity;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_spareparts);
    progressBar = findViewById(R.id.progressBar_image);
    returnActivity = findViewById(R.id.imageButton);
    nameElement = findViewById(R.id.nameElement);
    LinearLayout_2 = findViewById(R.id.LinearLayout_2);

    String element = getIntent().getStringExtra("element");
    String NameEtiq = getIntent().getStringExtra("Etiq");
    nameElement.setText(element);

    //DEFINIENDO EL RECYCLERVIEW
    recyclerView = findViewById(R.id.recyclerViewSidel);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    //////////
    SetTypeActivity(NameEtiq,element);//SETEAR LA ACTIVITY SEGÚN LA ETIQUETADORA

    //RETORNAR A LA ANTERIOR ACTIVITY
    returnActivity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent return_ = new Intent(activity_spareparts.this,activity_view_elements.class);
        return_.putExtra("Etiq",NameEtiq);//REGRESA EL TIPO DE ETIQ
        startActivity(return_);
      }
    });
  }
  private void SetTypeActivity(String s,String element){
    if(Objects.equals(s,"Krones")) {
      ReadDatabase("1M4gIjCfaaZLcXnEPJ7Twq-Y3cRYLQ0ugRmBU6tiAy4Y","DataKrones",element);
      LinearLayout_2.setBackgroundColor(android.graphics.Color.parseColor("#032995"));
      returnActivity.setBackgroundColor(android.graphics.Color.parseColor("#032995"));
    }
    else if(Objects.equals(s, "Sidel")){
      ReadDatabase("1FmFRYsyTjHhQEG80gL3MTYvdKDBKvhkyBIWMJgU4DHI","DataSidel",element);//"Data" la bse de datos sidel
      LinearLayout_2.setBackgroundColor(android.graphics.Color.parseColor("#FF6B4E"));
      returnActivity.setBackgroundColor(android.graphics.Color.parseColor("#FF6B4E"));
    }
  }

  //FUNCIÓN PARA SELECCIONAR LA DATBASE SEGÚN EL TIPO DE ETIQ
  private void ReadDatabase(String iddrive,String s,String element){ //s -> SIDEL/KRONES   element -> elemento para filtrar
    myDatabase = FirebaseDatabase.getInstance().getReference().child(iddrive).child(s);
    //Query query = myDatabase.orderByChild("Elemento").startAt(element).endAt(element + "\uf8ff"); //query -> filtra por el element escogido
    Query query = myDatabase.orderByChild("Elemento").equalTo(element); // SOLO LOS QUE SON IGUALES AL ELEMENT
    FirebaseRecyclerOptions<Model> firebaseRecyclerOptions =
            new FirebaseRecyclerOptions.Builder<Model>().setQuery(query,Model.class).build();//setQuery(query.Model.class) query es el filtro antes creado
    //options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(myDatabase,Model.class).build();
    mAdapter = new Adapter(firebaseRecyclerOptions,this,getSupportFragmentManager(),progressBar,LinearLayout_2);
    mAdapter.startListening();
    recyclerView.setAdapter(mAdapter);
  }
}