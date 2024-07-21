package com.example.bytebusters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificacionesAdapter extends RecyclerView.Adapter<NotificacionesAdapter.NotificacionViewHolder> {

    private List<Notificacion> notificacionesList;

    public NotificacionesAdapter(List<Notificacion> notificacionesList) {
        this.notificacionesList = notificacionesList;
    }

    @NonNull
    @Override
    public NotificacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificacion, parent, false);
        return new NotificacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificacionViewHolder holder, int position) {
        Notificacion notificacion = notificacionesList.get(position);
        holder.notificacionIcon.setImageResource(notificacion.getIcono());
        holder.notificacionTexto.setText(notificacion.getTexto());
    }

    @Override
    public int getItemCount() {
        return notificacionesList.size();
    }

    static class NotificacionViewHolder extends RecyclerView.ViewHolder {

        ImageView notificacionIcon;
        TextView notificacionTexto;

        public NotificacionViewHolder(@NonNull View itemView) {
            super(itemView);
            notificacionIcon = itemView.findViewById(R.id.notificacionIcon);
            notificacionTexto = itemView.findViewById(R.id.notificacionTexto);
        }
    }
}
