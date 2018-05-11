
package com.rennover.client.framework.thread;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Point d'entrée du manager de thread. A utiliser lorsqu'on a besoin d'un
 * thread.
 * 
 * <p>
 * Utilisation typique:
 * 
 * <pre>
 * Runnable task = new Runnable()
 * {
 * 	public void run()
 * 	{
 * 		// action
 * 	}
 * };
 * TaskHelper.run(task);
 * </pre>
 * 
 * </p>
 * 
 * @todo permettre un close() du pool (pour cela faut garder une référence vers
 *       les threads actifs)
 * @todo "amaigrissement" du pool au delà d'une certaine taille ? ou taille max ?
 * @audit dattias 27/09/02
 * @tochange utiliser une pile en lieu et place d'un vecteur
 * @tochange mettre plus de javadoc
 */
public class TaskHelper
{
	/**
	 * Synchronisation uniquement sur la liste pour éviter la contention dans le
	 * cas d'un nouveau Runner
	 */
	private static List s_runnerList = new Vector(5);

	/**
	 * TODO (dattias, le 10 nov. 2003) : retourner le thread pour permettre au
	 * développeur de mieux controler sa tache.
	 * 
	 * @param task
	 */
	public static void run(Runnable task)
	{
		getRunner().run(task);
	}

	private static TaskRunner getRunner()
	{
		Iterator iterator = s_runnerList.iterator();
		if (iterator.hasNext())
		{
			TaskRunner runner = (TaskRunner) iterator.next();
			s_runnerList.remove(runner);
			return runner;
		} else
		{
			return new TaskRunner();
		}
	}

	/**
	 * @tochange il faut vérifier que le runner n'est pas ajouter à une liste
	 *           qui le contient déjà ca qui peut arriver si le wait du thread
	 *           est interrompu par une InterruptedException.
	 */
	static void returnRunner(TaskRunner runner)
	{
		s_runnerList.add(runner);
	}

	/**
	 * @tochange détruire les threads avant de faire un clear.
	 * @todo garder une référence de TOUS les threads (donc avoir une liste des
	 *       threads actifs en + de la liste des inactifs) pour que ce reset
	 *       soit réellement efficace pour l'instant on ne RAZ que les threads
	 *       inactifs à l'instant t.
	 */
	static void reset()
	{
		s_runnerList.clear();
	}
}