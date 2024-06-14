package com.example.remember.red;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.example.remember.modelos.FichaMedica;
import com.example.remember.modelos.AuthRequest;
import com.example.remember.modelos.AuthResponse;
import com.example.remember.modelos.CentroMedico;
import com.example.remember.modelos.HistorialMedico;
import com.example.remember.modelos.HistorialMedicoDTO;
import com.example.remember.modelos.JwtRespuesta;
import com.example.remember.modelos.JwtSolicitud;
import com.example.remember.modelos.Paciente;
import com.example.remember.modelos.PacienteDTO;
import com.example.remember.modelos.Profesional;

import java.util.List;

public interface ApiService {
    @GET("api/centrosmedicos")
    Call<List<CentroMedico>> obtenerCentrosMedicos();

    @POST("api/profesionales/register")
    Call<Profesional> registrarProfesional(@Body Profesional profesional);

    @POST("api/profesionales/login")
    Call<AuthResponse> iniciarSesion(@Body AuthRequest authRequest);


    @POST("api/pacientes/register")
    Call<Paciente> registrarPaciente(@Body PacienteDTO pacienteDTO);

    @GET("api/fichamedica/paciente/{dni}")
    Call<FichaMedica> getFichaMedica(@Path("dni") String dni);

    @GET("api/historialmedico/paciente/{dni}")
    Call<List<HistorialMedico>> getHistorialMedico(@Path("dni") String dni);

    @Headers("Content-Type: application/json")
    @POST("api/historialmedico/agregar")
    Call<Void> agregarHistorialMedico(@Body HistorialMedicoDTO historialMedicoDTO);

    Call<JwtRespuesta> autenticar(JwtSolicitud solicitud);
}
