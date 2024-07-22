package com.example.bytebusters;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AyudaSoporteActivity extends AppCompatActivity {

    private EditText nombreEditText, correoEditText, pedidoEditText, asuntoEditText, mensajeEditText;
    private Button enviarButton, telefonoButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_soporte);

        nombreEditText = findViewById(R.id.nombreEditText);
        correoEditText = findViewById(R.id.correoEditText);
        pedidoEditText = findViewById(R.id.pedidoEditText);
        asuntoEditText = findViewById(R.id.asuntoEditText);
        mensajeEditText = findViewById(R.id.mensajeEditText);

        enviarButton = findViewById(R.id.enviarButton);
        telefonoButton = findViewById(R.id.telefonoButton);

        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarFormulario();
            }
        });

        telefonoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelefono();
            }
        });
    }

    private void enviarFormulario() {
        String nombre = nombreEditText.getText().toString().trim();
        String correo = correoEditText.getText().toString().trim();
        String pedido = pedidoEditText.getText().toString().trim();
        String asunto = asuntoEditText.getText().toString().trim();
        String mensaje = mensajeEditText.getText().toString().trim();

        if (nombre.isEmpty() || correo.isEmpty() || asunto.isEmpty() || mensaje.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            // Aquí puedes agregar la lógica para enviar el formulario
            Toast.makeText(this, "Formulario enviado", Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirTelefono() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Teléfonos de Soporte")
                .setMessage("Soporte Técnico: 123-456-7890\nAtención al Cliente: 098-765-4321")
                .setPositiveButton("OK", null)
                .show();
    }
}
