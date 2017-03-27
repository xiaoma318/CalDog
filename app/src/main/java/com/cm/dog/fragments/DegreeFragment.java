package com.cm.dog.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cm.dog.R;

/**
 * Created by chenm on 1/11/2017.
 */

public class DegreeFragment extends Fragment {
    EditText celcius, fahrenheit;

    public DegreeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_degree, container, false);
        celcius = (EditText) view.findViewById(R.id.etC);
        fahrenheit = (EditText) view.findViewById(R.id.etF);


        celcius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (celcius.hasFocus()) {
                    try {
                        float c = Float.parseFloat(s.toString());
                        float f = 9 * c / 5 + 32;
                        fahrenheit.setText(String.valueOf(Math.round(f)));
                    } catch (Exception e) {
                    }
                }
            }
        });

        fahrenheit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(fahrenheit.hasFocus()){
                    try {
                        float f = Float.parseFloat(s.toString());
                        float c = (f - 32) * 5 / 9;
                        celcius.setText(String.valueOf(Math.round(c)));
                    } catch (Exception e) {
                    }
                }

            }
        });

        return view;
    }


}
