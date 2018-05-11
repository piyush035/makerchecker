
package com.rennover.client.framework.widget.combobox;

import java.util.List;

/**
 * @author dattias
 */
public interface SearchAlgorithm
{
	List search(String searchValue) throws SearchAlgorithmException;
}