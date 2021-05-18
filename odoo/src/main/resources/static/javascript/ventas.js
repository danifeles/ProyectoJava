        
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
	window.location.href = "http://localhost:8080/borrarcliente/"+clicked
			
			
}

function cambiartipo() {
	var lista1 = document.getElementById("lista");
	
	var cambiar = document.getElementById("cambiartipo1");
	if(lista1.value == 2) {
	cambiar.type = "text";
	} else 
	{
	cambiar.type = "number";
	}
	}

function aparecerbasura(x) {
	debugger;
	var basura1 = document.getElementById("basura");
	var lista = document.getElementsByTagName("input");
	var cont = 0;
	if(x.checked == true){
	basura1.style.visibility = "visible";
	} else if(x.checked == false){
	for (i=0; i<lista.length; i++) 
	{
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		cont ++;
	} 
	}
	if(cont == 0) {
		basura1.style.visibility = "hidden";
	}
	else {
		basura1.style.visibility = "visible";
	}
	} 
	
	var lapiz1 = document.getElementById("lapiz");
	debugger;
	cont = 0;
	for (i=0; i<lista.length; i++) 
	{
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		++ cont;
	} 
	}
	if(cont == 1) {
		lapiz1.style.visibility = "visible";
	}
	else {
		lapiz1.style.visibility = "hidden";
		}
}



function editarventas() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/FormVentasEditar/"+lista[i].id
			}
	}
}

function editaractividad() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/FormActividadEditar/"+lista[i].id
			}
	}
}

function editarcomercial() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/FormComercialEditar/"+lista[i].id
			}
	}
}

function editarestado() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/FormEstadoEditar/"+lista[i].id
			}
	}
}

function editarestadopago() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/FormEstadoPagoEditar/"+lista[i].id
			}
	}
}

function editarfacturacion() {
	var lista = document.getElementsByTagName("input")
	for (i=0; i<lista.length; i++) 
	{
		console.log(lista[i].id);
	if(lista[i].type == 'checkbox' && lista[i].checked == true){
		window.location.href = "http://localhost:8080/FormFacturacionEditar/"+lista[i].id
			}
	}
}
