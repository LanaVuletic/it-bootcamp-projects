package zavrsniRad;

public class Rec {
	private String word;
	private String wordtype;
	private String definition;

	public Rec(String word, String wordtype, String definition) {
		this.word = word;
		this.wordtype = wordtype;
		this.definition = definition;
	}

	public String getWordtype() {
		return wordtype;
	}

	public String getDefinition() {
		return definition;
	}

	public String getWord() {
		return word;
	}
}
