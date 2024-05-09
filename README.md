**Pantallas principales**

**HomeScreen:** Esta pantalla contiene la lista de recetas disponibles. Cada item de la lista de recetas muestra el país de origen, la imagen y el nombre de la receta. También incluye un filtro que permite buscar recetas por nombre, ingredientes o país de origen.

**DetailScreen:** Esta pantalla contiene el detalle de la receta seleccionada desde la pantalla de Home Screen. En esta pantalla se muestra la imagen, nombre y lista de ingredientes, también se incluyen las instrucciones de preparación de la receta. También cuenta con un botón flotante que permite acceder a la pantalla de MapScreen, donde se puede ver la ubicación del país de origen de la receta.

**MapScreen:** Esta pantalla muestra el país de origen de la receta.

**Características principales**

**1. Patrón de diseño MVVM:** Utilicé el patrón de diseño Modelo-Vista-ViewModel, el cual permite hacer escalable la aplicación.

**2. Pruebas automatizadas con Espresso:** Implementé Espresso para realizar pruebas automatizadas.

**3. Pruebas unitarias con Mockito:** Hice pruebas unitarias utilizando Mockito para realizar pruebas sobre el servicio REST.

**4. Consumo de servicios REST con Retrofit:** Se implementó Retrofit para realizar el consumo del servicio REST.

**5. Carga de imágenes con Glide:** Utilicé Glide, que permite cargar imágenes desde cualquier URL.

**6. Integración de Google Maps:** Integré Google Maps en la aplicación para realizar la visualización del país de origen de las recetas.