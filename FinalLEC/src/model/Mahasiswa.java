package model;

public class Mahasiswa {
	private String NIM;
	private String namaMahasiswa;
	private String asalUni;
	
	public Mahasiswa(String NIM, String namaMahasiswa, String asalUni) {
		super();
		this.NIM = NIM;
		this.namaMahasiswa = namaMahasiswa;
		this.asalUni = asalUni;
	}

	public String getNIM() {
		return NIM;
	}

	public void setNIM(String NIM) {
		this.NIM = NIM;
	}

	public String getNamaMahasiswa() {
		return namaMahasiswa;
	}

	public void setNamaMahasiswa(String namaMahasiswa) {
		this.namaMahasiswa = namaMahasiswa;
	}

	public String getAsalUni() {
		return asalUni;
	}

	public void setAsalUni(String asalUni) {
		this.asalUni = asalUni;
	}
	
	
	
}
