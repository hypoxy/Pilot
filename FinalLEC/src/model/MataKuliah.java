package model;

public class MataKuliah {
	private String idMatKul;
	private String namaMatKul;
	
	public MataKuliah(String idMatKul, String namaMatKul) {
		super();
		this.idMatKul = idMatKul;
		this.namaMatKul = namaMatKul;
	}

	public String getIdMatKul() {
		return idMatKul;
	}

	public void setIdMatKul(String idMatKul) {
		this.idMatKul = idMatKul;
	}

	public String getNamaMatKul() {
		return namaMatKul;
	}

	public void setNamaMatKul(String namaMatKul) {
		this.namaMatKul = namaMatKul;
	}
	
	
	
}
