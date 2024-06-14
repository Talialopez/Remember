package com.example.remember;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remember.modelos.HistorialMedico;
import com.example.remember.red.ApiService;
import com.example.remember.red.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialMedicoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistorialMedicoAdapter adapter;
    private List<HistorialMedico> historialMedicoList;
    private String dniPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial_medico);

        dniPaciente = getIntent().getStringExtra("dniPaciente");

        recyclerView = findViewById(R.id.recycler_view_historial);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historialMedicoList = new ArrayList<>();

        adapter = new HistorialMedicoAdapter(historialMedicoList);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.agregar_registro).setOnClickListener(v -> {
            Intent intent = new Intent(HistorialMedicoActivity.this, NuevoRegistroActivity.class);
            intent.putExtra("dniPaciente", dniPaciente);
            startActivity(intent);
        });

        cargarHistorialMedico();
    }

    private void cargarHistorialMedico() {
        ApiService apiService = ApiUtils.getAPIService();
        Call<List<HistorialMedico>> call = apiService.getHistorialMedico(dniPaciente);
        call.enqueue(new Callback<List<HistorialMedico>>() {
            @Override
            public void onResponse(Call<List<HistorialMedico>> call, Response<List<HistorialMedico>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    historialMedicoList.clear();
                    historialMedicoList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<HistorialMedico>> call, Throwable t) {
                // Manejar error
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarHistorialMedico();
    }
}
