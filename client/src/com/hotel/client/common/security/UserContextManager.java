package com.hotel.client.common.security;

import com.hotel.base.common.domain.user.dto.UserContext;
import com.hotel.base.common.tech.exception.user.ConnectionSimultaneousException;
import com.hotel.base.common.tech.exception.user.ConnexionException;
import com.hotel.base.common.tech.exception.user.DesactivationException;
import com.hotel.base.common.tech.exception.user.LoginIncorrectException;
import com.hotel.base.common.tech.exception.user.PasswordIncorrectException;
import com.hotel.common.facade.ClientFacadeInitializer;
import com.hotel.common.facade.FacadeFactory;
import com.rennover.hotel.common.exception.IncoherenceException;
import com.rennover.hotel.common.helper.DateHelper;

/**
 * 
 * @author Piyush
 * 
 */
public abstract class UserContextManager {

	public static int NB_MAX_CONNECTIONS = 5;
	
	/** */
	private static UserContext userContext;

	/** */
	private static boolean facadeInitialized = false;

	/**
	 * 
	 * @return
	 */
	public static UserContext getUserContext() {
		// checkLogged();
		return userContext;
	}

	/**
	 * 
	 * @param login
	 * @param password
	 * @param nbConnections
	 * @throws LoginIncorrectException
	 * @throws PasswordIncorrectException
	 * @throws DesactivationException
	 * @throws ConnectionSimultaneousException
	 * @throws ConnexionException
	 */
	public static void login(String login, String password, int nbConnections)
			throws LoginIncorrectException, PasswordIncorrectException,
			DesactivationException, ConnectionSimultaneousException,
			ConnexionException {

		if (!facadeInitialized) {
			ClientFacadeInitializer.init(null, null);
		}

		FacadeFactory.getFacadeUserLogin().authenticate(login, password,
				nbConnections);
		FacadeFactory.init(login, password);
		userContext = FacadeFactory.getFacadeUserInformation().connectUser();

		/*
		 * if (!facadeInitialized) { ClientFacadeInitializer.init(); }
		 */

		//DateHelper.initDateDuJour(userContext.getDate());
		/*
		 * schedule(new AutomaticLogoutTask(), userContext.getSessionTimeout());
		 */
	}

	// public static void logout() {
	// FacadeFactory.getGestionConnexion().deconnecter();
	// s_contexteUtilisateur = null;
	// }
	//
	public static boolean isLogged() {
		return (null != userContext);
	}

	private static void checkLogged() {
		if (!isLogged()) {
			throw new IncoherenceException("Not logged in.");
		}
		// }
		//
		// /**
		// * Indique si un service fonctionnel appartient aux services
		// accessibles à
		// * l'utilisateur connecté
		// */
		// public static boolean checkAccess(ServiceFonctionnel service) {
		// checkLogged();
		//
		// if (null != s_contexteUtilisateur.getServiceFonctionnelSet()) {
		// return s_contexteUtilisateur.getServiceFonctionnelSet().contains(
		// service);
		// }
		// return false;
		// }
		//
		// /**
		// * Indique si les services fonctionnels appartiennent aux services
		// * accessibles à l'utilisateur connecté
		// */
		// public static boolean checkAccess(List serviceFonctionnelList) {
		// checkLogged();
		//
		// if (null != s_contexteUtilisateur.getServiceFonctionnelSet()) {
		// return s_contexteUtilisateur.getServiceFonctionnelSet()
		// .containsAll(serviceFonctionnelList);
		// }
		// return false;
		// }
		//
		// /**
		// * Indique si au moins un service fonctionnel appartient aux services
		// * accessibles à l'utilisateur connecté
		// */
		// public static boolean checkAccessAtOneService(List
		// serviceFonctionnelList) {
		// checkLogged();
		// boolean res = false;
		//
		// if (null != s_contexteUtilisateur.getServiceFonctionnelSet()
		// && serviceFonctionnelList != null) {
		// for (Iterator it = s_contexteUtilisateur.getServiceFonctionnelSet()
		// .iterator(); it.hasNext() && !res;) {
		// res = serviceFonctionnelList.contains(it.next());
		// }
		// }
		// return res;
		// }
		//
		// /**
		// * Indique si le site fait partie des sites de visiblité de
		// l'utilisateur
		// * connecté
		// */
		// public static boolean isAuthorizedOnSite(ObjectId siteId) {
		// checkLogged();
		// Set visibiliteSet = s_contexteUtilisateur.getSiteVisibleIdSet();
		//
		// return (null != visibiliteSet && visibiliteSet.contains(siteId));
		// }
		//
		// /**
		// * Indique si au moins un des sites passés en paramètre fait partie
		// des
		// * sites de visiblité de l'utilisateur connecté
		// */
		// public static boolean isAuthorizedOnAtLeastOneSite(List siteIdList) {
		// checkLogged();
		// Set visibiliteSet = s_contexteUtilisateur.getSiteVisibleIdSet();
		//
		// return (siteIdList != null && visibiliteSet != null &&
		// isIntersectionNotNull(
		// visibiliteSet, siteIdList));
		// }
		//
		// /**
		// * precondition : Les 2 collections ne doivent pas être nulles
		// */
		// private static boolean isIntersectionNotNull(Set set, List
		// siteIdList) {
		// boolean res = false;
		// Iterator itSiteId = siteIdList.iterator();
		// ObjectId siteId;
		//
		// while (!res && itSiteId.hasNext()) {
		// siteId = (ObjectId) itSiteId.next();
		//
		// res = set.contains(siteId);
		// }
		// return res;
		// }
		//
		// /**
		// * Renvoie la liste des sites régionaux ou inter-régionaux faisant
		// partie
		// * des sites de visiblité de l'utilisateur connecté
		// */
		// public static List getListeSitesRegionauxVisibles() {
		// List siteIdList = new ArrayList(getContexteUtilisateur()
		// .getSiteVisibleIdSet());
		// List siteList =
		// DataCacheHelper.getDataCache().getSiteList(siteIdList);
		// List siteRegionauxList = new ArrayList();
		// for (Iterator itr = siteList.iterator(); itr.hasNext();) {
		// Site site = (Site) itr.next();
		// if (site.getNiveauOrganisationnelId().equals(
		// NiveauOrganisationnel.NIVEAU_INTER_REGIONAL)
		// || site.getNiveauOrganisationnelId().equals(
		// NiveauOrganisationnel.NIVEAU_REGIONAL)) {
		// siteRegionauxList.add(site);
		// }
		// }
		// return siteRegionauxList;
		// }
		//
		// /**
		// *
		// */
		// public static AccessManager createAccessManager() {
		// return new UserContextAccessManager();
		// }
		//
		// static class UserContextAccessManager implements AccessManager {
		// public boolean checkAccess(List serviceAccessList) {
		// if (!UserContextManager.isLogged()) {
		// return false;
		// }
		//
		// return UserContextManager.checkAccess(serviceAccessList);
		// }
		//
		// public boolean checkAccessAtOneService(List serviceAccessList) {
		// if (!UserContextManager.isLogged()) {
		// return false;
		// }
		//
		// return UserContextManager
		// .checkAccessAtOneService(serviceAccessList);
		// }
		// }
		//
		// // /////////////////////////////////////
		// // /// Session Objects Management //////
		// // /////////////////////////////////////
		//
		// private static HashMap s_sessionObjects;
		//
		// public static Object getSessionObject(SessionObjectKey key) {
		// if (s_sessionObjects == null) {
		// return null;
		// }
		//
		// return s_sessionObjects.get(key);
		// }
		//
		// public static Object putSessionObject(SessionObjectKey key,
		// Object sessionObject) {
		// if (s_sessionObjects == null) {
		// s_sessionObjects = new HashMap();
		// }
		//
		// return s_sessionObjects.put(key, sessionObject);
		// }
		//
		// // /////////////////////////////////////
		// // /// TaskTimer Management //////
		// // /////////////////////////////////////
		//
		// private static Timer s_timer = new Timer();
		//
		// /**
		// * Schedule a one-time task. This task runs after "delay" ms
		// *
		// * @param delay
		// * in milliseconds
		// */
		// public static void schedule(TimerTask task, long delay) {
		// s_timer.schedule(task, delay);
		// }
		//
		// /**
		// * Schedule a repeated task. This task runs every "period" ms
		// *
		// * @param period
		// * in milliseconds
		// */
		// public static void scheduleRepeated(TimerTask task, long period) {
		// s_timer.schedule(task, 0, period);
		// }

	}
}
