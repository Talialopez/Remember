package com.example.remember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.remember.modelos.HistorialMedicoDTO;
import com.example.remember.red.ApiService;
import com.example.remember.red.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoRegistroActivity extends AppCompatActivity {

    private EditText editFecha, editConsulta, dniPaciente;
    private Button añadirRegistroButton;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_historial);

        editFecha = findViewById(R.id.edit_fecha);
        editConsulta = findViewById(R.id.edit_consulta);
        dniPaciente = findViewById(R.id.dni_paciente);
        añadirRegistroButton = findViewById(R.id.añadir_registro);

        apiService = ApiUtils.getAPIService();

        añadirRegistroButton.setOnClickListener(v -> agregarRegistro());
    }

    private void agregarRegistro() {
        String fecha = editFecha.getText().toString();
        String consulta = editConsulta.getText().toString();
        String dni = dniPaciente.getText().toString();

        HistorialMedicoDTO historialMedicoDTO = new HistorialMedicoDTO(dni, fecha, consulta);

        apiService.agregarHistorialMedico(historialMedicoDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(NuevoRegistroActivity.this, "Registro añadido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NuevoRegistroActivity.this, "Error al añadir el registro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(NuevoRegistroActivity.this, "Fallo en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
