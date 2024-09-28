package model;

public class Nilai {
	private int idNilai;
	private String NIM;
	private String idMataKuliah;
	private String idPengguna;
	private int jumlahNilai;
	private String grade;
	private String status;
	
	public Nilai(int idNilai, String NIM, String idMataKuliah, String idPengguna, int jumlahNilai, String grade,
			String status) {
		this.idNilai = idNilai;
		this.NIM = NIM;
		this.idMataKuliah = idMataKuliah;
		this.idPengguna = idPengguna;
		this.jumlahNilai = jumlahNilai;
		this.grade = grade;
		this.status = status;
	}
	
	public int getIdNilai() {
		return idNilai;
	}
	public void setIdNilai(int idNilai) {
		this.idNilai = idNilai;
	}
	public String getNIM() {
		return NIM;
	}
	public void setNIM(String NIM) {
		NIM = this.NIM;
	}
	public String getIdMataKuliah() {
		return idMataKuliah;
	}
	public void setIdMataKuliah(String idMataKuliah) {
		this.idMataKuliah = idMataKuliah;
	}
	public String getIdPengguna() {
		return idPengguna;
	}
	public void setIdPengguna(String idPengguna) {
		this.idPengguna = idPengguna;
	}
	public int getJumlahNilai() {
		return jumlahNilai;
	}
	public void setJumlahNilai(int jumlahNilai) {
		this.jumlahNilai = jumlahNilai;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
