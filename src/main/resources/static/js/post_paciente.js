window.addEventListener('load', function () {
  const formulario = document.querySelector('#add_new_paciente');

  formulario.addEventListener('submit', function (event) {
    const formData = {
      nombre: document.querySelector('#nombre').value,
      apellido: document.querySelector('#apellido').value,
      cedula: document.querySelector('#cedula').value,
      fechaIngreso: document.querySelector('#fechaIngreso').value,
      domicilio: {
        calle: document.querySelector('#calle').value,
        numero: document.querySelector('#numero').value,
        localidad: document.querySelector('#localidad').value,
        provincia: document.querySelector('#provincia').value
      },
      email: document.querySelector('#email').value
    };

    const url = '/paciente'; // Ruta correspondiente para agregar pacientes
    const settings = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData)
    };

    fetch(url, settings)
      .then(response => {
        if (response.ok) {
          return response.json();
        }
        throw new Error('Error al agregar paciente');
      })
      .then(data => {
        let successAlert = '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          '<strong></strong> Paciente agregado </div>';

        document.querySelector('#response').innerHTML = successAlert;
        document.querySelector('#response').style.display = "block";
        resetUploadForm();
      })
      .catch(error => {
        let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          '<strong> Error: </strong>' + error.message + '</div>';

        document.querySelector('#response').innerHTML = errorAlert;
        document.querySelector('#response').style.display = "block";
        resetUploadForm();
      });

    event.preventDefault();
  });

  function resetUploadForm() {
    document.querySelector('#nombre').value = "";
    document.querySelector('#apellido').value = "";
    document.querySelector('#cedula').value = "";
    document.querySelector('#fechaIngreso').value = "";
    document.querySelector('#calle').value = "";
    document.querySelector('#numero').value = "";
    document.querySelector('#localidad').value = "";
    document.querySelector('#provincia').value = "";
    document.querySelector('#email').value = ""; // También limpiamos el campo de email
  }

  // Lógica para activar la clase "active" en el enlace correspondiente en la barra de navegación
  (function () {
    let pathname = window.location.pathname;
    if (pathname === "/") {
      document.querySelector(".nav .nav-item a:first").classList.add("active");
    } else if (pathname === "/pacienteList.html") {
      document.querySelector(".nav .nav-item a:last").classList.add("active");
    }
  })();
});
