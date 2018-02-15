package com.example.thomas.trailcaden.map;

/**
 * Created by Safiah on 12/02/2018.
 */
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.example.thomas.trailcaden.BaseActivity;
import com.example.thomas.trailcaden.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.kml.KmlLayer;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;

import static com.google.android.gms.maps.MapFragment.newInstance;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final LatLng CADEN = new LatLng(47.6333,-2.2833);
    private  KmlLayer layer10km;
    private  KmlLayer layer8km;
    private KmlLayer layer;


    public MapActivity() throws IOException, XmlPullParserException {}

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
        if(layer != null){
            try {
                layer.addLayerToMap();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            mMap.addMarker(new MarkerOptions().position(CADEN).title("Caden"));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(47.6333,-2.2933), 13));
    }

    public GoogleMap getMap() {
        return this.mMap;
    }

    private void setLayer(KmlLayer layer){
        this.layer = layer;
    }

    public void button10km(View view) throws IOException, XmlPullParserException {
        if(layer!=null){layer.removeLayerFromMap();}
        mMap.clear();
        layer10km = new KmlLayer(mMap, R.raw.parcours10km,this);
        setLayer(layer10km);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void button8km(View view) throws IOException, XmlPullParserException {
        if(layer!=null){layer.removeLayerFromMap();}
        mMap.clear();
        layer8km = new KmlLayer(mMap, R.raw.parcours8km,this);
        setLayer(layer8km);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
}
