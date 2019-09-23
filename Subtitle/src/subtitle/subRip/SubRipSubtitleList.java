package subtitle.subRip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import subtitle.Subtitle;
import subtitle.mPlayer.MPlayerSubtitle;
import subtitle.mPlayer.MPlayerSubtitleList;
import subtitle.microDVD.MicroDVDSubtitle;
import subtitle.microDVD.MicroDVDSubtitleList;

public class SubRipSubtitleList {
private ArrayList<SubRipSubtitle> list;
	
	public SubRipSubtitleList() {
		this.list = new ArrayList<SubRipSubtitle>();
	}
	
	public ArrayList<SubRipSubtitle> getList() {
		return list;
	}

	public void setList(ArrayList<SubRipSubtitle> list) {
		this.list = list;
	}
	
	public void dodaj(Subtitle subtitle) {
		this.list.add((SubRipSubtitle) subtitle);
	}
	
	public void read(String fileName) {	
		try {
			BufferedReader readerFajla = new BufferedReader(new FileReader(fileName));

			int titleNumber = 1;

			String linija = readerFajla.readLine(); 
			while (linija != null) { 
				if (!linija.equals("")) {
					
					titleNumber = Integer.valueOf(linija);
					
					linija = readerFajla.readLine();
					
					SubRipSubtitle sub = linijaUSubtitle(linija, titleNumber, readerFajla);
					this.list.add(sub);
				}

				linija = readerFajla.readLine(); 
			}

			readerFajla.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private SubRipSubtitle linijaUSubtitle(String linija, int titleNumber, BufferedReader readerFajla) {
		String text = "";
		int begin = -1;
		int end = -1;
		
		String[] splittedString = linija.split(" --> ");
		String beginS = splittedString[0];
		String[] splittedBegin = beginS.split(":");
		int satiB = Integer.parseInt(splittedBegin[0]);
		int minB = Integer.parseInt(splittedBegin[1]);
		String[] splitSekB = splittedBegin[2].split(",");
		
		begin = Integer.valueOf(splitSekB[1]) +
				(Integer.valueOf(splitSekB[0]) * 1000) + 
				(Integer.valueOf(minB) * 60 * 1000) + 
				(Integer.valueOf(satiB) * 60 * 60 * 1000);
		
		String endS = splittedString[1];
		String[] splittedEnd = endS.split(":");
		int satiE = Integer.parseInt(splittedEnd[0]);
		int minE = Integer.parseInt(splittedEnd[1]);
		String[] splitSekE = splittedEnd[2].split(",");
		
		
		end = Integer.valueOf(splitSekE[1]) +
				(Integer.valueOf(splitSekE[0]) * 1000) + 
				(Integer.valueOf(minE) * 60 * 1000) + 
				(Integer.valueOf(satiE) * 60 * 60 * 1000);	
		
		try {
			String novaLinija = readerFajla.readLine();
			
			text = novaLinija;
			
			novaLinija = readerFajla.readLine();
			
			if(novaLinija != null && !novaLinija.equals("")) {
				text += "\n" + novaLinija;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SubRipSubtitle subRip = new SubRipSubtitle(text, titleNumber, begin, end);
		return subRip;
	}

	public void print(String fileName) {
		File file = new File(fileName);
		try {
			file.createNewFile(); 

			FileWriter writer = new FileWriter(file); 

			// upisujemo sadrzaj u fajl

			for (SubRipSubtitle subRipSubtitle : list) {
				StringBuilder sb = new StringBuilder();
				sb.append(subRipSubtitle.getTitleNumber());
				sb.append("\n");
				sb.append(konvertujVreme(subRipSubtitle.getBegin()));
				sb.append(" --> ");
				sb.append(konvertujVreme(subRipSubtitle.getEnd()));
				sb.append("\n");
				sb.append(subRipSubtitle.getText());
				sb.append("\n\n");

				writer.write(sb.toString());
			}

			writer.flush();
			writer.close();
		} catch (Exception e) {

		}
	}
	
	private String konvertujVreme(int milis) {
		String result = "";
		
		int sat = 0;
		int min = 0;
		int sec = 0;
		int milisek = 0;
		
		milisek = milis % 1000;
		
		sec = milis / 1000;
		
		min = sec / 60;
		
		sec = sec % 60;
		
		sat = min / 60;
		
		min = min % 60;
		
		result = dodavanjeNule(sat) +
				":" +
				dodavanjeNule(min) +
				":" +
				dodavanjeNule(sec) +
				"," +
				dodavanjeNulaMilis(milisek);
		
		return result;
	}
	
	private String dodavanjeNule(int broj) {
		String result = "";
		
		if(broj > 9) {
			result = "" + broj;
		}
		else {
			result = "0" + broj;
		}
		
		return result;
	}
	
	private String dodavanjeNulaMilis(int broj) {
		String result = "";
		
		if(broj > 99) {
			result = "" + broj;
		}
		else if(broj > 9) {
			result = "0" + broj;
		}
		else {
			result = "00" + broj;
		}
		
		return result;
	}
	
	public MicroDVDSubtitleList konvertujUMicroDVDSubtitle() {
		MicroDVDSubtitleList microDVDSubtitleList = new MicroDVDSubtitleList();
		
		for(SubRipSubtitle subRipSubtitle : list) {
			String text = subRipSubtitle.getText();
			text = text.replace("\n", "|");
			
			double frameBegin = (subRipSubtitle.getBegin() / 1000 ) * MicroDVDSubtitle.FPS;
			double frameEnd = (subRipSubtitle.getEnd() / 1000 ) * MicroDVDSubtitle.FPS;
			
			MicroDVDSubtitle microDVDSubtitle = new MicroDVDSubtitle(text,
																	 subRipSubtitle.getTitleNumber(),
																	 frameBegin,
																	 frameEnd);
			
			microDVDSubtitleList.dodaj(microDVDSubtitle);
		}
		
		return microDVDSubtitleList;
	}
	
	public MPlayerSubtitleList konvertujUMPlayerSubtitle() {
		MPlayerSubtitleList mPlayerSubtitleList = new MPlayerSubtitleList();
		
		for(int i = 0; i < list.size(); i++) {
			double delay = -1;
			double duration = -1;
			
			if(i == 0) {
				delay = list.get(i).getBegin() / 1000;
			}
			else {
				delay = ((double)list.get(i).getBegin() - (double)list.get(i - 1).getEnd()) / 1000;
			}
			
			duration = ((double)list.get(i).getEnd() - (double)list.get(i).getBegin()) / 1000;
			
			MPlayerSubtitle mPlayerSubtitle = new MPlayerSubtitle(list.get(i).getText(),
																 list.get(i).getTitleNumber(),
																 delay,
																 duration);
			
			mPlayerSubtitleList.dodaj(mPlayerSubtitle);
		}
		
		return mPlayerSubtitleList;
	}
	
}
