# LiterAlura - Catálogo de Libros (Consola)

Aplicación Java / Spring Boot que consume la API **Gutendex**, persiste libros y autores en **H2** y ofrece un menú en consola para consultar:

- Buscar y guardar libro por título (API)
- Consultar libros
- Consultar autores
- Listar libros por idioma (en, es, fr, pt, ...)
- Listar autores vivos en un año dado

## Requisitos
- Java 17+
- Maven 3.8+

## Cómo ejecutar
```bash
mvn spring-boot:run
```
o
```bash
mvn clean package
java -jar target/literalura-0.0.1-SNAPSHOT.jar
```

## API de Libros
Se usa **Gutendex**: `https://gutendex.com/books/?search=<termino>`

## Persistencia
Base de datos **H2** embebida (modo file). Consola H2 disponible en `/h2-console` si agregas `spring-boot-starter-web` y ejecutas la app.
- URL: `jdbc:h2:file:./data/literalura-db`

## Notas de diseño
- **DTOs** para parseo del JSON (`LibroDTO`, `AutorDTO`, `GutendexResponse`).
- **Entidades JPA** (`Libro`, `Autor`) con relación **ManyToMany**.
- **Repositorios** con consultas derivadas y una JPQL para autores vivos en un año.
- **Service** (`CatalogoService`) concentra la lógica de negocio.
- Menú en `CommandLineRunner`.
