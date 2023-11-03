package com.example.arcaapk.Adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arcaapk.Model.Model;
import com.example.arcaapk.R;
import com.example.arcaapk.fragment_config;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
public class Adapter extends FirebaseRecyclerAdapter<Model,Adapter.ViewHolder> {
  private ProgressBar progressBar;
  private LinearLayout recyclerViewContainer;
  public Adapter(@NonNull FirebaseRecyclerOptions<Model> options, Activity activity, FragmentManager fm,ProgressBar progressBar, LinearLayout recyclerViewContainer) {
    super(options);
    this.progressBar = progressBar;
    this.recyclerViewContainer = recyclerViewContainer;
  }

  @Override
  protected void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position, @NonNull Model model) {

    holder.CodigoSAP.setText(model.getCódigoSAP());
    holder.DescripcionBreve.setText(model.getDescripciónBreve());
    holder.NombreTecnico.setText(model.getNombreTécnico());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        String getSap = model.getCódigoSAP();
        String getDescExt = model.getDescripciónExtensa();
        String getNumPart = model.getNúmeroDeParte();
        String getTec = model.getNombreTécnico();
        String getMarc = model.getMarca();
        String getImage = model.getEnlaceImagen();
        String message = getSap + ";"+ getTec + ";" + getNumPart + ";" + getDescExt + ";" + getMarc + ";" + getImage;
        Bundle bundle = new Bundle();
        bundle.putString("message",message);

        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        fragment_config myFragment = new fragment_config();
        myFragment.setArguments(bundle);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteneder, myFragment).addToBackStack(null).commit();
      }
    });
  }
  @Override
  public void onDataChanged() {
    super.onDataChanged();
    // Los datos se han cargado, oculta el ProgressBar y muestra el RecyclerView
    progressBar.setVisibility(View.GONE);
    recyclerViewContainer.setVisibility(View.VISIBLE);
  }


  @NonNull
  @Override
  public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spareparts_view, parent, false);
    return new ViewHolder(v);
  }


  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView CodigoSAP, DescripcionBreve, NombreTecnico;


    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      CodigoSAP = itemView.findViewById(R.id.cod_sap);
      DescripcionBreve = itemView.findViewById(R.id.descripcionBreve);
      NombreTecnico = itemView.findViewById(R.id.nombreTecnico);
    }
  }
}
