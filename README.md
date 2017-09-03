Letta
==========

Aplicación y arquitectura de ejemplo para la asignatura Desarrollo Ágil de
Aplicaciones del Grado en Ingeniería Informática de la Escuela Superior de
Ingeniería Informática de la Universidad de Vigo.

## Ejecución con Maven
La configuración de Maven ha sido preparada para permitir varios tipos de
ejecución. En concreto:
* La ejecución por defecto (p.ej. `mvn install`) incluye los tests de
unidad, integración y aceptación (con Selenium).
* Si no se desea ejecutar los tests de aceptación debe desactivarse el perfil
`acceptance-tests-cargo`. Por ejemplo: `mvn -P -acceptance-tests-cargo install`.
* Si se desea arrancar el servidor para ejecutar los tests de aceptación
manualmente (se usará una base de datos HSQL), se debe ejecutar el comando:
`mvn -Dcargo.tomcat.start.skip=true -Dcargo.tomcat.run.skip=false
-DskipTests=true pre-integration-test`
* Si se desea arrancar el servidor con la base de datos MySQL, debe utilizarse
el comando: `mvn -P run-tomcat-mysql,-acceptance-tests-cargo cargo:run`. Es
necesario que el proyecto se haya empaquetado antes (p.ej. `mvn package`). En el
directorio `db` del proyecto se pueden encontrar los scripts necesarios para
crear la base de datos en MySQL.

LETTA es una aplicación que se basa en dos ideas centrales: los clubes de lectura y el
aprovechamiento   de   pequeños   espacios   de   tiempo   disponibles   a   lo   largo   del   día
(descanso del trabajo, hora de la comida, viajes en metro, etc.). De los clubes de lectura
se toma la idea de juntar un grupo de personas para poner en común sus opiniones sobre
un   libro   seleccionado   previamente.   LETTA   actualiza   este   concepto   para   extender   el
ámbito cultural de estos clubes más allá de la literatura y, especialmente, para flexibilizar
la organización de los mismos
