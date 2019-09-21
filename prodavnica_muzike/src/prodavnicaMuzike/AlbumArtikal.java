package prodavnicaMuzike;

public abstract class AlbumArtikal extends Artikal {
	private String izdavac;
	protected Album album; // protected je da bi mogle da ga koriste child klase sa this.album

	public AlbumArtikal(double cena, int kolicina, String izdavac, Album album) {
		super(cena, kolicina);
		this.izdavac = izdavac;
		this.album = album;
	}

	public abstract String ime();

}
