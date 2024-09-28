package model;

public class Pengguna {
	private String idPengguna;
	private String namaPengguna;
	private String tempatKerjaPengguna;
	private String emailPengguna;
	private String nomorTelpPengguna;
	private String passPengguna;
	
	public Pengguna(String idPengguna, String namaPengguna, String tempatKerjaPengguna, String emailPengguna,
			String nomorTelpPengguna, String passPengguna) {
		this.idPengguna = idPengguna;
		this.namaPengguna = namaPengguna;
		this.tempatKerjaPengguna = tempatKerjaPengguna;
		this.emailPengguna = emailPengguna;
		this.nomorTelpPengguna = nomorTelpPengguna;
		this.passPengguna = passPengguna;
	}

	public String getPassPengguna() {
		return passPengguna;
	}

	public void setPassPengguna(String passPengguna) {
		this.passPengguna = passPengguna;
	}

	public String getIdPengguna() {
		return idPengguna;
	}

	public void setIdPengguna(String idPengguna) {
		this.idPengguna = idPengguna;
	}

	public String getNamaPengguna() {
		return namaPengguna;
	}

	public void setNamaPengguna(String namaPengguna) {
		this.namaPengguna = namaPengguna;
	}

	public String getTempatKerjaPengguna() {
		return tempatKerjaPengguna;
	}

	public void setTempatKerjaPengguna(String tempatKerjaPengguna) {
		this.tempatKerjaPengguna = tempatKerjaPengguna;
	}

	public String getEmailPengguna() {
		return emailPengguna;
	}

	public void setEmailPengguna(String emailPengguna) {
		this.emailPengguna = emailPengguna;
	}

	public String getNomorTelpPengguna() {
		return nomorTelpPengguna;
	}

	public void setNomorTelpPengguna(String nomorTelpPengguna) {
		this.nomorTelpPengguna = nomorTelpPengguna;
	}
	
	
	
}
