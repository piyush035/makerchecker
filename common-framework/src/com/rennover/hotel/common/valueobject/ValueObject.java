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
 * Cette classe est la super-classe de tous les objet métier de l'application.
 * 
 * <p>
 * Les sous-classes de ValueObject propose des constantes représentant les attributs du
 * ValueObject. Ces constantes sont utilisées par le framework de validation (pour
 * indiquer les règles de validation sur un attribut),  le front end (pour lier un
 * élément graphique à un attribut du ValueObject) et dans les fichiers de propriétés
 * contenant les messages d'aide de saisie du ValueObject.
 * </p>
 * 
 * <p>
 * Le seul constructeur proposé est le constructeur par défaut. Dans certains cas, on
 * pourra toutefois ajouter  un constructeur prenant en paramètres les différents
 * attributs du ValueObject. par contre, il ne faut pas  proposer de constructeur par
 * copie.
 * </p>
 * 
 * <p>
 * Les attributs de type chaîne ne doivent jamais contenir NULL. Ils doivent être
 * initialisés par {@link
 * com.rennover.hotel.common.valueobject.PropertyHelper#EMPTY_STRING}. De plus, les
 * setters de ces attributs doivent utiliser la méthode {@link
 * #nullToEmptyString(String)}.
 * </p>
 * 
 * <p>
 * Les objets référencés sont identifiés par 2 attributs : leur {@link
 * com.rennover.hotel.common.valueobject.ObjectId} et le ValueObject lui-même. <br>
 * Cependant, il faut respecter certaines règles :
 * 
 * <ol>
 * <li>
 * L'ObjectId est toujours renseigné si le lien existe
 * </li>
 * <li>
 * Dans certains cas, l'objet lié est présent. Ce choix est fonction du besoin côté
 * client et est implémenté par le back.
 * </li>
 * <li>
 * Si l'objet lié est présent, il faut assurer manuellement la cohérence entre l'ObjectId
 * et l'objet lui-même.  Pour cela, il faut que le setter de l'ObjectId mette à NULL
 * l'objet lié ET que le setter de l'objet utilise la méthode {@link
 * com.rennover.hotel.common.valueobject.DomainObject#getObjectId(Persistent)} pour
 * renseigner l'ObjectId.
 * </li>
 * </ol>
 * </p>
 * 
 * TODO (dattias, le 23 oct. 2003) : Revoir la gestion et la génération du toString
 * TODO (dattias, le 23 oct. 2003) : Revoir le clone et le copyFrom pour éviter la double
 * copie. 
 */
public abstract class ValueObject extends AbstractBean
	implements Cloneable
{
	// TODO supprimer ce message et refactorer la gestion
	// du toString sur tous les VO
	public static final String MSG_OBJET_NON_AFFICHABLE = "Cet objet ne doit pas être affiché";

	public ValueObject()
	{
	}

	////////////////////////////////////////
	///// clone and CopyFrom methods ///////
	////////////////////////////////////////

	/**
	 * Crée une copie parfaite de l'objet.
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
	 * Cette méthode est utilisée par les value objects générés
	 * pour cloner leurs attributs sans se soucier si c'est possible
	 * ou non.
	 *
	 * Renvoie null si le paramètre est null.
	 * Renvoie le paramètre si ce n'est pas un value object.
	 * Renvoie le clone du paramètre si c'est un value object.
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
	* surchargé par les sous classes pour faire une copie d'objet attribut par attribut
	*/
	public void copyFrom(ValueObject valueObject)
	{
	}

	/**
	 * Cette méthode est utilisée par les value objects générés
	 * pour coder le copyFrom sans se soucier des null.
	 *
	 * Renvoie null si le paramètre from est null.
	 * Renvoie from.clone si le paramètre to est null.
	 * Renvoie to après un to.copyFrom(from) dans le cas contraire.
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
	* @param obj  precondition Les objets sont de même type precondition Les objets sont
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