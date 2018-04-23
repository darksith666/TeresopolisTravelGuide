package com.gfeo.teresopolistravelguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * An Adapter of {@link Place} objects. Provides ready Views for ListViews. Uses the
 * <a href="https://bumptech.github.io/glide/">Glide</a> library to load images.
 *
 * @author gabrielfeo
 */

class PlaceAdapter extends ArrayAdapter<Place> {

	PlaceAdapter(Context context, ArrayList<Place> placeArrayList) {
		super(context, 0, placeArrayList);
	}

	/**
	 * <p>
	 * Gets the {@link android.widget.ListView} item at the specified position and inflates it
	 * if it doesn't exist. Then, sets the text of the layout's TextViews to the Strings
	 * provided by the {@link Place#getTitle()} methods from the {@link Place} item at the
	 * current position; loads the drawable from the resource ID returned by the related
	 * {@link Place#getThumbnailResourceId()} method into the layout's {@link ImageView}; sets an
	 * OnClickListener to the layout's root ViewGroup for opening the Google Maps application
	 * with the search query composed of the current {@link Place#mTitle} and
	 * {@link Place#mLocation}, provided by the respective methods from the current {@code Place}
	 * item.
	 * </p>
	 * <p>
	 * Employs a View holder pattern.
	 * </p>
	 *
	 * @see PlaceViewHolder
	 */
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		final Context mContext = getContext();
		final PlaceViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_places,
			                                                     parent,
			                                                     false
			                                                    );
			viewHolder = new PlaceViewHolder();
			viewHolder.constraintLayout = convertView
					.findViewById(R.id.listitem_constraintlayout_layout);
			viewHolder.progressBar = convertView
					.findViewById(R.id.places_listitem_progressbar_thumbnail);
			viewHolder.imageView = convertView
					.findViewById(R.id.places_listitem_imageview_thumbnail);
			viewHolder.textViewTitle = convertView
					.findViewById(R.id.places_listitem_textview_title);
			viewHolder.textViewSubTitle = convertView
					.findViewById(R.id.places_listitem_textview_subtitle);
			viewHolder.textViewLocation = convertView
					.findViewById(R.id.places_listitem_textview_location);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (PlaceViewHolder) convertView.getTag();
		}

		final Place currentPlace = getItem(position);

		viewHolder.textViewTitle.setText(currentPlace.getTitle());
		viewHolder.textViewSubTitle.setText(currentPlace.getSubtitle());
		viewHolder.textViewLocation.setText(currentPlace.getLocation());

		Glide.with(mContext)
		     .load(currentPlace.getThumbnailResourceId())
		     .listener(new RequestListener<Drawable>() {
			     @Override
			     public boolean onLoadFailed(@Nullable GlideException e, Object model,
			                                 Target<Drawable> target, boolean isFirstResource) {
				     return false;
			     }

			     @Override
			     public boolean onResourceReady(Drawable resource, Object model, Target<Drawable>
					     target, DataSource dataSource, boolean isFirstResource) {
				     viewHolder.progressBar.setVisibility(View.GONE);
				     viewHolder.imageView.setVisibility(View.VISIBLE);
				     return false;
			     }
		     })
		     .into(viewHolder.imageView);


		viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri mapsUri = Uri.parse("geo:0,0?q="
						                        + currentPlace.getTitle()
						                        + ", " + currentPlace.getLocation()
						                        + ", Teresópolis"
				                       );
				Intent intent = new Intent(Intent.ACTION_VIEW, mapsUri);
				intent.setPackage("com.google.android.apps.maps");
				if (intent.resolveActivity(getContext().getPackageManager()) != null) {
					getContext().startActivity(intent);

				}
			}
		});

		return convertView;
	}

}
