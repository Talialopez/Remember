package com.example.remember;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.remember.modelos.Profesional;
import com.example.remember.red.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroDoctorActivity2 extends AppCompatActivity {
    private EditText emailTextView, contrasenaTextView, repetirContrasenaTextView;
    private Button continuarButton;
    private ApiService servicioApi;
    private Profesional profesional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_doctor2);

        emailTextView = findViewById(R.id.email_registro_paciente);
        contrasenaTextView = findViewById(R.id.contrasena_paciente);
        repetirContrasenaTextView = findViewById(R.id.confirmar_contraseña);
        continuarButton = findViewById(R.id.iniciar_secion_paciente);

        profesional = (Profesional) getIntent().getSerializableExtra("profesional");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") // Asegúrate de usar la IP correcta si estás en un emulador
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioApi = retrofit.create(ApiService.class);

        continuarButton.setOnClickListener(v -> registrarProfesional());
    }

    private void registrarProfesional() {
        String email = emailTextView.getText().toString();
        String contrasena = contrasenaTextView.getText().toString();
        String repetirContrasena = repetirContrasenaTextView.getText().toString();

        if (!contrasena.equals(repetirContrasena)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        profesional.setEmail(email);
        profesional.setContrasena(contrasena);

        Call<Profesional> call = servicioApi.registrarProfesional(profesional);
        call.enqueue(new Callback<Profesional>() {
            @Override
            public void onResponse(Call<Profesional> call, Response<Profesional> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegistroDoctorActivity2.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistroDoctorActivity2.this, ConfirmacionValidacionActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegistroDoctorActivity2.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Profesional> call, Throwable t) {
                Toast.makeText(RegistroDoctorActivity2.this, "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
