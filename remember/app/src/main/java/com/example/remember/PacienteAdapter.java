package com.example.remember;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.remember.modelos.Paciente;
import com.example.remember.modelos.PacienteDTO;
import java.util.List;

public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder> {
    private List<Paciente> pacientesList;
    private Context context;

    public PacienteAdapter(List<Paciente> pacientesList, Context context) {
        this.pacientesList = pacientesList;
        this.context = context;
    }

    @NonNull
    @Override
    public PacienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_paciente, parent, false);
        return new PacienteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PacienteViewHolder holder, int position) {
        Paciente paciente = pacientesList.get(position);
        holder.nombreTextView.setText(paciente.getNombre());
        holder.apellidoTextView.setText(paciente.getApellido());

        holder.itemView.setOnClickListener(v -> {
            PacienteDTO pacienteDTO = new PacienteDTO(
                    paciente.getDni(),
                    paciente.getNombre(),
                    paciente.getApellido(),
                    paciente.getEmail(),
                    paciente.getContrasena(),
                    paciente.getProfesionalId()
            );

            Intent intent = new Intent(context, PerfilPaciente.class);
            intent.putExtra("paciente", pacienteDTO);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pacientesList.size();
    }

    public static class PacienteViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreTextView;
        public TextView apellidoTextView;

        public PacienteViewHolder(View view) {
            super(view);
            nombreTextView = view.findViewById(R.id.nombreTextView);
            apellidoTextView = view.findViewById(R.id.apellidoTextView);
        }
    }
}
