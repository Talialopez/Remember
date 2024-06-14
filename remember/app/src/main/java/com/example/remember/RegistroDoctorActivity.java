package com.example.remember;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.remember.modelos.CentroMedico;
import com.example.remember.modelos.Profesional;
import com.example.remember.red.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroDoctorActivity extends AppCompatActivity {
    private EditText nombreTextView, apellidoTextView, numeroColegiadoTextView;
    private Spinner centroMedicoSpinner;
    private Button continuarButton;
    private ApiService servicioApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_doctor);

        // Inicializar vistas
        nombreTextView = findViewById(R.id.nombre_registro_paciente);
        apellidoTextView = findViewById(R.id.apellido);
        centroMedicoSpinner = findViewById(R.id.spinner);
        numeroColegiadoTextView = findViewById(R.id.numeroColegiado);
        continuarButton = findViewById(R.id.iniciar_secion_paciente);

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") // Asegúrate de usar la IP correcta si estás en un emulador
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioApi = retrofit.create(ApiService.class);

        // Obtener los centros médicos y llenar el spinner
        obtenerCentrosMedicos();

        // Configurar el botón de continuar
        continuarButton.setOnClickListener(v -> continuarRegistro());
    }

    private void obtenerCentrosMedicos() {
        Call<List<CentroMedico>> call = servicioApi.obtenerCentrosMedicos();
        call.enqueue(new Callback<List<CentroMedico>>() {
            @Override
            public void onResponse(Call<List<CentroMedico>> call, Response<List<CentroMedico>> response) {
                if (response.isSuccessful()) {
                    List<CentroMedico> centrosMedicos = response.body();
                    ArrayAdapter<CentroMedico> adapter = new ArrayAdapter<>(RegistroDoctorActivity.this, android.R.layout.simple_spinner_item, centrosMedicos);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    centroMedicoSpinner.setAdapter(adapter);
                } else {
                    Toast.makeText(RegistroDoctorActivity.this, "Error al obtener los centros médicos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CentroMedico>> call, Throwable t) {
                Toast.makeText(RegistroDoctorActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void continuarRegistro() {
        String nombre = nombreTextView.getText().toString();
        String apellido = apellidoTextView.getText().toString();
        Long centroMedicoId = ((CentroMedico) centroMedicoSpinner.getSelectedItem()).getId();
        Long identificacionUnica = Long.parseLong(numeroColegiadoTextView.getText().toString());

        Profesional profesional = new Profesional();
        profesional.setNombre(nombre);
        profesional.setApellido(apellido);
        profesional.setCentroMedicoId(centroMedicoId);
        profesional.setIdentificacionUnica(identificacionUnica);

        Intent intent = new Intent(RegistroDoctorActivity.this, RegistroDoctorActivity2.class);
        intent.putExtra("profesional", profesional);
        startActivity(intent);
    }
}
