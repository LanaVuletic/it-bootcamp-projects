package subtitle.mPlayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import subtitle.microDVD.MicroDVDSubtitle;
import subtitle.microDVD.MicroDVDSubtitleList;
import subtitle.subRip.SubRipSubtitle;
import subtitle.subRip.SubRipSubtitleList;

public class MPlayerSubtitleList {
	private ArrayList<MPlayerSubtitle> list;

	public MPlayerSubtitleList() {
		this.list = new ArrayList<MPlayerSubtitle>();
	}
	
	public ArrayList<MPlayerSubtitle> getList() {
		return list;
	}

	public void setList(ArrayList<MPlayerSubtitle> list) {
		this.list = list;
	}

	public void dodaj(MPlayerSubtitle subtitle) {
		list.add(subtitle);
	}

	public void read(String fileName) {
		try {
			BufferedReader readerFajla = new BufferedReader(new FileReader(fileName));

			int titleNumber = 1;

			String linija = readerFajla.readLine(); // citamo linije fajla
			while (linija != null) { // i dok god ima redova
				if (!linija.equals("")) {
					MPlayerSubtitle sub = linijaUSubtitle(linija, titleNumber, readerFajla);
					this.list.add(sub);
					titleNumber++;
				}

				linija = readerFajla.readLine(); // nastavljamo sa citanjem
			}

			readerFajla.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private MPlayerSubtitle linijaUSubtitle(String linija, int titleNumber, BufferedReader reader) {
		String text = "";
		double delay = -1;
		double duration = -1;

		try {

			String[] splittedString = linija.split(" ");
			delay = Double.valueOf(splittedString[0]);
			duration = Double.valueOf(splittedString[1]);

			String nextLine = reader.readLine();
			text += nextLine;

			nextLine = reader.readLine();
			if (!nextLine.equals("")) {
				text += "\n" + nextLine;
			}
		} 
		catch (Exception e) {
		}

		MPlayerSubtitle mPlayerSubtitle = new MPlayerSubtitle(text, titleNumber, delay, duration);

		return mPlayerSubtitle;
	}

	public void print(String fileName) {
		File file = new File(fileName);
		try {
			file.createNewFile(); // ovde kreiramo novi fajl

			FileWriter writer = new FileWriter(file); // ovde kreiramo novi FileWriter objekat

			// upisujemo sadrzaj u fajl

			for (MPlayerSubtitle mPlayerSubtitle : list) {
				StringBuilder sb = new StringBuilder();
				sb.append(mPlayerSubtitle.getDelay());
				sb.append(" ");
				sb.append(mPlayerSubtitle.getDuration());
				sb.append("\n");
				sb.append(mPlayerSubtitle.getText());
				sb.append("\n\n");

				writer.write(sb.toString());
			}

			writer.flush();
			writer.close();
		} catch (Exception e) {

		}
	}
	
	public MicroDVDSubtitleList konvertujUMicroDVDSubtitle() {
		MicroDVDSubtitleList result = new MicroDVDSubtitleList();
		
		for(int i = 0; i < list.size(); i++) {
			String text = list.get(i).getText();
			text = text.replace("\n", "|");
			
			double frameBegin = -1;
			
			if(i == 0) {
				frameBegin = list.get(i).getDelay() * MicroDVDSubtitle.FPS;
			}
			else {
				frameBegin = result.getList().get(i - 1).getFrameEnd() + (list.get(i).getDelay() * MicroDVDSubtitle.FPS);
			}
			
			double frameEnd = frameBegin + (list.get(i).getDuration() * MicroDVDSubtitle.FPS);
			
			MicroDVDSubtitle microDVDSubtitle = new MicroDVDSubtitle(text,
																	 list.get(i).getTitleNumber(),
																	 frameBegin,
																	 frameEnd);
			
			result.dodaj(microDVDSubtitle);
		}
		
		return result;
	}
	
	public SubRipSubtitleList konvertujUSubRipSubtitle() {
		SubRipSubtitleList subRipSubtitleList = new SubRipSubtitleList();
		
		for(int i = 0; i < list.size(); i++) {
			int begin = -1;
			int end = -1;
			
			if(i == 0) {
				begin = (int) (list.get(i).getDelay() * 1000);
			}
			else {
				begin = (int) (subRipSubtitleList.getList().get(i - 1).getEnd() + list.get(i).getDelay() * 1000);
			}
			
			end = (int) (begin + (list.get(i).getDuration() * 1000));
			
			SubRipSubtitle subRipSubtitle = new SubRipSubtitle(list.get(i).getText(),
															   list.get(i).getTitleNumber(),
															   begin,
															   end);
			
			subRipSubtitleList.dodaj(subRipSubtitle);
			
		}
		
		return subRipSubtitleList;
	}

}
