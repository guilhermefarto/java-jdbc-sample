package farto.cleva.guilherme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import farto.cleva.guilherme.bo.interfaces.IAlunoBO;
import farto.cleva.guilherme.bo.interfaces.ICursoBO;
import farto.cleva.guilherme.dao.interfaces.IAlunoDAO;
import farto.cleva.guilherme.services.ServiceFactory;
import farto.cleva.guilherme.utils.DateUtil;
import farto.cleva.guilherme.vo.AlunoVO;
import farto.cleva.guilherme.vo.CursoVO;

public class AlunoDAO implements IAlunoDAO {

	@Override
	public boolean inserir(Connection conn, AlunoVO aluno) throws Exception {
		StringBuilder sql = new StringBuilder("");

		sql.append(" INSERT INTO ALUNOS ");
		sql.append(" (ID_ALUNO, RA, NOME, SEXO, DT_NASC, EMAIL, ID_CURSO, FG_ATIVO) VALUES ");
		sql.append(" (?, ?, ?, ?, ?, ?, ?, ?) ");

		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			int index = 0;

			stmt.setLong(++index, aluno.getId());
			stmt.setString(++index, aluno.getRa());
			stmt.setString(++index, aluno.getNome());
			stmt.setString(++index, aluno.getSexo());
			stmt.setDate(++index, new java.sql.Date(aluno.getDataNascimento().getTime()));
			stmt.setString(++index, aluno.getEmail());
			stmt.setLong(++index, aluno.getCurso().getId());
			stmt.setString(++index, aluno.isFgAtivo() ? "S" : "N");

			int rows = stmt.executeUpdate();

			return rows > 0;
		} catch (Exception e) {
			throw e;
		} finally {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
				stmt = null;
			}
		}
	}

	@Override
	public boolean alterar(Connection conn, AlunoVO aluno) throws Exception {
		StringBuilder sql = new StringBuilder("");

		sql.append(" UPDATE ALUNOS ");
		sql.append(" SET RA = ?, NOME = ?, SEXO = ?, DT_NASC = ?, EMAIL = ?, ID_CURSO = ?, FG_ATIVO = ? ");
		sql.append(" WHERE ID_ALUNO = ? ");

		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			int index = 0;

			stmt.setString(++index, aluno.getRa());
			stmt.setString(++index, aluno.getNome());
			stmt.setString(++index, aluno.getSexo());
			stmt.setDate(++index, new java.sql.Date(aluno.getDataNascimento().getTime()));
			stmt.setString(++index, aluno.getEmail());
			stmt.setLong(++index, aluno.getCurso().getId());
			stmt.setString(++index, aluno.isFgAtivo() ? "S" : "N");
			stmt.setLong(++index, aluno.getId());

			int rows = stmt.executeUpdate();

			return rows > 0;
		} catch (Exception e) {
			throw e;
		} finally {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
				stmt = null;
			}
		}
	}

	@Override
	public boolean remover(Connection conn, long id) throws Exception {
		StringBuilder sql = new StringBuilder("");

		sql.append(" DELETE FROM ALUNOS ");
		sql.append(" WHERE ID_ALUNO = ? ");

		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			int index = 0;

			stmt.setLong(++index, id);

			int rows = stmt.executeUpdate();

			return rows > 0;
		} catch (Exception e) {
			throw e;
		} finally {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
				stmt = null;
			}
		}
	}

	@Override
	public List<AlunoVO> selecionarTodos(Connection conn) throws Exception {
		List<AlunoVO> alunos = new LinkedList<AlunoVO>();

		StringBuilder sql = new StringBuilder("");

		sql.append(" SELECT ID_ALUNO, RA, NOME, SEXO, DT_NASC, EMAIL, ID_CURSO, FG_ATIVO ");
		sql.append(" FROM ALUNOS ");
		sql.append(" ORDER BY NOME ");

		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			rs = stmt.executeQuery();

			while (rs.next()) {
				AlunoVO aluno = new AlunoVO();

				aluno.setId(rs.getLong("ID_ALUNO"));
				aluno.setRa(rs.getString("RA"));
				aluno.setNome(rs.getString("NOME"));
				aluno.setSexo(rs.getString("SEXO"));
				aluno.setDataNascimento(new Date(rs.getDate("DT_NASC").getTime()));
				aluno.setEmail(rs.getString("EMAIL"));
				aluno.setCurso(new CursoVO(rs.getLong("ID_CURSO")));
				aluno.setFgAtivo(rs.getString("FG_ATIVO").equals("S"));

				alunos.add(aluno);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
				rs = null;
			}

			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
				stmt = null;
			}
		}

		return alunos;
	}

	@Override
	public AlunoVO selecionarPeloId(Connection conn, long id) throws Exception {
		AlunoVO aluno = null;

		StringBuilder sql = new StringBuilder("");

		sql.append(" SELECT ID_ALUNO, RA, NOME, SEXO, DT_NASC, EMAIL, ID_CURSO, FG_ATIVO ");
		sql.append(" FROM ALUNOS ");
		sql.append(" WHERE ID_ALUNO = ? ");

		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			int index = 0;

			stmt.setLong(++index, index);

			rs = stmt.executeQuery();

			if (rs.next()) {
				aluno = new AlunoVO();

				aluno.setId(rs.getLong("ID_ALUNO"));
				aluno.setRa(rs.getString("RA"));
				aluno.setNome(rs.getString("NOME"));
				aluno.setSexo(rs.getString("SEXO"));
				aluno.setDataNascimento(new Date(rs.getDate("DT_NASC").getTime()));
				aluno.setEmail(rs.getString("EMAIL"));
				aluno.setCurso(new CursoVO(rs.getLong("ID_CURSO")));
				aluno.setFgAtivo(rs.getString("FG_ATIVO").equals("S"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
				rs = null;
			}

			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
				stmt = null;
			}
		}

		return aluno;
	}

	@Override
	public AlunoVO selecionarPeloRa(Connection conn, String ra) throws Exception {
		AlunoVO aluno = null;

		StringBuilder sql = new StringBuilder("");

		sql.append(" SELECT ID_ALUNO, RA, NOME, SEXO, DT_NASC, EMAIL, ID_CURSO, FG_ATIVO ");
		sql.append(" FROM ALUNOS ");
		sql.append(" WHERE RA = ? ");

		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			int index = 0;

			stmt.setString(++index, ra);

			rs = stmt.executeQuery();

			if (rs.next()) {
				aluno = new AlunoVO();

				aluno.setId(rs.getLong("ID_ALUNO"));
				aluno.setRa(rs.getString("RA"));
				aluno.setNome(rs.getString("NOME"));
				aluno.setSexo(rs.getString("SEXO"));
				aluno.setDataNascimento(new Date(rs.getDate("DT_NASC").getTime()));
				aluno.setEmail(rs.getString("EMAIL"));
				aluno.setCurso(new CursoVO(rs.getLong("ID_CURSO")));
				aluno.setFgAtivo(rs.getString("FG_ATIVO").equals("S"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
				rs = null;
			}

			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
				stmt = null;
			}
		}

		return aluno;
	}

	@Override
	public List<AlunoVO> selecionarPeloCurso(Connection conn, CursoVO curso) throws Exception {
		List<AlunoVO> alunos = new LinkedList<AlunoVO>();

		StringBuilder sql = new StringBuilder("");

		sql.append(" SELECT ID_ALUNO, RA, NOME, SEXO, DT_NASC, EMAIL, ID_CURSO, FG_ATIVO ");
		sql.append(" FROM ALUNOS ");
		sql.append(" WHERE ID_CURSO = ? ");
		sql.append(" ORDER BY NOME ");

		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			int index = 0;

			stmt.setLong(++index, curso.getId());

			rs = stmt.executeQuery();

			while (rs.next()) {
				AlunoVO aluno = new AlunoVO();

				aluno.setId(rs.getLong("ID_ALUNO"));
				aluno.setRa(rs.getString("RA"));
				aluno.setNome(rs.getString("NOME"));
				aluno.setSexo(rs.getString("SEXO"));
				aluno.setDataNascimento(new Date(rs.getDate("DT_NASC").getTime()));
				aluno.setEmail(rs.getString("EMAIL"));
				aluno.setCurso(new CursoVO(rs.getLong("ID_CURSO")));
				aluno.setFgAtivo(rs.getString("FG_ATIVO").equals("S"));

				alunos.add(aluno);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null && !rs.isClosed()) {
				rs.close();
				rs = null;
			}

			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
				stmt = null;
			}
		}

		return alunos;
	}

	public static void main(String[] args) {
		try {
			IAlunoBO alunoBO = ServiceFactory.getInstance().getAlunoBO();
			ICursoBO cursoBO = ServiceFactory.getInstance().getCursoBO();

			// AlunoDAO dao = new AlunoDAO();
			// CursoDAO cursoDao = new CursoDAO();

			// Connection conn =
			// DatabaseConnectionFactory.getInstance().getConnection();

			AlunoVO aluno = new AlunoVO(1L);
			// aluno.setId(AbstractVO.generateGUID());
			aluno.setRa("1234500001");
			aluno.setNome("Guilherme de Cleva Farto");
			aluno.setSexo("M");
			aluno.setDataNascimento(DateUtil.parse("02/03/1989"));
			aluno.setEmail("guilherme.farto@fema.edu.br");
			aluno.setCurso(cursoBO.selecionarPeloCodigo("BCC.2016"));
			aluno.setFgAtivo(true);

			aluno = new AlunoVO(2L);
			// aluno.setId(AbstractVO.generateGUID());
			aluno.setRa("1234500002");
			aluno.setNome("Tamires Alves da Silva");
			aluno.setSexo("F");
			aluno.setDataNascimento(DateUtil.parse("10/04/1990"));
			aluno.setEmail("tamires.silva@fema.edu.br");
			aluno.setCurso(cursoBO.selecionarPeloCodigo("ADS.2016"));
			aluno.setFgAtivo(false);

			// dao.inserir(conn, aluno);
			// dao.alterar(conn, aluno);
			// dao.remover(conn, aluno.getId());

			// alunoBO.inserir(aluno);

			for (AlunoVO alunoAux : alunoBO.selecionarTodos()) {
				System.out.println(alunoAux.getId() + " - " + alunoAux.getNome() + " - " + alunoAux.getCurso().getDescricao());
			}

			// conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
