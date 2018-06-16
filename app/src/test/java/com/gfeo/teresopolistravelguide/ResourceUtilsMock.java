package com.gfeo.teresopolistravelguide;

import org.powermock.api.mockito.PowerMockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

public class ResourceUtilsMock {

	public ResourceUtilsMock(String expectedString,
	                           int expectedResourceId,
	                           String... expectedStringArray) {
		mockStatic(ResourceUtils.class);
		when(ResourceUtils.getStringFromResourceName(any(), any()))
				.thenReturn(expectedString);
		when(ResourceUtils.getResourceIdFromName(any(), any(), any()))
				.thenReturn(expectedResourceId);
		when(ResourceUtils.getStringArrayFromResourceName(any(), any()))
				.thenReturn(expectedStringArray);
	}

	public void verify(boolean getStringMethod,
	                   boolean getResourceIdMethod,
	                   boolean getStringArrayMethod) {
		if (getStringMethod) {
			PowerMockito.verifyStatic(ResourceUtils.class);
			ResourceUtils.getStringFromResourceName(any(), any());
		}
		if (getResourceIdMethod) {
			PowerMockito.verifyStatic(ResourceUtils.class);
			ResourceUtils.getResourceIdFromName(any(), any(), any());
		}
		if (getStringArrayMethod) {
			PowerMockito.verifyStatic(ResourceUtils.class);
			ResourceUtils.getStringArrayFromResourceName(any(), any());
		}
	}
}