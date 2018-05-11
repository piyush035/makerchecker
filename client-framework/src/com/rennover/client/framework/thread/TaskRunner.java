package com.rennover.client.framework.thread;

import com.rennover.hotel.common.log.Logger;

/**
 * @todo attention à l'initialisation, l'atente de l'état ready est a améliorer
 * @todo suppression des Sytem.out
 * 
 * 
 * @tochange mettre plus de javadoc
 */
class TaskRunner extends Thread
{
	private Runnable m_currentTask;

	private TaskRunnerMonitor m_monitor;

	TaskRunner()
	{
		m_monitor = new TaskRunnerMonitor();
		start();
	}

	public void run()
	{
		while (true)
		{
			try
			{
				m_monitor.waitForTask();

				if (m_currentTask != null)
				{
					m_currentTask.run();
				}
			} catch (InterruptedException exc)
			{
				Logger.error(this, exc);
				interrupted();
			} finally
			{
				m_currentTask = null;
				TaskHelper.returnRunner(this);
			}
		}
	}

	public void run(Runnable task)
	{
		m_currentTask = task;

		m_monitor.notifyTaskArrived();
	}

	/**
	 * pour debug et suivi des threads
	 */

	// removed the commented method getNextRunnerID()
	/**
	 * Mise a jour de l'état du thread: préparation à une remise dans le pool
	 * note: on RAZ le flag "interrupted" du thread pour le remettre dans un
	 * état correct. on évite ainsi qu'une InterruptedException malheureuse soit
	 * levée pendant le wait() de la remise en pool.
	 * <p>
	 * 
	 * @tochange enlever les print et remplacer par des Logger.debug
	 */
	// removed the commented method toIdleState()
	// removed the commented method toWorkingState()
}