package com.hotel.base.common.helper;

import java.util.Collection;

public class CollectionHelper {
	/**
	 * 
	 * 
	 * @param c
	 */
	public static boolean isNullOrEmpty(Collection collection) {
		if (collection == null) {
			return true;
		}

		return collection.isEmpty();
	}

	/**
	 * 
	 * 
	 * @param c
	 * @param element
	 */
	public static void addIfNotNull(Collection collection, Object element) {
		if (element != null) {
			collection.add(element);
		}
	}

	
	public static void addAllIfNotNull(Collection dest, Collection src) {
		if (src != null) {
			dest.addAll(src);
		}
	}

	public static Collection merge(Collection dest, Collection src) {
		if (src == null) {
			return dest;
		}

		if (dest == null) {
			return src;
		}

		dest.addAll(src);

		return dest;
	}

}
