package controleur;

import javafx.beans.property.StringProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class TimerAnimationService extends ScheduledService<Float>
{
	private float valeur;

	public TimerAnimationService(float ref)
	{
		super();
		this.valeur = ref;
	}

	@Override
	protected Task<Float> createTask()
	{
		return new Task<Float>()
		{
			private float depart = 0;

			@Override
			protected Float call() throws Exception
			{
				while (!this.isCancelled())
				{
					updateValue(getLastValue() + 1);
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException ie)
					{
					}
				}
				return getValue();
			};
		};
	}
}
