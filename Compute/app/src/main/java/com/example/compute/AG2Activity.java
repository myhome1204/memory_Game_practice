package com.example.compute;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.compute.databinding.ActivityAg2Binding;

public class AG2Activity extends AppCompatActivity {
    private ActivityAg2Binding binding;
    CalendarView cal;
    Button startbtn, endbtn;
    Chronometer chro;
    TimePicker timePicker;
    RadioButton rdoCal, rdoTime;
    int year1, month1, day1;
    TextView tvyear, tvmonth, tvday, tvhour, tvmin;
    AutoCompleteTextView autoCompleteTextView;
    String[] str = {"hy_공대", "hy_미대", "hy_음대", "friday", "friend"}; //1

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAg2Binding.inflate(getLayoutInflater());
        tvyear = binding.tvyear;
        tvmonth = binding.tvmonth;
        tvday = binding.tvday;
        tvhour = binding.tvhour;
        tvmin = binding.tvmin;
        startbtn = binding.startbtn;
        chro = binding.chrono;
        cal = binding.calendarview;
        timePicker = binding.timepicker;
        rdoCal = binding.rdocal;
        rdoTime = binding.rdotime;
        endbtn = binding.btgnEnd;
        autoCompleteTextView = binding.autosingle;//2
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AG2Activity.this,
                android.R.layout.simple_dropdown_item_1line,
                str);
        autoCompleteTextView.setAdapter(adapter);
        cal.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);
        setContentView(binding.getRoot());
        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);
            }
        });

        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                year1 = year;
                month1 = month;
                day1 = dayOfMonth;
            }
        });

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chro.setBase(SystemClock.elapsedRealtime());
                chro.start();
                chro.setTextColor(Color.RED);
            }
        });
        endbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chro.stop();
                chro.setTextColor(Color.GREEN);
                tvyear.setText(Integer.toString(year1));
                tvmonth.setText(Integer.toString(month1));
                tvday.setText(Integer.toString(day1));
                tvhour.setText(Integer.toString(timePicker.getCurrentHour()));
                tvmin.setText(Integer.toString(timePicker.getCurrentMinute()));
            }
        });
    }
}