package com.example.thomas.trailcaden;

/**
 * Created by Safiah on 12/02/2018.
 */
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.kml.KmlLayer;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    protected final LatLng CADEN = new LatLng(47.6269912719726562,-2.2877552509307861);

    public MapActivity() throws IOException, XmlPullParserException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            KmlLayer layer = new KmlLayer(mMap, R.raw.parcours10km,getApplicationContext());
            layer.addLayerToMap();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMap.addMarker(new MarkerOptions().position(CADEN).title("Caden"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CADEN, 13));

    }

    public GoogleMap getMap() {
        return this.mMap;
    }
}
