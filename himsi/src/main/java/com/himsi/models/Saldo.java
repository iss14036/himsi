package com.himsi.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_saldo")
public class Saldo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private int total_saldo;
	private String last_update;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_iuran_bulanan_id")
	private IuranBulanan iuranBulanan;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_pengajuan_dana_id")
	private PengajuanDana pengajuanDana;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getTotal_saldo() {
		return total_saldo;
	}
	public void setTotal_saldo(int total_saldo) {
		this.total_saldo = total_saldo;
	}
	public String getLast_update() {
		return last_update;
	}
	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}
	public IuranBulanan getIuranBulanan() {
		return iuranBulanan;
	}
	public void setIuranBulanan(IuranBulanan iuranBulanan) {
		this.iuranBulanan = iuranBulanan;
	}
	public PengajuanDana getPengajuanDana() {
		return pengajuanDana;
	}
	public void setPengajuanDana(PengajuanDana pengajuanDana) {
		this.pengajuanDana = pengajuanDana;
	}
	
	
	
}
