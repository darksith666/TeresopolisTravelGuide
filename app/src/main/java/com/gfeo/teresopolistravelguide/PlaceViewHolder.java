package com.gfeo.teresopolistravelguide;

import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Holds {@link android.view.View} objects from a Place list item layout (listitem_places.xml).
 * Used in a {@link PlaceAdapter} with a {@link Place} object; only the Views that will be
 * manipulated by the {@code PlaceAdapter} are declared. See the usage in the {@code
 * PlaceAdapter} class for details.
 *
 * @author gabrielfeo
 * @see AttributionViewHolder
 * @see <a href="https://developer.android.com/training/improving-layouts/smooth-scrolling.html">
 * Android Developers training article describing the "view holder" pattern
 * </a>
 */

class PlaceViewHolder {

	ConstraintLayout constraintLayout;
	ProgressBar progressBar;
	ImageView imageView;
	TextView textViewTitle;
	TextView textViewSubTitle;
	TextView textViewLocation;

}
