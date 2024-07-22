package com.example.bytebusters;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PerfilActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private EditText nombreEditText, apellidoEditText, fechaNacimientoEditText, nacionalidadEditText, dniEditText;
    private Spinner generoSpinner;
    private Switch notificacionesSwitch;
    private Button saveButton, contactoDireccionButton;
    private ProgressDialog progressDialog;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Uri imageUri;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        profileImageView = findViewById(R.id.profileImageView);
        nombreEditText = findViewById(R.id.nombreEditText);
        apellidoEditText = findViewById(R.id.apellidoEditText);
        fechaNacimientoEditText = findViewById(R.id.fechaNacimientoEditText);
        generoSpinner = findViewById(R.id.generoEditText);
        nacionalidadEditText = findViewById(R.id.nacionalidadEditText);
        dniEditText = findViewById(R.id.dniEditText);
        notificacionesSwitch = findViewById(R.id.notificacionesSwitch);
        saveButton = findViewById(R.id.saveButton);
        contactoDireccionButton = findViewById(R.id.contactoDireccionButton);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Aguarde...");

        adapter = ArrayAdapter.createFromResource(this,
                R.array.genero_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        generoSpinner.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference("usuarios").child(user.getUid());
            storageReference = FirebaseStorage.getInstance().getReference("profile_images").child(user.getUid() + ".jpg");

            loadUserProfile();

            saveButton.setOnClickListener(v -> saveUserProfile());

            profileImageView.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            });

            fechaNacimientoEditText.setOnClickListener(v -> showDatePickerDialog());

            contactoDireccionButton.setOnClickListener(v -> {
                Intent intent = new Intent(PerfilActivity.this, ContactoDireccionActivity.class);
                startActivity(intent);
            });
        }
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> fechaNacimientoEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1),
                year, month, day);
        datePickerDialog.show();
    }

    private void loadUserProfile() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nombre = snapshot.child("nombre").getValue(String.class);
                    String apellido = snapshot.child("apellido").getValue(String.class);
                    String fechaNacimiento = snapshot.child("fechaNacimiento").getValue(String.class);
                    String genero = snapshot.child("genero").getValue(String.class);
                    String nacionalidad = snapshot.child("nacionalidad").getValue(String.class);
                    String dni = snapshot.child("dni").getValue(String.class);
                    Boolean notificaciones = snapshot.child("notificaciones").getValue(Boolean.class);
                    String profileImageUrl = snapshot.child("profileImageUrl").getValue(String.class);

                    nombreEditText.setText(nombre);
                    apellidoEditText.setText(apellido);
                    fechaNacimientoEditText.setText(fechaNacimiento);
                    if (genero != null) {
                        int spinnerPosition = adapter.getPosition(genero);
                        generoSpinner.setSelection(spinnerPosition);
                    }
                    nacionalidadEditText.setText(nacionalidad);
                    dniEditText.setText(dni);
                    notificacionesSwitch.setChecked(notificaciones != null ? notificaciones : false);

                    if (profileImageUrl != null) {
                        loadProfileImage(profileImageUrl);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PerfilActivity.this, "Failed to load profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProfileImage(String profileImageUrl) {
        StorageReference fileRef = FirebaseStorage.getInstance().getReferenceFromUrl(profileImageUrl);
        fileRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(bytes -> {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            profileImageView.setImageBitmap(bitmap);
        }).addOnFailureListener(e -> Toast.makeText(PerfilActivity.this, "Failed to load image", Toast.LENGTH_SHORT).show());
    }

    private void saveUserProfile() {
        progressDialog.show();

        String nombre = nombreEditText.getText().toString();
        String apellido = apellidoEditText.getText().toString();
        String fechaNacimiento = fechaNacimientoEditText.getText().toString();
        String genero = generoSpinner.getSelectedItem().toString();
        String nacionalidad = nacionalidadEditText.getText().toString();
        String dni = dniEditText.getText().toString();
        boolean notificaciones = notificacionesSwitch.isChecked();

        Map<String, Object> userUpdates = new HashMap<>();
        userUpdates.put("nombre", nombre);
        userUpdates.put("apellido", apellido);
        userUpdates.put("fechaNacimiento", fechaNacimiento);
        userUpdates.put("genero", genero);
        userUpdates.put("nacionalidad", nacionalidad);
        userUpdates.put("dni", dni);
        userUpdates.put("notificaciones", notificaciones);

        if (imageUri != null) {
            StorageReference fileRef = storageReference;
            fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                userUpdates.put("profileImageUrl", uri.toString());
                databaseReference.updateChildren(userUpdates);
                loadProfileImage(uri.toString());
                progressDialog.dismiss();
                Toast.makeText(PerfilActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
            })).addOnFailureListener(e -> {
                progressDialog.dismiss();
                Toast.makeText(PerfilActivity.this, "Image Upload Failed", Toast.LENGTH_SHORT).show();
            });
        } else {
            databaseReference.updateChildren(userUpdates).addOnCompleteListener(task -> {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(PerfilActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PerfilActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
        }
    }
}
