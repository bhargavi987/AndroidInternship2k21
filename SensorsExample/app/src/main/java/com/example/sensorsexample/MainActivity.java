package com.example.sensorsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.github.nisrulz.sensey.LightDetector;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.ShakeDetector;
import com.github.nisrulz.sensey.TouchTypeDetector;

public class MainActivity extends AppCompatActivity {
    Switch shake,light,touch;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shake = findViewById(R.id.shake);
        light = findViewById(R.id.light);
        touch = findViewById(R.id.touch);
        tv = findViewById(R.id.mysensor);
        Sensey.getInstance().init(this);
        ShakeDetector.ShakeListener listener = new ShakeDetector.ShakeListener() {
            @Override
            public void onShakeDetected() {
              tv.setText("Shake Sensor Detected");
            }

            @Override
            public void onShakeStopped() {
                tv.setText("Shake Sensor Stopped");
            }
        };
        shake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Sensey.getInstance().startShakeDetection(listener);
                } else {
                    Sensey.getInstance().stopShakeDetection(listener);
                }
            }
        });
        LightDetector.LightListener lightListener = new LightDetector.LightListener() {
            @Override
            public void onDark() {
               tv.setText("Is it dark Here");
            }

            @Override
            public void onLight() {
                tv.setText("It is bright Here");
            }
        };
        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Sensey.getInstance().startLightDetection(lightListener);
                }else {
                    Sensey.getInstance().stopLightDetection(lightListener);
                }
            }
        });
        TouchTypeDetector.TouchTypListener touchTypListener = new TouchTypeDetector.TouchTypListener() {
            @Override
            public void onDoubleTap() {
                tv.setText("Double Tapped");
            }

            @Override
            public void onLongPress() {
                tv.setText("Long Pressed");
            }

            @Override
            public void onScroll(int i) {
                tv.setText("On Scrolled");
            }

            @Override
            public void onSingleTap() {
                tv.setText("Single Tap");
            }

            @Override
            public void onSwipe(int i) {
                tv.setText("Swipped");
            }

            @Override
            public void onThreeFingerSingleTap() {
                tv.setText("Three finger tap");
            }

            @Override
            public void onTwoFingerSingleTap() {
                tv.setText("Two finger tap");
            }
        };
        touch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Sensey.getInstance().startTouchTypeDetection(MainActivity.this,touchTypListener);
                } else {
                    Sensey.getInstance().stopTouchTypeDetection();
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Sensey.getInstance().setupDispatchTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Sensey.getInstance().stop();
    }
}