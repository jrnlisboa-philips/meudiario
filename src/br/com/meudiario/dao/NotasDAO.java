package br.com.meudiario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.meudiario.beans.Nota;
import br.com.meudiario.cnn.ConnectionFactory;

public class NotasDAO {

	public static List<Nota> getListNotas() {
		List<Nota> listaRetorno = new ArrayList<Nota>();
		Connection cnn = ConnectionFactory.getConnection();
		String query = "SELECT * FROM notas";
		
		try {
			PreparedStatement pStm = cnn.prepareStatement(query);
			ResultSet rsNotas = pStm.executeQuery();
			while(rsNotas.next()) {
				Nota nt = new Nota(rsNotas.getInt("id"),rsNotas.getString("assunto"),rsNotas.getString("dt_nota"),rsNotas.getString("texto"));
				listaRetorno.add(nt);
			}
			rsNotas.close();
			cnn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaRetorno;
	}

	public static int cadNota(Nota nt) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int editNota(Nota nt) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static Nota getNotaById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int delNota(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
