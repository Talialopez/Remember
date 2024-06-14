package com.example.remember.modelos;

public class HistorialMedico {
    private String fecha;
    private String motivoConsulta;

    public HistorialMedico(String fecha, String motivoConsulta) {
        this.fecha = fecha;
        this.motivoConsulta = motivoConsulta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }
}
