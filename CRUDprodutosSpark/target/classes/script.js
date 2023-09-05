let headersList = {
    "User-Agent": "http://127.0.0.1:5500/"
   }
   
fetch("http://localhost:6789/planta", { 
     method: "GET",
     headers: headersList
   }).then((Response)=>{
    return Response.text()
   }).then((data)=>{
    let parser = new DOMParser();
    let xml = parser.parseFromString(data, "application/xml");
    converterXML(xml);
    addHtml();
   });
   const plantas = [];
   function converterXML(xml){
    let produtos = xml.getElementsByTagName("produto");
    for(i = 0; i < produtos.length; i++){
        let index = produtos[i];
        let id = parseInt(index.querySelector("id").textContent);
        let nome = (index.querySelector("nome").textContent);
        let descricao = (index.querySelector("descricao").textContent);
        let preco = parseFloat(index.querySelector("preco").textContent);
        let idade = parseInt(index.querySelector("idade").textContent);
        plantas.push({"id": id, "nome" : nome, "descricao":descricao, "preco":preco,"idade": idade})
    }
   }
   function addHtml(){
    for(i = 0; i < plantas.length; i++){
        let index = plantas[i];
        let cards =                 
    '<div class="col-12">'+
        '<a href="#">'+
            '<div id="produtos" class="card" style="width: 100%; height: auto;">'+
                '<div class="row g-0">'+
                '<div class="col-md-6">'+
                    '<div class="card-body">'+
                    '<h5 class="card-title">'+index.nome+'</h5>'+
                    '<p class="card-text">Idade: '+index.idade +' anos<br>'+index.descricao+'</p>'+
                    '<p class="card-text"><small class="text-body-secondary ">R$'+index.preco+'</small></p>'+
                    '<button class="me-5"><a href="http://127.0.0.1:5500/crud.html?id='+index.id+'">Editar</a></button>'+
                    '<button><a href="http://localhost:6789/planta/delete/'+index.id+'">Deletar</a></button>'+
                    '</div>'+
                '</div>'+
                '</div>'+
            '</div>'+
        '</a>'+
    '</div>'
    document.getElementById("aqui").innerHTML += cards;
    }
    
   }