package com.example.madhumitha.samplemap;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Random;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private LatLng latlon1;
    private GoogleMap mMap;
    private static final String TAG = "Debug";
    Cursor cursor = null;
    double stringLatitude = 0;
    double stringLongitude= 0;
    LocationManager locationManager;
    Context context;
    Cursor c = null;
    DatabaseHelper  DatabaseHelper;
    String ListItemsName[];

    LocationListener mlocationListener;
    public class Example {
        public Double a;
        public Double b;
    }
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);

            LoadCoords();
       /* if (stringLatitude != 0){
            MyMarker();
        }*/

      //  latlon1 = getLocation(stringLongitude,stringLatitude,50);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        final Button button = (Button) findViewById(R.id.button);
        final Button graph = (Button) findViewById(R.id.graph);
        final Button list = (Button) findViewById(R.id.list);

        DatabaseHelper = new DatabaseHelper(MapsActivity.this);

        try {
            DatabaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DatabaseHelper.open();

        c = DatabaseHelper.selecttop("SELECT  Title , snippet FROM MapDetails where title like  '%HOMICIDE%' ");

        Integer i = 0;
        int count = c.getCount();

        ListItemsName = new String[count];

        if (c.moveToFirst()) {
            do {
                ListItemsName[i] = (c.getString(0))+"--"+(c.getString(1));
                ListItemsName[i] = ListItemsName[i].replaceAll("\t","");
                ListItemsName[i] = ListItemsName[i].replaceAll("--","\n");
                i = i + 1;
            } while (c.moveToNext());
        }

        list.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i=new Intent(MapsActivity.this, MainActivity.class);
                i.putExtra("Strings", ListItemsName);
                startActivity(i);
            }
        });

        graph.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i=new Intent(MapsActivity.this, datagraph.class);
                startActivity(i);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mMap.clear();
                LoadCoords();

               if (stringLatitude != 0){
                    Marker();
                   MyMarker();
            }
            }
        });

    }
    public void open(){
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("This app needs access to your location. Please turn on location access.");
        alertDialogBuilder.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        MapsActivity.this.startActivity(new Intent(action));
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoadCoords();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void LoadCoords(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //   Toast.makeText(this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();

            GPSTracker gps = new GPSTracker(this);
            if (gps.canGetLocation) {
                stringLatitude = 0.0;
                stringLatitude = gps.getLatitude();
                stringLongitude = gps.getLongitude();
            }
        }

        else {
                            open();

            // stringLatitude = 41.839165;
            //stringLongitude = -87.616540;
        }
    }


   @Override
    public void onMapReady(GoogleMap googleMap) {
       mMap = googleMap;
       if (stringLatitude != 0){
       Marker();
       }
   }

    public void Marker(){
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        // Add a marker in Sydney and move the camera
        //LatLng mtcc = new LatLng(41.834526, -87.626571);
        Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(stringLatitude, stringLongitude))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.white))
        .title("Latitude : " + String.valueOf(stringLatitude)+"\n"+"Longitude : "+String.valueOf(stringLongitude))
        .snippet("https://docs.google.com/forms/d/1kks3EPKEuYHu9xCzGGc9pI3QwRZHBg2rO5VaWS5u3_c/viewform?edit_requested=true"));
        //marker.showInfoWindow();
        //mMap.addMarker(new MarkerOptions().position(mtcc).icon(BitmapDescriptorFactory.fromResource(R.drawable.gun)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(stringLatitude, stringLongitude)));

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(stringLatitude, stringLongitude));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(16);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);

        double latf =  stringLatitude - 0.005;
        double latb =  stringLatitude + 0.005;

        double lonf =  stringLongitude + 0.005;
        double lonb=  stringLongitude - 0.005;

        cursor = DatabaseHelper.selectwithwhere(" Latitude Between "+latf+" And "+latb+
                    " and Longitude Between " +lonb+ " And "+lonf);
        String[] Array = new String[7];
        Array[0] = "Yellow";
        Array[1] = "Blue";
        Array[2] = "Green";
        Array[3] = "Orange";
        Array[4] = "Red";
        Array[5] = "Black";

        if (cursor.moveToFirst()) {
            do {
                switch ((cursor.getString(5))){
                    case "Yellow":
                        String yelsnip = (cursor.getString(4));
                        yelsnip = yelsnip.replaceAll("--", "\n");
                        marker = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(
                                        Double.valueOf((cursor.getString(1))),
                                        Double.valueOf((cursor.getString(2)))))
                                .title(cursor.getString(3))
                                .snippet(yelsnip)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.yellow)));
                        yelsnip = "";
                        break;
                    case "Blue":

                        String bluesnip = (cursor.getString(4));
                        bluesnip = bluesnip.replaceAll("--", "\n");
                        marker = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(
                                        Double.valueOf((cursor.getString(1))),
                                        Double.valueOf((cursor.getString(2)))))
                                .title(cursor.getString(3))
                                .snippet(bluesnip)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.blue)));
                        bluesnip = "";
                        break;
                    case "Pink":
                        String greensnip = (cursor.getString(4));
                        greensnip = greensnip.replaceAll("--", "\n");
                        marker = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(
                                        Double.valueOf((cursor.getString(1))),
                                        Double.valueOf((cursor.getString(2)))))
                                .title(cursor.getString(3))
                                .snippet(greensnip)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pink)));
                        greensnip = "";
                        break;
                    case "HOS":
                        String osnip = (cursor.getString(4));
                        osnip = osnip.replaceAll("--", "\n");
                        osnip = osnip.replaceAll("-", "");
                        marker = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(
                                        Double.valueOf((cursor.getString(1))),
                                        Double.valueOf((cursor.getString(2)))))
                                .title(cursor.getString(3))
                                .snippet(osnip)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.hos)));
                        osnip="";
                        break;
                    case "POL":
                        String psnip = (cursor.getString(4));
                        psnip = psnip.replaceAll("--", "\n");
                        psnip = psnip.replaceAll("\t", "");
                        psnip = psnip.replaceAll(" ", "");
                        marker = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(
                                        Double.valueOf((cursor.getString(1))),
                                        Double.valueOf((cursor.getString(2)))))
                                .title(cursor.getString(3))
                                .snippet(psnip)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.green)));
                        osnip="";
                        break;
                    case "FIRE":
                        String fsnip = (cursor.getString(4));
                        fsnip = fsnip.replaceAll("--", "\n");

                        marker = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(
                                        Double.valueOf((cursor.getString(1))),
                                        Double.valueOf((cursor.getString(2)))))
                                .title(cursor.getString(3))
                                .snippet(fsnip)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fire)));
                        osnip="";
                        break;
                    case "BUS":
                        String busnip = (cursor.getString(4));
                        busnip = busnip.replaceAll("--", "\n");
                        marker = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(
                                        Double.valueOf((cursor.getString(1))),
                                        Double.valueOf((cursor.getString(2)))))
                                .title(cursor.getString(3))
                                .snippet(busnip)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));
                        osnip="";
                        break;
                    case "Red":
                        String rsnip = (cursor.getString(4));
                        rsnip = rsnip.replaceAll("--", "\n");
                        marker = mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(
                                        Double.valueOf((cursor.getString(1))),
                                        Double.valueOf((cursor.getString(2)))))
                                .title(cursor.getString(3))
                                .snippet(rsnip)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.red)));
                        rsnip="";
                        break;
                }
            } while (cursor.moveToNext());
        }

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                Context mContext = getApplicationContext();
                LinearLayout info = new LinearLayout(mContext);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(mContext);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(mContext);
                snippet.setTextColor(Color.GRAY);
                snippet.setLinkTextColor(Color.BLUE);

                snippet.setMovementMethod(LinkMovementMethod.getInstance());
                snippet.setText(marker.getSnippet());
/*
                ImageView imga = new ImageView(mContext);
                imga.setImageResource(R.drawable.fist);
                snippet.setText(marker.getSnippet());*/

                info.addView(title);
                info.addView(snippet);
                // info.addView(imga);

                return info;
            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            final String description = marker.getSnippet();
            if (!TextUtils.isEmpty(description)) {
                final Spannable span = Spannable.Factory.getInstance().newSpannable(description);
                if (Linkify.addLinks(span, Linkify.PHONE_NUMBERS)) {
                    final URLSpan[] old = span.getSpans(0, span.length(), URLSpan.class);
                    if (old != null && old.length > 0) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(old[0].getURL()));
                        startActivity(intent);
                    }
                }
                if (Linkify.addLinks(span, Linkify.WEB_URLS)) {
                    final URLSpan[] old = span.getSpans(0, span.length(), URLSpan.class);
                    if (old != null && old.length > 0) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(old[0].getURL()));
                        startActivity(intent);
                    }
                }
            }
        }
    });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                mMap.clear();
                stringLatitude = point.latitude;
                stringLongitude = point.longitude;
                Marker();
            }
        });
    }

    public void MyMarker(){

        Marker mymarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(stringLatitude, stringLongitude))
                .title("You are Here!!!")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.white)));

        mymarker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(stringLatitude, stringLongitude)));

        CameraUpdate center =
                CameraUpdateFactory.newLatLng(new LatLng(stringLatitude, stringLongitude));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);

    }





   /* public static LatLng getLocation(double lon, double lat, int radius)
    {
        Random random = new Random();

        // Convert radius from meters to degrees
        double radiusInDegrees = radius / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(lat);

        double foundLongitude = new_x + lon;
        double foundLatitude = y + lat;
        return new LatLng(foundLatitude, foundLongitude);

    }*/

}