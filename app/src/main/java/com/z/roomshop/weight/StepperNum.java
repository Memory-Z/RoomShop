package com.z.roomshop.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.z.roomshop.R;

/**
 * Created by ZJer on 2017/5/24.
 */

public class StepperNum extends LinearLayout {

    private ImageButton ibtnMinus, ibtnAdds;
    private EditText txtNum;
    private String num;

    public StepperNum(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.stepper_num, null);

        initView();
    }

    private void initView() {
        ibtnMinus = (ImageButton) findViewById(R.id.ibtn_stepper_minus);
        ibtnMinus.setOnClickListener(new ImagetButtonOnClick());
        ibtnAdds = (ImageButton) findViewById(R.id.ibtn_stepper_add);
        ibtnAdds.setOnClickListener(new ImagetButtonOnClick());
        txtNum = (EditText) findViewById(R.id.edt_stepper_num);

    }

    public int getTextNum() {
        int number = 0;
        num = txtNum.getText().toString();

        number = Integer.parseInt(num);

        return number;
    }

    class ImagetButtonOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ibtn_stepper_minus:

                    break;
                case R.id.ibtn_stepper_add:

                    break;
                default:
                    break;
            }
        }
    }
}
