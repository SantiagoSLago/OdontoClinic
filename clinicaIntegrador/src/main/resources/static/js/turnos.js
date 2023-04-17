
//-----------------VARIABLES-----------------
let formularioAlta = document.querySelector('#formulario_turnoAlta');
let formularioModificacion = document.querySelector('#formulario_turnoModificacion');
let formularioBaja = document.querySelector('#formulario_turnoBaja');

//----------VISIBILIDAD DE DATA----------
window.addEventListener('click', (event) => {

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

    let url = '/turnos/listarTurnos'

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
    event.preventDefault();

    const url = '/turnos/altaTurno';

    const data = {
        dni_paciente : document.querySelector('#dni').value,
        matricula_odontologo : document.querySelector('#matricula').value,
        fecha_turno : document.querySelector('#fecha').value
    }

    const settings = {
        method : 'POST',
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
        window.location.reload();
    })
     .catch(function(e){
             let error = JSON.stringify(e.message);
                         if (error.includes("pacient")) {
                             if (confirm("Paciente no registrado, ¿desea registrar un nuevo paciente?")) {
                                 location.href = "http://localhost:8080/pacientes.html"
                             }
                         } else if(error.includes("odontol")){
                             if (confirm("Odontologo no registrado, ¿desea registrar un nuevo odontologo?")) {
                                 location.href = "http://localhost:8080/odontologos.html"
                             }
                         }else if(error.includes("dni")){
                          alert("El dni del paciente no debe ser nulo ni debe estar vacio")
                         }else if(error.includes("matricu")){
                          alert("La matricula del odontologo no debe ser nula ni debe estar vacia")
                          }else if(error.includes("fecha")){
                          alert("La fecha no debe ser nula ni debe estar vacia")
                          }


        })
})
formularioModificacion.addEventListener('submit',(event)=>{
    event.preventDefault();

    const url = '/turnos/actualizarTurno';

    const data = {
        id : document.querySelector('#id_turnom').value,
        fecha_turno : document.querySelector('#fecham').value,
        matricula_odontologo : document.querySelector('#matriculam').value
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
        window.location.reload();
    })
    .catch(function(e){
            let error = JSON.stringify(e.message);
            console.log(error)
            if(error.includes("El id del")){
                alert("El id del turno no puede ser nulo ni debe estar vacio")
            }else if(error.includes("fecha")){
                alert("La fecha o matricula de odontologo deben contener algun dato")
            }else if(error.includes("turno")){
                alert("El turno solicitado no se encuentra registrado en la base de datos")
            }else if(error.includes("odontol")){
                alert("El odontologo solicitado no se encuentra registrado")
            }
})
})
formularioBaja.addEventListener('submit',(event)=>{
    event.preventDefault();

    const id_turno = document.querySelector('#id_turnob').value


    const url = "/turnos/eliminarTurno/"+id_turno;

    const settings = {
        method : 'DELETE'
    }

    fetch(url,settings)
        .then((response)=>{
            return response.json()
        })
        .then((responseData)=>{
            console.log(responseData)
        })
        .catch(function(e){
            let error = JSON.stringify(e.message)
            if(error.includes("El turno s")){
                alert("El turno solicitado no se encuentra en la base de datos")
            }
        })
        window.location.reload();


})


//-----------------Funciones Especificas-----------------
function renderizarTabla(datos) {

    let tabla_cpo = document.querySelector('#tablaTurnosBody')

    tabla_cpo.innerHTML = "";

    datos.forEach(element => {
        tabla_cpo.innerHTML += `
        <tr>

   <td>${element.id_turno}</td>
   <td>${element.fecha_turno}</td>
   <td>${element.nombre_paciente} / ${element.apellido_paciente}</td>
   <td>${element.nombre_odontologo} / ${element.apellido_odontologo}</td>
</tr> `


    })

}
