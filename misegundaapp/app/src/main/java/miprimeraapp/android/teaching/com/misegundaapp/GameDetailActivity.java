package miprimeraapp.android.teaching.com.misegundaapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import fragments.GameDetailFragment;
import model.GameModel;
import presenter.gamedetailpresenter;
import view.gamedetailview;


//aqui se implenta los metodos que es el implent metodo
public class GameDetailActivity extends AppCompatActivity
        implements gamedetailview {
    private gamedetailpresenter presenter;
    private int currentPosition;
    private String currentGameWebsite;
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        presenter = new gamedetailpresenter();
        Toolbar myToolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new gamedetailpresenter();

        currentPosition = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startPresenting(this);

        final ViewPager myViewPager = findViewById(R.id.view_page);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myPagerAdapter);
        myViewPager.setCurrentItem(currentPosition);
        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // cambio el titulo de la toobalr con esto
                getSupportActionBar().setTitle(myPagerAdapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onGameLoaded(GameModel game) {
    }


    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            int gameId = presenter.getGames().get (position).getId();
            return GameDetailFragment.newInstance(gameId);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return presenter.getGames().get(position).getName();
        }

        @Override
        public int getCount() {
            return presenter.getGames().size();
        }
    }
}
