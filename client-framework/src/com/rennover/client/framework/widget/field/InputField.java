package com.rennover.client.framework.widget.field;

import com.rennover.client.framework.widget.base.ZLabel;

/**
 * @author tcaboste
 * @audit dattias 31/12/02
 */
public interface InputField
{
	/**
	 * Positionne le label qui sera utilis� pour afficher (avec des couleurs et
	 * l'utilisation d'une police de caract�res en gras) l'�tat de validit� et
	 * le caract�re obligatoire de ce champ.
	 */
	public void setAssociatedLabel(ZLabel label);

	/**
	 * Retourne le label qui sera utilis� pour afficher (avec des couleurs et
	 * l'utilisation d'une police de caract�res en gras) l'�tat de validit� et
	 * le caract�re obligatoire de ce champ.
	 */
	public ZLabel getAssociatedLabel();

	/**
	 * Positionne l'�tat obligatoire et met � jour l'aspect graphique.
	 * 
	 * <p>
	 * Attention: positionner le widget comme obligatoire aura uniquement un
	 * effet sur l'apparence graphique. La v�rification du caract�re obligatoire
	 * sera fait par le framework de validation (les "Validators").
	 * </p>
	 * 
	 * @change renommer setMandatory.
	 */
	public void setMandatoryField(boolean mandatory);

	/**
	 * Retourne l'�tat obligatoire de la widget
	 * 
	 * @change renommer isMandatory.
	 */
	public boolean isMandatoryField();

	/**
	 * Positionne l'�tat de validit� et met � jour l'aspect graphique.
	 * 
	 * <p>
	 * Attention: positionner le widget comme valide aura uniquement un effet
	 * sur l'apparence graphique. La v�rification du caract�re de validit� sera
	 * fait par le framework de validation (les "Validators").
	 * </p>
	 * 
	 * @change renommer setValid.
	 */
	public void setValidField(boolean valid);

	/**
	 * Calcul l'�tat de validit� de la valeur contenu dans la widget
	 * 
	 * @change renommer isValid.
	 */
	public boolean isValidValue();

	/**
	 * Retourne l'�tat de validit� de la widget
	 * 
	 * @change renommer isValid.
	 */
	public boolean isValidField();

	public boolean isValueSetted();

	/**
	 * Retourne la valeur contenue dans le composant et qui servira � remplir le
	 * champ d'un objet
	 */
	public Object getValue();

	/**
	 * Met � jour le contenu du composant
	 */
	public void setValue(Object value);

	/**
	 * Indique si le champs est actif ou non
	 */
	public boolean isEnabled();

	/**
	 * Positionne l'�tat d'activation du champs
	 */
	public void setEnabled(boolean enabled);

	/**
	 * Indique si le champs est modifiable(activable) ou non
	 */
	public boolean isReadOnly();

	/**
	 * Positionne la capacit� du champs � �tre modifiable
	 */
	public void setReadOnly(boolean enabled);
}