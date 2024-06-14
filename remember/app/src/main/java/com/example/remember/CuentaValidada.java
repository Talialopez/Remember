package com.example.remember;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CuentaValidada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmacion_validacion);

        // Configurar el botón de continuar
        Button btnContinuarVerificacion = findViewById(R.id.btn_continuar_verificacion);
        btnContinuarVerificacion.setOnClickListener(v -> {
            Intent intent = new Intent(CuentaValidada.this, MenuDoctor.class);
            startActivity(intent);
            finish();
        });
    }
}
