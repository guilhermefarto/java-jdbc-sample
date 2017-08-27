package farto.cleva.guilherme.bo;

import java.sql.Connection;
import java.util.List;
import farto.cleva.guilherme.bo.interfaces.ICursoBO;
import farto.cleva.guilherme.dao.CursoDAO;
import farto.cleva.guilherme.dao.interfaces.ICursoDAO;
import farto.cleva.guilherme.framework.db.DatabaseConnectionFactory;
import farto.cleva.guilherme.vo.AbstractVO;
import farto.cleva.guilherme.vo.CursoVO;

public class CursoBO implements ICursoBO {

	private ICursoDAO cursoDAO = new CursoDAO();

	@Override
	public boolean inserir(CursoVO curso) throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			// GUID = Global Unique Identifier
			curso.setId(AbstractVO.generateGUID());

			return cursoDAO.inserir(conn, curso);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	public boolean alterar(CursoVO curso) throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			return cursoDAO.alterar(conn, curso);
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
			return cursoDAO.remover(conn, id);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	public List<CursoVO> selecionarTodos() throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			return cursoDAO.selecionarTodos(conn);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	public CursoVO selecionarPeloId(long id) throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			return cursoDAO.selecionarPeloId(conn, id);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	public CursoVO selecionarPeloCodigo(String codigo) throws Exception {
		Connection conn = DatabaseConnectionFactory.getInstance().getConnection();

		try {
			return cursoDAO.selecionarPeloCodigo(conn, codigo);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

}
