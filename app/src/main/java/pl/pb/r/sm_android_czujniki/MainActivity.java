package pl.pb.r.sm_android_czujniki;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        ArrayAdapter<Sensor> arrayAdapter = new ArrayAdapter<Sensor>(this,android.R.layout.simple_list_item_1,deviceSensors);

        final SensorAdapter sensorAdapter = new SensorAdapter(this,deviceSensors);

        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.lv);
        mListView.setAdapter(sensorAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.new_activity_sensor:
                Intent intent = new Intent(this, AccelerometerSensorActivity.class);
                startActivity(intent);
                break;

            case R.id.new_activity_sensor_proximity:
                intent = new Intent(this, ProximityActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
