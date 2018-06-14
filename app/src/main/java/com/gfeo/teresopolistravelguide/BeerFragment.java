package com.gfeo.teresopolistravelguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Uses generic methods {@link PlacesFragment} to display a View of bar Place
 * objects.
 *
 * @author gabrielfeo
 * @see PlacesFragment
 * @see TourismFragment
 * @see FoodFragment
 */
public class BeerFragment extends PlacesFragment {

	/**
	 * Required empty public constructor
	 */
	public BeerFragment() {
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
		addToArrayList(getString(R.string.resname_beer_villa_85));
		addToArrayList(getString(R.string.resname_beer_st_gallen));
		addToArrayList(getString(R.string.resname_beer_kanton_bier));
		addToArrayList(getString(R.string.resname_beer_esquina));
		addToArrayList(getString(R.string.resname_beer_confraria));
		addToArrayList(getString(R.string.resname_beer_vagao));
		addToArrayList(getString(R.string.resname_beer_armazem_cervejeiro));
	}

}
