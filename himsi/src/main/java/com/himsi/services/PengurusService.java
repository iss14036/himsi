package com.himsi.services;

import java.util.List;

import com.himsi.models.Pengurus;

public interface PengurusService {
	public List<Pengurus> getAllPengurus();
	public List<Pengurus> getAllAnggotaPengurusByid(int id);
	public Pengurus getPengurusById(int id);
}
