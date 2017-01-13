package com.cm.caldog.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cm.caldog.R;

/**
 * Created by chenm on 1/11/2017.
 */

public class TipFragment extends Fragment {
    EditText etInput = null;
    TextView tvResult, tvPeople;
    RadioGroup gbSelect = null;
    SeekBar seekBar = null;
    int people = 1;

    SparseArray<Double> rateMap = new SparseArray<Double>(4) {
        {
            put(R.id.rb_10, 0.1);
            put(R.id.rb_13, 0.13);
            put(R.id.rb_15, 0.15);
            put(R.id.rb_18, 0.18);
        }
    };

    public TipFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip, container, false);
        etInput = (EditText) view.findViewById(R.id.et_input);
        tvResult = (TextView) view.findViewById(R.id.tv_result);
        tvPeople = (TextView) view.findViewById(R.id.tv_people);
        gbSelect = (RadioGroup) view.findViewById(R.id.gb_select);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        people = seekBar.getProgress() + 1;
        tvPeople.setText(people + " ppl");

        (view.findViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal();
            }
        });


        gbSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                cal();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                people = seekBar.getProgress() + 1;
                tvPeople.setText(people + " ppl");
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        return view;
    }

    private void cal() {
        int rate = gbSelect.getCheckedRadioButtonId();
        double actualRate = rateMap.get(rate);
        double input = 0;
        try{
            input = Double.parseDouble(etInput.getText().toString());
        }catch (Exception e){ }

        tvResult.setText(String.format("Tip: \t\t%.1f\nTotal: \t\t%.1f\nAvg: \t\t%.1f", actualRate * input, actualRate * input + input, (actualRate * input + input) / people));
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
    }
}
