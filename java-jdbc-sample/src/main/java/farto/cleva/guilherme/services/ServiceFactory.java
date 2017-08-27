package farto.cleva.guilherme.services;

import farto.cleva.guilherme.bo.AlunoBO;
import farto.cleva.guilherme.bo.CursoBO;
import farto.cleva.guilherme.bo.interfaces.IAlunoBO;
import farto.cleva.guilherme.bo.interfaces.ICursoBO;

public class ServiceFactory {

	// Singleton
	private static ServiceFactory INSTANCE = null;

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ServiceFactory();
		}

		return INSTANCE;
	}

	public ICursoBO getCursoBO() {
		return new CursoBO();
	}

	public IAlunoBO getAlunoBO() {
		return new AlunoBO();
	}

}
