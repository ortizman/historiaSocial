0. Hacer la historia Social de un paciente. - HECHO
1. Agrupar los actions de struts por concepto.  - en Progreso
2. Crear los test de unidad a nivel de action - En Progreso
3. Crear la capa de BO y hacerla transaccional. Pasar la logica de los actions a esta capa. - TOMADA
4. Crear test JMeter y medir la carga maxima. Adjuntar las pruebas al Repositorio - NO Iniciada
5. global-exception de struts para el manejo de errores - En Progreso: global excep para session expirada
6. mapeo de los actions de forma dinamica.por convención. - No Iniciada
7. Control de errores en los actions, hacer excepciones especificas y pantallas de error. - No Iniciada
8. Busquedas en las grillas de JQuery-Grid. - En Progreso:La grilla de pacientes tiene busqueda simple.
9. Al ingresar a la HS de un paciente y agregar una practica nueva, hacer que se vuelva a la HS de del paciente (filtrada).
10. Crear el script de la base de datos de la hs y subirlo al SVN. 
11. Crear tag en el SVN de la version que va a salir a produccion.



//Se hizo el interceptor de seguridad, pero no se saco el codigo de verificacion de los actions. Ademas, habría que hacer
otro interceptor con seguridad de director y ponerlo delande de los actions que corresponda. Despues limpiar los actins
de logica de seguridad. HECHO
BUGS conocidos: TODO HECHOS

(1) Listado de instituciones, cdo hay más de 5 filas no se puede pasar a la próxima (las flechitas están deshabilitadas). Lo mimso para el Listado de Responsables.
(2) En la grilla de Instituciones, en las opciones de cantidad de filas muestra 2,10,15 ... en vez de 5, 10,15 ...
(3) En la grilla de Intituciones, columna Responsable, sólo se ve el Nombre, se deberia ver el Apellido (ahi concatenado o en otra columna) como te parezca.
(4) En la carga de Intituciones, muestra bien Apellido, Nombre, faltaría un blanquito después de la coma.
(5) El alta de especialidades me parece que anda, solo que no muestra la descripción en la grilla.

(6) Bug de seguridad cuando entras con un usuario comun y reescribis la url.