package com.himsi.services;

import java.util.List;

import com.himsi.models.LaporanKeuangan;
import com.himsi.models.ProgramKerja;
import com.himsi.models.PengajuanDana;

public interface PengajuanDanaService {
	public PengajuanDana saveOrUpdate(PengajuanDana pengajuanDana);
	public List<PengajuanDana> getAllPengajuanDana();
	public List<PengajuanDana> getAllPengajuanDanaByPK(ProgramKerja programKerja);
	public PengajuanDana getPengajuanDanaByLK(LaporanKeuangan laporanKeuangan);
	public PengajuanDana getById(Integer id);
	public List<PengajuanDana> getAllPengajuanDanaByStatus(String status);
	public List<PengajuanDana> getAllPendingByBendahara();
}
