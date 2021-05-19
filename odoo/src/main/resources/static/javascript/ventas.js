        
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

function editarclientes(clicked) {
		window.location.href = "http://localhost:8080/FormularioEditar/"+clicked

}

function agruparpor() {
			debugger;

			window.location.href = "http://localhost:8080/clientes"
}


(function () {
    var Message;
    Message = function (arg) {
        this.text = arg.text, this.message_side = arg.message_side;
        this.draw = function (_this) {
            return function () {
                var $message;
                $message = $($('.message_template').clone().html());
                $message.addClass(_this.message_side).find('.text').html(_this.text);
                $('.messages').append($message);
                return setTimeout(function () {
                    return $message.addClass('appeared');
                }, 0);
            };
        }(this);
        return this;
    };
    
    
    $(function () {
        var getMessageText, message_side, sendMessage;
        message_side = 'right';
        getMessageText = function () {
            var $message_input;
            $message_input = $('.message_input');
            return $message_input.val();
        };
        sendMessage = function (text) {
            var $messages, message;
            if (text.trim() === '') {
                return;
            }
            $('.message_input').val('');
            $messages = $('.messages');
            message_side = message_side === 'left' ? 'right' : 'left';
            message = new Message({
                text: text,
                message_side: message_side
            });
            message.draw();
            return $messages.animate({ scrollTop: $messages.prop('scrollHeight') }, 300);
        };
        $('.send_message').click(function (e) {
            return sendMessage(getMessageText());
        });
        $('.message_input').keyup(function (e) {
            if (e.which === 13) {
                return sendMessage(getMessageText());
            }
        });
        sendMessage('Hola');
        
    });
}.call(this));

function aparecerchat() {
	var chat1 = document.getElementById("chat");
	
	chat1.style.visibility = "visible";
	
	
}