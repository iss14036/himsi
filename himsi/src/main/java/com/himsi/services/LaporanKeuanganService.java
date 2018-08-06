package com.himsi.services;

import java.util.List;

import com.himsi.models.LaporanKeuangan;

public interface LaporanKeuanganService {
	public LaporanKeuangan saveOrUpdate(LaporanKeuangan laporanKeuangan);
	public List<LaporanKeuangan> getAllLaporanKeuangan();
	public List<LaporanKeuangan> getAllLaporanByTipe(String tipe);	
	public LaporanKeuangan getById(int id);
}
