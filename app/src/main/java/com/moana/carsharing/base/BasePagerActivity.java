package com.moana.carsharing.base;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.moana.carsharing.R;

public abstract class BasePagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, BaseSettingFragment.OnFragmentInteractionListener {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentPagerAdapter sectionsPagerAdapter = getPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(sectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        String title = getString(R.string.title_register_personal) + "(1/" + mViewPager.getAdapter().getCount() + ")";
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        String title = getString(R.string.title_register_personal) + "(" + ++position + "/" + mViewPager.getAdapter().getCount() + ")";
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void toBackFragment() {
        if (mViewPager.getCurrentItem() > 0)
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
    }

    @Override
    public void toNextFragment() {
        if (mViewPager.getCurrentItem() < mViewPager.getAdapter().getCount() - 1)
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_base_pager, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    protected abstract FragmentPagerAdapter getPagerAdapter(FragmentManager fm);
}
