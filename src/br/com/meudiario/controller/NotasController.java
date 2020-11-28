package br.com.meudiario.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.meudiario.beans.Nota;
import br.com.meudiario.model.NotasModel;


@WebServlet("/notas")
public class NotasController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
		case "list":
			listAction(request,response);
			break;
		case "cad":
			cadAction(request,response);
			break;
		case "edit":
			editAction(request,response);
			break;
		case "del":
			delAction(request,response);
			break;

		default:
			notFoundAction(request,response);
			break;
		}
	}


	private void delAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (request.getParameter("id") != null)? Integer.parseInt(request.getParameter("id")) : 0;
		int retorno;
		String textoResponse = "Ocorreu um Erro ao Salvar a Nota";
		
		if(id > 0) {
			retorno = NotasModel.delNota(id);
			if(retorno > 0) {
				textoResponse = "Nota Apagada com Sucesso!";
			}
		}
		
		response.getWriter().println(textoResponse);
	}


	private void editAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (request.getParameter("id") != null)? Integer.parseInt(request.getParameter("id")) : 0;
		Nota nota = new Nota();
		
		if(id > 0) {
			nota = NotasModel.getNotaById(id);
		}
		
		request.setAttribute("nota", nota);
		RequestDispatcher rd = request.getRequestDispatcher("editNotas.jsp");
		rd.forward(request, response);
		
	}


	private void cadAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		RequestDispatcher rd = request.getRequestDispatcher("cadNotas.jsp");
		rd.forward(request, response);
	}


	private void listAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Nota> listaNotas = NotasModel.getListNotas();
		
		request.setAttribute("listaNotas", listaNotas);
		request.setAttribute("listSize", listaNotas.size());
		RequestDispatcher rd = request.getRequestDispatcher("listNotas.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		String assunto = request.getParameter("assunto");
		String dtNota = request.getParameter("dt_nota");
		String texto = request.getParameter("texto");
		int id = (request.getParameter("id") != null)? Integer.parseInt(request.getParameter("id")) : 0;
		int retorno = 0;
		
		try {

			if(id > 0) {
				retorno = NotasModel.editNota(id, assunto, dtNota, texto);
			} else {
				retorno = NotasModel.cadNota(assunto, dtNota, texto);
			}
			
			if(retorno > 1) {
				rd = request.getRequestDispatcher("pageSuccess.jsp");
			} else {
				rd = request.getRequestDispatcher("pageError.jsp");
			}
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	private void notFoundAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>Página Não Encontrada</h1>");
		out.println("</html></body>");		
	}

}
