package com.example.bytebusters;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CompraDetalleActivity extends AppCompatActivity {

    private TextView nombreGiftCardTextView, fechaCompraTextView, fechaExpiracionTextView, valorTextView, codigoTextView;
    private ImageView giftCardImageView;
    private Button cancelarCompraButton, facturaButton, personalizarRegaloButton, activarGiftCardButton, renovarVencimientoButton, dondeUsarButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_detalle);

        giftCardImageView = findViewById(R.id.giftCardImageView);
        nombreGiftCardTextView = findViewById(R.id.nombreGiftCardTextView);
        fechaCompraTextView = findViewById(R.id.fechaCompraTextView);
        fechaExpiracionTextView = findViewById(R.id.fechaExpiracionTextView);
        valorTextView = findViewById(R.id.valorTextView);
        codigoTextView = findViewById(R.id.codigoTextView);

        cancelarCompraButton = findViewById(R.id.cancelarCompraButton);
        facturaButton = findViewById(R.id.facturaButton);
        personalizarRegaloButton = findViewById(R.id.personalizarRegaloButton);
        activarGiftCardButton = findViewById(R.id.activarGiftCardButton);
        renovarVencimientoButton = findViewById(R.id.renovarVencimientoButton);
        dondeUsarButton = findViewById(R.id.dondeUsarButton);

        // Obtener los datos de la compra del intent
        Intent intent = getIntent();
        if (intent != null) {
            String nombreGiftCard = intent.getStringExtra("NOMBRE_GIFTCARD");
            String fechaCompra = intent.getStringExtra("FECHA_COMPRA");
            String fechaExpiracion = intent.getStringExtra("FECHA_EXPIRACION");
            String valor = intent.getStringExtra("VALOR");
            String codigo = intent.getStringExtra("CODIGO");
            int imagenResourceId = intent.getIntExtra("IMAGEN_RESOURCE_ID", R.drawable.adidas); // Valor por defecto

            nombreGiftCardTextView.setText(nombreGiftCard);
            fechaCompraTextView.setText("Fecha de compra: " + fechaCompra);
            fechaExpiracionTextView.setText("Fecha de expiración: " + fechaExpiracion);
            valorTextView.setText("Valor: $" + valor);
            codigoTextView.setText("Código: " + codigo);
            giftCardImageView.setImageResource(imagenResourceId);
        }
    }
}
