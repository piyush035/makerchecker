package com.rennover.client.framework.widget.base;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class ComparatorHelper
{
	public static Comparator createComparator()
	{
		return createComparator(true);
	}

	public static void sort(List list)
	{
		Collections.sort(list, ComparatorHelper.createComparator());
	}

	public static Comparator createComparator(boolean ascendingSort)
	{
		// Tri selon le sens souhaité
		if (ascendingSort)
		{
			return new Comparator()
			{
				public int compare(Object o1, Object o2)
				{
					return compareObject(o1, o2);
				}
			};
		} else
		{
			return new Comparator()
			{
				public int compare(Object o1, Object o2)
				{
					return -compareObject(o1, o2);
				}
			};
		}
	}

	/**
	 * Comparateur d'objets quelconque
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static int compareObject(Object o1, Object o2)
	{
		Class objectClass = null;
		if (o1 != null)
		{
			objectClass = o1.getClass();
		} else if (o2 != null)
		{
			objectClass = o2.getClass();
		}

		if (objectClass == null)
		{
			return compareToString(o1, o2);
		} else if (Date.class.isAssignableFrom(objectClass))
		{
			return compareDate((Date) o1, (Date) o2);
		} else if (Number.class.isAssignableFrom(objectClass))
		{
			return compareNumber((Number) o1, (Number) o2);
		} else
		{
			return compareToString(o1, o2);
		}
	}

	/**
	 * Comparateur de Dates
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	private static int compareDate(Date v1, Date v2)
	{
		if (v1 != null && v2 != null)
		{
			return v1.compareTo(v2);
		} else if (v1 == null && v2 == null)
		{
			return 0;
		} else if (v1 == null)
		{
			return 1;
		} else
		{
			return -1;
		}
	}

	/**
	 * Comparateur de Nombres
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	private static int compareNumber(Number v1, Number v2)
	{
		if (v1 != null && v2 != null)
		{
			return Double.compare(v1.doubleValue(), v2.doubleValue());
		} else if (v1 == null && v2 == null)
		{
			return 0;
		} else if (v1 == null)
		{
			return 1;
		} else
		{
			return -1;
		}
	}

	/**
	 * Comparateur de Chaînes
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	private static int compareToString(Object v1, Object v2)
	{
		if (v1 != null && v2 != null)
		{
			return v1.toString().compareTo(v2.toString());
		} else if (v1 == null && v2 == null)
		{
			return 0;
		} else if (v1 == null)
		{
			return 1;
		} else
		{
			return -1;
		}
	}
}