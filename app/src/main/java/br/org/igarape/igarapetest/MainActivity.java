package br.org.igarape.igarapetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.location.Location;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


 public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
         GoogleApiClient.OnConnectionFailedListener {

     private GoogleApiClient myGoogleApiClient;
     private Location myLastLoc;
     private TextView myLat;
     private TextView myLon;

     protected synchronized void buildGoogleApiClient() {
         myGoogleApiClient = new GoogleApiClient.Builder(this)
                 .addConnectionCallbacks(this)
                 .addOnConnectionFailedListener(this)
                 .addApi(LocationServices.API)
                 .build();

         myGoogleApiClient.connect();

     }
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         myLat = (TextView) findViewById(R.id.myLat);
         myLon = (TextView) findViewById(R.id.myLon);

         buildGoogleApiClient();


     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_main, menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.
         int id = item.getItemId();

         //noinspection SimplifiableIfStatement
         if (id == R.id.action_settings) {
             return true;
         }

         return super.onOptionsItemSelected(item);
     }


     @Override
     public void onConnected(Bundle connectionHint) {
         myLastLoc = LocationServices.FusedLocationApi.getLastLocation(
                 myGoogleApiClient);
         if (myLastLoc != null) {
             myLat.append(String.valueOf(myLastLoc.getLatitude()));
             myLon.append(String.valueOf(myLastLoc.getLongitude()));

         }
     }

     @Override
     public void onConnectionSuspended(int i) {

     }

     @Override
     public void onConnectionFailed(ConnectionResult connectionResult) {

     }
 }


