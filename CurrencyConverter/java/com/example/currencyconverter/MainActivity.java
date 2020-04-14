package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    List<ItemModel> items;
    Spinner spinnerFirst, spinnerSecond;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btndel, btnce, btnpoint;
    boolean first = false, second = false;
    ImageView imgfirst, imgsecond;
    LinearLayout layoutfirst, layoutsecond;
    StringBuilder s1, s2;
    TextView textfirst, textsecond;
    double pound = 0.8, dong = 23432, won = 1217.38, dollar = 1;
    double weightfirst = dollar, weightsecond = dollar;
    DecimalFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        s1 = new StringBuilder();
        s2 = new StringBuilder();
        items.add(new ItemModel("United States - Dollar", R.drawable.dollar, dollar));
        items.add(new ItemModel("Korea - Won", R.drawable.won, won));
        items.add(new ItemModel("United Kingdom - Pound", R.drawable.pound, pound));
        items.add(new ItemModel("Viet Nam - Dong", R.drawable.dong, dong));
        CurrencyAdapter currencyAdapter = new CurrencyAdapter(items);
        spinnerFirst = findViewById(R.id.spinner_first);
        spinnerSecond = findViewById(R.id.spinner_second);
        spinnerFirst.setAdapter(currencyAdapter);
        spinnerSecond.setAdapter(currencyAdapter);
        imgfirst = findViewById(R.id.img_first);
        imgsecond = findViewById(R.id.img_second);
        textfirst = findViewById(R.id.text_first);
        textsecond = findViewById(R.id.text_second);
        findViewById(R.id.layout_first).setOnClickListener(this);
        findViewById(R.id.layout_second).setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btndel).setOnClickListener(this);
        findViewById(R.id.btnce).setOnClickListener(this);
        findViewById(R.id.btnpoint).setOnClickListener(this);
        spinnerFirst.setOnItemSelectedListener(this);
        spinnerSecond.setOnItemSelectedListener(this);
        format= new DecimalFormat("###,###,###.###");
        textfirst.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("") && first == true) {
                    double value = Double.parseDouble(s.toString()) * weightsecond / weightfirst;
                    textsecond.setText(String.valueOf(value));
                }
            }
        });
        textsecond.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("") && second == true) {
                    double value = Double.parseDouble(s.toString()) * weightfirst / weightsecond;
                    textfirst.setText(String.valueOf(value));
                }


            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner_first) {
            imgfirst.setImageResource(items.get(position).getCurrencySymbol());
            weightfirst = items.get(position).getWeight();
        } else {
            imgsecond.setImageResource(items.get(position).getCurrencySymbol());
            weightsecond = items.get(position).getWeight();
        }
        if (!s1.toString().equals(""))
            s1.delete(0, s1.length());
        if (!s2.toString().equals(""))
            s2.delete(0, s2.length());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.layout_first) {
            first = true;
            second = false;
            textfirst.setTypeface(Typeface.DEFAULT_BOLD);
            textsecond.setTypeface(Typeface.DEFAULT);
            if (!s1.toString().equals(""))
                s1.delete(0, s1.length());
            if (!s2.toString().equals(""))
                s2.delete(0, s2.length());
        } else if (id == R.id.layout_second) {
            first = false;
            second = true;
            textsecond.setTypeface(Typeface.DEFAULT_BOLD);
            textfirst.setTypeface(Typeface.DEFAULT);
            if (!s1.toString().equals(""))
                s1.delete(0, s1.length());
            if (!s2.toString().equals(""))
                s2.delete(0, s2.length());
        }
        if (first) {
            if (!s1.toString().equals("")) {
                if (id == R.id.btnce) {
                    s1.delete(0, s1.length());
                }
                if (id == R.id.btndel) {
                    s1.deleteCharAt(s1.length() - 1);
                }
                textfirst.setText(s1.toString());
            }
            if ((id == R.id.btn0)) {
                s1.append('0');
                textfirst.setText(s1.toString());
            } else if (id == R.id.btn1) {
                s1.append('1');
                textfirst.setText(s1.toString());
            } else if (id == R.id.btn2) {
                s1.append('2');
                textfirst.setText(s1.toString());
            } else if (id == R.id.btn3) {
                s1.append('3');
                textfirst.setText(s1.toString());
            } else if (id == R.id.btn4) {
                s1.append('4');
                textfirst.setText(s1.toString());
            } else if (id == R.id.btn5) {
                s1.append('5');
                textfirst.setText(s1.toString());
            } else if (id == R.id.btn6) {
                s1.append('6');
                textfirst.setText(s1.toString());
            } else if (id == R.id.btn7) {
                s1.append('7');
                textfirst.setText(s1.toString());
            } else if (id == R.id.btn8) {
                s1.append('8');
                textfirst.setText(s1.toString());
            } else if (id == R.id.btn9) {
                s1.append('9');
                textfirst.setText(s1.toString());
            } else if (id == R.id.btnpoint) {
                s1.append('.');
                textfirst.setText(s1.toString());
            }
        } else if (second) {
            if (!s2.toString().equals("")) {
                if (id == R.id.btnce) {
                    s2.delete(0, s2.length());
                }
                if (id == R.id.btndel) {
                    s2.deleteCharAt(s2.length() - 1);
                }
                textsecond.setText(s2.toString());

            }
            if (id == R.id.btn0) {
                s2.append('0');
                textsecond.setText(s2.toString());
            } else if (id == R.id.btn1) {
                s2.append('1');
                textsecond.setText(s2.toString());
            } else if (id == R.id.btn2) {
                s2.append('2');
                textsecond.setText(s2.toString());
            } else if (id == R.id.btn3) {
                s2.append('3');
                textsecond.setText(s2.toString());
            } else if (id == R.id.btn4) {
                s2.append('4');
                textsecond.setText(s2.toString());
            } else if (id == R.id.btn5) {
                s2.append('5');
                textsecond.setText(s2.toString());
            } else if (id == R.id.btn6) {
                s2.append('6');
                textsecond.setText(s2.toString());
            } else if (id == R.id.btn7) {
                s2.append('7');
                textsecond.setText(s2.toString());
            } else if (id == R.id.btn8) {
                s2.append('8');
                textsecond.setText(s2.toString());
            } else if (id == R.id.btn9) {
                s2.append('9');
                textsecond.setText(s2.toString());
            } else if (id == R.id.btnpoint) {
                s2.append('.');
                textsecond.setText(s2.toString());
            }


        }
    }
}
