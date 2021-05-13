function añadir() { 
                var individual = document.getElementById("individual").value
                var compañia = document.getElementById("compañia").value
                var nombre = document.getElementById("nombre").value
                var address1 = document.getElementById("address1").value
                var address2 = document.getElementById("address2").value
                var ciudad = document.getElementById("ciudad").value
                var provincia = document.getElementById("provincia").value
                var cp = document.getElementById("CP").value
                var pais = document.getElementById("pais").value
                var nif = document.getElementById("NIF").value
                var tel = document.getElementById("tel").value
                var mov = document.getElementById("mov").value
                var correo = document.getElementById("correo").value
                var website = document.getElementById("website").value
                var categorias = document.getElementById("categorias").val


                let cliente1 = new cliente(individual, compañia, nombre, address1, address2, ciudad, provincia, cp, pais, nif, tel, mov, correo, website, categorias);

                //aquí probamos si funciona en la consola 
                console.log(cliente1.individual, cliente1.compañia, cliente1.nombre, cliente1.address1, cliente1.address2, cliente1.ciudad, cliente1.provincia, cliente1.cp, cliente1.pais, cliente1.nif, cliente1.tel, cliente1.mov, cliente1.correo, cliente1.website, cliente1.categorias);
                localStorage.setItem(nombre, JSON.stringify(cliente1));

                //definimos el Objeto con propiedades y metodos
                var cliente = {
                    individual: false,
                    compañia: false,
                    nombre: "",
                    address1,
                    address2,
                    ciudad,
                    provincia,
                    cp,
                    pais,
                    nif,
                    tel,
                    mov,
                    correo,
                    website,
                    categorias,
                }
                function cliente(individual, compañia, nombre, address1, address2, ciudad, provincia, cp, pais, nif, tel, mov, correo, website, categorias) {
                    this.individual = individual;
                    this.compañia = compañia;
                    this.nombre = nombre,
                   	this.ciudad = ciudad,
                    this.provincia = provincia;
                    this.address1 = address1;
                    this.address2 = address2;
                    this.cp = cp;
                    this.pais = pais;
                    this.nif = nif;
                    this.tel = tel;
                    this.mov = mov;
                    this.correo = correo;
                    this.website = website;
                    this.categorias = categorias;


                }

            }
        
        document.getElementById("compañia").addEventListener("change", versolonombre);

		document.getElementById("individual").addEventListener("change", verapellidos);
            
      function versolonombre() {
		document.getElementById("divapellidos").style.display = "none";

	
		}
      
      function verapellidos() {
			
			document.getElementById("divapellidos").style.display = "inline";
			
		}