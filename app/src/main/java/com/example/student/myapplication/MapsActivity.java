package com.example.student.myapplication;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity
        implements GoogleMap.OnMapLongClickListener,LocationListener{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    LocationManager lMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        //Set Map to accept Long click
        mMap.setOnMapLongClickListener(this);

        //LocationListener
        lMgr = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        lMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LatLng BKK = new LatLng( 13.721932, 100.532517);
        LatLng BKK2 = new LatLng( 13, 100);

//        mMap.addMarker(new MarkerOptions().position(new LatLng(
//                13.721932, 100.532517)).title("Marker"));

//        mMap.addMarker(new MarkerOptions().position(BKK).title("Bangkok"));
//
//        mMap.addMarker(new MarkerOptions().position(BKK2).title("Marker2"));

//        CameraUpdate start_map = CameraUpdateFactory.newLatLng(BKK);
//        mMap.moveCamera(start_map);
        //Set center of camera
//
//        CameraUpdate start_zoom = CameraUpdateFactory.zoomTo(5);
//        mMap.animateCamera(start_zoom);
        //Set zoom size


        //Draw line
//        mMap.addPolyline(new PolylineOptions().add(BKK).add(BKK2));
    }


   @Override
    public void onMapLongClick(LatLng latLng) {
        LatLng other = latLng;
        mMap.addMarker(new MarkerOptions().position(other).title("Other"));

       CameraUpdate start_map = CameraUpdateFactory.newLatLng(other);
       mMap.moveCamera(start_map);

       CameraUpdate start_zoom = CameraUpdateFactory.zoomTo(5);
       mMap.animateCamera(start_zoom);


       if(oldplace!= null) {
           mMap.addPolyline(new PolylineOptions().add(oldplace).add(other));
       }
       oldplace = other;


        //From LocationListener
    }
    int Place = 0;
    LatLng oldplace = null;
    @Override
    public void onLocationChanged(Location location) {
        LatLng otherplace = new LatLng(location.getLatitude(),location.getLongitude());

        mMap.addMarker(new MarkerOptions().position(otherplace).title("Place" + ++Place));

        CameraUpdate start_map = CameraUpdateFactory.newLatLng(otherplace);
        mMap.moveCamera(start_map);

        CameraUpdate start_zoom = CameraUpdateFactory.zoomTo(5);
        mMap.animateCamera(start_zoom);


        if(oldplace!= null) {
            mMap.addPolyline(new PolylineOptions().add(oldplace).add(otherplace));
        }
        oldplace = otherplace;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}


//telnet localhost 5554
//geo fix <lang> <lat>new LatLng(location.getLatitude(),location.getLongitude())
