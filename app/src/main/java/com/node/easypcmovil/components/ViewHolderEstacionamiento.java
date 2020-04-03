package com.node.easypcmovil.components;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.node.easypcmovil.R;


public class ViewHolderEstacionamiento extends RecyclerView.ViewHolder {

     ImageView imgvFotoEstacionamiento;
     TextView txtNombre;
     TextView txtHoraCierre;
     TextView txtCosto;
     TextView txtLugaresDisponibles;
    TextView txtLongitud;
    TextView txtLatitud;

    public ViewHolderEstacionamiento(@NonNull View itemView) {
        super(itemView);

        imgvFotoEstacionamiento = itemView.findViewById(R.id.imgvFotoEstacionamiento);
        txtNombre = itemView.findViewById(R.id.txtNombre);
        txtHoraCierre = itemView.findViewById(R.id.txtHoraCierre);
        txtCosto = itemView.findViewById(R.id.txtCosto);
        txtLugaresDisponibles = itemView.findViewById(R.id.txtLugaresDisponibles);
        txtLatitud = itemView.findViewById(R.id.txtLatitud);
        txtLongitud = itemView.findViewById(R.id.txtLongitud);

    }
}
