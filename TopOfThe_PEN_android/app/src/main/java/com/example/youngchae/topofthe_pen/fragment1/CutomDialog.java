package com.example.youngchae.topofthe_pen.fragment1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.youngchae.topofthe_pen.R;

/**
 * Created by youngchae on 2016-07-23.
 */
public class CutomDialog extends Dialog {
    private EditText mEditSubject;
    private EditText mEditBook;
    private Spinner mHourSpinner;
    private Spinner mMinuateSpinner;
//    private EditText mEditTime;
    private Button mLeftButton;
    private Button mRightButton;

    private int hour;
    private int minuates;

    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;

    public CutomDialog(Context context
            , View.OnClickListener leftLitener,View.OnClickListener rightListener) {
        super(context);
        this.mLeftClickListener = leftLitener;
        this.mRightClickListener = rightListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.cutom_dialog);

        mEditSubject = (EditText) findViewById(R.id.edit_subject);
        mEditBook = (EditText) findViewById(R.id.edit_book);
//        mEditTime = (EditText) findViewById(R.id.edit_time);
        mHourSpinner = (Spinner) findViewById(R.id.spinner_hour);
        mMinuateSpinner= (Spinner) findViewById(R.id.spinner_minuate);
        mHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("asdf",adapterView.getItemAtPosition(i).toString().substring(0,1));
                hour = Integer.valueOf(adapterView.getItemAtPosition(i).toString().substring(0,1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mMinuateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                minuates = Integer.valueOf(adapterView.getItemAtPosition(i).toString());
                Log.d("asdf",adapterView.getItemAtPosition(i).toString().substring(0,2));
                minuates = Integer.valueOf(adapterView.getItemAtPosition(i).toString().substring(0,2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mLeftButton = (Button) findViewById(R.id.leftButton);
        mRightButton = (Button) findViewById(R.id.rightButton);

        mLeftButton.setOnClickListener(mLeftClickListener);
        mRightButton.setOnClickListener(mRightClickListener);
    }

    public String getSubject(){
        return mEditSubject.getText().toString();
    }
    public String getBook(){
        return mEditBook.getText().toString();
    }
    public String getTime(){
        return String.valueOf(hour*60+minuates);
    }
}
