# Proyecto Tienda de barrio

Este proyecto está diseñado para controlar el flujo de productos dentro la tienda, con este sistema es posible realizar la compra de productos y almacenarlos en un stock, realizar la venta de los productos disponibles en el stock, buscar productos por su código, incluso tener un kardex del que es posible consultar los productos más vendidos, productos que están por debajo de un umbral preestablecido y buscar la ganancia que se tuvo en un rango de fechas.

## Instalación

Para poder instalar y usar este sistema es necesario contar con Docker y Docker compose instalado, con el siguiente comando se comienza con el levantamiento del sistema:

```batch=
$ docker-compose up
```

La creación de la base de datos y sus seeders se lo hace de manera automática con el microservicio llamado: msvc-users, el cual cuenta con la dependencia para realizar migraciones: Flyway.

## Flujos

Para entender el flujo, se resaltan en negrita a las peticiones a ser usadas con Postman.

### Flujo para la creación de productos

Este flujo comienza pasando solo los datos necesarios para su creación **(1. Tienda - Registrar un nuevo producto)**.

### Flujo para el agregado de nuevos lotes

Este flujo comienza con el listado de los productos disponibles **(1.2 Listado de productos)** y el listado de los proveedores disponibles **(1.3 Listado de proveedores)**, una vez obtenidos los detalles del producto y el proveedor con su respectivo id, pasamos al siguiente paso donde creamos un nuevo lote pasando sus respectivos datos **(2. Tienda - Guardar lote)**, una finalizada la creación pasamos a registrar este movimiento de SALIDA en el Kardex, el cual se hace de manera interna.

### Flujo para registrar una nueva venta

El flujo comienza obteniendo la información de un producto por su código **(1.1 Buscar producto por código)**, este proceso es repetitivo hasta que el cliente haya pasado todos los productos que se llevara, después de haber obtenido todos los productos crearemos un array con la información de todos los productos que se llevara y pasamos a realizar la venta **(3. Realizar venta)**, una vez registrada la venta pasamos a registrar las ENTRADA's en el Kardex, el cual se lo hace de manera interna.

### Flujo para obtener el top de los productos más vendidos

Para obtener esta información solo es necesario hacer la petición a **(4. Kardex - Obtener los productos más vendidos)**, donde el único parámetro que debemos pasar es el límite.

### Flujo para obtener productos debajo de su umbral establecido

Para obtener esta información solo es necesario hacer la petición a **(5. Kardex - Obtener los productos que están por debajo del umbral)**.

### Flujo para obtener la ganancia en un rango de fechas

Para obtener esta información solo es necesario hacer la petición a **(6. Kardex - Obtener ganancias)**, donde sus parámetros son la fecha de inicio y la fecha final.

### Flujo para actualizar la información de un producto

Este flujo es igual al **Flujo para la creación de productos**, solamente cambia el método **(5.1 Actualizar el Precio o Umbral de un producto)**.

## Pruebas de integración

Para realizar las pruebas de integración es necesario contar con Postman instalado e importar el archivo **Pruebas de integracion de los microservicio.postman_collection.json**, donde ahí se encuentra su respectiva documentación.

## Conclusiones

El producto cumple con los requerimientos dados, pero aún puede ser más escalable si se decide agregar más funcionalidades como los descuentos a clientes frecuentes, compras online, carrito de compras, favoritos y productos con descuentos.
