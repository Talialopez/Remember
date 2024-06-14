package com.example.remember.modelos;

public class HistorialMedicoDTO {
    private String dniPaciente;
    private String fecha;
    private String motivoConsulta;

    // Constructor, getters y setters
    public HistorialMedicoDTO(String dniPaciente, String fecha, String motivoConsulta) {
        this.dniPaciente = dniPaciente;
        this.fecha = fecha;
        this.motivoConsulta = motivoConsulta;
    }

    // Getters y setters
    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
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
