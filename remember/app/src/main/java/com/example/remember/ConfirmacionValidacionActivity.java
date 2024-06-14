package com.example.remember;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmacionValidacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmacion_verificacion);

        // Configurar el botón de salir
        Button btnSalirVerificacion = findViewById(R.id.btn_salir_verificacion);
        btnSalirVerificacion.setOnClickListener(v -> {
            Intent salirIntent = new Intent(ConfirmacionValidacionActivity.this, MainActivity.class);
            startActivity(salirIntent);
            finish();
        });
    }
}
