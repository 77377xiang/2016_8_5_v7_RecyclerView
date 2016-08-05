package com.xiang.map.directionproect;

import android.animation.ObjectAnimator;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textView;
    ImageView image;
    private SensorManager sensorManager;
    private Sensor magneticSensor;
    private TextView showTextView;
    private Sensor accelerometerSensor;
    private Sensor gyroscopeSensor;
    float anglex;
    // 将纳秒转化为秒
    private static final float NS2S = 1.0f / 1000000000.0f;
    private float timestamp;
    private float angle[] = new float[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);
        textView = (TextView) findViewById(R.id.textview);


    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
        // sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL); //为传感器注册监听器
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    public void onSensorChanged(SensorEvent event) {
//        if (timestamp != 0) {
// 得到两次检测到手机旋转的时间差（纳秒），并将其转化为秒
        final float dT = (event.timestamp - timestamp) * NS2S;
// 将手机在各个轴上的旋转角度相加，即可得到当前位置相对于初始位置的旋转弧度

        angle[0] += event.values[0] * dT;
        angle[1] += event.values[1] * dT;
        angle[2] += event.values[2] * dT;
// 将弧度转化为角度
        anglex = (float) Math.toDegrees(angle[0]);
        float angley = (float) Math.toDegrees(angle[1]);
        float anglez = (float) Math.toDegrees(angle[2]);
        timestamp = event.timestamp;
        // }
        float z = anglex % 360;

        ObjectAnimator.ofFloat(image, "rotation", 0, z).setDuration(3000).start();
        // ObjectAnimator animator1 = ObjectAnimator.ofFloat(image, "rotation", 0, z);
        // animator1.setDuration(500);


//        //判断当前是加速度传感器还是地磁传感器
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {     // 注意赋值时要调用clone()方法
//            accelerometerValues = event.values.clone();
//        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
//            //注意赋值时要调用clone()方法
//            magneticValues = event.values.clone();
//        }
//        float[] R = new float[9];
//        float[] values = new float[3];
//        SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticValues);
//        SensorManager.getOrientation(R, values);


       // Log.d("MainActivity", "value[0] is " + Math.toDegrees(values[0]));


        Log.i("sss", z + "");
    }
}