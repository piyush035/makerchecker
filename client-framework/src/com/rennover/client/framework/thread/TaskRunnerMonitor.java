
package com.rennover.client.framework.thread;


class TaskRunnerMonitor
{
	private boolean m_isTaskWaiting = false;

	/**
	 * Classe permettant de gérer le lock de synchronisation
	 */
	synchronized void waitForTask() throws InterruptedException
	{
		if (!m_isTaskWaiting)
		{
			wait();
		}

		m_isTaskWaiting = false;
	}

	synchronized void notifyTaskArrived()
	{
		m_isTaskWaiting = true;

		notify();
	}
}