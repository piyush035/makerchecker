package com.rennover.client.framework.widget.field;

import com.rennover.client.framework.widget.base.ZLabel;

/**
 * @author tcaboste
 * @audit dattias 31/12/02
 */
public interface InputField
{
	/**
	 * Positionne le label qui sera utilisé pour afficher (avec des couleurs et
	 * l'utilisation d'une police de caractères en gras) l'état de validité et
	 * le caractère obligatoire de ce champ.
	 */
	public void setAssociatedLabel(ZLabel label);

	/**
	 * Retourne le label qui sera utilisé pour afficher (avec des couleurs et
	 * l'utilisation d'une police de caractères en gras) l'état de validité et
	 * le caractère obligatoire de ce champ.
	 */
	public ZLabel getAssociatedLabel();

	/**
	 * Positionne l'état obligatoire et met à jour l'aspect graphique.
	 * 
	 * <p>
	 * Attention: positionner le widget comme obligatoire aura uniquement un
	 * effet sur l'apparence graphique. La vérification du caractère obligatoire
	 * sera fait par le framework de validation (les "Validators").
	 * </p>
	 * 
	 * @change renommer setMandatory.
	 */
	public void setMandatoryField(boolean mandatory);

	/**
	 * Retourne l'état obligatoire de la widget
	 * 
	 * @change renommer isMandatory.
	 */
	public boolean isMandatoryField();

	/**
	 * Positionne l'état de validité et met à jour l'aspect graphique.
	 * 
	 * <p>
	 * Attention: positionner le widget comme valide aura uniquement un effet
	 * sur l'apparence graphique. La vérification du caractère de validité sera
	 * fait par le framework de validation (les "Validators").
	 * </p>
	 * 
	 * @change renommer setValid.
	 */
	public void setValidField(boolean valid);

	/**
	 * Calcul l'état de validité de la valeur contenu dans la widget
	 * 
	 * @change renommer isValid.
	 */
	public boolean isValidValue();

	/**
	 * Retourne l'état de validité de la widget
	 * 
	 * @change renommer isValid.
	 */
	public boolean isValidField();

	public boolean isValueSetted();

	/**
	 * Retourne la valeur contenue dans le composant et qui servira à remplir le
	 * champ d'un objet
	 */
	public Object getValue();

	/**
	 * Met à jour le contenu du composant
	 */
	public void setValue(Object value);

	/**
	 * Indique si le champs est actif ou non
	 */
	public boolean isEnabled();

	/**
	 * Positionne l'état d'activation du champs
	 */
	public void setEnabled(boolean enabled);

	/**
	 * Indique si le champs est modifiable(activable) ou non
	 */
	public boolean isReadOnly();

	/**
	 * Positionne la capacité du champs à être modifiable
	 */
	public void setReadOnly(boolean enabled);
}