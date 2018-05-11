/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.rennover.hotel.common.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.rennover.hotel.common.valueobject.PropertyHelper;


public abstract class DateHelper
{
	public static final Date INVALID_DATE = new Date(Long.MAX_VALUE);

	/**
	 * Utilisée pour que le processus puisse indiquer la date du jour à utiliser
	 */
	private static Date s_dateDuJour = null;

	public static void initDateDuJour(Date dateDuJour)
	{
		s_dateDuJour = dateDuJour;
	}

    /**
	 * @return un entier négatif, zero, ou un entier positif si date1 est plus
	 *         ancien, égal, ou plus récent que date2. Cas de la nullité : zero,
	 *         un entier négatif, ou un entier positif si date1 and date2 sont
	 *         nuls, date1 est nul mais pas date2, date2 est nul mais pas date1
	 */
    public static int compareAnnee(Date date1, Date date2)
	{
		int res = compareNullDate(date1, date2);

		if (res == Integer.MAX_VALUE)
		{
			res = compareNotNullDate(date1, date2, Calendar.YEAR);
		}

		return res;
	}

    /**
	 * @return un entier négatif, zero, ou un entier positif si date1 est plus
	 *         ancien, égal, ou plus récent que date2. Cas de la nullité : zero,
	 *         un entier négatif, ou un entier positif si date1 and date2 sont
	 *         nuls, date1 est nul mais pas date2, date2 est nul mais pas date1
	 */
    public static int compareMois(Date date1, Date date2)
	{
		int res = compareNullDate(date1, date2);

		if (res == Integer.MAX_VALUE)
		{
			res = compareNotNullDate(date1, date2, Calendar.YEAR);

			if (res == 0)
			{
				res = compareNotNullDate(date1, date2, Calendar.MONTH);
			}
		}

		return res;
	}

    /**
	 * @return un entier négatif, zero, ou un entier positif si date1 est plus
	 *         ancien, égal, ou plus récent que date2. Cas de la nullité : zero,
	 *         un entier négatif, ou un entier positif si date1 and date2 sont
	 *         nuls, date1 est nul mais pas date2, date2 est nul mais pas date1
	 */
    public static int compareJour(Date date1, Date date2)
	{
		int res = compareNullDate(date1, date2);

		if (res == Integer.MAX_VALUE)
		{
			res = compareNotNullDate(date1, date2, Calendar.YEAR);

			if (res == 0)
			{
				res = compareNotNullDate(date1, date2, Calendar.MONTH);

				if (res == 0)
				{
					res = compareNotNullDate(date1, date2, Calendar.DAY_OF_YEAR);
				}
			}
		}

		return res;
	}

    /**
	 * Teste les valeurs nulles et renvoie Integer.MAX_VALUE si les tests
	 * doivent être affinés
	 * 
	 * @param date1
	 *            une date non nulle
	 * @param date2
	 *            une date non nulle
	 * @param une
	 *            des constantes définies dans Calendar
	 * @return a negative integer, zero, or a positive integer as date1 is less
	 *         than, equal to, or greater than date2.
	 */
    private static int compareNotNullDate(Date date1, Date date2, int typeComparaison)
	{
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);

		int value1 = cal1.get(typeComparaison);

		GregorianCalendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);

		int value2 = cal2.get(typeComparaison);

		if (value1 == value2)
		{
			return 0;
		} else if (value1 > value2)
		{
			return 1;
		} else
		{
			return -1;
		}
	}

    /**
	 * Teste les valeurs nulles et renvoie Integer.MAX_VALUE si les tests
	 * doivent être affinés
	 * 
	 * @return zero, a negative integer, or a positive integer as date1 and
	 *         date2 are null, date1 is null but date2 isn't, date2 is null but
	 *         date1 isn't
	 */
    private static int compareNullDate(Date date1, Date date2)
	{
		if (PropertyHelper.isNull(date1))
		{
			if (PropertyHelper.isNull(date2))
			{
				return 0;
			}

			return -1;
		} else if (PropertyHelper.isNull(date2))
		{
			return 1;
		} else
		{
			return Integer.MAX_VALUE;
		}
	}

    /**
	 * @return un entier négatif, zero, ou un entier positif si date est plus
	 *         récent, égal, ou plus ancien que dateDuJour. Cas de la nullité :
	 *         -1 si date est null
	 */
    public static int compareAvecDateCourante(Date date)
	{
		if (date == null)
		{
			return -1;
		}

		long base = getTodayDateNoTime().getTime();
		long value = trimTime(date).getTime();

		if (base == value)
		{
			return 0;
		}

		if (base < value)
		{
			return 1;
		}

		return -1;
	}

    /**
	 * Sets the time of the calandar object to 00h 00m 00s.
	 */
    public static GregorianCalendar trimTime(GregorianCalendar calandar)
	{
		calandar.set(GregorianCalendar.HOUR_OF_DAY, 0);
		calandar.set(GregorianCalendar.MINUTE, 0);
		calandar.set(GregorianCalendar.SECOND, 0);
		calandar.set(GregorianCalendar.MILLISECOND, 0);

		return calandar;
	}

    /**
	 * Sets the time of the date object to 00h 00m 00s.
	 */
    public static Date trimTime(Date date)
	{
		if (date == null)
		{
			return null;
		}

		GregorianCalendar calandar = new GregorianCalendar();
		calandar.setTime(date);

		return trimTime(calandar).getTime();
	}

    /**
	 * Returns the date of today with a time set to 00h 00m 00s.
	 */
    public static Date getTodayDateNoTime()
	{
        //		------------>>>>>>> The above if condition has been blocked for the following reason, <<<<<<<------------
        //		------------>>>>>>> when  the  connected user  was  logged on  for  more than a day , <<<<<<<------------
        //		------------>>>>>>> the  getTodayDateNoTime() would erronously  return the wrong date <<<<<<<------------  
        //		------------>>>>>>> eventhough the system was expecting Today's date. 				  <<<<<<<------------
        //		------------>>>>>>> Issue realized while working on defect #7584 and #7586 -ashisht   <<<<<<<------------ 
        GregorianCalendar calandar = new GregorianCalendar();

		return trimTime(calandar).getTime();
	}

    /**
	 * Returns the date of tomorrow with a time set to 00h 00m 00s.
	 */
    public static Date getTomorrowDateNoTime()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getTodayDateNoTime());
		calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);

		return trimTime(calendar).getTime();
	}

    /**
	 * @return <code>date</code> plus one day
	 */
    public static Date getDatePlusOneDay(Date date)
	{
		return getDatePlusDays(date, 1);
	}

	public static Date getDatePlusDays(Date date, int nbDays)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DAY_OF_YEAR, nbDays);

		return calendar.getTime();
	}

    /**
	 * Returns a specific date with a time set to 00h 00m 00s.
	 */
    public static Date getSpecificDate(int annee, int mois, int jour)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(GregorianCalendar.DAY_OF_MONTH, jour);
		calendar.set(GregorianCalendar.MONTH, mois);
		calendar.set(GregorianCalendar.YEAR, annee);

		return trimTime(calendar).getTime();
	}

    /**
	 * expl : 27 juin 1973
	 */
    public static String getFormatedCourrierDate(Date date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy");

		return formatter.format(date);
	}

    /**
	 * expl : 27/06/1973
	 */
    public static String getFormatedDate(Date date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		return formatter.format(date);
	}

    /**
	 * UC-R-26 expl: 1973-06-27
	 */
    public static String getFormatedDateForFileName(Date date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		return formatter.format(date);
	}

	public static String getFormatedDateTime(Date date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - hh:mm");

		return formatter.format(date);
	}

	public static String getFormatedDate(Date date, String format)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);

		return formatter.format(date);
	}

    /*
	 * This will print the date in "20 août 2004 à 02h30" format if passed date
	 * is '20/08/2004 14:30' for default locale = "FRENCH"
	 * 
	 * This will print the date in "20 Aug 2004 à 02h30" format if passed date
	 * is '20/08/2004 14:30' for default locale = "ENGLISH"
	 * 
	 */
    public static String getFormatedDateTimeForDefaultLocale(Date date)
	{
		Locale locale = Locale.getDefault();

		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy 'à' HH'h'mm", locale);

		return formatter.format(date);
	}

    // Fix for 3372

    /*
	 * This will print the date in "20 août 2004 à 02h30" format if passed date
	 * is '20/08/2004 14:30' for locale = "FRENCH"
	 */
    public static String getFormatedDateTimeForFrenchLocale(Date date, String format)
	{
		Locale french = new Locale("fr", "FR");
		SimpleDateFormat formatter = new SimpleDateFormat(format, french);

		return formatter.format(date);
	}

    // End of fix for 3372
    
    
    /*
     * ME 1013
     * This will print the date in "20 septembre 2004 " format
     * if passed date is  '20/09/2004 ' for locale = "FRENCH"
    */
    public static String getFormatedDateTimeFullForFrenchLocale(Date date)
	{

		DateFormat f = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);
		return f.format(date);
	}
    
    /**
	 * To get the current year only. This method is added for ME-1431 under UC-R-60.
	 * like 2009.
	 * @return int
	 * @author snrChimpiri
	 */
	public static int getCurrentYear()
	{
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
}
