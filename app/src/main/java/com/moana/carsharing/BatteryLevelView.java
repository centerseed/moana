package com.moana.carsharing;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class BatteryLevelView extends FrameLayout {

    int mHintW;
    int mLevel;
    float mDpi;

    public BatteryLevelView(Context context) {
        super(context);
        init();
    }

    public BatteryLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BatteryLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BatteryLevelView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        mDpi = getResources().getDisplayMetrics().density;
        this.setBackgroundResource(R.mipmap.bk_carinfo_battery);
    }

    public void setLevel(final int charge) {
        if (this.getChildCount() > 0) {
            this.removeAllViews();
        }

        mLevel = charge / 10;

        ViewTreeObserver vto2 = this.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                BatteryLevelView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                mHintW = BatteryLevelView.this.getMeasuredWidth();
                int hintW = (int) ((mHintW - 120) / 10);
                final LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setPadding(25, 5, 25, 5);

                View dummy = new View(getContext());
                LinearLayout.LayoutParams dummyParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                dummyParams.weight = 1;
                dummy.setLayoutParams(dummyParams);

                linearLayout.addView(dummy);
                for (int i = 0; i < mLevel; i++) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(hintW, ViewGroup.LayoutParams.MATCH_PARENT);
                    params.setMargins(2, (int) (7.5 * mDpi), 3, (int) (7.5 * mDpi));
                    ImageView hint = new ImageView(getContext());
                    if (mLevel > 2)
                        hint.setImageResource(R.mipmap.bk_carinfo_battery_green);
                    else
                        hint.setImageResource(R.mipmap.bk_carinfo_battery_red);
                    hint.setScaleType(ImageView.ScaleType.FIT_XY);
                    hint.setLayoutParams(params);
                    linearLayout.addView(hint);
                }

                BatteryLevelView.this.addView(linearLayout);
            }
        });

        invalidate();
    }
}
