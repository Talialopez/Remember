package com.example.remember;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.remember.modelos.HistorialMedico;

import java.util.List;

public class HistorialMedicoAdapter extends RecyclerView.Adapter<HistorialMedicoAdapter.ViewHolder> {

    private List<HistorialMedico> historialMedicoList;

    public HistorialMedicoAdapter(List<HistorialMedico> historialMedicoList) {
        this.historialMedicoList = historialMedicoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.historial_medico_consulta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistorialMedico historialMedico = historialMedicoList.get(position);
        holder.fechaTextView.setText("Fecha: " + historialMedico.getFecha());
        holder.motivoConsultaTextView.setText("Motivo de la Consulta: " + historialMedico.getMotivoConsulta());
    }

    @Override
    public int getItemCount() {
        return historialMedicoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fechaTextView;
        public TextView motivoConsultaTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            fechaTextView = itemView.findViewById(R.id.texto_fecha);
            motivoConsultaTextView = itemView.findViewById(R.id.texto_motivo_consulta);
        }
    }
}
