# EjercicioProxy

Para Poder ejecutar el proxy se debe tener instalado Java 8 en la PC.
En la carpera "JAR" se encuentra el archivo .jar para ejecutarlo
Para lanzar el proxy se debe ingresar el comando "java -jar [ruta del archivo Jar] [puerto]" en la consola. El puerto es opcional, por defecto se inicia el en puerto 10000.
Al lanzarse se mostrara un mensaje con el resultado de el inicio del proxy.
Una vez lanzado se debe ir al navegador web que se utilice e ir a la la configuración del mismo. Este paso varia según el explorador que se utilice, en el caso de Google Chrome en la esquina superior derecha se encuentra el menú (un icono de 3 puntos) ingresar a la opción “Configuración”. Se abrirá una pestaña con todas las configuraciones del navegador, se debe buscar la opción "Abrir la configuración de proxy” (Para localizarla mas fácilmente poner la palabra “proxy” en el buscador de la pagina.
Se abrira una ventana oca la configuración de la PC. Alli buscamos la opción “Proxy de Web HTTP” y la configuramos con la ruta “0.0.0.0 : [puerto]”
Aceptar y guardar los cambios.
El sistema mostrara por consola las URLs a las que se accedan y los resultados obtenidos en las mismas.
