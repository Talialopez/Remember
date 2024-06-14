package com.example.remember;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.remember.modelos.AuthRequest;
import com.example.remember.modelos.AuthResponse;
import com.example.remember.red.ApiService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IniciarSesionActivity extends AppCompatActivity {

    private EditText emailTextView, contrasenaTextView;
    private Button iniciarSesionButton;
    private ApiService servicioApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_sesion);

        emailTextView = findViewById(R.id.editTextTextEmailAddress);
        contrasenaTextView = findViewById(R.id.editTextTextPassword);
        iniciarSesionButton = findViewById(R.id.btn_iniciar_sesion);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioApi = retrofit.create(ApiService.class);

        iniciarSesionButton.setOnClickListener(v -> iniciarSesion());
    }

    private void iniciarSesion() {
        String email = emailTextView.getText().toString();
        String contrasena = contrasenaTextView.getText().toString();

        AuthRequest authRequest = new AuthRequest(email, contrasena);

        Call<AuthResponse> call = servicioApi.iniciarSesion(authRequest);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AuthResponse authResponse = response.body();
                    Log.d("LoginResponse", "Nombre: " + authResponse.getNombre() + ", Validated: " + authResponse.isValidated());
                    if (authResponse.isValidated()) {
                        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("NombreUsuario", authResponse.getNombre());
                        editor.putLong("ProfesionalId", authResponse.getProfesionalId()); // Guardar profesionalId

                        // Guardar la lista de pacientes como un JSON
                        Gson gson = new Gson();
                        String pacientesJson = gson.toJson(authResponse.getPacientes());
                        editor.putString("Pacientes", pacientesJson);

                        editor.apply();

                        Log.d("LoginResponse", "NombreUsuario y Pacientes guardados en SharedPreferences");

                        Intent intent = new Intent(IniciarSesionActivity.this, MenuDoctor.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(IniciarSesionActivity.this, "Cuenta no validada", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("LoginError", "Error en el inicio de sesión: " + response.errorBody());
                    Toast.makeText(IniciarSesionActivity.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("LoginFailure", "Fallo en la conexión: " + t.getMessage());
                Toast.makeText(IniciarSesionActivity.this, "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
