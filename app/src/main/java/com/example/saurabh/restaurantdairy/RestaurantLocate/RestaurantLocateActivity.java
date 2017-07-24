package com.example.saurabh.restaurantdairy.RestaurantLocate;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.saurabh.restaurantdairy.R;
import com.example.saurabh.restaurantdairy.RestaurantDisplay.RestaurantDisplayActivity;
import com.example.saurabh.restaurantdairy.apimodel.LocationModel.ResultData;
import com.example.saurabh.restaurantdairy.root.App;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantLocateActivity extends AppCompatActivity implements RestaurantLocateActivityMVP.View, OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = RestaurantLocateActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.app_name)
    TextView appname;

    @BindView(R.id.toolbar_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    SupportMapFragment mapFragment;

    private GoogleMap mMap;

    private FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;
    GoogleApiClient googleApiClient;

    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;

    ConnectivityManager connectivityManager;

    @Inject
    RestaurantLocateActivityMVP.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidermlocateactivity);
        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);
        setSupportActionBar(toolbar);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/angelina.ttf");
        appname.setTypeface(face);

        connectivityManager = (ConnectivityManager)this.getSystemService (Context.CONNECTIVITY_SERVICE);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


        progressBar.setVisibility(View.VISIBLE);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationView navigationViewRight = (NavigationView) findViewById(R.id.nav_view_right);
        navigationViewRight.setNavigationItemSelectedListener(this);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            Log.d(TAG, "Location update resumed .....................");
        }
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        startLocationUpdates();

    }

    @Override
    public void onConnectionSuspended(int i)
    {
        Log.d(TAG, "onConnectionSuspended - onConnectionSuspended ...............: " + mGoogleApiClient.isConnected());
        //mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Log.d(TAG, "onConnectionFailed - onConnectionFailed ...............: " + mGoogleApiClient.isConnected());
       // mGoogleApiClient.disconnect();
    }


    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, ((App) getApplication()).mLocationRequest, this);
        Log.d(TAG, "Location update started ..............: ");
    }


    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged..............................................");
        mCurrentLocation = location;
        Log.i(TAG, "location=" + mCurrentLocation.getLatitude());
       if(presenter.isNetworkConnected(connectivityManager) && presenter.isInternetAvailable()) {
           presenter.LoadRestaurant(mCurrentLocation);
       }
       else
       {
           Log.i(TAG, "No internet");
           showError("No internet");
       }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG, "On map ready");
        Float lat = null;
        Float lng = null;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerClickListener(this);

        for (int i = 0; i < ((App) getApplicationContext()).restaurantData.getRestaurants().size(); i++) {
            String stringlat = ((App) getApplicationContext()).restaurantData.getRestaurants().get(i).getRestaurant().getLocation().getLatitude();

            String stringlng = ((App) getApplicationContext()).restaurantData.getRestaurants().get(i).getRestaurant().getLocation().getLongitude();

            lat = Float.valueOf(stringlat);
            lng = Float.valueOf(stringlng);

            Marker NewRestaurantMarket = googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .title(((App) getApplicationContext()).restaurantData.getRestaurants().get(i).getRestaurant().getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            NewRestaurantMarket.setTag(i);

            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    Intent i = new Intent(RestaurantLocateActivity.this, RestaurantDisplayActivity.class);
                    startActivity(i);
                }
            });

        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15));
        stopLocationUpdates();
        progressBar.setVisibility(View.INVISIBLE);


    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        ((App) getApplicationContext()).SelectedRestaurant = (Integer) marker.getTag();
        return false;
    }


    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        Log.d(TAG, "Location update stopped .......................");
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
        mGoogleApiClient.disconnect();
        Log.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
    }


    @Override
    public void showError(String message)
    {

        Snackbar snackbar = Snackbar
                .make(drawer, "No Internet Connectivity", Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        presenter.LoadRestaurant(mCurrentLocation);
                    }
                });

        snackbar.show();

    }

    @Override
    public void showRestaurant(ResultData resultData) {
        ((App) getApplicationContext()).restaurantData = resultData;
        Log.i("LogData", "result shown=" + resultData.getResultsShown());
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.displaymap);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            //getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_right_menu) {
            if (drawer.isDrawerOpen(GravityCompat.END)) {
                drawer.closeDrawer(GravityCompat.END);
            } else {
                drawer.openDrawer(GravityCompat.END);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            // Handle the camera action
        } else if (id == R.id.setting) {

        } else if (id == R.id.offers) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
