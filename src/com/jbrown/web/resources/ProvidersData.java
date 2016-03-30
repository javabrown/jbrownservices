package com.jbrown.web.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProvidersData {
	private Provider[] providers;

	public void setProviders(Provider[] providers) {
		this.providers = providers;
	}

	public String[] getProvidersName() {
		List<String> list = new ArrayList<String>();
		for (Provider provider : providers) {
			list.add(provider.getName());
		}

		return list.toArray(new String[0]);
	}

	public Map<String, String> getProviderData(String providerName) {
		for (Provider provider : providers) {
			if (providerName.equalsIgnoreCase(provider.getName())) {
				return provider.getData();
			}
		}

		System.out.println("No provider found" + providerName);
		return null;
	}

	
	static class Provider {
		private String name;
		private Map<String, String> data;

		public void setName(String name) {
			this.name = name;
		}

		public void setData(Map<String, String> data) {
			this.data = data;
		}

		public String getName() {
			return name;
		}

		public Map<String, String> getData() {
			return data;
		}
	}
}
