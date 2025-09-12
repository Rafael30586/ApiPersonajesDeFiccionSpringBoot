# API REST sobre personajes de ficción

## Personajes

### Solicitud POST

- **endpoint:** /api/personajes  
- **body:** 

~~~Json
{
    "nombre_completo": string,
    "apodos": string[],
    "url_imagenes": string[],
    "obras": Obra[],
    "especie":Especie
}
~~~

### Solicitudes GET

1. #### Mostrar todos los personjes 

- **endpoint:** /api/personajes

2. #### Buscar un personaje por su id

- **endpoint:** /api/personajes/{*id*}
  - **"id"** es un número entero

3. #### Buscar personajes por su nombre completo  
  
- **endpoint:** /api/personajes/por-nombre-completo/{*nombre-completo*}
  - **"nombre-completo"** es una cadena de texto y deberá tener guiones bajos "_" en reemplazo de espacios vacíos. Ejemplo mde solicitud: .../api/personajes/por-nombre-completo/**cloud_strife**

4. #### Buscar personajes por un fragmento de su nombre completo

- **endpoint:** /api/personajes/por-fragmento-nombre/{*fragmento-nombre*}
  - **"fragmento-nombre"** es una cadena de texto

5. #### Buscar personajes por alguno de sus apodos

- **endpoint:** /api/personajes/por-apodo/{*apodo*}
  - **"apodo"** es una cadena de texto

6. #### Buscar personajes por un fragmento de alguno de sus apodos

- **endpoint:** /api/personajes/por-fragmento-apodo/{*fragmento-apodo*}
  - **"fragmento-apodo"** es una cadena de texto

6. #### Buscar personajes por título de algunas de las obras en las cuales están presentes 

- **endpoint:** /api/personajes/por-titulo-obra/{*titulo-obra*}
  - **"titulo-obra"** es una cadena de texto y deberá presentar guiones bajos "_" en lugar de espacios vacíos. Ejemplo de solicitud:  .../api/personajes/por-titulo-obra/**super_mario_64**

7. #### Buscar personajes por su especie

- **endpoint:** /api/personajes/por-especie/{*especie*}
  - **"especie"** es una cadena de texto

### Solicitud PUT

- **endpoint:** /api/personajes
- **body:**

~~~Json
{
    "id": número_entero
    "nombre_completo": string,
    "apodos": string[],
    "url_imagenes": string[],
    "obras": Obra[],
    "especie": Especie
}
~~~

### Solicitud DELETE

- **endpoint:** /api/personajes/{*id*}
  - **"id"** es un número entero

### Solicitudes PATCH

1. #### Editar el nombre completo del personaje

- **endpoint:** /api/personajes/editar-nombre/{*personaje-id*}?nombre-completo=
  - **"personaje-id"** es número entero
  - El nombre completo pasado como argumento deberá tener "_" en reemplazo de espacios vacíos: Ejemplo de solicitud:  
  .../api/personajes/editar-nombre/4/**barry_allen**

2. #### Editar los apodos del personaje

- **endpoint:** /api/personajes/editar-apodos/{*personaje-id*}
  - **"personaje-id"** es un número entero

3. #### Cambiar las URL de imágenes del personaje

- **endpoint:** /api/personajes/cambiar-imagenes/{*personaje-id*}
  - **"personaje-id"** es número entero

4. #### Agregar una obra al personaje

- **endpoint:** /api/personajes/agregar-obra/{*personaje-id*}/{*obra-id*}
  - **"personaje-id"** y **obra-id** son números enteros

5. #### Remover una obra del personaje

- **endpoint:** /api/personajes/remover-obra/{*personaje-id*}/{*obra-id*}
  - **"personaje-id"** y **obra-id** son números enteros

6. #### Cambiar la especie del personaje

- **endpoint:** /api/personajes/editar-especie/{*personaje-id*}/{*especie-id*}
  - **"personaje-id"** y **"especie-id"** son numeros enteros

## Especies

### Solicitud POST

- **endpoint:** /api/especies
- **body**

~~~Json
{
  "nombre": string
}
~~~

### Solicitudes GET

1. #### Mostrar todas las especies

- **endpoint:** /api/especies

2. #### Buscar una especie por su id

- **endpoint:** /api/especies/{*id*}
  - **"id"** es un número entero

3. #### Buscar una especie por su nombre

- **endpoint:** /api/especies/por-nombre/{*nombre*}
  - **"nombre"** es una cadena de texto

4. #### Buscar especie por un fragmento de su nombre

- **endpoint:** /api/especies/por-fragmento-nombre/{*fragmento-nombre*}
  - **"fragmento-nombre"** es una cadena de texto

### Solicitud PUT 

- **endpoint:** /api/especies
- **body:**

~~~Json
{
  "id": número_entero,
  "nombre": string
} 
~~~

### Solicitud DELETE

- **endpoint:** /api/personajes/**{id}**
  - **id** es un número entero

## Obras

### Solicitud POST
- **endpoint:** /api/obras
- **body:** 

~~~Json
{
  "titulo": string,
  "url_imagenes": string[],
  "fecha_lanzamiento": fecha ("dd-MM-aaaa"),
  "clasificacion": ClasificacionObra
}
~~~

- ClasificacionObra hace referencia a un tipo enumerado que es el siguiente: VIDEO_JUEGO, PELICULA, SERIE, COMIC, CUENTO, NOVELA

### Solicitudes GET

#### Mostrar todas las obras guardadas hasta el momento

- **endpoint:** /api/obras

#### Buscar una obra por su id

- **endpoint:** /api/obras/**{id}**
  - **"id"** es un numero entero

#### Buscar obra por su titulo

- **endpoint:** /api/obras/por-titulo/**{titulo}**
  - **"titulo"** es una cadena de texto y deberá ser pasado como argumento con guiones bajos "_" en lugar de espacios vacíos. Ejemplo de solicitud: .../api/obras/por-titulo/**star_wars**

#### Buscar obras por una cadena de texto en su titulo

- **endpoint:** /api/obras/por-fragmento-titulo/**{fragmento-titulo}**
  - **"fragmento-titulo"** es una cadena de texto

### Solicitud PUT 

- **endpoint:** /api/obras
- **body:**

~~~Json
{
  "id": número_entero,
  "titulo": string,
  "url_imagenes": string[],
  "fecha_lanzamiento": fecha ("dd-MM-aaaa"),
  "clasificacion": ClasificacionObra
}
~~~

- ClasificacionObra hace referencia a un tipo enumerado que es el siguiente: VIDEO_JUEGO, PELICULA, SERIE, COMIC, CUENTO, NOVELA

### Soplicitud DELETE

- **endpoint:** /api/obras/**{id}**
  - **"id"** es un número entero

### Solicitudes PATCH

#### Cambiar el título de una obra

- **endpoint:** /api/obras/editar-titulo/**{obra-id}**
  - **"obra-id"** es un número entero

#### Cambiar las URLs de imágenes de la obra

- **endpoint:** /api/obras/cambiar-imagenes/{obra-id}
  - **"obra-id"** es un número entero
- **body:**

~~~Json
string[]
~~~

#### Cambiar la fecha de lanzamiento

- **endpoint:** /api/obras/editar-fecha/**{obra-id}**?fecha-lanzamiento=
  - **"obra-id"** es un número entero y a "fecha-lanzamiento" debe asignársele una fecha en formato "dd-MM-aaaa"

#### Cambiar la clasificación de la obra

- **endpoint:** /api/obras/editar-clasificacion/**{obra-id}**?numero-clasificacion=
  - **"obra-id"** es un número entero y a "numero-clasificacion" debe asignársele un número entero que equivalga a alguna de las siguientes cadenas de texto: VIDEO_JUEGO = 0, PELICULA = 1, SERIE = 2, COMIC = 4, CUENTO = 5, NOVELA = 6. Ejemplo de solicitud:  ...api/obras/editar-clasificacion/3?numero-clasificacion=2. Esta solicitud está asignándole el valor de SERIE a la obra con id = 3


