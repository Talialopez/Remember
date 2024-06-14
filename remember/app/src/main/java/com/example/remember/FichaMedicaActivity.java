package com.example.remember;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.remember.modelos.FichaMedica;
import com.example.remember.red.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FichaMedicaActivity extends AppCompatActivity {
    private TextView nombreCompletoTextView;
    private TextView edadTextView;
    private TextView generoTextView;
    private TextView fechaNacimientoTextView;
    private TextView tipoSangreTextView;
    private TextView epsTextView;
    private TextView direccionResidenciaTextView;
    private TextView pesoTextView;
    private TextView estaturaTextView;
    private TextView esquemaVacunacionTextView;
    private TextView enfermedadesOjosTextView;
    private TextView lentesPermanentesTextView;
    private TextView protesisTextView;
    private TextView alergiasTextView;
    private TextView cirugiasTextView;
    private TextView condicionesEspecialesTextView;
    private TextView diabetesTextView;
    private TextView cancerTextView;
    private TextView hipertensionTextView;
    private TextView enfermedadesCardiovascularesTextView;
    private TextView otrasEnfermedadesTextView;

    private ApiService servicioApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_medica);

        nombreCompletoTextView = findViewById(R.id.texto_nombre_completo);
        edadTextView = findViewById(R.id.texto_edad);
        generoTextView = findViewById(R.id.texto_genero);
        fechaNacimientoTextView = findViewById(R.id.texto_fecha_nacimiento);
        tipoSangreTextView = findViewById(R.id.texto_tipo_sangre);
        epsTextView = findViewById(R.id.texto_eps);
        direccionResidenciaTextView = findViewById(R.id.texto_direccion);
        pesoTextView = findViewById(R.id.texto_peso);
        estaturaTextView = findViewById(R.id.texto_estatura);
        esquemaVacunacionTextView = findViewById(R.id.texto_vacunacion);
        enfermedadesOjosTextView = findViewById(R.id.texto_enfermedades_ojos);
        lentesPermanentesTextView = findViewById(R.id.texto_lentes);
        protesisTextView = findViewById(R.id.texto_protesis);
        alergiasTextView = findViewById(R.id.texto_alergias);
        cirugiasTextView = findViewById(R.id.texto_cirugias);
        condicionesEspecialesTextView = findViewById(R.id.texto_condiciones_especiales);
        diabetesTextView = findViewById(R.id.texto_diabetes);
        cancerTextView = findViewById(R.id.texto_cancer);
        hipertensionTextView = findViewById(R.id.texto_hipertension);
        enfermedadesCardiovascularesTextView = findViewById(R.id.texto_enfermedades_cardiovasculares);
        otrasEnfermedadesTextView = findViewById(R.id.texto_otras_enfermedades);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioApi = retrofit.create(ApiService.class);

        String dniPaciente = getIntent().getStringExtra("dniPaciente");
        obtenerFichaMedica(dniPaciente);
    }

    private void obtenerFichaMedica(String dni) {
        Call<FichaMedica> call = servicioApi.getFichaMedica(dni);
        call.enqueue(new Callback<FichaMedica>() {
            @Override
            public void onResponse(Call<FichaMedica> call, Response<FichaMedica> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FichaMedica fichaMedica = response.body();
                    nombreCompletoTextView.setText(fichaMedica.getNombreCompleto());
                    edadTextView.setText(String.valueOf(fichaMedica.getEdad()));
                    generoTextView.setText(fichaMedica.getGenero());
                    fechaNacimientoTextView.setText(fichaMedica.getFechaNacimiento());
                    tipoSangreTextView.setText(fichaMedica.getTipoSangre());
                    epsTextView.setText(fichaMedica.getEps());
                    direccionResidenciaTextView.setText(fichaMedica.getDireccionResidencia());
                    pesoTextView.setText(String.valueOf(fichaMedica.getPeso()));
                    estaturaTextView.setText(String.valueOf(fichaMedica.getEstatura()));
                    esquemaVacunacionTextView.setText(fichaMedica.getEsquemaVacunacion());
                    enfermedadesOjosTextView.setText(fichaMedica.getEnfermedadesOjos());
                    lentesPermanentesTextView.setText(fichaMedica.getLentesPermanentes());
                    protesisTextView.setText(fichaMedica.getProtesis());
                    alergiasTextView.setText(fichaMedica.getAlergias());
                    cirugiasTextView.setText(fichaMedica.getCirugias());
                    condicionesEspecialesTextView.setText(fichaMedica.getCondicionesEspeciales());
                    diabetesTextView.setText(fichaMedica.getDiabetes());
                    cancerTextView.setText(fichaMedica.getCancer());
                    hipertensionTextView.setText(fichaMedica.getHipertension());
                    enfermedadesCardiovascularesTextView.setText(fichaMedica.getEnfermedadesCardiovasculares());
                    otrasEnfermedadesTextView.setText(fichaMedica.getOtrasEnfermedades());
                }
            }

            @Override
            public void onFailure(Call<FichaMedica> call, Throwable t) {
                // Manejo de errores
            }
        });
    }
}
