package com.example.remember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CrearCuentaActivity extends AppCompatActivity {
    private Button btnCrearCuenta;
    private TextView cuentaRegistradaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_cuenta);

        btnCrearCuenta = findViewById(R.id.crear_cuenta);
        cuentaRegistradaTextView = findViewById(R.id.cuenta_registrada);

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearCuentaActivity.this, RegistroDoctorActivity.class);
                startActivity(intent);
            }
        });

        cuentaRegistradaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearCuentaActivity.this, IniciarSesionActivity.class);
                startActivity(intent);
            }
        });
    }
}
