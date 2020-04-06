package com.node.easypcmovil.components;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.node.easypcmovil.MainActivity;
import com.node.easypcmovil.MapsActivity;
import com.node.easypcmovil.R;
import com.node.easypcmovil.modelo.Estacionamiento;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


public class AdapterEstacionamiento extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private MainActivity activityMain;

    private Context context;

    private List<Estacionamiento> estacionamientos;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView textVnombre = v.findViewById(R.id.txtNombre);
            TextView textVLatitud = v.findViewById(R.id.txtLatitud);
            TextView textVLongitud = v.findViewById(R.id.txtLongitud);

            String nombre = textVnombre.getText().toString();
            String latitud = textVLatitud.getText().toString();
            String longitud = textVLongitud.getText().toString();

            Snackbar.make(v, nombre, Snackbar.LENGTH_SHORT)
                    .show();

            Intent intent = new Intent(context, MapsActivity.class);
            intent.putExtra("nombre", "Casa");
            intent.putExtra("latitud", "21.117602");
            intent.putExtra("longitud", "-101.719329");
            context.startActivity(intent);
        }
    };


    public AdapterEstacionamiento(MainActivity activityMain, List<Estacionamiento> estacionamientos) {
        super();

        this.activityMain = activityMain;
        setItems(estacionamientos);

    }

    public void setItems(List<Estacionamiento> estacionamientos) {
        this.estacionamientos = estacionamientos != null ? estacionamientos : new ArrayList<Estacionamiento>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_estacionamiento, parent, false);
        ViewHolderEstacionamiento vhe = new ViewHolderEstacionamiento(v);
        this.context = parent.getContext();


        v.setOnClickListener(mOnClickListener);
        return vhe;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Estacionamiento e = estacionamientos.get(position);

        ViewHolderEstacionamiento vhe = (ViewHolderEstacionamiento) holder;
        vhe.txtNombre.setText(e.getNombre());

        vhe.txtHoraCierre.setText("Responsable: " + e.getAdministrador().getPersona().getNombre());
        vhe.txtLugaresDisponibles.setText("Lugares disponibles " + e.getCapacidad());
        vhe.txtCosto.setText("Costo: " + e.getCosto());
        vhe.txtLongitud.setText("" + e.getLongitud());
        vhe.txtLatitud.setText("" + e.getLatitud());
        //Para ingresar urls en imageView
        //Picasso.get().load(e.getFoto()).into(vhe.imgvFotoEstacionamiento);
        try {
            System.out.println(e.getFoto());
            vhe.imgvFotoEstacionamiento.setImageDrawable(fromBase64(context, e.getFoto()));
        } catch (Exception ex) {
            System.out.println("error foto");
            ex.printStackTrace();
        }
    }

    private static Drawable fromBase64(Context context, @NonNull String base64) throws Exception {
        Drawable ret = null;//from w ww.  ja  va  2  s  .  c  o  m
        if (!base64.equals("")) {
            ByteArrayInputStream bais = new ByteArrayInputStream(
                    Base64.decode(base64.getBytes(), Base64.DEFAULT));
            ret = Drawable.createFromResourceStream(context.getResources(),
                    null, bais, null, null);

            bais.close();
        }

        return ret;
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (estacionamientos != null) {
            return estacionamientos.size();
        } else {
            return 0;
        }
    }
}
