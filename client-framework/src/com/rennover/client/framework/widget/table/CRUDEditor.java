package com.rennover.client.framework.widget.table;

import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * Cette interface est utilis�e par le CRUDControler lorsque les boutons
 * ajouter, modifier ou supprimer sont utilis�s. Le CRUDController appelle cette
 * interface et manipule ensuite le mod�le sous jacent pour effectivement y
 * ajouter, modifier ou supprimer des VO.
 */
public interface CRUDEditor
{
	/**
	 * Cr�e un nouveau ValueObject et le retourne au CRUDController. Typiquement
	 * cette m�thode permet a l'utilisateur de saisir des donn�es dans une boite
	 * de dialogue et cr�e le VO avec ces donn�es. Retourne null si l'ajout est
	 * annull�.
	 */
	public ValueObject doAdd();

	/**
	 * Modifie un ValueObject et indique si la modification est valid�e ou
	 * annull�e (retourne vrai si la modification est valid�e, faux sinon
	 * Typiquement cette m�thode permet a l'utilisateur du modifier le VO dans
	 * une boite de dialogue.
	 */
	public boolean doUpdate(ValueObject vo);

	/**
	 * Retourne vrai si l"effacement de l'objet est valid� , faux sinon.
	 */
	public boolean doRemove(ValueObject vo);

	/**
	 * Affiche l'objet en consultation
	 */
	public void doDisplay(ValueObject vo);

	public void cleanReferences();
}