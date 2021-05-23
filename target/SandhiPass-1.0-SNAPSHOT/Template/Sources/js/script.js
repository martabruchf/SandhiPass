/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Onclick dels botons. Envien la informació dels formularis

function lanzarModal(event) {
    document.forms.formModal.accionEvento.value = event;
    document.forms.formModal.submit();
}

function lanzarSortir(event) {
    document.forms.formSortir.accionEvento.value = event;
    document.forms.formSortir.inputIdUsuari.value = "0";
    document.forms.formSortir.submit();
}

function lanzarEdi(event, idUsuari) {
    document.forms.formEdi.accionEvento.value = event;
    document.forms.formEdi.inputIdUsuari.value = idUsuari;
    document.forms.formEdi.submit();
}

function lanzarGuardar(event, idGuardar, idUsuari) {
    document.forms.formEdi.accionEvento.value = event;
    document.forms.formEdi.inputIdGuardar.value = idGuardar;
    document.forms.formEdi.inputIdUsuari.value = idUsuari;
    document.forms.formEdi.submit();
}

function lanzarInfo(event, id, idUsuari) {
    document.forms.formInfo.accionEvento.value = event;
    document.forms.formInfo.inputid.value = id;
    document.forms.formInfo.inputIdUsuari.value = idUsuari;
    document.forms.formInfo.submit();
}

function lanzarBuscar(event, idUsuari) {
    document.forms.formBuscar.accionEvento.value = event;
    document.forms.formBuscar.inputIdUsuari.value = idUsuari;
    document.forms.formBuscar.submit();
}


function lanzarEliminar(event, id, nom, url, usuari, idUsuari) {
    var text1 = "Nom: " + nom + " - Adreça: " + url + " - Usuari: " + usuari;
    Swal.fire({
        title: 'Segur que ho vols esborrar?',
        text: text1,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, esborra\'l!',
        cancelButtonText: 'No!'
    }).then((result) => {
        if (result.isConfirmed) {
            document.forms.formInfo.accionEvento.value = event;
            document.forms.formInfo.inputid.value = id;
            document.forms.formInfo.inputIdUsuari.value = idUsuari;
            document.forms.formInfo.submit();
        }
    });

}

