package com.cm.caldog.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cm.caldog.R;

import java.text.DecimalFormat;

/**
 * Created by chenm on 1/11/2017.
 */

public class LoanFragment extends Fragment {
    TextView result;
    EditText month, apr, price;
    Button calcaulte;

    public LoanFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loan, container, false);
        month = (EditText) view.findViewById(R.id.month);
        apr = (EditText) view.findViewById(R.id.apr);
        price = (EditText) view.findViewById(R.id.price);
        calcaulte = (Button)view.findViewById(R.id.calculate);
        result = (TextView)view.findViewById(R.id.result);

        calcaulte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int m = Integer.parseInt(month.getText().toString());
                    double a = Double.parseDouble(apr.getText().toString());
                    double p = Double.parseDouble(price.getText().toString());

                    result.setText(calMonthPay(m, a, p)+" /month");
                }catch (Exception e){}

            }
        });

        return view;
    }

    private String calMonthPay(int numOfMonth, double apr, double total) {
        double monthRate = apr / 12;
        double discountFactor = (Math.pow((1 + monthRate), numOfMonth) - 1) / (monthRate * Math.pow(1 + monthRate, numOfMonth));
        DecimalFormat formatter = new DecimalFormat("#,###.00");

        Activity activity = getActivity();
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return formatter.format(total / discountFactor);
    }
}
