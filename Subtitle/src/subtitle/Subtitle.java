package subtitle;

public abstract class Subtitle {
	private String text;
	private int titleNumber;
	
	public Subtitle(String text, int titleNumber) {
		this.text = text;
		this.titleNumber = titleNumber;
	}
	
	public abstract String toString();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getTitleNumber() {
		return titleNumber;
	}

	public void setTitleNumber(int titleNumber) {
		this.titleNumber = titleNumber;
	}
}
