package com.himsi.services;

import java.util.List;

import com.himsi.models.LaporanKeuangan;
import com.himsi.models.IuranBulanan;

public interface IuranBulananService {
	public IuranBulanan saveOrUpdate(IuranBulanan iuranBulanan);
	public void createAllPembayaran(IuranBulanan iuranBulanan, String bulan);
	public List<IuranBulanan> getAllIuranBulanan();
	public List<IuranBulanan> getAllById(int id);
	public List<IuranBulanan> getAllByIdUser(int id);	
	public List<IuranBulanan> getAllByLaporan(LaporanKeuangan lKeuangan);
	public IuranBulanan getIuranBulananById(int id);
	public int getMahasiswaBlmBayar(LaporanKeuangan lKeuangan);
	public int getMahasiswaSdhBayar(LaporanKeuangan lKeuangan);
}
