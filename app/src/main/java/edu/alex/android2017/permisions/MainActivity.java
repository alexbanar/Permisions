package edu.alex.android2017.permisions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.health.PackageHealthStats;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CALL = 1;
    private static final int REQUEST_CODE_SMS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == REQUEST_CODE_CALL) {
            //responce for Call Now permissions.

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call(null);
            } else {
                Toast.makeText(this, "No Permission for Call", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            if (requestCode == REQUEST_CODE_SMS)
            {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendSMS(null);
                }
                else
                {
                    Toast.makeText(this, "No Permission for sending SMS", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(this, "Problem", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void call(View view)
    {
        Uri phoneNumber = Uri.parse("tel:0504545427");
        Intent callIntent = new Intent(Intent.ACTION_CALL, phoneNumber);

       // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        //return;
        //}


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_CALL);
            return;
        }

        startActivity(callIntent);
    }

    public void sendSMS(View view)
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE_SMS);
            return;
        }

        SmsManager.getDefault().sendTextMessage(
                "0507123012", null, "Hello", null, null);

    }

}
