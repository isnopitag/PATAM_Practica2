package com.carmona.bateryreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BatteryActivity extends AppCompatActivity {

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level",0);

            ProgressBar pb = (ProgressBar)findViewById(R.id.pb);
            pb.setProgress(level);

            TextView tv = (TextView)findViewById(R.id.tv);
            tv.setText("Battery Text: "+Integer.toString(level)+"%");

            if(level < 55){
                Toast.makeText(BatteryActivity.this, "Tienes Bateria Baja", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder ab = new AlertDialog.Builder(BatteryActivity.this);
                ab.setTitle("Bateria Baja");
                ab.setMessage("Tienes bateria baja");
                ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); }; });
                AlertDialog ad = ab.create();
                ad.show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        registerReceiver(br,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}
