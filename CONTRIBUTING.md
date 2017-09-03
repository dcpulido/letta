# ¿Cómo contribuir a este proyecto?

## Tabla de contenido
  * [1. Empezando](#1-empezando)
  * [2. Desarrollo de una tarea](#2-desarrollo-de-una-tarea)
  * [3. Estructura del proyecto](#3-estructura-del-proyecto)
  * [4. Entorno de desarrollo](#4-entorno-de-desarrollo)
  * [5. Configuración de un entorno de desarrollo](#5-configuración-de-un-entorno-de-desarrollo)
  * [6. Control de versiones (Git)](#6-control-de-versiones-git)
    * [6.1. <em>Commits</em> con errores de construcción](#61-commits-con-errores-de-construcción)
    * [6.2. <em>Push</em> con <em>commits</em> nuevos en el servidor remoto](#62-push-con-commits-nuevos-en-el-servidor-remoto)
    * [6.3. Hacer <em>pull</em> ](#63-hacer-pull)
    * [6.4. <em>Pull</em> con cambios locales no <em>commiteados</em> ](#64-pull-con-cambios-locales-no-commiteados)
  * [7. Tests](#7-tests)  
  * [8. Guía de estilo](#8-guía-de-estilo)
    * [8.1. Código fuente](#81-código-fuente)
    * [8.2. Control de versiones](#82-control-de-versiones)


## 1. Empezando

El proyecto LETTA se desarrolla en un entorno de integración continua con
despliegue continuo en un servidor de pre-producción (*staging*). Este entorno
está compuesto por varias herramientas que automatizan el proceso, todas ellas
dirigidas por el POM de este proyecto.

En este documento encontrarás una descripción de este entorno y las
instrucciones para saber como contribuir correctamente a este proyecto.


## 2. Desarrollo de una tarea
El proceso habitual para realizar una tarea será, normalmente, el siguiente:
1. En Kunagi selecciona la tarea de la que seas responsable que deseas
desarrollar y lee bien la descripción de la misma.
2. Abre el entorno de desarrollo.
3. Verifica que te encuentras en la rama `develop`. Si no es así, cámbiate a
esta rama.
4. Haz *pull* de los últimos cambios (ver [sección 6](#6-control-de-versiones-git)).
5. Implementa la solución, incluyendo los tests (ver [sección 7](#7-tests)).
  1. Haz un *commit* con cada parte estable (completa y testeada) que
  desarrolles.
  2. Cada vez que hagas un *commit* envíalo al repositorio central para
  compartirlo con el resto del equipo (ver [sección 6](#6-control-de-versiones-git)).
  3. Comprueba que la construcción funciona correctamente en el servidor de
  integración continua.
  4. Si la construcción falla, sigue los pasos descritos en la
  [sección 6.3](#63-pull-con-cambios-locales-no-commiteados).
  5. Si la construcción es correcta, comprueba que el proyecto se ha desplegado
  y funciona correctamente en el servidor Wildfly de pre-producción y que el
  repositorio Maven tiene una nueva versión del proyecto (ver [sección 4](#4-entorno-de-desarrollo)).
6. Cuando acabes la jornada de trabajo recuerda introducir las horas en la tarea
de Kunagi.

En las siguientes secciones encontrarás información que te ayudará a realizar
este trabajo.


## 3. Estructura del proyecto
Este proyecto está estructurado en 7 módulos:

* **tests**:
Módulo que contiene utilidades para realizar los tests. Este módulo será
importado por el resto de módulos con el *scope* tests para hacer uso de sus
utilidades.
* **domain**:
Módulo que contiene las clases de dominio (entidades).
* **service**:
Módulo que contiene los EJB del sistema, que serán utilizados tanto por la capa
JSF como por la capa REST.
* **rest**:
Módulo que contiene una capa de servicios REST.
* **jsf**:
Módulo que contiene la interfaz Web del sistema implementada con Java Server
Faces (JSF).
* **ear**:
Módulo que está destinado, únicamente, a la construcción del EAR desplegable del
sistema.
* **additional-material**:
Este último no es realmente un módulo del proyecto. Simplemente es un directorio
que acompaña al resto del proyecto en el que se almacenarán ficheros adicionales
que puedan resultar de utilidad. Algunos ejemplos de ficheros que pueden ir en
este directorio son plantillas HTML, ficheros de configuración del servidor
Wildfly, etc.


## 4. Entorno de desarrollo
Las herramientas que componen el entorno de integración continua son las
siguientes:

* **Maven 3**:
Maven es un entorno de construcción de proyectos para Java. Esta será una
herramienta clave, ya que es quien dirigirá todo el proyecto. Es necesario que
tengas instalado Maven 3 en tu equipo de desarrollo para poder construir el
proyecto.
* **Kunagi**:
Es una herramienta de gestión de proyectos Scrum. En ella encontrarás toda la
información sobre las funcionalidades desarrolladas y por desarrollar, el
alcance de las publicaciones, el estado de desarrollo, etc. Puedes acceder a
través del siguiente [enlace](http://sing.ei.uvigo.es/dt/kunagi).
* **Git y Gitlab**:
Git es el sistema de control de versiones que se utiliza en el proyecto. Es un
sistema de control de versiones distribuido que facilita la colaboración entre
desarrolladores. Es necesario que tengas instalado Git en tu sistema para poder
realizar cambios en el proyecto y colaborar con el resto del equipo.
Por otro lado, Gitlab es un *front-end* del repositorio Git común. Esta
herramienta facilita la visualización de los *commits* y ficheros del proyecto,
además de proporcionar alguna otra funcionalidad que mejora la colaboración.
Puedes acceder a través del siguiente
[enlace](http://sing.ei.uvigo.es/dt/gitlab).
* **Jenkins**:
Jenkins es un servidor de integración continua. Este servidor está configurado
para vigilar el repositorio Git y, ante cualquier cambio, lanzar una
construcción completa del sistema. Para cada construcción se ejecutarán todos
los tests incluidos y se generarán varios informes finales a los que podrás
acceder a través de la web. Además, si la construcción ha tenido éxito, el
sistema se desplegará en un Wildfly de pre-producción (explicado más adelante).
Si la construcción no ha tenido éxito, entonces se enviará un email de aviso a
todo el equipo de desarrollo y resolver esta situación se convertirá en una
prioridad para el equipo.
Puedes acceder a través del siguiente
[enlace](http://sing.ei.uvigo.es/dt/jenkins).
* **Nexus**:
Nexus es un gestor de repositorios Maven. Lo utilizaremos para publicar el
proyecto empaquetado en formato Maven. Puedes acceder a través del siguiente
[enlace](http://sing.ei.uvigo.es/dt/nexus)
Utilizaremos dos de los repositorios de este servidor:
  * `Snapshots`: En el que se publicarán las versiones de trabajo del proyecto.
  * `Releases`: En el que se publicarán las versiones publicables del proyecto.
* **Wildfly de pre-producción**:
En este contenedor se desplegará el sistema cada vez que una construcción se
complete con éxito en Jenkins. Las URLs en las que se desplegarán las
aplicaciones del proyecto son:
  * JSF: http://sing.ei.uvigo.es/letta/jsf
  * REST: http://sing.ei.uvigo.es/letta/rest
* **Wildfly local**:
Para poder ejecutar el servidor en tu entorno de desarrollo también será
necesario que dispongas de un Wildfly local. En la [sección 5.1](#511-ejecución-en-un-wildfly-local)
encontrarás una explicación de cómo instalarlo y configurarlo.
* **MySQL**:
MySQL es el sistema gestor de base de datos (SGDB) que utilizará el sistema
definitivo. En la explicación de cómo ejecutar el sistema en local utilizaremos
este SGBD, por lo que deberás tenerlo instalado en tu equipo.


## 5. Configuración de un entorno de desarrollo
Empezar a trabajar en el proyecto es tan sencillo como seguir los siguientes
pasos:

1. Instala Git y Maven. Si estás en un entorno Ubuntu es tan sencillo como
ejecutar `sudo apt-get install git maven`. También es recomendable que instales
algún visor de Git como `gitk` o `qgit`.
2. Clona el repositorio Git utilizando el comando:
```bash
git clone http://sing.ei.uvigo.es/dt/gitlab/dgss/letta.git
```
3. Instala Eclipse Mars for Java EE (opcional pero recomendado):
  1. Descarga el IDE desde http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/mars1
  2. Importa el proyecto en Eclipse utilizando `Import...->Existing Maven
projects`, selecciona el directorio del proyecto en `Root directory` y marca
todos los proyectos que aparezcan.

En la [sección 7.3](#73-ejecución-de-los-tests-en-eclipse) aparece detallada la
configuración necesaria para ejecutar los tests desde Eclipse.

Con esto ya sería suficiente para poder empezar a trabajar en el proyecto. Si,
además, quieres poder ejecutarlo de forma local, deberás seguir las siguientes
instrucciones.


## 6. Control de versiones (Git)
El modelo de control de versiones que utilizaremos inicialmente será muy
sencillo ya que solo utilizaremos dos ramas:
* `master`: A esta rama solo se enviarán los *commits* cuando se llegue a una
versión estable y publicable (una *release*). Estas versiones deberán estar
etiquetadas con el número de versión correspondiente.
* `develop`: Esta será la rama principal de trabajo. Los *commits* que se envíen
deben ser estables, lo que supone que el código debe incluir tests y todos deben
superarse existosamente al construir la aplicación en local.

### 6.1. *Commits* con errores de construcción
Ambas ramas estarán controladas por el servidor de integración que ejecutará los
tests inmediatamente después de que se haga un *commit*. En el caso de que una
**construcción falle** en Jenkis es muy importante **deshacer el último *commit*
para volver a un estado estable**.

Aunque existen varias formas de hacer esto, la forma más directa es:
```
git push origin +HEAD^:develop
```
Este comando fuerza a que la rama `develop` remota se sitúe en el *commit*
anterior a `HEAD`, ya que `HEAD` es el *commit* conflictivo. El *commit* seguirá
existiendo en local y se espera que tras corregir los errores se realice un
`git commit --amend`.
Si se desea descartar el *commit* local pero mantener el estado de los ficheros,
puede utilizarse un `git reset --mixed HEAD^`.

### 6.2. *Push* con *commits* nuevos en el servidor remoto
Si se desea hacer un *push* a un servidor remoto en el cual hay *commits* que
nuevos que no tenemos en local, entonces Git muestra un error en el que nos
indica que debemos hacer un *pull* antes de poder hacer *push*.

Dado que no nos interesa tener que añadir un *commit* de *merge* adicional,
el *pull* debe hacerse aplicando un *rebase*. Para ello debe usarse el comando:
```
git pull --rebase
```
Este comando iniciará un proceso de *rebase* entre desde la rama local hacia la
rama remota. Es decir, los *commit* locales no *pusheados* pasarán a tener como
padre el último *commit* remoto.

### 6.3. Hacer *pull*
Antes de hacer un *pull* siempre se debe revisar el servidor de integración
continua. En el caso de que haya una construcción en ejecución **no debe hacerse
*pull*** hasta que finalice y se compruebe que ha sido con éxito.

En el caso de que la construcción falle, debe esperarse a que el repositorio
vuelva a un estado estable (ver [sección 6.1](#61-commits-con-errores-de-construcción))
antes de hacer *pull*.

### 6.4. *Pull* con cambios locales no *commiteados*
En caso de que nos encontremos en medio de un *commit* (no se ha completado los
cambios necesarios para realizar un *commit*) y deseemos descargar nuevos
*commits* del servidor central, podemos hacerlo utilizando los comandos:
```
git stash
git pull --rebase
git stash pop
```


## 7. Tests
Lo primero que se debe tener en cuenta a la hora de realizar tests es la
existencia del módulo `tests`. Este proyecto está pensado para recoger las
clases de utilidad que puedan ser compartidas por los tests de los distintos
módulos que forman el proyecto. Por lo tanto, siempre que exista una clase o
fichero que sea compartido por varios proyectos, debería almacenarse en este
módulo.

En segundo lugar, es importante ser consciente de que, dependiendo del módulo en
el que nos encontremos, deberemos hacer diferentes tipos de test.

Por último, como norma general, los métodos de prueba deben ser **lo más
sencillos posible**, de modo que sea sencillo comprender qué es lo que se está
evaluando. En base a esta regla, no añadiremos documentación Javadoc a los
métodos de prueba (esto no se aplica a las clases de utilidad del módulo
`tests`, que sí que deben estar documentadas con Javadoc).

A continuación se detalla el proceso de realización de tests.


## 8. Guía de estilo
Un elemento importante para poder colaborar es que exista una uniformidad en el
código y otros elementos que forman parte del desarrollo. Esta sección sirve
como una pequeña guía de estilo que debe respetarse al trabajar en el proyecto.

### 8.1. Código fuente
Para uniformizar el código fuente deben respetarse las siguientes normas:
* **Idioma**: Todo el código (incluyendo la documentación) debe desarrollarse en
inglés.
* **Formato de código**: El código debe estar formateado, preferiblemente,
siguiendo la [Guía de Estilo para Java de Google](https://google.github.io/styleguide/javaguide.html)
o, al menos, utilizando el formato de código de Eclipse (`Ctrl`+`Mayus`+`F`).
* **Comentarios**: Debe evitarse **completamente** el código comentado y, en la
medida de lo posible, los comentarios en el código.
* **Documentación**: Todos los métodos públicos o protegidos deben incluir
documentación Javadoc. Se recomienda que se verifique que la documentación es
correcta utilizando el comando `mvn javadoc:javadoc`. Este comando generará la
documentación en formato HTML y fallará si encuentra algún error en la
documentación.

### 8.2. Control de versiones
Una de las bases de desarrollo que utilizaremos en este proyecto es el
**integrar tan pronto como se pueda**. Para ello, deben seguirse las siguientes
normas:
* **Contenido de los *commits***: Los *commits* deben ser completos en el
sentido de que no deben romper la construcción. Además, el código debe estar
probado, incluyendo los tests descritos en la [sección 7](#7-tests), para que el
resto de desarrolladores puedan confiar en el código. Es muy recomendable
revisar los informes de tests y de cobertura antes de hacer un *commit*.
* **Formato**: El formato de los *commits* deberá respetar las siguientes
normas:
  * Escritos en Español.
  * Limitar el tamaño de línea a 80 columnas. Si se utiliza Eclipse, esto se
  hace de forma automática.
  * Primera línea descriptiva de lo que hace el *commit*:
    * Si está relacionado con alguna tarea concreta de las descritas en Kunagi,
    debe comenzar con el identificador de la tarea (p.ej. "tsk1 Adds...").
    * Si está relacionado con varias tareas, su número se separará con un guión
    (p.ej. "tsk1-2-13 Fixes...").
    * Debe estar redactada en tercera persona del presente (p.ej. *Añade...*,
      *Mejora...*, *Modifica...*, etc.).
    * No debe llevar punto al final.
  * Cuerpo del *commit* descriptivo. Con una línea vacía de separación de la
  primera línea, debe escribirse un texto de explique claramente el trabajo
  hecho en el *commit*.
* **Frecuencia de *commit***: Los *commits* deben hacerse en pequeños pasos para
que la frecuencia sea alta. Para ello es recomendable desarrollar de una forma
ordenada, atacando partes concretas.
* **Frecuencia de *push***: Simpre que se haga un *commit* debe hacerse un
*push*. La única excepción a esta regla es que estemos haciendo pruebas locales
para evaluar una posible solución. En tal caso, es recomendable que esto se
haga en una rama independiente para evitar enviar *commits* accidentalmente a
la rama *develop* remota.

