<h1 style="color:rgb(187,39,245)">API sobre personajes de ficción</h1>

## Esta API fué desarrollada en java utilizando spring boot y el IDE IntelliJIDEA. Contiene tres entidades principales: Personaje, Especie y Obra. Un personaje es aquel que ha aparecido en obras de ficción (películas, series de televisión, video juegos, etc) como podrían ser Neo de la película Matrix, Sonic de los video juegos de Sega, Bart Simpson de la serie de televisión, etc. La entidad obra hace referencia a comics, novelas, películas, video juegos y otros productos en los cuales hayan aparecido los personajes. Especie es el como clasificaríamos a un personaje como ser vivo ,por ejemplo, un personaje de ficción puede ser un humano, un elfo, un perro o un marciano, por nombrar solo algunos

---

## Personajes

---

<h3 style="color:rgb(93, 125, 212)">Solicitud POST (Personajes)</h3>

- **Endpoint:** /api/personajes  
- **Body:**

~~~Javascript
{
    "nombre_completo": string,
    "apodos": string[],
    "url_imagenes": string[],
    "obras": Obra[],
    "especie":Especie
}
~~~

- **Ejemplo:**

~~~Json
{
  "nombre_completo":"kal-el",
  "apodos":[
    "superman",
    "clark kent"
  ],
  "url_imagenes":[
    "https://cinepremiere.com.mx/wp-content/uploads/2018/09/superman40.jpg",
    "https://www.cultture.com/pics/2015/02/supre01.jpg"
  ],
  "obras":[
    {"id": 23},
    {"id": 12},
    {"id": 58}
  ],
  "especie": {"id" : 5}

}
~~~

---

<h3 style="color:rgb(89, 194, 97)">Solicitudes GET (Personajes)</h3>

1. #### Mostrar todos los personajes

    - **Endpoint:** /api/personajes

2. #### Buscar un personaje por su id

    - **Endpoint:** /api/personajes/**{id}**
        - **"id"** es un número entero

3. #### Buscar personajes por su nombre completo  
  
    - **Endpoint:** /api/personajes/por-nombre-completo/**{nombre-completo}**
        - **"nombre-completo"** es una cadena de texto y deberá tener guiones bajos "_" en reemplazo de espacios vacíos. Ejemplo de solicitud: .../api/personajes/por-nombre-completo/**cloud_strife**

4. #### Buscar personajes por un fragmento de su nombre completo

    - **Endpoint:** /api/personajes/por-fragmento-nombre/**{fragmento-nombre}**
      - **"fragmento-nombre"** es una cadena de texto

5. #### Buscar personajes por alguno de sus apodos

    - **Endpoint:** /api/personajes/por-apodo/**{apodo}**
       - **"apodo"** es una cadena de texto

6. #### Buscar personajes por un fragmento de alguno de sus apodos

    - **Endpoint:** /api/personajes/por-fragmento-apodo/**{fragmento-apodo}**
      - **"fragmento-apodo"** es una cadena de texto

7. #### Buscar personajes por título de algunas de las obras en las cuales están presentes

    - **Endpoint:** /api/personajes/por-titulo-obra/**{titulo-obra}**
      - **"titulo-obra"** es una cadena de texto y deberá presentar guiones bajos "_" en lugar de espacios vacíos. Ejemplo de solicitud:  .../api/personajes/por-titulo-obra/**super_mario_64**

8. #### Buscar personajes por su especie

    - **Endpoint:** /api/personajes/por-especie/**{especie}**
       - **"especie"** es una cadena de texto

---

<h3 style="color:rgb(93, 212, 206)">Solicitud PUT (Personajes)</h3>

- **Endpoint:** /api/personajes
- **Eody:**

~~~Javascript
{
    "id": número_entero
    "nombre_completo": string,
    "apodos": string[],
    "url_imagenes": string[],
    "obras": Obra[],
    "especie": Especie
}
~~~

---

<h3 style="color:rgb(217, 39, 55)">Solicitud DELETE (Personajes)</h3>

- **Endpoint:** /api/personajes/**{id}**
  - **"id"** es un número entero

---

<h3 style="color:rgb(212, 242, 85)">Solicitudes PATCH (Personajes)</h3>

1. #### Editar el nombre completo del personaje

    - **Endpoint:** /api/personajes/editar-nombre/**{personaje-id}**?nombre-completo=
       - **"personaje-id"** es número entero
       - El "nombre-completo" pasado como argumento deberá tener "_" en reemplazo de espacios vacíos: Ejemplo de solicitud:  
       .../api/personajes/editar-nombre/4/**barry_allen**

2. #### Editar los apodos del personaje

    - **Endpoint:** /api/personajes/editar-apodos/**{personaje-id}**
        - **"personaje-id"** es un número entero
    - **Body:**

        ~~~Javascript
        string[]
        ~~~

    - **Ejemplo:**

        ~~~Json
       [
       "capitán marvel",
       "shazam"
       ]
       ~~~

3. #### Cambiar las URL de imágenes del personaje

    - **Endpoint:** /api/personajes/cambiar-imagenes/**{personaje-id}**
        - **"personaje-id"** es número entero
    - **Body:**

        ~~~Javascript
        string[]
        ~~~

        - **Ejemplo:**

        ~~~Json
        [
          "https://m.media-amazon.com/images/M/MV5BOWQ0N2RjMDAtYWRiNy00MjRiLWE2NzQtNjQ3NGZiYTBjN2MwXkEyXkFqcGc@._V1_.jpg",
         "https://neiloseman.com/wp-content/uploads/2021/06/T2-poster.jpg",
         "https://duckduckgo.com/?q=terminator+2&t=newext&atb=v474-1&ia=images&iax=images&iai=https%3A%2F%2Fimages2.alphacoders.com%2F516%2Fthumb-1920-516815.jpg",
         "https://static1.srcdn.com/wordpress/wp-content/uploads/2023/06/arnold-schwarzenegger-terminator-2.jpg"
         ]
         ~~~

4. #### Agregar una obra al personaje

   - **Endpoint:** /api/personajes/agregar-obra/**{personaje-id}**/**{obra-id}**
      - **"personaje-id"** y **"obra-id"** son números enteros

5. #### Remover una obra del personaje

    - **Endpoint:** /api/personajes/remover-obra/**{personaje-id}**/**{obra-id}**
      - **"personaje-id"** y **"obra-id"** son números enteros

6. #### Cambiar la especie del personaje

    - **Endpoint:** /api/personajes/editar-especie/**{personaje-id}**/**{especie-id}**
      - **"personaje-id"** y **"especie-id"** son numeros enteros

---

## Especies

---

<h3 style="color:rgb(93, 125, 212)">Solicitud POST (Especies)</h3>

- **Endpoint:** /api/especies
- **Body:**

~~~Javascript
{
  "nombre": string
}
~~~

- **Ejemplo:**

~~~Json
{
  "nombre": "humano"
}
~~~

---

<h3 style="color:rgb(89, 194, 97)">Solicitudes GET (Especies)</h3>

1. #### Mostrar todas las especies

    - **Endpoint:** /api/especies

2. #### Buscar una especie por su id

    - **Endpoint:** /api/especies/**{id}**
      - **"id"** es un número entero

3. #### Buscar una especie por su nombre

   - **Endpoint:** /api/especies/por-nombre/**{nombre}**
      - **"nombre"** es una cadena de texto

4. #### Buscar especie por un fragmento de su nombre

    - **Endpoint:** /api/especies/por-fragmento-nombre/**{fragmento-nombre}**
      - **"fragmento-nombre"** es una cadena de texto

---

<h3 style="color:rgb(93, 212, 206)">Solicitud PUT (Especies)</h3>

- **Endpoint:** /api/especies
- **Body:**

~~~Javascript
{
  "id": número_entero,
  "nombre": string
}  

~~~

---

<h3 style="color:rgb(217, 39, 55)">Solicitud DELETE (Especies)</h3>

- **Endpoint:** /api/personajes/**{id}**
  - **"id"** es un número entero

---

## Obras

---

<h3 style="color:rgb(93, 125, 212)">Solicitud POST (Obras)</h3>

- **Endpoint:** /api/obras
- **Body:**

~~~Javascript
{
  "titulo": string,
  "url_imagenes": string[],
  "fecha_lanzamiento": fecha ("dd-MM-aaaa"),
  "clasificacion": ClasificacionObra
}
~~~

- **Ejemplo:**

~~~Json
{
  "titulo": "the legend of zelda: ocarina of time",
  "url_imagenes": [
    "https://images5.alphacoders.com/105/1056197.png",
    "http://img2.wikia.nocookie.net/__cb20091005154846/zelda/images/e/e1/Gameplay_(Ocarina_of_Time).png",
    "https://static1.cbrimages.com/wordpress/wp-content/uploads/2022/09/B6D25B73-BB33-4937-AD05-CD4B53D5FCA3.jpeg"
  ],
  "fecha_lanzamiento":"21-11-1998",
  "clasificacion":"VIDEO_JUEGO"
}
~~~

- ClasificacionObra hace referencia a un tipo enumerado que es el siguiente: VIDEO_JUEGO, PELICULA, SERIE, COMIC, CUENTO, NOVELA

---

<h3 style="color:rgb(89, 194, 97)">Solicitudes GET (Obras)</h3>

1. #### Mostrar todas las obras guardadas hasta el momento

    - **Endpoint:** /api/obras

2. #### Buscar una obra por su id

    - **Endpoint:** /api/obras/**{id}**
      - **"id"** es un numero entero

3. #### Buscar obra por su titulo

    - **Endpoint:** /api/obras/por-titulo/**{titulo}**
      - **"titulo"** es una cadena de texto y deberá ser pasado como argumento con guiones bajos "_" en lugar de espacios vacíos. Ejemplo de solicitud: .../api/obras/por-titulo/**star_wars**

4. #### Buscar obras por una cadena de texto en su título

    - **Endpoint:** /api/obras/por-fragmento-titulo/**{fragmento-titulo}**
      - **"fragmento-titulo"** es una cadena de texto

5. #### Buscar por periodo

    -**Endpoint:** /api/obras/por-periodo?desde= &hasta=
      -A los argumentos "desde" y "hasta" hay que asignarles los años que conforman el período de interés del lanzamiento de obras. Ejemplo de solicitud : /api/obras/por-periodo?desde=1980&hasta=2002.

6. #### Buscar por clasificación

    -**Endpoint:** /api/obras/por-clasificacion/**{clasificacion}**
      - **"clasificacion"** es una cadena de caracteres que puede ser una de las siguientes: video_juego, pelicula, serie, comic, cuento o novela.

---

<h3 style="color:rgb(93, 212, 206)">Solicitud PUT (Obras)</h3>

- **Endpoint:** /api/obras
- **Body:**

~~~Javascript
{
  "id": número_entero,
  "titulo": string,
  "url_imagenes": string[],
  "fecha_lanzamiento": fecha ("dd-MM-aaaa"),
  "clasificacion": ClasificacionObra
}
~~~

- ClasificacionObra hace referencia a un tipo enumerado que es el siguiente: VIDEO_JUEGO, PELICULA, SERIE, COMIC, CUENTO, NOVELA

---

<h3 style="color:rgb(217, 39, 55)">Solicitud DELETE (Obras)</h3>

- **Endpoint:** /api/obras/**{id}**
  - **"id"** es un número entero

---

<h3 style="color:rgb(212, 242, 85)">Solicitudes PATCH (Obras)</h3>

1. #### Cambiar el título de una obra

    - **Endpoint:** /api/obras/editar-titulo/**{obra-id}**?nuevo-titulo=
      - **"obra-id"** es un número entero y a "nuevo-titulo" hay que asignarle una cadena de caracteres que posea guiones bajos "_" en lugar de espacios vacíos. Ejemplo de solicitud:  
  .../api/obras/editar-titulo/13/street_fighter_II  

2. #### Cambiar las URLs de imágenes de la obra

    - **Endpoint:** /api/obras/cambiar-imagenes/**{obra-id}**
      - **"obra-id"** es un número entero
    - **Body:**

    ~~~Javascript
    string[]
    ~~~

    - **Ejemplo:**

    ~~~Json
    [
      "https://duckduckgo.com/?q=the+office&iar=images&t=newext&atb=v474-1&iai=https%3A%2F%2Fstatic1.colliderimages.com%2Fwordpress%2Fwp-content%2Fuploads%2F2023%2F03%2Fthe-office-cropped-poster.jpg",
      "https://helios-i.mashable.com/imagery/articles/04TIB5XbJ8VOWGtvqlCtqSk/hero-image.fill.size_1200x675.v1614270434.jpg"
    ]
    ~~~

3. #### Cambiar la fecha de lanzamiento

    - **Endpoint:** /api/obras/editar-fecha/**{obra-id}**?fecha-lanzamiento=
      - **"obra-id"** es un número entero y a "fecha-lanzamiento" debe asignársele una fecha en formato "dd-MM-aaaa"

4. #### Cambiar la clasificación de la obra

    - **Endpoint:** /api/obras/editar-clasificacion/**{obra-id}**?numero-clasificacion=
      - **"obra-id"** es un número entero y a "numero-clasificacion" debe asignársele un número entero que equivalga a alguna de las siguientes cadenas de texto: VIDEO_JUEGO = 0, PELICULA = 1, SERIE = 2, COMIC = 4, CUENTO = 5, NOVELA = 6. Ejemplo de solicitud:  ...api/obras/editar-clasificacion/3?numero-clasificacion=2. Esta solicitud está asignándole el valor de SERIE a la obra con id = 3

---

<span style="color:orange">Notas importantes</span>

- A excepción del tipo enumarado ClasificacionObra y de los números romanos, se recomienda utilizar letras minúsculas para las cadenas de texto, tales como títulos, nombres, etc;
- Se recomienda usar mayúsculas para números romanos, ejemplo: final fantasy XII (final fantasy 12).
- Cuando se habla de fragmentos se hace referencia a cadenas de texto que puedan estar contendias en un nombre, título o apodo, ejemplo, el fragmento "ald" está contenido en el nombre "donald", es decir, al buscar personajes por "fragmento-nombre" con el valor "ald", obtendríamos personajes que se llamen "donald" y "aldo" entre algunos de los resultados.
- Al realizar solicitudes del tipo PUT, tener en cuenta que el id presente en el cuerpo o body de la solicitud debe corresponder a la entidad que queramos editar, aunque para modificar entidades es mejor usar las solicitudes del tipo PATCH, ya que permiten cambiar atributos de manera aislada sin la necesidad de abarcar la entidad entera.
- Después de crear las imágenes y contenedores con el dockerfile y el docker-compose.yml, se recomienda ejecutar el contenedor mysql antes que el de openjdk.
