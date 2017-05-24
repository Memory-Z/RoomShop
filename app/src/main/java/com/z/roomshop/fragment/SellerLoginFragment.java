package com.z.roomshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.z.roomshop.R;

/**
 * Created by ZJer on 2017/4/26.
 */

public class SellerLoginFragment extends Fragment implements View.OnClickListener{

    private CheckBox chk_remember;
    private CheckBox chk_autoLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_seller_fragment, null);

        chk_remember = (CheckBox) v.findViewById(R.id.chk_seller_remember);
        chk_remember.setOnClickListener(this);
        chk_autoLogin = (CheckBox) v.findViewById(R.id.chk_seller_autoLogin);
        chk_autoLogin.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chk_seller_remember:
                if (!chk_remember.isChecked()) {
                    if (chk_autoLogin.isChecked()) {
                        chk_autoLogin.setChecked(false);
                    }
                }
                break;
            case R.id.chk_seller_autoLogin:
                if (chk_autoLogin.isChecked()) {
                    chk_remember.setChecked(true);
                }
                break;
            default:
                break;
        }
    }
}
