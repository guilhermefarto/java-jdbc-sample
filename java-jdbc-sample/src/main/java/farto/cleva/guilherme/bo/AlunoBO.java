package farto.cleva.guilherme.bo;

import java.sql.Connection;
import java.util.List;
import farto.cleva.guilherme.bo.interfaces.IAlunoBO;
import farto.cleva.guilherme.dao.AlunoDAO;
import farto.cleva.guilherme.dao.CursoDAO;
import farto.cleva.guilherme.dao.interfaces.IAlunoDAO;
import farto.cleva.guilherme.dao.interfaces.ICursoDAO;
import farto.cleva.guilherme.framework.db.DatabaseConnectionFactory;
import farto.cleva.guilherme.vo.AbstractVO;
import farto.cleva.guilherme.vo.AlunoVO;
import farto.cleva.guilherme.vo.CursoVO;

public class AlunoBO implements IAlunoBO {

	private IAlunoDAO alunoDAO = new AlunoDAO();
	private ICursoDAO cursoDAO = new CursoDAO();
	// private ICursoBO cursoBO = new CursoBO();

	@Override
	public boolean inserir(AlunoVO aluno) throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			aluno.setId(AbstractVO.generateGUID());

			return alunoDAO.inserir(conn, aluno);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	public boolean alterar(AlunoVO aluno) throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			return alunoDAO.alterar(conn, aluno);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	public boolean remover(long id) throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			return alunoDAO.remover(conn, id);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	public List<AlunoVO> selecionarTodos() throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			List<AlunoVO> alunos = alunoDAO.selecionarTodos(conn);

			if (alunos != null && !alunos.isEmpty()) {
				for (AlunoVO aluno : alunos) {
					long idCurso = aluno.getCurso().getId();
					aluno.setCurso(cursoDAO.selecionarPeloId(conn, idCurso));
				}
			}

			return alunos;
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	public AlunoVO selecionarPeloId(long id) throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			AlunoVO aluno = alunoDAO.selecionarPeloId(conn, id);

			if (aluno != null) {
				long idCurso = aluno.getCurso().getId();
				aluno.setCurso(cursoDAO.selecionarPeloId(conn, idCurso));
			}

			return aluno;
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	public AlunoVO selecionarPeloRa(String ra) throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			AlunoVO aluno = alunoDAO.selecionarPeloRa(conn, ra);

			if (aluno != null) {
				long idCurso = aluno.getCurso().getId();
				aluno.setCurso(cursoDAO.selecionarPeloId(conn, idCurso));
			}

			return aluno;
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	public List<AlunoVO> selecionarPeloCurso(CursoVO curso) throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			List<AlunoVO> alunos = alunoDAO.selecionarPeloCurso(conn, curso);

			curso = cursoDAO.selecionarPeloId(conn, curso.getId());

			if (alunos != null && !alunos.isEmpty()) {
				for (AlunoVO aluno : alunos) {
					aluno.setCurso(curso);
				}
			}

			return alunos;
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

}
