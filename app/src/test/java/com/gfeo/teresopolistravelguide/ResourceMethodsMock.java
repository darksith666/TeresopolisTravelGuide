package com.gfeo.teresopolistravelguide;

import org.powermock.api.mockito.PowerMockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

public class ResourceMethodsMock {

	public ResourceMethodsMock(String expectedString,
	                           int expectedResourceId,
	                           String... expectedStringArray) {
		mockStatic(ResourceMethods.class);
		when(ResourceMethods.getStringFromResourceName(any(), any()))
				.thenReturn(expectedString);
		when(ResourceMethods.getResourceIdFromName(any(), any(), any()))
				.thenReturn(expectedResourceId);
		when(ResourceMethods.getStringArrayFromResourceName(any(), any()))
				.thenReturn(expectedStringArray);
	}

	public void verify(boolean getStringMethod,
	                   boolean getResourceIdMethod,
	                   boolean getStringArrayMethod) {
		if (getStringMethod) {
			PowerMockito.verifyStatic(ResourceMethods.class);
			ResourceMethods.getStringFromResourceName(any(), any());
		}
		if (getResourceIdMethod) {
			PowerMockito.verifyStatic(ResourceMethods.class);
			ResourceMethods.getResourceIdFromName(any(), any(), any());
		}
		if (getStringArrayMethod) {
			PowerMockito.verifyStatic(ResourceMethods.class);
			ResourceMethods.getStringArrayFromResourceName(any(), any());
		}
	}
}