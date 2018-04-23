package com.gfeo.teresopolistravelguide;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * The main screen of the app. Uses a ViewPager to display two Fragments that make for the bigger
 * part of the screen and functionality.
 *
 * @author gabrielfeo
 */

public class MainActivity extends AppCompatActivity {

	/**
	 * Performs a layout and toolbar related routine, setting up the Content View, a
	 * {@link ViewPager}, the Support Action Bar and a {@link TabLayout}.
	 *
	 * @see SimpleFragmentPagerAdapter
	 */
	/*
	 * REVIEWER: I read the JavaDoc how-to document and it states a Doc comment "must precede
	 * a class, field, constructor or method declaration". However, the onCreate method seems very
	 * commonplace to me, do I have to write a Doc comment for it at all? */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbarMain = findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbarMain);
		toolbarMain.showOverflowMenu();

		ViewPager viewPager = findViewById(R.id.main_viewpager);
		TabLayout tabLayout = findViewById(R.id.main_tablayout);

		viewPager.setAdapter(new SimpleFragmentPagerAdapter(this, getSupportFragmentManager()));
		tabLayout.setupWithViewPager(viewPager);
	}

	/**
	 * Inflates the action bar menu layout.
	 *
	 * @return true - so that the menu will be displayed
	 */
	/* REVIEWER: The JavaDoc how-to document and, as I understand it, a link to the overridden
	* method will be automatically included in the documentation. However, I don't know when I
	* should write specific @return and @param tags myself, especially because I don't understand
	* certain parameters such as the "menu" in onCreateOptionsMenu. In this case, for example,
	* was it necessary to write the @return tag or would the inherited doc be sufficient? */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toolbar_menu, menu);
		return true;
	}

	/** Starts the About Activity when the About button is clicked in the overflow menu. */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.main_menu_about) {
			startActivity(new Intent(getApplicationContext(), AboutActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}
}
