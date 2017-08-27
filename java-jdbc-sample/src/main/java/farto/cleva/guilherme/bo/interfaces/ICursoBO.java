package farto.cleva.guilherme.bo.interfaces;

import java.util.List;
import farto.cleva.guilherme.vo.CursoVO;

public interface ICursoBO {

	public boolean inserir(CursoVO curso) throws Exception;

	public boolean alterar(CursoVO curso) throws Exception;

	public boolean remover(long id) throws Exception;

	public List<CursoVO> selecionarTodos() throws Exception;

	public CursoVO selecionarPeloId(long id) throws Exception;

	public CursoVO selecionarPeloCodigo(String codigo) throws Exception;

}
