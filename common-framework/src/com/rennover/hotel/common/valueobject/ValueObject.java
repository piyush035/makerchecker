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
package com.rennover.hotel.common.valueobject;

import com.rennover.hotel.common.bean.AbstractBean;
import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.helper.StringHelper;

/**
 * Cette classe est la super-classe de tous les objet m�tier de l'application.
 * 
 * <p>
 * Les sous-classes de ValueObject propose des constantes repr�sentant les attributs du
 * ValueObject. Ces constantes sont utilis�es par le framework de validation (pour
 * indiquer les r�gles de validation sur un attribut),  le front end (pour lier un
 * �l�ment graphique � un attribut du ValueObject) et dans les fichiers de propri�t�s
 * contenant les messages d'aide de saisie du ValueObject.
 * </p>
 * 
 * <p>
 * Le seul constructeur propos� est le constructeur par d�faut. Dans certains cas, on
 * pourra toutefois ajouter  un constructeur prenant en param�tres les diff�rents
 * attributs du ValueObject. par contre, il ne faut pas  proposer de constructeur par
 * copie.
 * </p>
 * 
 * <p>
 * Les attributs de type cha�ne ne doivent jamais contenir NULL. Ils doivent �tre
 * initialis�s par {@link
 * com.rennover.hotel.common.valueobject.PropertyHelper#EMPTY_STRING}. De plus, les
 * setters de ces attributs doivent utiliser la m�thode {@link
 * #nullToEmptyString(String)}.
 * </p>
 * 
 * <p>
 * Les objets r�f�renc�s sont identifi�s par 2 attributs : leur {@link
 * com.rennover.hotel.common.valueobject.ObjectId} et le ValueObject lui-m�me. <br>
 * Cependant, il faut respecter certaines r�gles :
 * 
 * <ol>
 * <li>
 * L'ObjectId est toujours renseign� si le lien existe
 * </li>
 * <li>
 * Dans certains cas, l'objet li� est pr�sent. Ce choix est fonction du besoin c�t�
 * client et est impl�ment� par le back.
 * </li>
 * <li>
 * Si l'objet li� est pr�sent, il faut assurer manuellement la coh�rence entre l'ObjectId
 * et l'objet lui-m�me.  Pour cela, il faut que le setter de l'ObjectId mette � NULL
 * l'objet li� ET que le setter de l'objet utilise la m�thode {@link
 * com.rennover.hotel.common.valueobject.DomainObject#getObjectId(Persistent)} pour
 * renseigner l'ObjectId.
 * </li>
 * </ol>
 * </p>
 * 
 * TODO (dattias, le 23 oct. 2003) : Revoir la gestion et la g�n�ration du toString
 * TODO (dattias, le 23 oct. 2003) : Revoir le clone et le copyFrom pour �viter la double
 * copie. 
 */
public abstract class ValueObject extends AbstractBean
	implements Cloneable
{
	// TODO supprimer ce message et refactorer la gestion
	// du toString sur tous les VO
	public static final String MSG_OBJET_NON_AFFICHABLE = "Cet objet ne doit pas �tre affich�";

	public ValueObject()
	{
	}

	////////////////////////////////////////
	///// clone and CopyFrom methods ///////
	////////////////////////////////////////

	/**
	 * Cr�e une copie parfaite de l'objet.
	 */
	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException exc)
		{
			throw new TechnicalException(exc);
		}
	}

	/**
	 * Cette m�thode est utilis�e par les value objects g�n�r�s
	 * pour cloner leurs attributs sans se soucier si c'est possible
	 * ou non.
	 *
	 * Renvoie null si le param�tre est null.
	 * Renvoie le param�tre si ce n'est pas un value object.
	 * Renvoie le clone du param�tre si c'est un value object.
	 *
	 * @param object
	 * @return null, object ou object.clone()
	 */
	protected static Object clone(Object object)
	{
		if (object == null)
		{
			return null;
		}
		if (object instanceof ValueObject)
		{
			return ((ValueObject) object).clone();
		}
		return object;
	}

	/**
	* surcharg� par les sous classes pour faire une copie d'objet attribut par attribut
	*/
	public void copyFrom(ValueObject valueObject)
	{
	}

	/**
	 * Cette m�thode est utilis�e par les value objects g�n�r�s
	 * pour coder le copyFrom sans se soucier des null.
	 *
	 * Renvoie null si le param�tre from est null.
	 * Renvoie from.clone si le param�tre to est null.
	 * Renvoie to apr�s un to.copyFrom(from) dans le cas contraire.
	 */
	protected static ValueObject copyFrom(ValueObject from, ValueObject to)
	{
		if (from == null)
		{
			return null;
		}
		if (to == null)
		{
			return (ValueObject) from.clone();
		}
		to.copyFrom(from);
		return to;
	}

	////////////////////////////
	///// equals methods ///////
	////////////////////////////
	
	public final boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		else if (this == obj)
		{
			return true;
		}
		else if (this.getClass().equals(obj.getClass()))
		{
			return equals2((ValueObject) obj);
		}
		else
		{
			return false;
		}
	}

	/**
	* @param obj  precondition Les objets sont de m�me type precondition Les objets sont
	*        non nuls
	*/
	protected abstract boolean equals2(ValueObject obj);

	//////////////////////////////////////////////////
	///// String attributes management methods ///////
	//////////////////////////////////////////////////

	/**
	 * Convertit une chaine null en chaine vide.
	 */
	protected static final String nullToEmpty(String value)
	{
		return value == null ? PropertyHelper.EMPTY_STRING : value;
	}

	/**
	 * Remove unnecessary space characters. Format the string like
	 * it should be formated in value objects.
	 */
	protected static final String trimString(String value)
	{
		return StringHelper.trimSpaces(value);
	}

	/**
	 * Remove unnecessary space characters. Format the string like
	 * it should be formated in value objects. Convert it to uppercase.
	 */
	protected static final String trimStringUpperCase(String value)
	{
		return StringHelper.toUpperCaseNoAccent(trimString(value));
	}
	
	/////////////////////////////////
	///// Static equals methods /////
	/////////////////////////////////
	
	public static final boolean equals(Object value1, Object value2)
	{
		return PropertyHelper.equals(value1, value2);	
	}

	public static final boolean equals(byte value1, byte value2)
	{
		return PropertyHelper.equals(value1, value2);	
	}

	public static final boolean equals(short value1, short value2)
	{
		return PropertyHelper.equals(value1, value2);	
	}

	public static final boolean equals(int value1, int value2)
	{
		return PropertyHelper.equals(value1, value2);	
	}

	public static final boolean equals(long value1, long value2)
	{
		return PropertyHelper.equals(value1, value2);	
	}

	public static final boolean equals(double value1, double value2)
	{
		return PropertyHelper.equals(value1, value2);	
	}

	public static final boolean equals(float value1, float value2)
	{
		return PropertyHelper.equals(value1, value2);	
	}

	public static final boolean equals(char value1, char value2)
	{
		return PropertyHelper.equals(value1, value2);	
	}

	public static final boolean equals(boolean value1, boolean value2)
	{
		return PropertyHelper.equals(value1, value2);	
	}
}