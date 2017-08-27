package farto.cleva.guilherme.dao.interfaces;

import java.sql.Connection;
import java.util.List;
import farto.cleva.guilherme.vo.AlunoVO;
import farto.cleva.guilherme.vo.CursoVO;

public interface IAlunoDAO {

	public boolean inserir(Connection conn, AlunoVO aluno) throws Exception;

	public boolean alterar(Connection conn, AlunoVO aluno) throws Exception;

	public boolean remover(Connection conn, long id) throws Exception;

	public List<AlunoVO> selecionarTodos(Connection conn) throws Exception;

	public AlunoVO selecionarPeloId(Connection conn, long id) throws Exception;

	public AlunoVO selecionarPeloRa(Connection conn, String ra) throws Exception;

	public List<AlunoVO> selecionarPeloCurso(Connection conn, CursoVO curso) throws Exception;

}
