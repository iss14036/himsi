package com.himsi.services;

import java.util.List;

import com.himsi.models.Berita;

public interface BeritaService {
	public Berita saveOrUpdate(Berita berita);
	public List<Berita> getAllBerita();
	public List<Berita> getAllBeritaAccepted();	
	public List<Berita> getAllBeritaImgTop3();		
	public List<Berita> getTop6();
	public List<Berita> getAllBeritaByIdDivisi(int id);
	public Berita getById(int id);
	public List<Berita> getAllBeritaByStatus(String status);
}
