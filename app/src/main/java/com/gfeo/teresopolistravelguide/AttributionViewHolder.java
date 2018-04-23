package com.gfeo.teresopolistravelguide;

import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Holds {@link android.view.View} objects from an Attribution list item layout (about_list_item
 * .xml). Used in an {@link AttributionAdapter} with an {@link Attribution} object; only Views
 * that will be manipulated by the {@code AttributionAdapter} are declared. See the usage in the
 * {@code AttributionAdapter} class for details.
 *
 * @author gabrielfeo
 * @see PlaceViewHolder
 * @see <a href="https://developer.android.com/training/improving-layouts/smooth-scrolling.html">
 * Android Developers training article describing the "view holder" pattern
 * </a>
 */

class AttributionViewHolder {

	ConstraintLayout constraintLayout;
	ProgressBar progressBar;
	ImageView imageView;
	TextView textView;

}
