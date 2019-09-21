package subtitle.mPlayer;

import subtitle.Subtitle;

public class MPlayerSubtitle extends Subtitle{
	private double delay;
	private double duration;
	
	public MPlayerSubtitle(String text, int titleNumber, double delay, double duration) {
		super(text, titleNumber);
		this.delay = delay;
		this.duration = duration;	
	}
	
	public double getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String toString() {
		return null;
	}

}
