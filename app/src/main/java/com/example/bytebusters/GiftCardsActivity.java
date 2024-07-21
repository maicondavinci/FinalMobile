package com.example.bytebusters;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class GiftCardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_cards);

        // Configurar los elementos de cada gift card
        configureGiftCard(
                findViewById(R.id.giftCardAdidas),
                R.drawable.adidas,
                "Adidas",
                "Regala estilo y rendimiento con la Gift Card de Adidas. Ideal para cualquier ocasión, esta tarjeta permite al destinatario elegir entre una amplia selección de productos Adidas, incluyendo zapatillas, ropa deportiva y accesorios. Es la manera perfecta de asegurar que tus seres queridos obtengan exactamente lo que desean para mejorar su rendimiento deportivo o simplemente lucir a la moda."
        );

        configureGiftCard(
                findViewById(R.id.giftCardNike),
                R.drawable.nike,
                "Nike",
                "Sorprende con la Gift Card de Nike, perfecta para cualquier ocasión. Permite al destinatario elegir entre una amplia gama de productos Nike, desde zapatillas y ropa deportiva hasta accesorios innovadores. Ideal para los amantes del deporte y la moda que buscan calidad y estilo."
        );

        configureGiftCard(
                findViewById(R.id.giftCardMcdonalds),
                R.drawable.mcdonalds,
                "McDonald's",
                "Haz felices a tus seres queridos con la Gift Card de McDonald's. Ideal para cualquier ocasión, esta tarjeta permite disfrutar de una amplia variedad de deliciosos menús, desde hamburguesas clásicas hasta opciones saludables y postres irresistibles. Un regalo que siempre será bien recibido."
        );

        configureGiftCard(
                findViewById(R.id.giftCardBurgerKing),
                R.drawable.burger_king,
                "Burger King",
                "Regala el sabor inconfundible de Burger King con su Gift Card. Perfecta para cualquier ocasión, permite al destinatario disfrutar de su amplia variedad de hamburguesas flame-grilled, acompañamientos y postres. Una opción deliciosa y práctica para los amantes de la buena comida."
        );

        configureGiftCard(
                findViewById(R.id.giftCardSteam),
                R.drawable.steam,
                "Steam",
                "Sorprende a los gamers con la Gift Card de Steam. Ideal para cualquier ocasión, esta tarjeta permite acceder a una vasta colección de videojuegos, contenido descargable y software en la plataforma de Steam. Es el regalo perfecto para los aficionados a los videojuegos, garantizando horas de entretenimiento."
        );

        configureGiftCard(
                findViewById(R.id.giftCardCineHoyts),
                R.drawable.cine_hoyts,
                "Cine Hoyts",
                "Regala una experiencia cinematográfica inolvidable con la Gift Card de Cine Hoyts. Perfecta para los amantes del cine, permite disfrutar de las últimas películas en salas de alta calidad, con la libertad de elegir el horario y la película preferida. ¡Un regalo que garantiza momentos de entretenimiento y diversión!"
        );

        // Configurar botones de categorías y más relevantes
        Button categoriasButton = findViewById(R.id.categoriasButton);
        Button masRelevantesButton = findViewById(R.id.masRelevantesButton);

        categoriasButton.setOnClickListener(v -> {
            // Lógica para mostrar categorías
        });

        masRelevantesButton.setOnClickListener(v -> {
            // Lógica para mostrar más relevantes
        });
    }

    private void configureGiftCard(LinearLayout giftCardLayout, int imageResId, String name, String description) {
        ImageView imageView = giftCardLayout.findViewById(R.id.giftCardImageView);
        TextView nameTextView = giftCardLayout.findViewById(R.id.giftCardNameTextView);
        TextView descriptionTextView = giftCardLayout.findViewById(R.id.giftCardDescriptionTextView);
        Button detallesButton = giftCardLayout.findViewById(R.id.detallesButton);
        Button agregarCarritoButton = giftCardLayout.findViewById(R.id.agregarCarritoButton);

        imageView.setImageResource(imageResId);
        nameTextView.setText(name);
        descriptionTextView.setText(description);

        detallesButton.setOnClickListener(v -> {
            Intent intent = new Intent(GiftCardsActivity.this, DetalleGiftCardActivity.class);
            intent.putExtra("NOMBRE_GIFTCARD", name);
            intent.putExtra("DESCRIPCION", description);
            intent.putExtra("IMAGEN_RES_ID", imageResId);
            startActivity(intent);
        });

        agregarCarritoButton.setOnClickListener(v -> {
            // Lógica para agregar gift card al carrito
        });

        // Configurar los botones de valores
        Button valor25Button = giftCardLayout.findViewById(R.id.valor25Button);
        Button valor50Button = giftCardLayout.findViewById(R.id.valor50Button);
        Button valor100Button = giftCardLayout.findViewById(R.id.valor100Button);

        Button[] valueButtons = {valor25Button, valor50Button, valor100Button};

        for (Button button : valueButtons) {
            button.setOnClickListener(v -> {
                for (Button btn : valueButtons) {
                    btn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonNormalValores));
                }
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorButtonPressed));
            });
        }
    }
}
