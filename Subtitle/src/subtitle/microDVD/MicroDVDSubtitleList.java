package subtitle.microDVD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import subtitle.mPlayer.MPlayerSubtitle;
import subtitle.mPlayer.MPlayerSubtitleList;
import subtitle.subRip.SubRipSubtitle;
import subtitle.subRip.SubRipSubtitleList;

public class MicroDVDSubtitleList {
	private ArrayList<MicroDVDSubtitle> subtitleList;
	
	public final static double FPS = 25;
	
	public MicroDVDSubtitleList() {
		this.subtitleList = new ArrayList<MicroDVDSubtitle>();
	}
	
	public void dodaj(MicroDVDSubtitle subtitle) {
		this.subtitleList.add(subtitle);
	}

	public ArrayList<MicroDVDSubtitle> getList() {
		return subtitleList;
	}

	public void setList(ArrayList<MicroDVDSubtitle> subtitleList) {
		this.subtitleList = subtitleList;
	}
	
	public void read(String fileName) {
		try {
			BufferedReader readerFajla = new BufferedReader(new FileReader(fileName));
			
			int titleNumber = 1;
						
			String linija = readerFajla.readLine(); // citamo linije fajla
			while (linija != null) { // i dok god ima redova				
				MicroDVDSubtitle sub = linijaUSubtitle(linija, titleNumber);
				subtitleList.add(sub);
				titleNumber++;
				
				linija = readerFajla.readLine(); // nastavljamo sa citanjem
			}
			
			readerFajla.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MicroDVDSubtitle linijaUSubtitle(String linija, int titleNumber) {
		int frameBegin = -1;
		int frameEnd = -1;
		String text = "";
		
		linija = linija.replace("{Y:i}", "");
		
		int indexBegin = linija.indexOf("}");
		frameBegin = Integer.parseInt(linija.substring(1, indexBegin));
		
		int indexEnd = linija.lastIndexOf("}");
		frameEnd = Integer.parseInt(linija.substring((indexBegin + 2), indexEnd));
		
		text = linija.substring(indexEnd + 1, linija.length());
		
		MicroDVDSubtitle sub = new MicroDVDSubtitle(text, titleNumber, frameBegin, frameEnd);
		
		return sub;			
	}
	
	public void print(String fileName) {
		File file = new File(fileName);
		try {
		file.createNewFile(); // ovde kreiramo novi fajl
		
		FileWriter writer = new FileWriter(file); // ovde kreiramo novi FileWriter objekat
		
		// upisujemo sadrzaj u fajl
		
		for(MicroDVDSubtitle mPlayerSubtitle : subtitleList) {
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append((int)mPlayerSubtitle.getFrameBegin());
			sb.append("}{");
			sb.append((int)mPlayerSubtitle.getFrameEnd());
			sb.append("}");
			sb.append(mPlayerSubtitle.getText());
			sb.append("\n");
			
			writer.write(sb.toString());
		}
		
		writer.flush();
		writer.close();
		}
		catch (Exception e) {
			
		}
	}
		
	public MPlayerSubtitleList konvertujUMPlayerSubtitle() {
		MPlayerSubtitleList result = new MPlayerSubtitleList();
		
		for(int i = 0; i < subtitleList.size(); i++) {
			double delay = -1;
			double duration = -1;
			
			if(i == 0) {
				delay = subtitleList.get(i).getFrameBegin() / FPS;
			}
			else {
				delay = (subtitleList.get(i).getFrameBegin() - subtitleList.get(i - 1).getFrameEnd()) / FPS;			
			}
			
			duration = (subtitleList.get(i).getFrameEnd() - subtitleList.get(i).getFrameBegin()) / FPS;

			String text = subtitleList.get(i).getText();
			text = text.replace("|", "\n");
			
			MPlayerSubtitle mPlayerSubtitle = new MPlayerSubtitle(text,
																  subtitleList.get(i).getTitleNumber(),
																  delay,
																  duration);		
			result.dodaj(mPlayerSubtitle);
		}
		
		return result;
	}
	
	public SubRipSubtitleList konvertujUSubRipSubtitle() {
		SubRipSubtitleList subRipSubtitleList = new SubRipSubtitleList();
		
		for(MicroDVDSubtitle microDVDSubtitle : subtitleList) {
			int begin = (int) ((microDVDSubtitle.getFrameBegin() / 25) * 1000);
			int end = (int) ((microDVDSubtitle.getFrameEnd() / 25) * 1000);
			
			String text = microDVDSubtitle.getText();
			text = text.replace("|", "\n");
			
			SubRipSubtitle subRipSubtitle = new SubRipSubtitle(text,
															   microDVDSubtitle.getTitleNumber(),
															   begin,
															   end);
			
			subRipSubtitleList.dodaj(subRipSubtitle);
		}
		
		return subRipSubtitleList;
	}
	
	
}
