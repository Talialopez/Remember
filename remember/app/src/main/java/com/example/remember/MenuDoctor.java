package com.example.remember;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remember.modelos.Paciente;
import com.example.remember.red.ApiService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuDoctor extends AppCompatActivity {
    private TextView nombreDoctorTextView;
    private RecyclerView recyclerViewPacientes;
    private PacienteAdapter pacienteAdapter;
    private List<Paciente> pacientesList = new ArrayList<>();
    private ApiService servicioApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pacientes); // Asegúrate de que este es el layout correcto

        nombreDoctorTextView = findViewById(R.id.nombre_doctor);
        recyclerViewPacientes = findViewById(R.id.recyclerViewPacientes);

        // Configura el RecyclerView con un LinearLayoutManager vertical
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewPacientes.setLayoutManager(layoutManager);
        pacienteAdapter = new PacienteAdapter(pacientesList, this);
        recyclerViewPacientes.setAdapter(pacienteAdapter);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String nombreDoctor = prefs.getString("NombreUsuario", "Usuario predeterminado");
        nombreDoctorTextView.setText("Hola, Dr. " + nombreDoctor);

        // Cargar los pacientes guardados en SharedPreferences
        String pacientesJson = prefs.getString("Pacientes", "[]");
        Gson gson = new Gson();
        Paciente[] pacientesArray = gson.fromJson(pacientesJson, Paciente[].class);
        pacientesList.addAll(Arrays.asList(pacientesArray));
        pacienteAdapter.notifyDataSetChanged();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioApi = retrofit.create(ApiService.class);

        // Manejar el botón de agregar paciente
        findViewById(R.id.boton_agregar).setOnClickListener(view -> {
            Intent intent = new Intent(MenuDoctor.this, NuevoPaciente.class);
            startActivity(intent);
        });
    }
}
