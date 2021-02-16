package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.CarrierConfigManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

public class MainActivity extends Activity {
    Button btnShowLocation;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = android.Manifest.permission.ACCESS_FINE_LOCATION;
    EditText txtnum1,txtnum2;
    TextView txtresult;
    Button btnplus,btnminus,btnmultiply,btndivide,btnsin,btncos,btntan,btnlog,btnsqrt,btnpower,btnclear,btnlocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialization, Definition
        txtnum1 = (EditText)findViewById(R.id.txtnum1);
        txtnum2 = (EditText)findViewById(R.id.txtnum2);
        txtresult = (TextView)findViewById(R.id.txtresult);


        btnplus = (Button)findViewById(R.id.btnplus);
        btnminus = (Button)findViewById(R.id.btnminus);
        btnmultiply = (Button)findViewById(R.id.btnmultiply);
        btndivide = (Button)findViewById(R.id.btndivide);
        btnsin = (Button)findViewById(R.id.btnsin);
        btncos = (Button)findViewById(R.id.btncos);
        btntan = (Button)findViewById(R.id.btntan);
        btnlog = (Button)findViewById(R.id.btnlog);
        btnsqrt = (Button)findViewById(R.id.btnsqrt);
        btnpower = (Button)findViewById(R.id.btnpower);
        btnclear = (Button)findViewById(R.id.btnclear);
        btnlocation = (Button)findViewById(R.id.btnlocation);

        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float num1 = Float.parseFloat(txtnum1.getText().toString());
                float num2 = Float.parseFloat(txtnum2.getText().toString());
                float val = num1 + num2;
                txtresult.setText(String.valueOf(val));
            }
        });

        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float num1 = Float.parseFloat(txtnum1.getText().toString());
                float num2 = Float.parseFloat(txtnum2.getText().toString());
                float val = num1 - num2;
                txtresult.setText(String.valueOf(val));
            }
        });

        btnmultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float num1 = Float.parseFloat(txtnum1.getText().toString());
                float num2 = Float.parseFloat(txtnum2.getText().toString());
                float val = num1 * num2;
                txtresult.setText(String.valueOf(val));
            }
        });

        btndivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float num1 = Float.parseFloat(txtnum1.getText().toString());
                float num2 = Float.parseFloat(txtnum2.getText().toString());
                float val = num1 / num2;
                txtresult.setText(String.valueOf(val));
            }
        });

        btnsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(txtnum1.getText().toString());
                double val = Math.sin(num1);
                txtresult.setText(String.valueOf(val));
            }
        });

        btncos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(txtnum1.getText().toString());
                double val = Math.cos(num1);
                txtresult.setText(String.valueOf(val));
            }
        });
        btntan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(txtnum1.getText().toString());
                double val = Math.tan(num1);
                txtresult.setText(String.valueOf(val));
            }
        });

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(txtnum1.getText().toString());
                double val = Math.log(num1);
                txtresult.setText(String.valueOf(val));
            }
        });

        btnpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Float.parseFloat(txtnum1.getText().toString());
                double num2 = Float.parseFloat(txtnum2.getText().toString());
                double val = Math.pow(num1,num2);
                txtresult.setText(String.valueOf(val));
            }
        });
        btnsqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(txtnum1.getText().toString());
                double val = Math.sqrt(num1);
                txtresult.setText(String.valueOf(val));
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtresult.setText("");
                txtnum1.setText("");
                txtnum2.setText("");
            }
        });
        public class GPSTracker extends Service implements LocationListener {

            private final Context mContext;

            // Flag for GPS status
            boolean isGPSEnabled = false;

            // Flag for network status
            boolean isNetworkEnabled = false;

            // Flag for GPS status
            boolean canGetLocation = false;

            Location location; // Location
            double latitude; // Latitude
            double longitude; // Longitude

            // The minimum distance to change Updates in meters
            private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

            // The minimum time between updates in milliseconds
            private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

            // Declaring a Location Manager
            protected LocationManager locationManager;

            public GPSTracker(Context context) {
                this.mContext = context;
                getLocation();
            }

            public Location getLocation() {
                try {
                    locationManager = (LocationManager) mContext
                            .getSystemService(LOCATION_SERVICE);

                    // Getting GPS status
                    isGPSEnabled = locationManager
                            .isProviderEnabled(LocationManager.GPS_PROVIDER);

                    // Getting network status
                    isNetworkEnabled = locationManager
                            .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                    if (!isGPSEnabled && !isNetworkEnabled) {
                        // No network provider is enabled
                    } else {
                        this.canGetLocation = true;
                        if (isNetworkEnabled) {

                            locationManager.requestLocationUpdates(
                                    LocationManager.NETWORK_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                            //   Log.d("Network", "Network");
                            if (locationManager != null) {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                if (location != null) {
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();
                                }
                            }
                        }
                        // If GPS enabled, get latitude/longitude using GPS Services
                        if (isGPSEnabled) {
                            if (location == null) {
                                locationManager.requestLocationUpdates(
                                        LocationManager.GPS_PROVIDER,
                                        MIN_TIME_BW_UPDATES,
                                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                                //    Log.d("GPS Enabled", "GPS Enabled");
                                if (locationManager != null) {
                                    location = locationManager
                                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                    if (location != null) {
                                        latitude = location.getLatitude();
                                        longitude = location.getLongitude();
                                    }
                                }
                            }
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                return location;
            }


            /**
             * Stop using GPS listener
             * Calling this function will stop using GPS in your app.
             * */
            public void stopUsingGPS(){
                if(locationManager != null){
                    locationManager.removeUpdates(GPSTracker.this);
                }
            }


            /**
             * Function to get latitude
             * */
            public double getLatitude(){
                if(location != null){
                    latitude = location.getLatitude();
                }

                // return latitude
                return latitude;
            }


            /**
             * Function to get longitude
             * */
            public double getLongitude(){
                if(location != null){
                    longitude = location.getLongitude();
                }

                // return longitude
                return longitude;
            }

            /**
             * Function to check GPS/Wi-Fi enabled
             * @return boolean
             * */
            public boolean canGetLocation() {
                return this.canGetLocation;
            }


            /**
             * Function to show settings alert dialog.
             * On pressing the Settings button it will launch Settings Options.
             * */
            public void showSettingsAlert(){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

                // Setting Dialog Title
                alertDialog.setTitle("GPS is settings");

                // Setting Dialog Message
                alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

                // On pressing the Settings button.
                alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        mContext.startActivity(intent);
                    }
                });

                // On pressing the cancel button
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }


            @Override
            public void onLocationChanged(Location location) {
            }



            @Override
            public void onProviderDisabled(String provider) {
            }


            @Override
            public void onProviderEnabled(String provider) {
            }


            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }


            @Override
            public IBinder onBind(Intent arg0) {
                return null;
            }
        }
        GPSTracker gps;
       btnlocation.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v)
           {   gps = new GPSTracker(MainActivity.this);

               // check if GPS enabled
               if(gps.canGetLocation()){

                   double latitude = gps.getLatitude();
                   double longitude = gps.getLongitude();

                   txtresult.setText(String.valueOf(latitude,longitude));
               }else{

                   gps.showSettingsAlert();
               }

         }
       });
    }
}