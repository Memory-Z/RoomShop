package com.z.roomshop.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.z.roomshop.R;

/**
 * Created by ZJer on 2017/5/5.
 */

public class StepperView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "StepperView";

    private ImageButton ibtn_minus;
    private ImageButton ibtn_add;
    private EditText edt;
    private TextView txt_save;
    public StepperView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.stepper_view, this);

        ibtn_minus = (ImageButton) findViewById(R.id.ibtn_stepper_minus);
        ibtn_minus.setOnClickListener(this);
        ibtn_add = (ImageButton) findViewById(R.id.ibtn_stepper_add);
        ibtn_add.setOnClickListener(this);
        edt = (EditText) findViewById(R.id.edt_stepper_num);
        txt_save = (TextView) findViewById(R.id.txt_stepper_save);
    }

    /**
     * 实现保存按钮点击监听事件
     * @param listener 点击事件
     */
    public void setSaveOnClickListener(OnClickListener listener) {
        txt_save.setOnClickListener(listener);
    }

    /**
     * 设置数量的初值
     * @param value
     */
    public void setNum(String value) {
        edt.setText(value);
    }

    /**
     * 获取数目
     * @return String 类型的数目
     */
    public String getNum() {
        return edt.getText().toString();
    }

    @Override
    public void onClick(View v) {
        String str_num = edt.getText().toString();
        int num = Integer.parseInt(str_num);
        switch (v.getId()) {
            case R.id.ibtn_stepper_minus:
                num -= 1;
                ibtn_add.setClickable(true);
                ibtn_add.setPressed(false);
                ibtn_add.setImageResource(R.drawable.view_stepper_add_selector);
                if (num == 0) {
                    ibtn_minus.setClickable(false);
                    ibtn_minus.setPressed(true);
                    ibtn_minus.setImageResource(R.drawable.view_ic_stepper_minus_box_press_24dp);
                }
                edt.setText(num + "");
                break;
            case R.id.ibtn_stepper_add:
                num += 1;
                ibtn_minus.setClickable(true);
                ibtn_minus.setPressed(false);
                ibtn_minus.setImageResource(R.drawable.view_stepper_minus_selector);
                if (num == 999) {
                    ibtn_add.setClickable(false);
                    ibtn_add.setPressed(true);
                    ibtn_add.setImageResource(R.drawable.view_ic_stepper_add_box_press_24dp);
                }
                edt.setText(num + "");
                break;
            default:
                break;
        }
    }

}
