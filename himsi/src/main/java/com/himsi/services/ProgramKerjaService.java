package com.himsi.services;

import java.util.List;

import com.himsi.models.ProgramKerja;

public interface ProgramKerjaService {
	public ProgramKerja saveOrUpdate(ProgramKerja programKerja);
	public List<ProgramKerja> getAllProgramKerja();
	public List<ProgramKerja> getAllProgramKerjaByDivisi(int divisi);
	public ProgramKerja getById(int id);
}
