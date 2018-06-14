package com.gfeo.teresopolistravelguide;

import android.content.Context;
import android.net.Uri;

/**
 * Holds basic information about an Attribution of an Image used in the app:
 * {@linkplain Attribution#mAuthorName author name},
 * {@linkplain Attribution#mLicenseUri a link to the license} and a
 * {@linkplain Attribution#mImageResourceId the resource ID of the work itself}.
 *
 * @author gabrielfeo
 */

class Attribution {

	/** A String containing the author name, as well as the license specification. */
	private final String mAuthorName;
	/** A {@link Uri} to the license. */
	private final Uri mLicenseUri;
	/** An int resource ID of the work being attributed to the author. */
	private final int mImageResourceId;

	/**
	 * Assigns the {@link Attribution#mAuthorName} and {@link Attribution#mImageResourceId}
	 * according to the arguments provided, using the {@link ResourceMethods}. Then, checks the
	 * {@code mAuthorName} String for a possible license name and parses its respective URL to a
	 * Uri, assigned to {@link Attribution#mLicenseUri}.
	 *
	 * @param context      Provides a {@link Context} for the object, used as an argument for
	 *                     {@link ResourceMethods}
	 * @param resourceName The name of the resource, for fetching the actual resource with {@code
	 *                     ResourceMethods}
	 */
	Attribution(Context context, String resourceName) {
		mImageResourceId = ResourceMethods.getResourceIdFromName(context,
		                                                         resourceName,
		                                                         "drawable"
		                                                        );
		String attributionResourceName = "attribution_" + resourceName;
		mAuthorName = ResourceMethods.getStringFromResourceName(context,
		                                                        attributionResourceName
		                                                       );
		mLicenseUri = checkForLicenseUri(context);
	}

	private Uri checkForLicenseUri(Context context) {
		for (int i = 0; true; i++) {
			String licenseResourceName = "license_" + i;
			String[] licenseStringArray = ResourceMethods
					.getStringArrayFromResourceName(context,
					                                licenseResourceName
					                               );
			if (licenseStringArray != null && mAuthorName.contains(licenseStringArray[0])) {
				return Uri.parse(licenseStringArray[1]);
			}
		}
	}

	int getImageResourceId() {
		return mImageResourceId;
	}

	String getAuthorName() {
		return mAuthorName;
	}

	Uri getLicenseUri() {
		return mLicenseUri;
	}

}
