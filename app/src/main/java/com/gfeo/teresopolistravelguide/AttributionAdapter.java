package com.gfeo.teresopolistravelguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
 * An Adapter of {@link Attribution} objects. Provides ready Views for ListViews. Uses the
 * <a href="https://bumptech.github.io/glide/">Glide</a> library to load images.
 *
 * @author gabrielfeo
 */

class AttributionAdapter extends ArrayAdapter<Attribution> {

	AttributionAdapter(Context context, ArrayList<Attribution> attributionArrayList) {
		super(context, 0, attributionArrayList);
	}

	/**
	 * Gets the {@link android.widget.ListView} item at the specified position and inflates it
	 * if it doesn't exist. Then, sets the text of the TextViews to the String provided by the
	 * {@link Attribution#getAuthorName()} method from the {@link Attribution} item at the
	 * current position; loads the drawable from the resource ID returned by the related
	 * {@link Attribution#getImageResourceId()} method into the layout's {@link ImageView}; sets
	 * an OnClickListener to the layout's root ViewGroup for opening the License document,
	 * provided by the {@link Attribution#getLicenseUri()} method of the current {@code
	 * Attribution} item.
	 */
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		final Context mContext = getContext();
		final AttributionViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.listitem_about, parent, false);
			viewHolder = new AttributionViewHolder();
			viewHolder.constraintLayout = convertView
					.findViewById(R.id.about_listitem_constraintlayout_layout);
			viewHolder.progressBar = convertView
					.findViewById(R.id.about_listitem_progressbar_thumbnail);
			viewHolder.imageView = convertView
					.findViewById(R.id.about_listitem_imageview_thumbnail);
			viewHolder.textView = convertView
					.findViewById(R.id.about_listitem_textview);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (AttributionViewHolder) convertView.getTag();
		}
		final Attribution currentAttribution = getItem(position);

		viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (currentAttribution.getLicenseUri() != null) {
					Intent intent = new Intent(Intent.ACTION_VIEW, currentAttribution
							.getLicenseUri());
					if (intent.resolveActivity(getContext().getPackageManager()) != null) {
						getContext().startActivity(intent);
					}
				}
			}
		});

		Glide.with(mContext)
		     .load(currentAttribution.getImageResourceId())
		     .listener(new RequestListener<Drawable>() {
			     @Override
			     public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
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

		viewHolder.textView.setText(currentAttribution.getAuthorName());

		return convertView;
	}
}
