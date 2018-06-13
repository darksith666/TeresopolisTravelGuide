package com.gfeo.teresopolistravelguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Displays information about the developer, as well as attribution and license of the photos used
 * throughout the app.
 *
 * @author gabrielfeo
 * @see Attribution
 * @see AttributionAdapter
 */

public class AboutActivity extends AppCompatActivity {
	/**
	 * ArrayList of {@link Attribution} objects to be passed into an {@link AttributionAdapter}
	 * .
	 */
	private final ArrayList<Attribution> attributionArrayList = new ArrayList<>();

	/**
	 * Inflates the content view layout, sets the content view and configures the support action
	 * bar. Also sets up the ListView with an {@link AttributionAdapter} and the
	 * {@link AboutActivity#attributionArrayList}.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		setSupportActionBar((Toolbar) findViewById(R.id.about_toolbar));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		addToAttributionArrayList(getString(R.string.resname_attribution_mirante_do_soberbo));
		addToAttributionArrayList(getString(R.string.resname_attribution_parnaso));
		addToAttributionArrayList(getString(R.string.resname_attribution_feirinha));
		addToAttributionArrayList(getString(R.string.resname_attribution_comary));
		addToAttributionArrayList(getString(R.string.resname_attribution_fonte_judith));

		ListView listView = findViewById(R.id.about_listview);
		listView.setAdapter(new AttributionAdapter(this, attributionArrayList));
		listView.addHeaderView(View.inflate(this, R.layout.listitem_about_header, null));
		listView.addFooterView(View.inflate(this, R.layout.listitem_about_footer, null));
	}

	/**
	 * Adds to the {@link AboutActivity#attributionArrayList} the {@link Attribution} object
	 * corresponding to the given resource name.
	 *
	 * @param resourceName the universal resource name. "Universal" because, for a certain place,
	 *                     the same resource name is used for the related strings, drawables
	 *                     and anything else.
	 */
	private void addToAttributionArrayList(String resourceName) {
		attributionArrayList.add(new Attribution(this, resourceName));
	}

}
