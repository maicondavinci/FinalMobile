package com.example.bytebusters;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificacionesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotificaciones;
    private NotificacionesAdapter adapter;
    private List<Notificacion> notificacionesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        recyclerViewNotificaciones = findViewById(R.id.recyclerViewNotificaciones);
        recyclerViewNotificaciones.setLayoutManager(new LinearLayoutManager(this));

        notificacionesList = new ArrayList<>();
        cargarNotificaciones();

        adapter = new NotificacionesAdapter(notificacionesList);
        recyclerViewNotificaciones.setAdapter(adapter);
    }

    private void cargarNotificaciones() {
        notificacionesList.add(new Notificacion(R.drawable.gift, "¡Nueva gift card disponible con 10% de descuento!"));
        notificacionesList.add(new Notificacion(R.drawable.discount, "Aprovecha las ofertas del mes en nuestras gift cards."));
        notificacionesList.add(new Notificacion(R.drawable.promo, "Promoción especial: compra 2 y lleva 1 gratis."));
        notificacionesList.add(new Notificacion(R.drawable.cards, "Has recibido una gift card de regalo por tu fidelidad."));
    }
}
