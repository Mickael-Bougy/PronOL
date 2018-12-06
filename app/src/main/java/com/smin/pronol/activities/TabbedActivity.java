package com.smin.pronol.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smin.pronol.R;
import com.smin.pronol.SectionPageAdapter;
import com.smin.pronol.Tab1Fragment;
import com.smin.pronol.Tab2Fragment;

public class TabbedActivity extends AppCompatActivity {

    private static final String TAG = "TabbedActivity";

    private SectionPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private FirebaseAuth session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
        mSectionsPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        session = FirebaseAuth.getInstance();

        mViewPager = findViewById(R.id.container);
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

    /**
     *  Gestion de l'appuie sur le bouton retour de l'appareil, permet la déconnexion de l'utilsateur
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Are you sure you want to disconnect ?")
                    .setTitle("Disconnction")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            session.signOut();
                            startActivity(new Intent(TabbedActivity.this, LoginActivity.class));
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
