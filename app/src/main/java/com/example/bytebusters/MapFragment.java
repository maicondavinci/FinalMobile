package com.example.bytebusters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ListView listView;
    private String giftCardName;
    private List<Store> stores;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        listView = view.findViewById(R.id.listView);

        giftCardName = getArguments() != null ? getArguments().getString("NOMBRE_GIFTCARD") : "";
        initializeStores();

        if (stores.isEmpty()) {
            Toast.makeText(getContext(), "Este gift card no tiene tienda física.", Toast.LENGTH_LONG).show();
        } else {
            StoreAdapter adapter = new StoreAdapter(getContext(), stores);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener((adapterView, view1, position, id) -> {
                Store store = stores.get(position);
                if (store != null) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(store.getLocation(), 15));
                    mMap.addMarker(new MarkerOptions().position(store.getLocation()).title(store.getName()));
                }
            });
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    private void initializeStores() {
        stores = new ArrayList<>();

        switch (giftCardName.toLowerCase()) {
            case "adidas":
                stores.add(new Store("Adidas Store 1", "Centro Comercial Abasto", "Av. Corrientes", "Buenos Aires", "1234", new LatLng(-34.603684, -58.381559)));
                stores.add(new Store("Adidas Store 2", "Galerías Pacífico", "Av. Santa Fe", "Buenos Aires", "5678", new LatLng(-34.615852, -58.433298)));
                stores.add(new Store("Adidas Store 3", "Shopping Alto Palermo", "Calle Florida", "Buenos Aires", "910", new LatLng(-34.608414, -58.370756)));
                break;
            case "mac donalds":
                stores.add(new Store("McDonald's 1", "Centro Comercial Abasto", "Av. Corrientes", "Buenos Aires", "1234", new LatLng(-34.609549, -58.380558)));
                stores.add(new Store("McDonald's 2", "Galerías Pacífico", "Av. 9 de Julio", "Buenos Aires", "5678", new LatLng(-34.603722, -58.381592)));
                stores.add(new Store("McDonald's 3", "Shopping Alto Palermo", "Calle Florida", "Buenos Aires", "910", new LatLng(-34.615852, -58.433298)));
                break;
            case "burger king":
                stores.add(new Store("Burger King 1", "Centro Comercial Abasto", "Av. Corrientes", "Buenos Aires", "1234", new LatLng(-34.605062, -58.380378)));
                stores.add(new Store("Burger King 2", "Galerías Pacífico", "Av. Santa Fe", "Buenos Aires", "5678", new LatLng(-34.608033, -58.397865)));
                stores.add(new Store("Burger King 3", "Shopping Alto Palermo", "Calle Florida", "Buenos Aires", "910", new LatLng(-34.610455, -58.375675)));
                break;
            case "nike":
                stores.add(new Store("Nike Store 1", "Centro Comercial Abasto", "Av. Corrientes", "Buenos Aires", "1234", new LatLng(-34.603722, -58.381592)));
                stores.add(new Store("Nike Store 2", "Galerías Pacífico", "Av. Santa Fe", "Buenos Aires", "5678", new LatLng(-34.611548, -58.385602)));
                stores.add(new Store("Nike Store 3", "Shopping Alto Palermo", "Calle Florida", "Buenos Aires", "910", new LatLng(-34.612787, -58.384534)));
                break;
            case "cine hoyts":
                stores.add(new Store("Cine Hoyts 1", "Centro Comercial Abasto", "Av. Corrientes", "Buenos Aires", "1234", new LatLng(-34.608414, -58.370756)));
                stores.add(new Store("Cine Hoyts 2", "Galerías Pacífico", "Av. 9 de Julio", "Buenos Aires", "5678", new LatLng(-34.609081, -58.382105)));
                stores.add(new Store("Cine Hoyts 3", "Shopping Alto Palermo", "Calle Florida", "Buenos Aires", "910", new LatLng(-34.610080, -58.377104)));
                break;
            case "steam":
                stores = new ArrayList<>();
                break;
            default:
                Toast.makeText(getContext(), "Este gift card no tiene tienda física.", Toast.LENGTH_LONG).show();
                stores = new ArrayList<>();
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Check for location permission
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }
}
