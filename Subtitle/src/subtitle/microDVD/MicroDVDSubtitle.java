package subtitle.microDVD;

import subtitle.Subtitle;

public class MicroDVDSubtitle extends Subtitle {
	private double frameBegin;
	private double frameEnd;
	public static final double FPS = 25;
	
	public MicroDVDSubtitle(String text, int titleNumber, double frameBegin, double frameEnd) {
		super(text, titleNumber);
		this.frameBegin = frameBegin;
		this.frameEnd = frameEnd;
	}
	
	public String toString() {
		return null;
	}

	public double getFrameBegin() {
		return frameBegin;
	}

	public void setFrameBegin(int frameBegin) {
		this.frameBegin = frameBegin;
	}

	public double getFrameEnd() {
		return frameEnd;
	}

	public void setFrameEnd(int frameEnd) {
		this.frameEnd = frameEnd;
	}
}
