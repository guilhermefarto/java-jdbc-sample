package farto.cleva.guilherme.dao.interfaces;

import java.sql.Connection;
import java.util.List;
import farto.cleva.guilherme.vo.CursoVO;

public interface ICursoDAO {

	public boolean inserir(Connection conn, CursoVO curso) throws Exception;

	public boolean alterar(Connection conn, CursoVO curso) throws Exception;

	public boolean remover(Connection conn, long id) throws Exception;

	public List<CursoVO> selecionarTodos(Connection conn) throws Exception;

	public CursoVO selecionarPeloId(Connection conn, long id) throws Exception;

	public CursoVO selecionarPeloCodigo(Connection conn, String codigo) throws Exception;

}
