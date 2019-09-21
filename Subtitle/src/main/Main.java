package main;


import subtitle.mPlayer.MPlayerSubtitleList;
import subtitle.subRip.SubRipSubtitleList;

public class Main {

	public static void main(String[] args) {
		
		MPlayerSubtitleList mPlayerSubtitleList = new MPlayerSubtitleList();
		
		mPlayerSubtitleList.read("Primer.sub");
		
		SubRipSubtitleList subRipSubtitleList = mPlayerSubtitleList.konvertujUSubRipSubtitle();
		
		subRipSubtitleList.print("subRipTitle.srt");

		
		System.out.println("Sambili smo program!");
	}
	


}
