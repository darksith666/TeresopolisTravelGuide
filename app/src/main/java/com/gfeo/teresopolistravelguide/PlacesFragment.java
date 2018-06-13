package com.gfeo.teresopolistravelguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * This class is extended by more specific classes such as the {@link FoodFragment}
 * class. It provides methods and fields common to classes that manipulate {@link Place} objects.
 *
 * @author gabrielfeo
 * @see TourismFragment
 * @see FoodFragment
 * @see BeerFragment
 */

public class PlacesFragment extends Fragment {

	/** ArrayList of {@link Place} objects to be passed into a {@link PlaceAdapter} */
	protected final ArrayList<Place> placeArrayList = new ArrayList<>();

	/**
	 * Inflates the places_fragment layout first with a null "root ViewGroup", and then inflates
	 * the same layout a second time, but now passing in a ViewGroup from the first inflated layout
	 * as a "root ViewGroup" argument.
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_places, null, false);
		return view;
	}

	/**
	 * Adds to the {@link PlacesFragment#placeArrayList} the {@link Place} object corresponding to
	 * the given resource name.
	 *
	 * @param resourceName the universal resource name. "Universal" because, for a certain place,
	 *                     the same resource name is used for the related strings, drawables
	 *                     and anything else.
	 */
	protected void addToArrayList(String resourceName) {
		placeArrayList.add(new Place(getContext(), resourceName));
	}

	/**
	 * Sets a PlaceAdapter for the {@link ListView} in the given View layout, passing into the
	 * adapter an {@linkplain PlacesFragment#placeArrayList ArrayList of place objects}. Used by
	 * subclasses.
	 *
	 * @param placeArrayList an ArrayList of Place objects.
	 * @param view           the layout containing the ListView to which the adapter will be set.
	 */
	protected void setupListView(View view, ArrayList<Place> placeArrayList) {
		((ListView) view.findViewById(R.id.places_listview))
				.setAdapter(new PlaceAdapter(getContext(), placeArrayList));
	}


}
