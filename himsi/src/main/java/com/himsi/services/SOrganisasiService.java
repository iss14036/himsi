package com.himsi.services;

import java.util.List;

import com.himsi.models.StrukturOrganisasi;

public interface SOrganisasiService {
	public StrukturOrganisasi saveOrUpdate(StrukturOrganisasi sOrganisasi);
	public List<StrukturOrganisasi> getAll();
	public StrukturOrganisasi getById(Integer id);	
}
