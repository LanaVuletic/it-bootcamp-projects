package prodavnicaMuzike;

public class LP extends AlbumArtikal {
	private int tezina;
	
	public LP(Album album, String izdavac, double cena, int kolicina, int tezina) {
		super (cena, kolicina, izdavac, album);
		this.tezina = tezina;
	}
	public String ime() {
		
		return this.album.getIzvodjac() + " - " + this.album.getNaziv() +  " (" + tezina + "g) LP";
	} // getIzvodjac i getNaziv su iz Albuma
	  // pristupamo polju albbum iz parent klase Album artikal (moze i bez this)
}
