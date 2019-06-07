# The_movie_db_app

1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?

  Cada clase o función tiene una sola responsabilidad
  
  El proposito es evitar la sensibilidad al cambio, eso pasa si una clase tiene mas responsabilidades  
  
  2. Qué características tiene, según su opinión, un “buen” código o código limpio?
  
  Un código limpio es aquel que utiliza una arquitectura para mantener buen entendimiento
  
  Las principal característica que puede tener un código limpio en mi opinion es el nombre de las clases, funciones y variables. Asi el
  código es mas legible para otro desarrollador que colabore en un mismo proyecto. Otra característica es mantener un patrón de diseño fácil
  de entender y adaptar a las necesidades.
 
    Desarrollada con el lenguaje de Programación Kotlin
    Android Jetpack
    Patrón MVP
    Uso de Dagger 2, RXJava, Retrofit
       Dagger 2 usando para separar la creación de la capa de red y es responsable de 3 clases:
          -  NetworkModule provee los interceptores, manejo de cache y configuración HTTP
          -  RetrofitModule provee las instanias ya configuradas de retrofit
          - ApiModule proveer las instancias retrofit con las interfaces destinadas para cada modulo de la app

 1. Capas de la aplicación
 
 -  Capa de negocio
    	MoviesPresenter, SeriePresenter realizan la conexión a la capa de red con RxJava, a su vez cada presentador con la capa de vista
     
 - Capa de red
    MovieApi, SerieApi realizan las peticiones http con retrofit
 
 - Capa de vista
    Esta compuesta por un Activity que aloja 2 fragmentos para mostrar las categorias de peliculas y series
    MovieFragment y SerieFragment se encargan de renderizar los datos de las Peliculas y Series obtenidas, cuentan con un modulo para realizar la injección de depencias y asi realizar las consultas de manera eficiente
    SerieDetailsFragment y MovieDetailsFragment muestran datos de una pelicula o serie seleccionada
    
    Se realiza la navegación entre fragmentos mediante el uso de Android Jetpack
  
    

		
