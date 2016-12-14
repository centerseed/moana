package com.moana.carsharing.guideline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.moana.carsharing.R;

public class SimpleGuideActivity extends AppCompatActivity {

    public static final String ARG_GUIDE_IMAGE = "_guide_img";
    public static final String ARG_GUIDE_TEXT = "guide_text";

    ImageView mGuideImage;
    TextView mGuideText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_guide_content);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("加入會員說明");

        mGuideImage = (ImageView) findViewById(R.id.guide_img);
        mGuideImage.setImageResource(getIntent().getIntExtra(ARG_GUIDE_IMAGE, 0));

        mGuideText = (TextView) findViewById(R.id.guide_text);
        mGuideText.setText(getIntent().getIntExtra(ARG_GUIDE_TEXT, 0));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
