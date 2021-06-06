//5 Juni 2021 - 10118323 - Riffa Alfaridzi Priatna - IF8
package com.example.a10118323_uts.View.Info;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a10118323_uts.R;
import com.google.android.material.tabs.TabLayout;

public class InfoFragment extends Fragment {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tab;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        getActivity().getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));

        tab = root.findViewById(R.id.tabInfo);
        viewPager = root.findViewById(R.id.pagerInfo);
        adapter = new ViewPagerAdapter(getContext());

        viewPager.setAdapter(adapter);

        tab.setupWithViewPager(viewPager);
        return root;
    }
}