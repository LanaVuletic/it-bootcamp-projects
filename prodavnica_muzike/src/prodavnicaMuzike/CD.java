package prodavnicaMuzike;

public class CD extends AlbumArtikal {

	public CD(Album album, String izdavac,double cena, int kolicina) {
		super(cena, kolicina, izdavac, album);
	}

	public String ime() {
			return this.album.getIzvodjac() + " - " + this.album.getNaziv() + " (CD)";
	}
}
