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

public class DireccionFragment extends Fragment {

    private EditText calleEditText, numeroEditText, pisoEditText, departamentoEditText, ciudadEditText, provinciaEditText, codigoPostalEditText, paisEditText, localidadEditText;
    private Button saveButton;

    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_direccion, container, false);

        calleEditText = view.findViewById(R.id.calleEditText);
        numeroEditText = view.findViewById(R.id.numeroEditText);
        pisoEditText = view.findViewById(R.id.pisoEditText);
        departamentoEditText = view.findViewById(R.id.departamentoEditText);
        ciudadEditText = view.findViewById(R.id.ciudadEditText);
        provinciaEditText = view.findViewById(R.id.provinciaEditText);
        codigoPostalEditText = view.findViewById(R.id.codigoPostalEditText);
        paisEditText = view.findViewById(R.id.paisEditText);
        localidadEditText = view.findViewById(R.id.localidadEditText); // Nuevo campo
        saveButton = view.findViewById(R.id.saveButton);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference("usuarios").child(user.getUid());

            // Cargar datos desde Firebase
            loadDireccionData();

            saveButton.setOnClickListener(v -> saveDireccion());
        }

        return view;
    }

    private void loadDireccionData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String calle = snapshot.child("calle").getValue(String.class);
                    String numero = snapshot.child("numero").getValue(String.class);
                    String piso = snapshot.child("piso").getValue(String.class);
                    String departamento = snapshot.child("departamento").getValue(String.class);
                    String ciudad = snapshot.child("ciudad").getValue(String.class);
                    String provincia = snapshot.child("provincia").getValue(String.class);
                    String codigoPostal = snapshot.child("codigoPostal").getValue(String.class);
                    String pais = snapshot.child("pais").getValue(String.class);
                    String localidad = snapshot.child("localidad").getValue(String.class); // Nuevo campo

                    // Mostrar los datos en los EditText si existen
                    calleEditText.setText(calle != null ? calle : "");
                    numeroEditText.setText(numero != null ? numero : "");
                    pisoEditText.setText(piso != null ? piso : "");
                    departamentoEditText.setText(departamento != null ? departamento : "");
                    ciudadEditText.setText(ciudad != null ? ciudad : "");
                    provinciaEditText.setText(provincia != null ? provincia : "");
                    codigoPostalEditText.setText(codigoPostal != null ? codigoPostal : "");
                    paisEditText.setText(pais != null ? pais : "");
                    localidadEditText.setText(localidad != null ? localidad : ""); // Nuevo campo
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveDireccion() {
        String calle = calleEditText.getText().toString();
        String numero = numeroEditText.getText().toString();
        String piso = pisoEditText.getText().toString();
        String departamento = departamentoEditText.getText().toString();
        String ciudad = ciudadEditText.getText().toString();
        String provincia = provinciaEditText.getText().toString();
        String codigoPostal = codigoPostalEditText.getText().toString();
        String pais = paisEditText.getText().toString();
        String localidad = localidadEditText.getText().toString(); // Nuevo campo

        // Crear un mapa con los campos (incluyendo los vacíos)
        Map<String, Object> addressUpdates = new HashMap<>();
        addressUpdates.put("calle", calle);
        addressUpdates.put("numero", numero);
        addressUpdates.put("piso", piso);
        addressUpdates.put("departamento", departamento);
        addressUpdates.put("ciudad", ciudad);
        addressUpdates.put("provincia", provincia);
        addressUpdates.put("codigoPostal", codigoPostal);
        addressUpdates.put("pais", pais);
        addressUpdates.put("localidad", localidad); // Nuevo campo

        // Actualizar en Firebase
        databaseReference.updateChildren(addressUpdates).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getActivity(), "Dirección actualizada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Error al actualizar la dirección", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
