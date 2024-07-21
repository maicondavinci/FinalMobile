package com.example.bytebusters;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.List;
import java.util.Arrays;

public class DetalleGiftCardActivity extends AppCompatActivity {

    private ImageView imagenGiftCardImageView;
    private TextView nombreGiftCardTextView, descripcionTextView;
    private Button comprarButton, favoritosButton;
    private Button valor25Button, valor50Button, valor100Button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_gift_card);

        imagenGiftCardImageView = findViewById(R.id.imagenGiftCardImageView);
        nombreGiftCardTextView = findViewById(R.id.nombreGiftCardTextView);
        descripcionTextView = findViewById(R.id.descripcionTextView);
        comprarButton = findViewById(R.id.comprarButton);
        favoritosButton = findViewById(R.id.favoritosButton);
        valor25Button = findViewById(R.id.valor25Button);
        valor50Button = findViewById(R.id.valor50Button);
        valor100Button = findViewById(R.id.valor100Button);

        // Obtener los datos de la gift card del intent
        Intent intent = getIntent();
        if (intent != null) {
            String nombreGiftCard = intent.getStringExtra("NOMBRE_GIFTCARD");
            String descripcion = intent.getStringExtra("DESCRIPCION");
            int imagenResId = intent.getIntExtra("IMAGEN_RES_ID", R.drawable.adidas);

            nombreGiftCardTextView.setText(nombreGiftCard);
            descripcionTextView.setText(descripcion);
            imagenGiftCardImageView.setImageResource(imagenResId);
        }

        // Lista de botones para seleccionar valores
        List<Button> buttons = Arrays.asList(valor25Button, valor50Button, valor100Button);

        // Configurar onClickListeners para cambiar el color de los botones seleccionados
        for (Button button : buttons) {
            button.setOnClickListener(v -> {
                for (Button btn : buttons) {
                    btn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonNormalValores));
                }
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonPressed));
            });
        }

        comprarButton.setOnClickListener(v -> {
            // Lógica para comprar
        });

        favoritosButton.setOnClickListener(v -> {
            // Lógica para añadir a favoritos
        });
    }
}
