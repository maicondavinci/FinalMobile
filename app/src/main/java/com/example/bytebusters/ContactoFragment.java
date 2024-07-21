package com.example.bytebusters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ContactoFragment extends Fragment {

    private EditText codigoPaisTelEditText, codigoAreaTelEditText, numeroTelEditText, codigoPaisCelEditText, codigoAreaCelEditText, numeroCelEditText;
    private Button saveButton;

    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacto, container, false);

        codigoPaisTelEditText = view.findViewById(R.id.codigoPaisTelEditText);
        codigoAreaTelEditText = view.findViewById(R.id.codigoAreaTelEditText);
        numeroTelEditText = view.findViewById(R.id.numeroTelEditText);
        codigoPaisCelEditText = view.findViewById(R.id.codigoPaisCelEditText);
        codigoAreaCelEditText = view.findViewById(R.id.codigoAreaCelEditText);
        numeroCelEditText = view.findViewById(R.id.numeroCelEditText);
        saveButton = view.findViewById(R.id.saveButton);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference("usuarios").child(user.getUid());

            // Cargar datos desde Firebase
            loadContactoData();

            saveButton.setOnClickListener(v -> saveContacto());
        }

        return view;
    }

    private void loadContactoData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String codigoPaisTel = snapshot.child("codigoPaisTel").getValue(String.class);
                    String codigoAreaTel = snapshot.child("codigoAreaTel").getValue(String.class);
                    String numeroTel = snapshot.child("numeroTel").getValue(String.class);
                    String codigoPaisCel = snapshot.child("codigoPaisCel").getValue(String.class);
                    String codigoAreaCel = snapshot.child("codigoAreaCel").getValue(String.class);
                    String numeroCel = snapshot.child("numeroCel").getValue(String.class);

                    // Mostrar los datos en los EditText si existen
                    codigoPaisTelEditText.setText(codigoPaisTel != null ? codigoPaisTel : "");
                    codigoAreaTelEditText.setText(codigoAreaTel != null ? codigoAreaTel : "");
                    numeroTelEditText.setText(numeroTel != null ? numeroTel : "");
                    codigoPaisCelEditText.setText(codigoPaisCel != null ? codigoPaisCel : "");
                    codigoAreaCelEditText.setText(codigoAreaCel != null ? codigoAreaCel : "");
                    numeroCelEditText.setText(numeroCel != null ? numeroCel : "");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveContacto() {
        String codigoPaisTel = codigoPaisTelEditText.getText().toString();
        String codigoAreaTel = codigoAreaTelEditText.getText().toString();
        String numeroTel = numeroTelEditText.getText().toString();
        String codigoPaisCel = codigoPaisCelEditText.getText().toString();
        String codigoAreaCel = codigoAreaCelEditText.getText().toString();
        String numeroCel = numeroCelEditText.getText().toString();

        // Crear un mapa con los campos (incluyendo los vacíos)
        Map<String, Object> contactUpdates = new HashMap<>();
        contactUpdates.put("codigoPaisTel", codigoPaisTel);
        contactUpdates.put("codigoAreaTel", codigoAreaTel);
        contactUpdates.put("numeroTel", numeroTel);
        contactUpdates.put("codigoPaisCel", codigoPaisCel);
        contactUpdates.put("codigoAreaCel", codigoAreaCel);
        contactUpdates.put("numeroCel", numeroCel);

        // Actualizar en Firebase
        databaseReference.updateChildren(contactUpdates).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getActivity(), "Contacto actualizado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Error al actualizar el contacto", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
