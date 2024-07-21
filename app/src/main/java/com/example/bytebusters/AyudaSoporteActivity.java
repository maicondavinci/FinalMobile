package com.example.bytebusters;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AyudaSoporteActivity extends AppCompatActivity {

    private EditText nombreEditText, correoEditText, pedidoEditText, asuntoEditText, mensajeEditText;
    private Button enviarButton, faqButton, chatButton, telefonoButton;

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
        faqButton = findViewById(R.id.faqButton);
        chatButton = findViewById(R.id.chatButton);
        telefonoButton = findViewById(R.id.telefonoButton);

        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarFormulario();
            }
        });

        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirFAQ();
            }
        });

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirChat();
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

    private void abrirFAQ() {
        // Aquí puedes agregar la lógica para abrir la sección de preguntas frecuentes
        Toast.makeText(this, "Abrir FAQ", Toast.LENGTH_SHORT).show();
    }

    private void abrirChat() {
        // Aquí puedes agregar la lógica para abrir el chat en vivo
        Toast.makeText(this, "Abrir Chat en Vivo", Toast.LENGTH_SHORT).show();
    }

    private void abrirTelefono() {
        // Aquí puedes agregar la lógica para mostrar el número de teléfono de soporte
        Toast.makeText(this, "Abrir Teléfono", Toast.LENGTH_SHORT).show();
    }
}
