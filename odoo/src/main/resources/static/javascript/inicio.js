function entrar() {
    var usuario = document.getElementById("usuario").value
    var contrasena = document.getElementById("contrasena").value
    var arr = JSON.parse(localStorage.getItem(usuario));
    if (localStorage.getItem(usuario)) {
        var valor = arr.usuario;
        if (valor == contrasena) {
            setTimeout(function () { document.location.href = "principal.html" }, 500);
        } else {
            alert("Contraseña equivocada")
        }
    } else {
        setTimeout(function () { document.location.href = "registro.html" }, 500);
    }


    document.cookie = "user = "+arr.nombre;

}

function leerCookie() {
    var lista = document.cookie.split(";");
    var micookie;
    for (i in lista) {
        var busca = lista[i].search("user");
        if (busca > -1) {micookie=lista[i]}
        }
    var igual = micookie.indexOf("=");
    var valor = micookie.substring(igual+1);
     document.getElementById("nombre1").innerHTML = valor;
    }

 
function enviar_registro() {
    var usuario = document.getElementById("usuario").value
    var contrasena = document.getElementById("contrasena").value
    var nombre = document.getElementById("nombre").value

    let cliente1 = new cliente(usuario, contrasena);

    localStorage.setItem(usuario, JSON.stringify(cliente1));

    //definimos el Objeto con propiedades y metodos
    var cliente = {
      usuario,
    }
    function cliente (usuario, contrasena) {
      this.usuario = contrasena;
      this.nombre = nombre;
    }

    if (document.getElementById('contrasena').value !=
    document.getElementById('confirmarcontrasena').value)
    {
        alert("Contraseñas no coinciden");
    }
  }
  
  function mostrarContrasena1(){
      var tipo = document.getElementById("contrasena");
      var ojo = document.getElementById("ojo1");
		
      if(tipo.type == "password"){
          ojo.className = "fas fa-eye";
          tipo.type = "text";
      }else{
		  ojo.className = "fas fa-eye-slash";
          tipo.type = "password";
      }
  }
  
  function mostrarContrasena2(){
      var tipo = document.getElementById("confirmarcontrasena");
      var ojo = document.getElementById("ojo2");
		
      if(tipo.type == "password"){
          ojo.className = "fas fa-eye";
          tipo.type = "text";
      }else{
		  ojo.className = "fas fa-eye-slash";
          tipo.type = "password";
      }
  }