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

import com.rennover.hotel.common.exception.Assert;
import com.rennover.hotel.common.idgen.IdGenerator;

/*
 * Super-classe de tous les objets persistents du système.
 * <p>
 * Les objets java sont stockés dans la base avec les correspondances de types suivantes:
 *
 * Type Java                 Type SQL                Valeur limite
 * --------------------------------------------------------------------------
 * char                 CHAR(1)
 * String                 CHAR ou VARCHAR                taille maximale autorisée est 32 767.
 * java.math.BigDecimal     NUMERIC                numeric(38,38) (38 d'échelle, 38 de précision)
 * java.math.BigInteger     NUMERIC                numeric(38)
 * boolean                 BIT
 * byte                     TINYINT                 -128 to 127
 * Short                     SMALLINT            -32768 to 32767
 * Int                     INTEGER                -2147483648 to 2147483647
 * long                     numeric(19)            -9223372036854775808 to 9223372036854775807
 * float                     REAL                3.402823e38 to 1.175495e-38
 * double                 DOUBLE                1.7e308 to 2.22507385850721e-308
 * java.SQL.Date             DATE
 * java.util.Date         DATE
 * <p>
 */
public abstract class PersistentDomainObject extends DomainObject
	implements Cloneable, Lockable, Inactivable, Insertable, Updatable, Deletable, HasObjectInfo
{
	public static final String ACTIF = "actif";
	/**
	 * @deprecated replaced by the actif property
	 */
	public static final String ACTIVE = "active";
	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation. The deactivation reason should be
	 * replaced by specific domain object attributs.
	 */
	public static final String DEACTIVATION_REASON = "deactivationReason";
	public static final String VERSION = "version";
	public static final String LOCK_USER_LOGIN = "lockUserLogin";
	public static final String OBJECT_ID = "objectId";
	private static ThreadLocal calledByPersistence = new ThreadLocal();
	private int version = 1;  // version initiale : 1
	private String lockUserLogin;  // verrouillage pessimiste
	private String deactivationReason;
	private boolean isReactivated = false;

	public PersistentDomainObject()
	{
	}

	/**
	* TODO (dattias, le 12 sept. 2003) : renommer les deux méthodes suivantes
	* isNoIdGenerationMode ?
	* isIdAssociatedAtInit ?
	* isGenerateIdInConstructorMode ?
	*/
	public static boolean isCalledByPersistence()
	{
		return calledByPersistence.get() != null;
	}

	public static void setCalledByPersistence(boolean persistence)
	{
		if (persistence)
		{
			calledByPersistence.set(Boolean.TRUE);
		}
		else
		{
			calledByPersistence.set(null);
		}
	}

	protected static long generateLong()
	{
		if (!isCalledByPersistence())
		{
			return IdGenerator.getNextId();
		}

		return 0;
	}

	/**
	* Renvoie un numero de version de l'objet, utilisé pour gérer le lock optimiste.
	*/
	public int getVersion()
	{
		return version;
	}

	/**
	* Renvoie vrai si l'objet est verrouillé de facon pessimiste, faux sinon
	*/
	public boolean isLocked()
	{
		return (lockUserLogin != null);
	}

	public String getLockUserLogin()
	{
		return lockUserLogin;
	}

	public void setLockUserLogin(String userlogin)
	{
		lockUserLogin = userlogin;
	}

	/**
	 * Returns the active status of the object.
	 * 
	 * @deprecated replaced by isActif()
	 */
	public boolean isActive()
	{
		return isActif();
	}

	/**
	 * Returns the active status of the object.
	 */
	public boolean isActif()
	{
		return (deactivationReason == null);
	}

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public String getDeactivationReason()
	{
		return deactivationReason;
	}

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public void deactivate()
	{
		deactivate(DeactivationReason.CODE_PAS_DE_MOTIF);
	}

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public void deactivateForever()
	{
		deactivate(DeactivationReason.CODE_SUPPRESSION_LOGIQUE);
	}

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public void deactivate(DeactivationReason reason)
	{
		Assert.checkNotNull(reason);
		deactivate(reason.getCode());
	}

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	private void deactivate(String reasonCode)
	{
		Assert.check(isActif(),
			"Inactivation impossible, l'objet est déjà inactif");
		deactivationReason = reasonCode;
		isReactivated = false;
		setDirty(true);
		firePropertyChange(DEACTIVATION_REASON, null, reasonCode);
		firePropertyChange(ACTIVE, Boolean.TRUE, Boolean.FALSE);
		firePropertyChange(ACTIF, Boolean.TRUE, Boolean.FALSE);
	}

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public void reactivate()
	{
		Assert.check(!isActif(),
			"Reactivation impossible, l'objet est déjà actif");
		Assert.check(!deactivationReason.equals(DeactivationReason.CODE_SUPPRESSION_LOGIQUE),
			"Reactivation impossible, l'objet est en état de suppression logique");
		isReactivated = true;
		String oldValue = deactivationReason;
		deactivationReason = null;
		setDirty(true);
		firePropertyChange(DEACTIVATION_REASON, oldValue, deactivationReason);
		firePropertyChange(ACTIVE, Boolean.FALSE, Boolean.TRUE);
		firePropertyChange(ACTIF, Boolean.FALSE, Boolean.TRUE);
	}

	/**
	 * @deprecated every domain object should use its own way to handle
	 * deactivation and reactivation.
	 */
	public boolean isReactivated()
	{
		return isReactivated;
	}

	public ObjectInfo getObjectInfo()
	{
		ObjectInfo info = new ObjectInfo();
		info.setObjectId(this.getObjectId());
		info.setVersion(this.getVersion());
		info.setLock(this.getLockUserLogin());
		return info;
	}

	/**
	* Renvoie un objet représentant l'identité de cet objet persistent.
	*/
	public ObjectId getObjectId()
	{
		ObjectId oid = new ObjectId(this.getClass());
		populateObjectId(oid);
		return oid;
	}

	protected void populateObjectId(ObjectId oid)
	{
	}

	protected boolean equals2(ValueObject obj)
	{
		return getObjectId().equals(((PersistentDomainObject) obj).getObjectId());
	}

	public int hashCode()
	{
		try
		{
			return getObjectId().hashCode();
		}
		catch (RuntimeException exc)
		{
			return super.hashCode();
		}
	}
	
	public Object clone()
	{
		PersistentDomainObject clone = (PersistentDomainObject) super.clone();

		clone.deactivationReason = this.deactivationReason;
		clone.isReactivated = this.isReactivated;
		clone.lockUserLogin = this.lockUserLogin;
		clone.version = this.version;

		return clone;
	}

	public void copyFrom(ValueObject valueObject)
	{
		if (valueObject == null)
		{
			throw new IllegalArgumentException("Parameter valueObject null");
		}

		super.copyFrom(valueObject);

		PersistentDomainObject clone = (PersistentDomainObject) valueObject;
		this.deactivationReason = clone.deactivationReason;
		this.isReactivated = clone.isReactivated;
		this.lockUserLogin = clone.lockUserLogin;
		this.version = clone.version;
	}

	public void setSaved()
	{
		if (isDirty())
		{
			version++;  // la version n'est incrémentée que si l'objet a été modifié.
		}
		super.setSaved();
	}
}
