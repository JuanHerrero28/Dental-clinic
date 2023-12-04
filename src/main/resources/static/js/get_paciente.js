window.addEventListener('load', function () {
    (function () {
        // Lógica para la petición fetch y la inserción en la tabla
        const url = '/paciente/todos';
        const settings = {
            method: 'GET'
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                // Recorrer la colección de pacientes del JSON
                for (const paciente of data) {
                    // Construir cada fila de la tabla con los botones
                    const table = document.getElementById("pacienteTable");
                    const pacienteRow = table.insertRow();
                    const tr_id = 'tr_' + paciente.id;
                    pacienteRow.id = tr_id;

                    const deleteButton = '<button id="btn_delete_' + paciente.id + '" type="button" onclick="deleteBy(' + paciente.id + ')" class="btn btn-danger btn_delete">&times;</button>';

                    const updateButton = '<button id="btn_id_' + paciente.id + '" type="button" onclick="findBy(' + paciente.id + ')" class="btn btn-info btn_id">' + paciente.id + '</button>';



                    pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                        '<td class="td_nombre">' + paciente.nombre.toUpperCase() + '</td>' +
                        '<td class="td_apellido">' + paciente.apellido.toUpperCase() + '</td>' +
                        '<td class="td_cedula">' + paciente.cedula.toUpperCase() + '</td>' +
                        '<td class="td_fechaDeIngreso">' + paciente.fechaIngreso + '</td>' +
                        '<td class="td_calle">' + paciente.domicilio.calle + '</td>' +
                        '<td class="td_numero">' + paciente.domicilio.numero + '</td>' +
                        '<td class="td_localidad">' + paciente.domicilio.localidad + '</td>' +
                        '<td class="td_provincia">' + paciente.domicilio.provincia + '</td>' +
                        '<td class="td_email">' + paciente.email.toUpperCase() + '</td>' +
                        '<td>' + deleteButton + '</td>';
                }
            });
    })();

    (function () {
        const pathname = window.location.pathname;
        if (pathname === "./get_pacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
