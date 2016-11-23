package pl.pb.r.sm_android_czujniki;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProximityActivity extends AppCompatActivity  implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mProximity;

    private View root;
    private TextView tv;

    private int counterNear = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        root = findViewById(R.id.activity_proximity);
        tv = (TextView)findViewById(R.id.tvprox);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mProximity,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY && counterNear != -1) {
            if (event.values[0] >= -0.01 && event.values[0]<= 0.01) {
                //near
                root.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
                counterNear++;
                if(counterNear == 2)
                    Toast.makeText(this,"Zaraz wywalę",Toast.LENGTH_LONG).show();
                else if(counterNear == 3){
                    Toast.makeText(this,"I stało się",Toast.LENGTH_LONG).show();
                    this.finishAffinity();
                }
            } else {
                //far

                root.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tv.getTextSize()+0.025f);
            }
        }
        else counterNear++;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
