package com.cm.dog.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.cm.dog.R;


/**
 * Created by chenm on 1/11/2017.
 */

public class TipFragment extends Fragment {
    EditText etInput = null;
    TextView tvTip, tvTotal, tvAvg, tvPeople;
    RadioGroup gbSelect = null;
    Spinner spinnerPeople = null;
    int people = 1;
    String numbers[] = {"1","2","3","4","5","6","7","8"};

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
        tvTip = (TextView) view.findViewById(R.id.tv_tip);
        tvTotal = (TextView) view.findViewById(R.id.tv_total);
        tvAvg = (TextView) view.findViewById(R.id.tv_avg);
        tvPeople = (TextView) view.findViewById(R.id.tv_people);
        gbSelect = (RadioGroup) view.findViewById(R.id.gb_select);
        spinnerPeople = (Spinner) view.findViewById(R.id.spinner_people);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.spinner_people, numbers);
        spinnerPeople.setAdapter(adapter);
        spinnerPeople.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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

        return view;
    }

    private void cal() {
        int rate = gbSelect.getCheckedRadioButtonId();
        double actualRate = rateMap.get(rate);
        double input = 0;
        try {
            people = Integer.parseInt(spinnerPeople.getSelectedItem().toString());
            input = Double.parseDouble(etInput.getText().toString());
        } catch (Exception e) {
        }

        tvTip.setText(String.format("$%.1f", actualRate * input ));
        tvTotal.setText(String.format("$%.1f", actualRate * input + input));
        tvAvg.setText(String.format("$%.1f", (actualRate * input + input) / people));

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
    }
}
