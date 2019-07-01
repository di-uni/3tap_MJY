package com.example.mytab0626;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class TabFragment3 extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCountSensor;
    TextView tvStepCount;
    TextView kcalCount;
    ImageView iv;
    Button button;
    private static final long number = 0;
    private long num = number;
    private ImageView appearedImage;
    private long delnum=0;
    private int initial=1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.tab_fragment_3, container, false);

        appearedImage = (ImageView) view.findViewById(R.id.prize);
        tvStepCount = (TextView)view.findViewById(R.id.tvStepCount);
        kcalCount = (TextView)view.findViewById(R.id.kcalCount);
        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        final ImageView iv = (ImageView) view.findViewById(R.id.imageView1);
        if(stepCountSensor == null) {
            Toast.makeText(getContext(), "No Step Detect Sensor", Toast.LENGTH_SHORT).show();
        }
        //if (roll == 1){
        //   Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        //    iv.startAnimation(animation);
        //}
        Log.d("num ", String.valueOf(num));

        button = (Button)view.findViewById(R.id.resetbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Step Count reset!", Toast.LENGTH_LONG).show();
                resetCount();
                ImageDisappear();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (initial == 1) {
                delnum = (int) sensorEvent.values[0];
                initial = 0;
            }
            num = (int) sensorEvent.values[0] - delnum;
            updateNUmber();
            Log.d("num here", String.valueOf(num));
            if ( (num%10 == 0 | num%10 == 1) && num!=0){
                ImageAppear();
            }
            else {
                ImageDisappear();
            }
        }
    }

    private void resetCount() {
        updateDelNumber();
        num = number;
        updateNUmber();
    }

    private void updateNUmber(){
        tvStepCount.setText("Step Count : " + String.valueOf(num));
        kcalCount.setText("Consumed "+ String.format("%.2f", num*0.03)+ " kcal");
    }

    private void updateDelNumber(){
        delnum=delnum+num;

    }

    private void ImageAppear(){
        appearedImage.setVisibility(View.VISIBLE);
    }

    private void ImageDisappear(){
        appearedImage.setVisibility(View.GONE);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}