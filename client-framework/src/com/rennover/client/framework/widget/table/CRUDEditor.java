package com.rennover.client.framework.widget.table;

import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * Cette interface est utilisée par le CRUDControler lorsque les boutons
 * ajouter, modifier ou supprimer sont utilisés. Le CRUDController appelle cette
 * interface et manipule ensuite le modèle sous jacent pour effectivement y
 * ajouter, modifier ou supprimer des VO.
 */
public interface CRUDEditor
{
	/**
	 * Crée un nouveau ValueObject et le retourne au CRUDController. Typiquement
	 * cette méthode permet a l'utilisateur de saisir des données dans une boite
	 * de dialogue et crée le VO avec ces données. Retourne null si l'ajout est
	 * annullé.
	 */
	public ValueObject doAdd();

	/**
	 * Modifie un ValueObject et indique si la modification est validée ou
	 * annullée (retourne vrai si la modification est validée, faux sinon
	 * Typiquement cette méthode permet a l'utilisateur du modifier le VO dans
	 * une boite de dialogue.
	 */
	public boolean doUpdate(ValueObject vo);

	/**
	 * Retourne vrai si l"effacement de l'objet est validé , faux sinon.
	 */
	public boolean doRemove(ValueObject vo);

	/**
	 * Affiche l'objet en consultation
	 */
	public void doDisplay(ValueObject vo);

	public void cleanReferences();
}