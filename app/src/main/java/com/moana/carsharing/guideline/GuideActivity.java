package com.moana.carsharing.guideline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.moana.carsharing.R;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("租賃規範");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddMemberClicked(View view) {
        toSimpleGuideActivity(R.mipmap.icon_member_add, R.string.guide_add_member);
    }

    public void onRentCarClicked(View view) {
        Intent intent = new Intent(this, RentCarGuideActivity.class);
        startActivity(intent);
    }

    public void onUsePlugClicked(View view) {
        toSimpleGuideActivity(R.mipmap.icon_member_evcharge, R.string.guide_use_plug);
    }

    private void toSimpleGuideActivity(int imgId, int textId) {
        Intent intent = new Intent(this, SimpleGuideActivity.class);
        intent.putExtra(SimpleGuideActivity.ARG_GUIDE_IMAGE, imgId);
        intent.putExtra(SimpleGuideActivity.ARG_GUIDE_TEXT, textId);
        startActivity(intent);
    }
}
