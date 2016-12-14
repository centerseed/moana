package com.moana.carsharing.guideline;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moana.carsharing.R;

public class GuideContentFragment extends Fragment {

    private static final String ARG_GUIDE_IMAGE = "_guide_img";
    private static final String ARG_GUIDE_TEXT = "guide_text";

    private int mGuideImgResId;
    private int mGuideTextResId;

    ImageView mGuideImage;
    TextView mGuideText;

    public GuideContentFragment() {
    }

    public static GuideContentFragment newInstance(int imgResourceId, int textResourceId) {
        GuideContentFragment fragment = new GuideContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_GUIDE_IMAGE, imgResourceId);
        args.putInt(ARG_GUIDE_TEXT, textResourceId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGuideImgResId = getArguments().getInt(ARG_GUIDE_IMAGE);
            mGuideTextResId = getArguments().getInt(ARG_GUIDE_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide_content, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGuideImage = (ImageView) view.findViewById(R.id.guide_img);
        mGuideImage.setImageResource(mGuideImgResId);

        mGuideText = (TextView) view.findViewById(R.id.guide_text);
        mGuideText.setText(mGuideTextResId);
    }
}
