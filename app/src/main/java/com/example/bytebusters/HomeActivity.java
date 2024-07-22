package com.example.bytebusters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.cardview.widget.CardView;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    private CardView cardGiftcards, cardPerfil, cardNotificaciones, cardMisCompras;
    private Button logoutButton;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private TextView greetingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardGiftcards = findViewById(R.id.card_giftcards);
        cardPerfil = findViewById(R.id.card_perfil);
        cardNotificaciones = findViewById(R.id.card_notificaciones);
        cardMisCompras = findViewById(R.id.card_mis_compras);
        logoutButton = findViewById(R.id.logoutButton);
        greetingTextView = findViewById(R.id.greetingTextView);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference("usuarios").child(user.getUid());
            // Cargar el nombre del usuario
            loadUserName();
        }

        cardGiftcards.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, GiftCardsActivity.class)));
        cardPerfil.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, PerfilActivity.class)));
        cardNotificaciones.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, NotificacionesActivity.class)));
        cardMisCompras.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, MisComprasActivity.class)));

        logoutButton.setOnClickListener(v -> {
            firebaseAuth.signOut();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        });

        // Solicitar permisos de ubicación
        getLocationPermission();
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
            }
        }
    }

    private void loadUserName() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nombre = snapshot.child("nombre").getValue(String.class);
                    greetingTextView.setText("Hola " + nombre + "!");
                } else {
                    greetingTextView.setText("Hola!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "Failed to load user name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
