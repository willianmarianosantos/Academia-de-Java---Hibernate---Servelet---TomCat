package projeto1.projeto1;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projeto1.projeto1.DAO.LivrosDAO;

import java.io.IOException;

@WebServlet("/editarLivro")
public class AtualizarLivroServlet extends HttpServlet {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Postgres");




    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int livroId = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String categoria = request.getParameter("categoria");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        LivrosDAO livrosDAO = new LivrosDAO(entityManager);

        Livros livroAtualizado = new Livros();
        livroAtualizado.setId(livroId);
        livroAtualizado.setNome(nome);
        livroAtualizado.setCategoria(categoria);
        livroAtualizado.setQUANTIDADE(quantidade);

        livrosDAO.atualizarPorid(livroId, livroAtualizado);

        entityManager.close();

        response.sendRedirect("exibirLivros");
    }



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int livroId = Integer.parseInt(request.getParameter("id"));

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        LivrosDAO livroDAO = new LivrosDAO(entityManager);

        Livros livro =  livroDAO.buscaPorId(livroId);

        entityManager.close();

        request.setAttribute("livro", livro);

      request.getRequestDispatcher("editarLivro.jsp").forward(request, response);

    }



}
