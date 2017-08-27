package farto.cleva.guilherme.bo.interfaces;

import java.util.List;
import farto.cleva.guilherme.vo.AlunoVO;
import farto.cleva.guilherme.vo.CursoVO;

public interface IAlunoBO {

	public boolean inserir(AlunoVO aluno) throws Exception;

	public boolean alterar(AlunoVO aluno) throws Exception;

	public boolean remover(long id) throws Exception;

	public List<AlunoVO> selecionarTodos() throws Exception;

	public AlunoVO selecionarPeloId(long id) throws Exception;

	public AlunoVO selecionarPeloRa(String ra) throws Exception;

	public List<AlunoVO> selecionarPeloCurso(CursoVO curso) throws Exception;

}
