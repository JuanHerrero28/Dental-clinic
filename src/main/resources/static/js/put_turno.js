window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la pelicula
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;


       const formData = {
                  id: document.querySelector('#turno_id').value,
                  fechaTurno: document.querySelector('#fechaTurno').value,
                  pacienteId: document.querySelector('#paciente_id').value,
                  odontologoId: document.querySelector('#odontologo_id').value
              };


        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de una pelicula del listado
    //se encarga de llenar el formulario con los datos de la pelicula
    //que se desea modificar
  function findBy(id) {
      const url = '/turnos' + "/" + id;
      const settings = {
          method: 'GET'
      };
      fetch(url, settings)
          .then(response => response.json())
          .then(data => {
              if (data && data.id !== undefined) {
                  // Verifica si 'data' y 'data.id' están definidos
                  let turno = data;

                  // Llena los campos del formulario solo si los datos esperados están presentes
                  document.querySelector('#turno_id').value = turno.id || '';
                  document.querySelector('#fechaTurno').value = turno.fechaTurno || '';

                  // Verifica la existencia de 'turno.paciente' y 'turno.paciente.id'
                  if (turno.paciente && turno.paciente.id !== undefined) {
                      document.querySelector('#paciente_id').value = turno.paciente.id;
                  } else {
                      document.querySelector('#paciente_id').value = '';
                  }

                  // Verifica la existencia de 'turno.odontologo' y 'turno.odontologo.id'
                  if (turno.odontologo && turno.odontologo.id !== undefined) {
                      document.querySelector('#odontologo_id').value = turno.odontologo.id;
                  } else {
                      document.querySelector('#odontologo_id').value = '';
                  }

                  document.querySelector('#div_turno_updating').style.display = "block";
              } else {
                  // Manejo si los datos recibidos no son como se esperaban
                  alert("Los datos recibidos no son válidos.");
              }
          })
          .catch(error => {
              alert("Error: " + error);
          });
  }