package subtitle.subRip;

import subtitle.Subtitle;

public class SubRipSubtitle extends Subtitle {
	private int begin;
	private int end;
	
	public SubRipSubtitle(String text, int titleNumber, int begin, int end) {
		super(text, titleNumber);
		this.begin = begin;
		this.end = end;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	public String toString() {
		return null;
	}

}
