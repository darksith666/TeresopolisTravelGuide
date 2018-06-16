package com.gfeo.teresopolistravelguide;

import android.content.Context;

/**
 * Holds basic information about a Place such as name and location, as well as a simple
 * description and an illustrative image resource ID.
 *
 * @author gabrielfeo
 */
class Place {

	/** A String holding the Place name. */
	private final String mTitle;
	/** A String holding a succint description of the Place. */
	private final String mSubtitle;
	/** A String holding the location of the Place */
	private final String mLocation;
	/** A String holding a resource ID for a thumbnail image to illustrate the Place. */
	private final int mThumbnailResourceId;

	/**
	 * Retrieves the resource ID of the thumbnail image ({@link Place#mThumbnailResourceId}) and
	 * the string array holding information about the Place using the {@link ResourceUtils}.
	 * Then, each item of the String array is assigned to a specific variable; namely,
	 * {@link Place#mTitle}, {@link Place#mSubtitle} and {@link Place#mLocation}.
	 *
	 * @param context      Provides a {@link Context} for the object, used as an argument for
	 *                     {@code ResourceUtils}
	 * @param resourceName The name of the resource, for fetching the actual resource with {@code
	 *                     ResourceUtils}
	 */
	Place(Context context, String resourceName) {
		mThumbnailResourceId = ResourceUtils.getResourceIdFromName(context,
		                                                           resourceName,
		                                                           "drawable"
		                                                          );
		String[] stringArray = ResourceUtils.getStringArrayFromResourceName(context,
		                                                                    resourceName
		                                                                   );
		mTitle = stringArray[0];
		mSubtitle = stringArray[1];
		mLocation = stringArray[2];
	}

	String getTitle() {
		return mTitle;
	}

	String getSubtitle() {
		return mSubtitle;
	}

	String getLocation() {
		return mLocation;
	}

	int getThumbnailResourceId() {
		return mThumbnailResourceId;
	}
}
