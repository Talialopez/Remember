package com.example.remember;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.remember.modelos.PacienteDTO;

public class PerfilPaciente extends AppCompatActivity {
    private TextView fichaMedicaTextView;
    private TextView historialMedicoTextView;
    private PacienteDTO paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_paciente);

        paciente = (PacienteDTO) getIntent().getSerializableExtra("paciente");

        fichaMedicaTextView = findViewById(R.id.ficha_medica);
        fichaMedicaTextView.setOnClickListener(v -> abrirFichaMedica());

        historialMedicoTextView = findViewById(R.id.historial_medico);
        historialMedicoTextView.setOnClickListener(v -> abrirHistorialMedico());
    }

    private void abrirFichaMedica() {
        Intent intent = new Intent(PerfilPaciente.this, FichaMedicaActivity.class);
        intent.putExtra("dniPaciente", paciente.getDni());
        startActivity(intent);
    }

    private void abrirHistorialMedico() {
        Intent intent = new Intent(PerfilPaciente.this, HistorialMedicoActivity.class);
        intent.putExtra("dniPaciente", paciente.getDni());
        startActivity(intent);
    }
}
