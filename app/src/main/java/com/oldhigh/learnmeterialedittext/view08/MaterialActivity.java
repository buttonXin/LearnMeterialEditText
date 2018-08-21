package com.oldhigh.learnmeterialedittext.view08;

import android.view.View;

import com.oldhigh.learnmeterialedittext.BaseActivity;
import com.oldhigh.learnmeterialedittext.R;

/**
 * 学习materialEdittext
 * @author oldhigh
 */
public class MaterialActivity extends BaseActivity{


    @Override
    protected void initView() {

        final MaterialEditText met = findViewById(R.id.met);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                met.setNeedImage(!met.isNeedImage());
            }
        });
    }

    @Override
    protected int layoutResId() {
        return R.layout.activity_material;
    }


}
