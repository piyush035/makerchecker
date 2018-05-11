package com.hotel.base.common.persistance.dao.impl;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * The Class AbstractDefaultDao.
 * 
 * @author pkGupta global methods to save, update, delete, ...
 */
public abstract class AbstractDefaultDao extends HibernateDaoSupport implements
		DefaultDao {

	/** For only merge the bean in the database. */
	protected boolean merge;

	/**
	 * {@inheritDoc}
	 */
	public void create(DefaultBean defaultBean) {
		setClientName();
		getHibernateTemplate().save(defaultBean);
	}

	/**
	 * {@inheritDoc}
	 */
	public void modify(DefaultBean defaultBean)// throws
												// ConcurrentAccessException
	{
		setClientName();
		if (isMerge()) {
			Object beanMerged = getHibernateTemplate().merge(defaultBean);
			getHibernateTemplate().saveOrUpdate(beanMerged);

		} else {
			getHibernateTemplate().update(defaultBean);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(DefaultBean defaultBean) {
		setClientName();
		if (isMerge()) {
			getHibernateTemplate().delete(
					getHibernateTemplate().merge(defaultBean));
		} else {
			getHibernateTemplate().delete(defaultBean);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public DefaultBean load(Serializable key) {
		setClientName();
		return (DefaultBean) getHibernateTemplate().get(getDefaultClassBean(),
				key);

	}

	/**
	 */
	protected void setClientName() {
		/* If ClientName is active we add a SET CLIENTNAME request */

		// User user = UserConnectedUtils.getUserConnected();
		/*
		 * if ((user != null) && IS_CLIENTNAME_ACTIVE.booleanValue()) {
		 * 
		 * final Query query =
		 * getSession().createSQLQuery("SET CLIENTNAME :clientUsername");
		 * query.setString("clientUsername", user.getName());
		 * 
		 * query.executeUpdate(); SetClientNameUtils.setClientName();
		 * 
		 * }
		 */

	}

	/**
	 * Checks if is merge.
	 * 
	 * @return the merge
	 */
	public boolean isMerge() {
		return merge;
	}

	/**
	 * Sets the merge.
	 * 
	 * @param merge
	 *            the merge to set
	 */
	public void setMerge(boolean merge) {
		this.merge = merge;
	}
}