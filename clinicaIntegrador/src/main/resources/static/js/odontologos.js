
//-----------------Variables-----------------
let formularioAlta = document.querySelector('#formulario_odontologoAlta');
let formularioModificacion = document.querySelector('#formulario_odontologoModificacion');
let formularioBaja = document.querySelector('#formulario_odontologoBaja');

//----------Visibilidad de Data----------
window.addEventListener('click',(event)=>{

    switch (event.target.id) {
        case "boton_agregar":
        formularioAlta.classList.toggle('oculto')
        break;
        case "boton_modificar":
            formularioModificacion.classList.toggle('oculto')
            break;
        case "boton_eliminar":
            formularioBaja.classList.toggle('oculto')
            break;

        default:
            break;
    }

})
window.onload = function(){

    let url = 'odontologos/listarOdontologos'

    const settings = {
        method : 'GET'
    }

    fetch(url,settings)
    .then(function(response){
        return response.json()
    })
    .then(function(dataResponse){
        console.log(dataResponse)
        renderizarTabla(dataResponse)
    })

}



//-----------------OPERACIONES CRUD-----------------
formularioAlta.addEventListener('submit',(event)=>{
event.preventDefault()

const url = '/odontologos/crearOdontologo'

const data = {
    nombre : document.querySelector('#nombre').value,
    apellido : document.querySelector('#apellido').value,
    matricula : document.querySelector('#matricula').value
}

const sets = {
    method : 'POST',
    headers : {
        'Content-Type' : 'application/json'
    },
    body : JSON.stringify(data)
}

console.log(sets);


fetch(url,sets)
.then((response)=>{
    return response.json()
})
.then((responseData)=>{
    console.log(responseData)
    window.location.reload()
})
.catch(function(e){
    let error = JSON.stringify(e.message)

    if(error.includes("nombre")){
        alert("El nombre debe ser valido o no debe estar vacion")
    }else if(error.includes("apellid")){
        alert("El apellido debe ser valido o no debe estar vacio")
    }else if (error.includes("matricu")){
        alert("La matricula debe ser valida o no debe estar vacia")
    }
})






})
formularioModificacion.addEventListener('submit',(event)=>{
    event.preventDefault();


    const url = '/odontologos/actualizarOdontologo'

    const data = {
        matricula : document.querySelector('#matriculam').value,
        nombre : document.querySelector('#nombrem').value,
        apellido : document.querySelector('#apellidom').value
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
        return response.json()
    })
    .then((responseData)=>{
        console.log(responseData)
        window.location.reload()
    })
    .catch(function(e){
            let error = JSON.stringify(e.message)

            if(error.includes(" odontol")){
                alert("El odontologo solicitado no se encuentra registrado")
            }else if(error.includes("nombre")){
                alert("El nombre o el apellido del odontologo deben tener algun valor")
            }else if(error.includes("matricu")){
                alert("La matricula no puede ser nula ni estar vacia")
            }

        })
})
formularioBaja.addEventListener('submit',(event)=>{
event.preventDefault()
    const matricula_odontologo = document.querySelector('#matriculab').value;

    const url = "/odontologos/eliminarOdontologo/"+matricula_odontologo;

    const settings = {
    method : 'DELETE'
    }

    fetch(url,settings)
    .then((response)=>{
        return response.json()
    })
    .then((responseData)=>{
    console.log("eliminacion exitosa")
        console.log(responseData)
    })
    .catch(function(e){
            let error = JSON.stringify(e.message)
            if(error.includes("El odontol")){
                alert("El odontologo no se encuentra registrado")
            }
        })

        window.location.reload();

})

//-----------------Funciones Especificas-----------------
function renderizarTabla(datos) {

    let tabla_cpo = document.querySelector('#tablaOdontologosBody')

    tabla_cpo.innerHTML = "";

    datos.forEach(element => {
        tabla_cpo.innerHTML += `
        <tr>

   <td>${element.matricula}</td>
   <td>${element.nombre}</td>
   <td>${element.apellido}</td>
</tr>

        `
    })

}








