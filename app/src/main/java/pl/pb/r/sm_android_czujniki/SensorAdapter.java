package pl.pb.r.sm_android_czujniki;

import android.content.Context;
import android.hardware.Sensor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by R on 2016-11-16.
 */

public class SensorAdapter extends ArrayAdapter<Sensor> {
    private Context mContext;
    List<Sensor> mList;

    public SensorAdapter(Context _context, List<Sensor> _myList) {
        super(_context, R.layout.lista_sensor, _myList);
        mContext = _context;
        this.mList = _myList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Sensor row = getItem(position);
        SensorViewHolder holder;
        if (convertView == null) {
            convertView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            convertView = vi.inflate(R.layout.lista_sensor, parent, false);

            holder = new SensorViewHolder();
            holder.nrSensor = (TextView) convertView.findViewById(R.id.nrSensor);
            holder.nameSensor = (TextView) convertView.findViewById(R.id.nameSensor);
            holder.vendorSensor = (TextView) convertView.findViewById(R.id.vendorSensor);

            convertView.setTag(holder);
        } else {
            holder = (SensorViewHolder) convertView.getTag();
        }
        holder.populate(row);
        return convertView;
    }

    static class SensorViewHolder {
        public TextView nrSensor;
        public TextView nameSensor;
        public TextView vendorSensor;
        private static int nrId = 1;

        void populate(Sensor s) {
            nrSensor.setText(Integer.toString(nrId++) + ": ");
            nameSensor.setText("name: " + s.getName());
            vendorSensor.setText("vendor: " + s.getVendor());

        }
    }

}
