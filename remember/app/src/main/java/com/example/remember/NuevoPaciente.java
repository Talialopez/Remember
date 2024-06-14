package com.example.remember;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.remember.modelos.Paciente;
import com.example.remember.modelos.PacienteDTO;
import com.example.remember.red.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NuevoPaciente extends AppCompatActivity {

    private EditText nombreEditText, apellidoEditText, dniEditText, contrasenaEditText, numeroColegiadoEditText;
    private Button finalizarRegistroButton;
    private ApiService servicioApi;
    private static final String TAG = "NuevoPaciente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_paciente);

        // Inicializar los EditText y el botón
        nombreEditText = findViewById(R.id.nombre_paciente);
        apellidoEditText = findViewById(R.id.edit_apellido);
        dniEditText = findViewById(R.id.edit_dni);
        contrasenaEditText = findViewById(R.id.edit_contraseña);
        numeroColegiadoEditText = findViewById(R.id.editText);
        finalizarRegistroButton = findViewById(R.id.finalizar_registro);

        // Agregar logs para verificar si los EditText se inicializan correctamente
        if (nombreEditText == null) {
            Log.e(TAG, "nombreEditText es null");
        } else {
            Log.d(TAG, "nombreEditText inicializado correctamente");
        }

        if (apellidoEditText == null) {
            Log.e(TAG, "apellidoEditText es null");
        } else {
            Log.d(TAG, "apellidoEditText inicializado correctamente");
        }

        if (dniEditText == null) {
            Log.e(TAG, "dniEditText es null");
        } else {
            Log.d(TAG, "dniEditText inicializado correctamente");
        }

        if (contrasenaEditText == null) {
            Log.e(TAG, "contrasenaEditText es null");
        } else {
            Log.d(TAG, "contrasenaEditText inicializado correctamente");
        }

        if (numeroColegiadoEditText == null) {
            Log.e(TAG, "numeroColegiadoEditText es null");
        } else {
            Log.d(TAG, "numeroColegiadoEditText inicializado correctamente");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioApi = retrofit.create(ApiService.class);

        finalizarRegistroButton.setOnClickListener(v -> registrarPaciente());
    }

    private void registrarPaciente() {
        String nombre = nombreEditText.getText().toString();
        String apellido = apellidoEditText.getText().toString();
        String dni = dniEditText.getText().toString();
        String contrasena = contrasenaEditText.getText().toString();
        Long numeroColegiado = Long.parseLong(numeroColegiadoEditText.getText().toString());

        PacienteDTO pacienteDTO = new PacienteDTO(dni, nombre, apellido, null, contrasena, numeroColegiado);

        Call<Paciente> call = servicioApi.registrarPaciente(pacienteDTO);
        call.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(NuevoPaciente.this, "Paciente registrado exitosamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(NuevoPaciente.this, "Error al registrar el paciente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {
                Toast.makeText(NuevoPaciente.this, "Error en la comunicación con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
