

//Variables
let formularioAlta = document.querySelector('#formulario_pacienteAlta');
let formModificacion = document.querySelector('#formulario_pacienteModificacion');
let formularioBaja = document.querySelector('#formulario_pacienteBaja');

//Eventos
formularioAlta.addEventListener('submit',(event)=>{
event.preventDefault()


const url = '/pacientes/altaPacientes';


const datos = {
    nombre : document.querySelector('#nombre').value,
    apellido : document.querySelector('#apellido').value,
    email : document.querySelector('#email').value,
    dni : document.querySelector('#dni').value,
    domicilio : {
        numero : document.querySelector('#numero').value,
        calle :  document.querySelector('#calle').value,
        ciudad : document.querySelector('#ciudad').value,
        provincia : document.querySelector('#provincia').value,
        pais : document.querySelector('#pais').value,

    }


}


const setting = {
    method : 'POST',
    headers : {'Content-Type' : 'application/json'},
    body : JSON.stringify(datos)
}

fetch (url,setting)
.then(function(response){
 return response.json();
})
.then (function(dataResponse){
console.log(dataResponse)
window.location.reload()
})
.catch(function(e){
let error = JSON.stringify(e.message);
mensajesAlerta(error)
})

})


formModificacion.addEventListener('submit',(event)=>{
event.preventDefault();

const url = 'pacientes/actualizarPaciente';

const data = {
    dni : document.querySelector('#dnim').value,
    nombre : document.querySelector('#nombrem').value,
    apellido : document.querySelector('#apellidom').value,
    email : document.querySelector('#emailm').value,
    domicilio : {
        numero : document.querySelector('#numerom').value,
        calle : document.querySelector('#callem').value,
        ciudad : document.querySelector('#ciudadm').value,
        provincia : document.querySelector('#provinciam').value,
        pais : document.querySelector('#paism').value
    }
}


const settings = {
    method : 'PUT',
    headers : {
        'Content-Type' : 'application/json'
    },
    body : JSON.stringify(data)

}


fetch(url,settings)
.then((response)=>{
    return response.json();
})
.then((dataResponse)=>{
    console.log(dataResponse)
    window.location.reload()
})
.catch(function(e){
let error = JSON.stringify(e.message);
mensajesAlerta(error)

if(error.includes("El pacient")){
alert("El paciente solicitado no se encuentra registrado en la base de datos")
}


})

})
formularioBaja.addEventListener('submit',(event)=>{

    event.preventDefault();



    let dni_paciente = document.querySelector('#dni_pacienteb').value;
    let url = '/pacientes/eliminarPaciente/' + dni_paciente;
    const settings = {
        method : 'DELETE',
        headers : {
            'Content-Type' : 'application/json'
        }
    }


    fetch(url,settings)
    .then(function (response){
        return response.json()
    })
    .then(function (data){
        console.log(data)

    })
    .catch(function(e){
    let error = JSON.stringify(e.message);
    if(error.includes("El pacient")){
    alert("El paciente solicitado no se encuentra registrado en la base de datos")
    }
    })

    window.location.reload();




})
window.addEventListener('click',(event)=>{
//Visibilidad de los formularios
    switch (event.target.id) {
        case "boton_agregar":
        formularioAlta.classList.toggle('oculto')
        break;
        case "boton_modificar":
            formModificacion.classList.toggle('oculto')
            break;
        case "boton_eliminar":
            formularioBaja.classList.toggle('oculto')
            break;

        default:
            break;
    }

})
window.onload = function(){

let url = '/pacientes/listarPacientes'

const settings = {
    methd : 'GET'
}

fetch(url,settings)
.then(function(response){
    return response.json()
})
.then(function(data){
    console.log(data)

    renderizarTabla(data)

})



}

//Funciones
function renderizarTabla(datos){
    let tabla_cpo = document.querySelector('#tablaPacientesBody');


    tabla_cpo.innerHTML = "";

    datos.forEach(element => {
        tabla_cpo.innerHTML += `
    <tr>


   <td>${element.dni}</td>
   <td>${element.nombre}</td>
   <td>${element.apellido}</td>
   <td>${element.email}</td>
   <td>${element.domicilio}</td>

</tr>`
    });

}
function mensajesAlerta(error) {
    if (error.includes("nombre")) {
        alert("El nombre no puede ser nulo ni estar vacio")
    }
    if (error.includes("apellid")) {
        alert("El apellido no puede ser nulo ni estar vacio")
    }
    if (error.includes("email")) {
        alert("El email no puede ser nulo ni estar vacio")
    }
    if (error.includes("dni")) {
        alert("El dni no puede ser nulo ni estar vacio")
    }
    if (error.includes("apellid")) {
        alert("El apellido no puede ser nulo ni estar vacio")
    }
    if (error.includes("numerac")) {
        alert("La numeracion debe ser valida o no debe estar vacia")
    }
    if (error.includes("calle")) {
        alert("La calle debe ser valida o no debe estar vacia")
    }
    if (error.includes("ciudad")) {
        alert("La ciudad debe ser valida o no debe estar vacia")
    }
    if (error.includes("provinc")) {
        alert("La provincia debe ser valida o no debe estar vacia")
    }
    if (error.includes("pais")) {
        alert("El pais debe ser valido o no debe estar vacio")
    }


}





