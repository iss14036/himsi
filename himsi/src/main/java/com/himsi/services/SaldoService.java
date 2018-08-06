package com.himsi.services;

import com.himsi.models.Saldo;

public interface SaldoService {
	public Saldo saveOrUpdate(Saldo saldo);
	public Saldo getSaldo();
}
