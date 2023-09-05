const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get("id");
let headersList = {
    "User-Agent": "http://127.0.0.1:5500/"
   }


function changeValues(xml){
    let produto = (xml.getElementsByTagName("produto"))[0];
    console.log(produto);
    $("#name").val(produto.querySelector("nome").textContent);
    $("#desc").val(produto.querySelector("descricao").textContent);
    $("#age").val(parseInt(produto.querySelector("idade").textContent));
    $("#price").val(parseFloat(produto.querySelector("preco").textContent));

}

$(document).ready(()=>{
    if(id != null){
        fetch("http://localhost:6789/planta/"+id, { 
             method: "GET",
             headers: headersList
           }).then((Response)=>{
            return Response.text()
           }).then((data)=>{
            let parser = new DOMParser();
            let xml = parser.parseFromString(data, "application/xml");
            changeValues(xml)
            $("#formula").attr("action","http://localhost:6789/planta/update/"+id);
            $("#formula").attr("method","GET"+id);
           });
    }
    $("#postaplanta").click( (event)=>{
        
        if(document.getElementById("formula").checkValidity()){
            console.log("jerson");
            $("#formula").trigger( "submit" );
        }
    
        $("#formula").addClass('was-validated')
    })
})

