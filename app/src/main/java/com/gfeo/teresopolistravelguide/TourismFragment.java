package com.gfeo.teresopolistravelguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Uses generic methods {@link PlacesFragment} to display a View of tourist attraction Place
 * objects.
 *
 * @author gabrielfeo
 * @see PlacesFragment
 * @see FoodFragment
 * @see BeerFragment
 */
public class TourismFragment extends PlacesFragment {

	/**
	 * Required empty public constructor
	 */
	public TourismFragment() {
	}

	/**
	 * Inflates the {@link Fragment} layout, fills the inherited
	 * {@link PlacesFragment#placeArrayList} with the relevant {@link Place} objects and sets up
	 * the layout {@link android.widget.ListView}. Common {@link PlacesFragment} fields and
	 * methods are used here, including a generic fragment_places.xml layout, for example.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		fillArrayList();
		setupListView(view, placeArrayList);
		return view;
	}

	private void fillArrayList() {
		addToArrayList(getString(R.string.resname_tourism_mirante_do_soberbo));
		addToArrayList(getString(R.string.resname_tourism_parnaso));
		addToArrayList(getString(R.string.resname_tourism_feirinha));
		addToArrayList(getString(R.string.resname_tourism_comary));
		addToArrayList(getString(R.string.resname_tourism_fonte_judith));
	}

}
