        
function checkAll(input) {
     var checkboxes = document.getElementsByTagName('input');
     if (input.checked) {
         for (var i = 0; i < checkboxes.length; i++) {
             if (checkboxes[i].type == 'checkbox') {
                 checkboxes[i].checked = 1;
             }
         }
     } else {
         for (var i = 0; i < checkboxes.length; i++) {
             console.log(i)
             if (checkboxes[i].type == 'checkbox') {
                 checkboxes[i].checked = 0;
             }
         }
     }
 }
        
function borrar() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/borrarpresupuesto/"+lista[i].id
			}
	}
}

function borrarfacturacion() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/borrarfacturacion/"+lista[i].id
			}
	}
}

function borraractividad() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/borraractividad/"+lista[i].id
			}
	}
}

function borrarcomercial() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/borrarcomercial/"+lista[i].id
			}
	}
}

function borrarestado() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/borrarestado/"+lista[i].id
			}
	}
}

function borrarestadopago() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/borrarestadopago/"+lista[i].id
			}
	}
}

function borrarcliente(clicked) {
		console.log("Hola2")
		console.log("Hola");
		console.log("Ha llegado aqui"+clicked);
		window.location.href = "http://localhost:8080/borrarcliente/"+clicked
			
			
}


