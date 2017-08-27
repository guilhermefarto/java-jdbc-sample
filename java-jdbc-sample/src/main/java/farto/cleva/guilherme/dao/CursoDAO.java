package farto.cleva.guilherme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import farto.cleva.guilherme.bo.interfaces.ICursoBO;
import farto.cleva.guilherme.dao.interfaces.ICursoDAO;
import farto.cleva.guilherme.services.ServiceFactory;
import farto.cleva.guilherme.vo.CursoVO;

public class CursoDAO implements ICursoDAO {

	@Override
	public boolean inserir(Connection conn, CursoVO curso) throws Exception {

		// Objeto responsável pela criação da instrução SQL a ser enviada ao banco de dados
		StringBuilder sql = new StringBuilder("");

		sql.append(" INSERT INTO CURSOS ");
		sql.append(" (ID_CURSO, CODIGO, DESCRICAO, SIGLA, QTDE_ANOS) ");
		sql.append(" VALUES (?, ?, ?, ?, ?) ");

		// Objeto responsável pela preparação da instrução SQL (montagem e concatenação de valores/binds)
		PreparedStatement stmt = null;

		try {
			// Preparação da instrução SQL (TYPE_FORWARD_ONLY = execução sequencial/para frente e CONCUR_READ_ONLY = modo de leitura)
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			int index = 0;

			// Atribuição de valores nos marcadores/bind (? da SQL)
			stmt.setLong(++index, curso.getId());
			stmt.setString(++index, curso.getCodigo());
			stmt.setString(++index, curso.getDescricao());
			stmt.setString(++index, curso.getSigla());
			stmt.setInt(++index, curso.getQuantidadeAnos());

			// Execução da instrução SQL (retorno da quantidade de linhas modificadas)
			int rows = stmt.executeUpdate();

			return rows > 0;
		} catch (Exception e) {
			throw e;
		} finally {
			stmt.close();
		}
	}

	@Override
	public boolean alterar(Connection conn, CursoVO curso) throws Exception {

		// Objeto responsável pela criação da instrução SQL a ser enviada ao banco de dados
		StringBuilder sql = new StringBuilder("");

		sql.append(" UPDATE CURSOS ");
		sql.append(" SET CODIGO = ?, DESCRICAO = ?, SIGLA = ?, QTDE_ANOS = ? ");
		sql.append(" WHERE ID_CURSO = ? ");

		// Objeto responsável pela preparação da instrução SQL (montagem e concatenação de valores/binds)
		PreparedStatement stmt = null;

		try {
			// Preparação da instrução SQL (TYPE_FORWARD_ONLY = execução sequencial/para frente e CONCUR_READ_ONLY = modo de leitura)
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			int index = 0;

			// Atribuição de valores nos marcadores/bind (? da SQL)
			stmt.setString(++index, curso.getCodigo());
			stmt.setString(++index, curso.getDescricao());
			stmt.setString(++index, curso.getSigla());
			stmt.setInt(++index, curso.getQuantidadeAnos());
			stmt.setLong(++index, curso.getId());

			// Execução da instrução SQL (retorno da quantidade de linhas modificadas)
			int rows = stmt.executeUpdate();

			return rows > 0;
		} catch (Exception e) {
			throw e;
		} finally {
			stmt.close();
		}
	}

	@Override
	public boolean remover(Connection conn, long id) throws Exception {

		// Objeto responsável pela criação da instrução SQL a ser enviada ao banco de dados
		StringBuilder sql = new StringBuilder("");

		sql.append(" DELETE FROM CURSOS ");
		sql.append(" WHERE ID_CURSO = ? ");

		// Objeto responsável pela preparação da instrução SQL (montagem e concatenação de valores/binds)
		PreparedStatement stmt = null;

		try {
			// Preparação da instrução SQL (TYPE_FORWARD_ONLY = execução sequencial/para frente e CONCUR_READ_ONLY = modo de leitura)
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			int index = 0;

			// Atribuição de valores nos marcadores/bind (? da SQL)
			stmt.setLong(++index, id);

			// Execução da instrução SQL (retorno da quantidade de linhas modificadas)
			int rows = stmt.executeUpdate();

			return rows > 0;
		} catch (Exception e) {
			throw e;
		} finally {
			stmt.close();
		}
	}

	@Override
	public List<CursoVO> selecionarTodos(Connection conn) throws Exception {
		List<CursoVO> cursos = new LinkedList<>();

		// Objeto responsável pela criação da instrução SQL a ser enviada ao banco de dados
		StringBuilder sql = new StringBuilder("");

		sql.append(" SELECT ");
		sql.append(" ID_CURSO, ");
		sql.append(" CODIGO, ");
		sql.append(" DESCRICAO, ");
		sql.append(" SIGLA, ");
		sql.append(" QTDE_ANOS ");
		sql.append(" FROM CURSOS ");
		sql.append(" ORDER BY CODIGO ");

		ResultSet rs = null;

		// Objeto responsável pela preparação da instrução SQL (montagem e concatenação de valores/binds)
		PreparedStatement stmt = null;

		try {
			// Preparação da instrução SQL (TYPE_FORWARD_ONLY = execução sequencial/para frente e CONCUR_READ_ONLY = modo de leitura)
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			// Execução da instrução SQL (retorno de uma coleção de registros do banco de dados)
			rs = stmt.executeQuery();

			while (rs.next()) {
				CursoVO curso = new CursoVO();

				// Atribuição de valores nos marcadores/bind (? da SQL)
				curso.setId(rs.getLong("ID_CURSO"));
				curso.setCodigo(rs.getString("CODIGO"));
				curso.setDescricao(rs.getString("DESCRICAO"));
				curso.setSigla(rs.getString("SIGLA"));
				curso.setQuantidadeAnos(rs.getInt("QTDE_ANOS"));

				cursos.add(curso);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			rs.close();
			stmt.close();
		}

		return cursos;
	}

	@Override
	public CursoVO selecionarPeloId(Connection conn, long id) throws Exception {
		CursoVO curso = null;

		// Objeto responsável pela criação da instrução SQL a ser enviada ao banco de dados
		StringBuilder sql = new StringBuilder("");

		sql.append(" SELECT ");
		sql.append(" ID_CURSO, ");
		sql.append(" CODIGO, ");
		sql.append(" DESCRICAO, ");
		sql.append(" SIGLA, ");
		sql.append(" QTDE_ANOS ");
		sql.append(" FROM CURSOS ");
		sql.append(" WHERE ID_CURSO = ? ");

		ResultSet rs = null;

		// Objeto responsável pela preparação da instrução SQL (montagem e concatenação de valores/binds)
		PreparedStatement stmt = null;

		try {
			// Preparação da instrução SQL (TYPE_FORWARD_ONLY = execução sequencial/para frente e CONCUR_READ_ONLY = modo de leitura)
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			int index = 0;

			// Atribuição de valores nos marcadores/bind (? da SQL)
			stmt.setLong(++index, id);

			// Execução da instrução SQL (retorno de uma coleção de registros do banco de dados)
			rs = stmt.executeQuery();

			if (rs.next()) {
				curso = new CursoVO();

				// Atribuição de valores nos marcadores/bind (? da SQL)
				curso.setId(rs.getLong("ID_CURSO"));
				curso.setCodigo(rs.getString("CODIGO"));
				curso.setDescricao(rs.getString("DESCRICAO"));
				curso.setSigla(rs.getString("SIGLA"));
				curso.setQuantidadeAnos(rs.getInt("QTDE_ANOS"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			rs.close();
			stmt.close();
		}

		return curso;
	}

	@Override
	public CursoVO selecionarPeloCodigo(Connection conn, String codigo) throws Exception {
		CursoVO curso = null;

		// Objeto responsável pela criação da instrução SQL a ser enviada ao banco de dados
		StringBuilder sql = new StringBuilder("");

		sql.append(" SELECT ");
		sql.append(" ID_CURSO, ");
		sql.append(" CODIGO, ");
		sql.append(" DESCRICAO, ");
		sql.append(" SIGLA, ");
		sql.append(" QTDE_ANOS ");
		sql.append(" FROM CURSOS ");
		sql.append(" WHERE CODIGO = ? ");

		ResultSet rs = null;

		// Objeto responsável pela preparação da instrução SQL (montagem e concatenação de valores/binds)
		PreparedStatement stmt = null;

		try {
			// Preparação da instrução SQL (TYPE_FORWARD_ONLY = execução sequencial/para frente e CONCUR_READ_ONLY = modo de leitura)
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			int index = 0;

			// Atribuição de valores nos marcadores/bind (? da SQL)
			stmt.setString(++index, codigo);

			// Execução da instrução SQL (retorno de uma coleção de registros do banco de dados)
			rs = stmt.executeQuery();

			if (rs.next()) {
				curso = new CursoVO();

				// Atribuição de valores nos marcadores/bind (? da SQL)
				curso.setId(rs.getLong("ID_CURSO"));
				curso.setCodigo(rs.getString("CODIGO"));
				curso.setDescricao(rs.getString("DESCRICAO"));
				curso.setSigla(rs.getString("SIGLA"));
				curso.setQuantidadeAnos(rs.getInt("QTDE_ANOS"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			rs.close();
			stmt.close();
		}

		return curso;
	}

	public static void main(String[] args) {
		try {
			ICursoBO cursoBO = ServiceFactory.getInstance().getCursoBO();

			// CursoDAO dao = new CursoDAO();

			// Connection conn =
			// DatabaseConnectionFactory.getInstance().getConnection();

			CursoVO curso = new CursoVO(1L);
			// curso.setId(AbstractVO.generateGUID());
			curso.setCodigo("BCC.2016");
			curso.setDescricao("Bacharelado em Ciência da Computação");
			curso.setSigla("BCC");
			curso.setQuantidadeAnos(4);

			cursoBO.inserir(curso);

			curso = new CursoVO(2L);
			// curso.setId(AbstractVO.generateGUID());
			curso.setCodigo("ADS.2016");
			curso.setDescricao("Análise e Desenv. de Sistemas");
			curso.setSigla("ADS");
			curso.setQuantidadeAnos(3);

			cursoBO.inserir(curso);

			curso = new CursoVO(3L);
			// curso.setId(AbstractVO.generateGUID());
			curso.setCodigo("MAT.2016");
			curso.setDescricao("Matemática");
			curso.setSigla("MAT");
			curso.setQuantidadeAnos(5);

			cursoBO.inserir(curso);

			// dao.inserir(conn, curso);
			// dao.alterar(conn, curso);
			// dao.remover(conn, curso.getId());

			curso = new CursoVO(4L);
			// curso.setId(AbstractVO.generateGUID());
			curso.setCodigo("DIR.2016");
			curso.setDescricao("Direito");
			curso.setSigla("DIR");
			curso.setQuantidadeAnos(5);

			cursoBO.inserir(curso);

			for (CursoVO cursoAux : cursoBO.selecionarTodos()) {
				System.out.println(cursoAux.getCodigo() + " " + cursoAux.getDescricao());
			}

			// conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
