# The_movie_db_app

1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?

  En que cada clase o función tiene una sola responsabilidad
  
  El proposito es evitar la sensibilidad al cambio, eso pasa si una clase tiene mas responsabilidades  
  
  2. Qué características tiene, según su opinión, un “buen” código o código limpio?
  
  Un código limpio es aquel que utiliza una arquitectura para mantener buen entendimiento
  
  Las principal característica que puede tener un código limpio en mi opinion es el nombre de las clases, funciones y variables. Asi el
  código es mas legible para otro desarrollador que colabore en un mismo proyecto. Otra característica es mantener un patrón de diseño fácil
  de entender y adaptar a las necesidades.

 1. Capas de la aplicación
 
  Desarrollada con el lenguaje de Programación Kotlin
  Patrón MVP
  Uso de Dagger 2, RXJava, Retrofit
  
  Descripcion de las capas usadas 
Dagger 2 usando para separar la creación de la capa de red y es responsable de 3 clases:
-  NetworkModule provee los interceptores, manejo de cache y configuración HTTP
-  RetrofitModule provee las instanias ya configuradas de retrofit
- ApiModule proveer las instancias retrofit con las interfaces destinadas para cada modulo de la app
