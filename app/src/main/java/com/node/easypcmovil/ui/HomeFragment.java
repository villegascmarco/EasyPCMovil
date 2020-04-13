package com.node.easypcmovil.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.node.easypcmovil.ActivityMain;
import com.node.easypcmovil.R;
import com.node.easypcmovil.commons.EasyPCCommons;
import com.node.easypcmovil.components.AdapterEstacionamiento;
import com.node.easypcmovil.modelo.Estacionamiento;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import com.node.easypcmovil.ui.home.HomeViewModel;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerEstacionamiento;

    List<Estacionamiento> estacionamientos;
    AdapterEstacionamiento adapterEstacionamiento;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerEstacionamiento = root.findViewById(R.id.recyclerEstacionamiento);
        recyclerEstacionamiento.setLayoutManager(new LinearLayoutManager(getActivity()));

        consultarEstacionamientos();


        return root;
    }

    public void consultarEstacionamientos() {
        //Instanciamos una nueva peticion HTTP a través de Volley:
        RequestQueue rq = Volley.newRequestQueue(getActivity());

        String url = EasyPCCommons.URL_SERVER + EasyPCCommons.URL_ESTACIONAMIENTO + "user/getAll?";

        //Genermos un nuevo objeto Response.Listener<String> para indicar que
        //tengamos una respuesta correcta:
        StringRequest postRquest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Type estacionamientoListType = new TypeToken<ArrayList<Estacionamiento>>() {
                        }.getType();

                        Gson gson = new Gson();

                        try {
                            estacionamientos = gson.fromJson(response, estacionamientoListType);
                        } catch (Exception exception) {
                            estacionamientos = null;
                        }

                        // System.out.println(estacionamientos);

                        if (adapterEstacionamiento == null) {
                            adapterEstacionamiento = new AdapterEstacionamiento((ActivityMain) getActivity(), estacionamientos);
                        } else {
                            adapterEstacionamiento.setItems(estacionamientos);
                        }

                        recyclerEstacionamiento.setAdapter(adapterEstacionamiento);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error", " volley");
                    }
                }

        );

        postRquest.setRetryPolicy(new DefaultRetryPolicy(
                1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        rq.add(postRquest);

    }
}
