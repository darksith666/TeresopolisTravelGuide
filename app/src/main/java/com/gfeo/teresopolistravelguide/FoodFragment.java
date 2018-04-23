package com.gfeo.teresopolistravelguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Uses generic methods {@link PlacesFragment} to display a View of restaurant Place
 * objects.
 *
 * @author gabrielfeo
 * @see PlacesFragment
 * @see TourismFragment
 * @see BeerFragment
 */
public class FoodFragment extends PlacesFragment {

	/**
	 * Required empty public constructor
	 */
	public FoodFragment() {
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
		addToArrayList(getString(R.string.resname_food_dona_irene));
		addToArrayList(getString(R.string.resname_food_taberna_alpina));
		addToArrayList(getString(R.string.resname_food_st_gallen));
		addToArrayList(getString(R.string.resname_food_viva_italia));
		addToArrayList(getString(R.string.resname_food_manjericao));
		addToArrayList(getString(R.string.resname_food_caldo_de_piranha));
		addToArrayList(getString(R.string.resname_food_recanto_dos_pescadores));
		addToArrayList(getString(R.string.resname_food_casa_da_picanha));
		addToArrayList(getString(R.string.resname_food_recanto_do_fondue));
		addToArrayList(getString(R.string.resname_food_recanto_sushi));
		addToArrayList(getString(R.string.resname_food_botania));
		setupListView(view, placeArrayList);
		return view;
	}
}
