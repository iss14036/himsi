package com.himsi.services;

import java.util.List;

import com.himsi.models.Divisi;

public interface DivisiService {
	public List<Divisi> getAllDivisi();
	public Divisi getById(int id);
}
