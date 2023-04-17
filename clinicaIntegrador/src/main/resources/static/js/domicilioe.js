
/*
window.onload = function(){
    fetch("/domicilios/listarDomicilios")
    .then(function(response){
        return response.json()
    })
    .then(function(data){
        console.log(data)
    })
}
*/

//Variables

let formularioAlta = document.querySelector('#formulario_domicilioAlta');
let formularioBaja = document.querySelector('#formulario_domicilioBaja');
let formularioModificacion = document.querySelector('#formulario_domicilioModificar')








//Eventos
window.onload = function(){


    let url = '/domicilios/listarDomicilios';

    const settings = {
        method : 'GET'
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
formularioAlta.addEventListener('submit',(event)=>{
event.preventDefault()



const datos = {
    numero : document.querySelector('#numero').value,
    calle : document.querySelector('#calle').value,
    ciudad : document.querySelector('#ciudad').value,
    provincia : document.querySelector('#provincia').value,
    pais : document.querySelector('#pais').value
}


const setting = {
    method : 'POST',
    headers : {'Content-Type' : 'application/json'},
    body : JSON.stringify(datos)
}

fetch ('domicilios/crearDomicilio',setting)
.then(function(response){
return response.json();
})
.then( function(dataResponse){
    console.log(dataResponse)})
})
formularioBaja.addEventListener('submit',(event)=>{
    event.preventDefault()


        id_domicilio = document.querySelector('#id_domicilio').value;

    const url = 'domicilios/eliminarDomicilio/' + id_domicilio;



    const settings = {
        method : 'DELETE',
        headers : {
            'Content-Type' : 'application/json'},
        }




    fetch(url,settings)
    .then(function(response){
        return response.status;
    })
    .then (function(dataResponse){
        console.log(dataResponse)
    })




})
formularioModificacion.addEventListener('submit',(event)=>{

const url = 'domicilios/actualizarDomicilio';

const data = {
    id_domicilio : document.querySelector('#id_domiciliom').value,
    numero : document.querySelector('#numerom').value,
    calle : document.querySelector('#callem').value,
    ciudad : document.querySelector('#ciudadm').value,
    provincia : document.querySelector('#provinciam').value,
    pais : document.querySelector('#paism').value
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
})

})
document.addEventListener('click', (event)=>{
   switch (event.target.id) {
    case "boton_agregar":
        formularioAlta.classList.toggle('oculto')
        break;
    case "boton_modificar" :
        formularioModificacion.classList.toggle('oculto')
        break;
    case "boton_eliminar":
        formularioBaja.classList.toggle('oculto')
        break;
    default:
        break;
   }



})



//Funciones
function renderizarTabla(datos){
    let tabla_cpo = document.querySelector('#tablaDomiciliosBody');


    tabla_cpo.innerHTML = "";

    datos.forEach(element => {
        tabla_cpo.innerHTML += `
    <tr>
    <th scope="row">${element.id_domicilio}</th>
   <td>${element.numero}</td>
   <td>${element.calle}</td>
   <td>${element.ciudad}</td>
   <td>${element.provincia}</td>
   <td>${element.pais}</td>
</tr>`
    });






}
function reloadListadoDomicilios(){
    let url = '/domicilios/listarDomicilios';

    const settings = {
        method : 'GET'
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
function completarFormularioModificacion(data){

                formularioModificacion.innerHTML = `<h3>Formulario Modificacion</h3>
                <div class="mb-3">
                    <label for="id_domicilio" class="form-label">Id Domicilio</label>
                    <input type="number" class="form-control" id="id_domiciliom" aria-describedby="emailHelp" name="id_domicilio" autocomoplete="${data.id_domicilio}">
                </div>
                <div class="mb-3">
                    <label for="numero" class="form-label">Numero</label>
                    <input type="number" class="form-control" id="numerom" aria-describedby="emailHelp" name="numero" autocomplete="${data.numero}}">
                </div>
                <div class="mb-3">
                    <label for="calle" class="form-label">Calle</label>
                    <input type="text" class="form-control" id="callem" aria-describedby="emailHelp" name="calle" autocomplete="${data.calle}">
                </div>
                <div class="mb-3">
                    <label for="ciudad" class="form-label">Ciudad</label>
                    <input type="text" class="form-control" id="ciudadm" aria-describedby="emailHelp" name="ciudad" autocomplete=${data.ciudad}>
                </div>
                <div class="mb-3">
                    <label for="provincia" class="form-label">Provincia</label>
                    <input type="text" class="form-control" id="provinciam" aria-describedby="emailHelp" name="provincia" autocomplete=${data.provincia}>
                </div>
                <div class="mb-3">
                    <label for="pais" class="form-label">Pais</label>
                    <input type="text" class="form-control" id="paism" aria-describedby="emailHelp" name="pais" autocomomplete=${data.pais}>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>`



            }


