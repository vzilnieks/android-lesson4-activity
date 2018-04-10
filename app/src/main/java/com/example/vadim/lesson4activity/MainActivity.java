package com.example.vadim.lesson4activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements BlankFragment.OnFragmentInteractionListener, BlankFragment2.OnFragmentInteractionListener {

  static final String STATE_SCORE = "STATE_SCORE";
  static final String STATE_LEVEL = "STATE_LEVEL";

  int mCurrentScore = 0;
  int mCurrentLevel = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // vadim: show 1st fragment
//    addFragment(new BlankFragment());

    // vadim: to load Main2Activity
    Intent intent = new Intent(this, Main2Activity.class);
    intent.putExtra("SOME_DATA_KEY", "SOME_DATA");
    startActivity(intent);
  }

  public void onSetScoreClick(View view) {
    mCurrentLevel = 10;
    mCurrentScore = 20;
  }

  public void addFragment(Fragment fragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.list,fragment);
    fragmentTransaction.commit();
  }

  @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
    savedInstanceState.putInt(STATE_SCORE,mCurrentScore);
    savedInstanceState.putInt(STATE_LEVEL,mCurrentLevel);

    super.onSaveInstanceState(savedInstanceState);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

    mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
    mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      return true;
    }

    if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
      return true;
    }

    return super.onKeyDown(keyCode, event);
  }

  @Override
  public void onFragmentInteraction(Uri uri) {
    addFragment(new BlankFragment2());
  }
}
