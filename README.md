# Proyecto2_Scrum
Proyecto gestor de Scrum (16/01/2019) - Grupo compuesto por Ali, David y Marc

<b>Sprint 1 (16/01/2019 - 23/01/2019)</b>
<br>Pasos necesarios para poner la base de datos en funcionamiento:</br>
- Crear una base de datos en phpmyadmin con el nombre 'madali_bd' (o modificar el persistence.xml)

- Crear la estructura de la base de datos (tablas) copiando el contenido del fichero en la ruta: <b>src/main/resources/madali_db.sql</b>

- Crear un usuario de phpmyadmin (o modificar el persistence.xml) con las credenciales: 
  <ul>
    <li>username: admin</li>
    <li>password: m@dali</li>
  </ul> 

- Usuario necesario para crear usuarios:
  <ul>
    <li>nombre_usuario: davidg</li>
    <li>password: 1234</li>
  </ul>

<b>Sprint 2 (23/01/2019 - 30/01/2019)</b>
<p>Para comprobar el funcionamiento de la base de datos embebida, al ejecutar la aplicación por primera vez, tiene que haber conexión con la base de datos remota para poder copiar el contenido de esta, crear la estructura de la base de datos embebida y poder utilizar la aplicación sin conexión. Una vez creada la base de datos embebida, ya se podrá utilizar la aplicación sin conexión con la remota.</p>
<p><b>Explicación breve:</b> Si la primera vez que se ejecuta la aplicación, hay conexión con la remota y la base de datos embebida no existe, un método se encarga de crearla.</p>

<b>Recordatorio!</b> Para generar un nombre de usuario, primero hay que escribir el nombre y los apellidos, al terminar de escribir pulsar ENTER.
- Usuario necesario para crear proyectos:
  <ul>
    <li>nombre_usuario: lzabala</li>
    <li>password: root</li>
  </ul>
- Usuario para comprobar que no tiene disponible la creación de usuarios ni la creación de proyectos:
  <ul>
    <li>nombre_usuario: marcb</li>
    <li>password: 1234</li>
  </ul>
