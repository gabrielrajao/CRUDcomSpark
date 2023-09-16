package app;
import spark.*;

import static spark.Spark.staticFiles;

import service.PlantaService;


public class Aplicacao {
	
	private static PlantaService produtoService = new PlantaService();
	
    public static void main(String[] args) {
    	Spark.port(6789);
        
    	staticFiles.location("/public");

        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
        });
    	Spark.post("/planta", (request, response) -> {
            // Check the request size
            long contentLength = request.contentLength();
            if (contentLength > (0.2 * 1024 * 1024)) {
                response.status(400); // Bad Request
                return "Request size exceeds the maximum allowed size.";
            }

            produtoService.add(request, response);
            response.redirect("http://127.0.0.1:5500/index.html");
            return null;
        });
        Spark.get("/planta/:id", (request, response) -> produtoService.get(request, response));

        Spark.get("/planta/update/:id", (request, response) -> {
        	produtoService.update(request, response);
        	response.redirect("http://127.0.0.1:5500/index.html");
            return null;
        	});

        Spark.get("/planta/delete/:id", (request, response) -> {produtoService.remove(request, response);
        response.redirect("http://127.0.0.1:5500/index.html");
        return null;
        });

        Spark.get("/planta", (request, response) -> produtoService.getAll(request, response));
               
    }
}
