package com.smin.pronol.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;

import com.google.firebase.auth.FirebaseAuth;
import com.smin.pronol.Match;
import com.smin.pronol.fragement.ListeMatchFrag;
import com.smin.pronol.R;
import com.smin.pronol.liste.SectionPageAdapter;
import com.smin.pronol.fragement.HistoriqueFrag;

import java.util.List;

public class MainTabActivity extends AppCompatActivity {

    private static final String TAG = "MainTabActivity";
    public static List<Match> matchListBis;
    private SectionPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private FirebaseAuth session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
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
        adapter.addFragment(new ListeMatchFrag(), getString(R.string.matchList));
        adapter.addFragment(new HistoriqueFrag(), getString(R.string.historical));

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
            AlertDialog.Builder builder = new AlertDialog.Builder(MainTabActivity.this);
            builder.setMessage(getString(R.string.disconnection_msg))
                    .setTitle(getString(R.string.disconnection))
                    .setNegativeButton(getString(R.string.no), null)
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            session.signOut();
                            startActivity(new Intent(MainTabActivity.this, LoginActivity.class));
                            finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        return super.onKeyDown(keyCode, event);
    }
}
