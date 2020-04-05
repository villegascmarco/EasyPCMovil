package com.node.easypcmovil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.node.easypcmovil.commons.EasyPCCommons;
import com.node.easypcmovil.modelo.Persona;
import com.node.easypcmovil.modelo.Usuario;

import java.util.HashMap;
import java.util.Map;


public class ActivityLogin extends AppCompatActivity {

    private SignInButton btnIngresarGoogle;

    private Button btnIngresar;
    private TextView txtUsuario;
    private TextView txtContrasenia;

    private int RC_SIGN_IN = 0;

    protected GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mapearElementos();
        inicializarListeners();

        crearSignInCliente();

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            Usuario usuario = new Usuario();
            usuario.setIdToken(account.getIdToken());
            verificarIdToken(usuario.toString(), "ingresarGoogle");
        }

    }

    private void mapearElementos() {
        btnIngresarGoogle = findViewById(R.id.btnIngresarGoogle);
        btnIngresar = findViewById(R.id.btnIngresar);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtContrasenia = findViewById(R.id.txtContrasenia);
    }

    private void inicializarListeners() {
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion(v);
            }
        });

        btnIngresarGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnIngresarGoogle:
                        cargarCuentasGoogle();
                        break;
                    // ...
                }

            }
        });
    }

    protected void iniciarSesion(View view) {
        Usuario usuario;
        Persona persona;
        String correo = txtUsuario.getText().toString().trim();
        String contrasenia = txtContrasenia.getText().toString().trim();

        if (!correo.isEmpty() || !contrasenia.isEmpty()) {
            usuario = new Usuario();
            persona = new Persona();

            persona.setCorreo(correo);
            persona.setContrasenia(contrasenia);
            usuario.setPersona(persona);

            verificarIdToken(usuario.toString(), "ingresar");
        } else {
            Snackbar.make(view, "Correo y/o contraseña faltante", Snackbar.LENGTH_SHORT)
                    .show();
        }
    }


    protected void crearSignInCliente() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void cargarCuentasGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Usuario usuario = new Usuario();
            usuario.setIdToken(account.getIdToken());
            verificarIdToken(usuario.toString(), "ingresarGoogle");
            // Signed in successfully, show authenticated UI.
            // cambiarIntent(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    private void verificarIdToken(final String usuario, String ingresar) {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = EasyPCCommons.URL_USUARIO + ingresar;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.d("Respuesta", response);

                        if (!response.contains("error") && response.contains("correo")) {
                            cambiarIntent(response);
                        } else {
                            View view = findViewById(R.id.txtUsuario).getRootView();

                            Snackbar.make(view, "Datos incorrectos", Snackbar.LENGTH_SHORT)
                                    .show();

                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", String.valueOf(error));
//                        txtView.setText(error.toString());

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("usuario", usuario);

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

    }

    private void cambiarIntent(String account) {
        if (account != null) {
            Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
            intent.putExtra("cuenta", account);
            startActivity(intent);
        }

    }

}
