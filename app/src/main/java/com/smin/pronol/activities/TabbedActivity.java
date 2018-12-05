package com.smin.pronol.activities;

import android.support.design.widget.TabLayout;
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

import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.smin.pronol.R;
import com.smin.pronol.SectionPageAdapter;
import com.smin.pronol.Tab1Fragment;
import com.smin.pronol.Tab2Fragment;

public class TabbedActivity extends AppCompatActivity {

    private static final String TAG = "TabbedActivity";
    ListView list;

    String[] domicile =
            {
                    "Lille",
                    "Lyon",
                    "Lyon"

            };

    private SectionPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
        mSectionsPageAdapter = new SectionPageAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);


        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Liste des matchs");
        adapter.addFragment(new Tab2Fragment(), "Historique");
        viewPager.setAdapter(adapter);
    }

}