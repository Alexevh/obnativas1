package com.example.obnativasp1;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public String direccion ="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        /*Obtengo la direccion*/


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                direccion= null;
            } else {
                direccion= extras.getString("valorMapa");
            }
        } else {
            direccion= (String) savedInstanceState.getSerializable("valorMapa");
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addTestMarkerAndEnableLocation();
    }


    /* Codigo propio*/
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager. PERMISSION_GRANTED
                    && grantResults[1] == PackageManager. PERMISSION_GRANTED ) {
                addTestMarkerAndEnableLocation();
            }
        }
    }

    private void addTestMarkerAndEnableLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission. ACCESS_FINE_LOCATION ) !=
                PackageManager. PERMISSION_GRANTED &&
                ActivityCompat. checkSelfPermission(this,
                        android.Manifest.permission. ACCESS_COARSE_LOCATION ) !=
                        PackageManager. PERMISSION_GRANTED ) {
            ActivityCompat. requestPermissions(this, new String[]
                    {android.Manifest.permission. ACCESS_FINE_LOCATION ,
                            android.Manifest.permission. ACCESS_COARSE_LOCATION }, 0);
            return;
        } else {
            //LatLng sydney = new LatLng(-34, 151);
           // LatLng ORT = new LatLng(-34.903939,-56.190827);
            //mMap.setMyLocationEnabled(true);


            //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            //mMap.addMarker(new MarkerOptions().position(ORT).title("LA ORT"));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(ORT));
            //mMap.animateCamera(CameraUpdateFactory. newLatLngZoom(ORT, 17.0f));

            String location = direccion;
            List<Address> addressList = null;
            if (location != null || !location.equals("")) {
                Geocoder geocoder = new Geocoder(this);
                try {
                    addressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory. newLatLngZoom(latLng, 17.0f));
            }
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }


    public void onSearch(View view) {
       //EditText location_tf = (EditText) findViewById(R.id.TFaddress);
       //String location = location_tf.getText().toString();
        String location ="18 de julio esquina beisso, montevideo, uruguay";
        List<Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    public void obtenerDireccion(View view, String direccion) {
        //EditText location_tf = (EditText) findViewById(R.id.TFaddress);
        //String location = location_tf.getText().toString();
        String location =direccion;
        List<Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }


    /* Cambia el tipo de mapa*/
    public void changeType(View view) {
        if(mMap.getMapType() == GoogleMap. MAP_TYPE_NORMAL ) {
            mMap.setMapType(GoogleMap. MAP_TYPE_SATELLITE );
        } else {
            mMap.setMapType(GoogleMap. MAP_TYPE_NORMAL );
        }
    }
}
