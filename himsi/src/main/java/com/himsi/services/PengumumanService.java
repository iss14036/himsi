package com.himsi.services;

import java.util.List;

import com.himsi.models.Pengumuman;

public interface PengumumanService {
	public Pengumuman saveOrUpdate(Pengumuman pengumuman);
	public List<Pengumuman> getAllPengumuman();
	public List<Pengumuman> getAllPengumumanTop15();	
	public Pengumuman getById(int id);
}
