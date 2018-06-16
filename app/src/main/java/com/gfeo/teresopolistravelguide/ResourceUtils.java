package com.gfeo.teresopolistravelguide;

import android.content.Context;

/**
 * Provides static methods for getting resources by their resource names instead of the IDs,
 * through {@link Context} methods. This is a useful ability for making the general
 * resource-fetching process simpler and more flexible.
 *
 * @author gabrielfeo
 */

class ResourceUtils {

	/**
	 * Gets the desired String according to the given name, using the
	 * {@link ResourceUtils#getResourceIdFromName(Context, String, String)} method.
	 *
	 * @param context      A {@code Context} to provide the
	 *                     {@link ResourceUtils#getResourceIdFromName(Context, String, String)}
	 *                     method's basic functionality
	 * @param resourceName The name of the String resource whose content will be returned
	 * @return the String contained in the resource of the given name
	 */
	static String getStringFromResourceName(Context context, String resourceName) {
		int stringResourceId = getResourceIdFromName(context, resourceName, "string");
		return context.getString(stringResourceId);
	}

	/**
	 * Gets the desired resource according to the given name and type, using similar methods from
	 * {@link Context}, though simplifying their usage.
	 *
	 * @param context      A {@code Context} to provide the method's basic functionality
	 * @param resourceName The name of the resource whose ID will be returned
	 * @param resourceType The type of the resource whose ID will be returned. Multiple different
	 *                     resources, a drawable, a String and a String array, for instance, can
	 *                     have the same name, but can't also have the same type; thus, they will
	 *                     be differentiated by their resource type
	 * @return The resource ID corresponding to the given name and type
	 */
	static int getResourceIdFromName(Context context, String resourceName, String
			resourceType) {
		return context.getResources().getIdentifier(resourceName,
		                                            resourceType,
		                                            context.getPackageName()
		                                           );
	}

	/**
	 * Gets the desired String array according to the given name, using the
	 * {@link ResourceUtils#getResourceIdFromName(Context, String, String)} method.
	 *
	 * @param context      A {@code Context} to provide the
	 *                     {@link ResourceUtils#getResourceIdFromName(Context, String, String)}
	 *                     method's basic functionality
	 * @param resourceName The name of the String array resource whose content will be returned
	 * @return the String array contained in the resource of the given name
	 */
	static String[] getStringArrayFromResourceName(Context context, String resourceName) {
		int stringArrayResourceId = getResourceIdFromName(context, resourceName, "array");
		if (stringArrayResourceId != 0) {
			return context.getResources().getStringArray(stringArrayResourceId);
		} else {
			return null;
		}
	}

}
