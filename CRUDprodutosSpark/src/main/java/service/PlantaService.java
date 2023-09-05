package service;

import java.io.IOException;




import dao.PlantaDAO;
import model.Planta;
import spark.Request;
import spark.Response;


public class PlantaService {


	private PlantaDAO produtoDAO;


	public PlantaService() {
		try {
			produtoDAO = new PlantaDAO("planta.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	

	public Object add(Request request, Response response) throws IOException {
		String nome = request.queryParams("nome");
		String descricao = request.queryParams("descricao");
		float preco = Float.parseFloat(request.queryParams("preco"));
		int idade = Integer.parseInt(request.queryParams("idade"));

		int id = produtoDAO.getMaxId() + 1;

		Planta produto = new Planta(id, nome, descricao, preco, idade);

		produtoDAO.add(produto);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response)  {
		int id = Integer.parseInt(request.params(":id"));
		
		Planta produto = (Planta) produtoDAO.get(id);
		
		if (produto != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");
            return "<produto>\n" + 
            		"\t<id>" + produto.getId() + "</id>\n" +
            		"\t<descricao>" + produto.getDescricao() + "</descricao>\n" +
            		"\t<preco>" + produto.getPreco() + "</preco>\n" +
            		"\t<nome>" + produto.getNome() +"</nome>\n" +
            		"\t<idade>" + produto.getIdade() +"</idade>\n" +
            		"</produto>\n";
        } else {
            response.status(404); // 404 Not found
            return "Planta " + id + " não encontrada.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Planta produto = (Planta) produtoDAO.get(id);

        if (produto != null) {
        	produto.setDescricao(request.queryParams("descricao"));
        	produto.setPreco(Float.parseFloat(request.queryParams("preco")));
        	produto.setNome(request.queryParams("nome"));
        	produto.setIdade(Integer.parseInt(request.queryParams("idade")));

        	produtoDAO.update(produto);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Planta não encontrada.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Planta produto = (Planta) produtoDAO.get(id);

        if (produto != null) {

            produtoDAO.remove(produto);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Planta não encontrada.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<produtos type=\"array\">");
		for (Planta produto : produtoDAO.getAll()) {
			returnValue.append("\n<produto>\n" + 
					"\t<id>" + produto.getId() + "</id>\n" +
            		"\t<descricao>" + produto.getDescricao() + "</descricao>\n" +
            		"\t<preco>" + produto.getPreco() + "</preco>\n" +
            		"\t<nome>" + produto.getNome() +"</nome>\n" +
            		"\t<idade>" + produto.getIdade() +"</idade>\n" +
            		"</produto>\n");
		}
		returnValue.append("</produtos>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
	
}
