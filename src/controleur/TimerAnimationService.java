package controleur;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class TimerAnimationService extends ScheduledService<Float>
{
	private float valeur;
	
	public TimerAnimationService(float ref){
		super();
		this.valeur = ref;
	}

	@Override
	protected Task<Float> createTask()
	{
		return new Task<Float>()
		{
			private float depart = TimerAnimationService.this.valeur;

			@Override
			protected Float call() throws Exception
			{
				updateValue(getValue());
				return getValue();
			};
		};
	}
}
