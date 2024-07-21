package com.example.bytebusters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MisComprasActivity extends AppCompatActivity {

    private Button detallesButton1, detallesButton2, detallesButton3, detallesButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_compras);

        detallesButton1 = findViewById(R.id.detallesButton1);
        detallesButton2 = findViewById(R.id.detallesButton2);
        detallesButton3 = findViewById(R.id.detallesButton3);
        detallesButton4 = findViewById(R.id.detallesButton4);

        detallesButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetalleCompra("Adidas", "15/07/2024", "15/07/2025", "$50", "ADI12345", R.drawable.adidas);
            }
        });

        detallesButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetalleCompra("McDonalds", "12/07/2024", "12/07/2025", "$25", "MCD67890", R.drawable.mcdonalds);
            }
        });

        detallesButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetalleCompra("Burger King", "10/07/2024", "10/07/2025", "$30", "BK54321", R.drawable.burger_king);
            }
        });

        detallesButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetalleCompra("Nike", "18/07/2024", "18/07/2025", "$40", "NIKE12345", R.drawable.nike);
            }
        });
    }

    private void openDetalleCompra(String nombreGiftCard, String fechaCompra, String fechaExpiracion, String valor, String codigo, int imagenResourceId) {
        Intent intent = new Intent(MisComprasActivity.this, CompraDetalleActivity.class);
        intent.putExtra("NOMBRE_GIFTCARD", nombreGiftCard);
        intent.putExtra("FECHA_COMPRA", fechaCompra);
        intent.putExtra("FECHA_EXPIRACION", fechaExpiracion);
        intent.putExtra("VALOR", valor);
        intent.putExtra("CODIGO", codigo);
        intent.putExtra("IMAGEN_RESOURCE_ID", imagenResourceId);
        startActivity(intent);
    }
}
