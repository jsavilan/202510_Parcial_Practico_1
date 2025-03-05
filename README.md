# Parcial Práctico 1

## Nombre, Apellido y codigo

Juan Sebastián Ávila Nivia - 202320465

## Instrucciones

1. Haga un _fork_ de este repositorio
2. Clone el repositorio bifurcado en su máquina virtual
3. Abra el proyecto en Visual Studio Code (o en el editor de su preferencia)
4. Lea el enunciado completamente antes de iniciar

## Punto 1 (40%). Persistencia

Esta aplicación tiene el propósito de crear un sistema de información para una clínica.

(15%) Cree la entidad _MedicoEntity_ en la carpeta correspondiente. Un médico tiene un nombre, un apellido, un registro médico y un id de tipo _Long_ que representa su llave primaria.

(15%) Cree la entidad _EspecialidadEntity_ en la carpeta correspondiente. Una especialidad tiene un nombre, una descripción y un id de tipo _Long_ que representa su llave primaria.

Tenga en cuenta que un médico puede tener varias especialiades y que una especialidad puede ser ejercida por varios médicos.

(10%) Cree los repositorios para _MedicoEntity_ y _EspecialidadEntity_.

## Punto 2 (40%). Lógica

(10%) Cree la clase correspondiente para la lógica de médico. Implemente unicamente el método _createMedicos_. Valide que el registro médico inicie solo con los caracteres "RM" (e.g., RM1745).

(10%) Cree la clase correspondiente para la lógica de especialidad. Implemente unicamente el método _createEspecialidad_. Valide que la descripción tenga como mínimo 10 caracteres.

(20%) Cree la clase correspondiente para la lógica de la asociación entre médico y especialidad. Implemente unicamente el método _addEspecialidad_. Este método recibe como parámetro el id del médico, el id de la especidad y le agrega la especialidad al médico. Valide que tanto el médico como la especialidad existan.

## Punto 3 (20%). Prueba de lógica

(5%) Implemente las pruebas para el método _createMedicos_ del servicio de médico. Asegúrese de crear dos pruebas: una donde el médico se crea correctamente y otra donde se lanza una excepción de negocio por la violación de la regla de negocio.

(5%) Implemente las pruebas para el método _createEspecialidad_ del servicio de especialidad. Asegúrese de crear dos pruebas: una donde la especialidad se crea correctamente y otra donde se lanza una excepción de negocio por la violación de la regla de negocio.

(10%) Implemente las pruebas para el método _addEspecialidad_ del servicio de la asociación. Asegúrese de crear tres pruebas: una donde la especialidad se agrega correctamente al médico; otra donde se lanza una excepción de negocio porque el médico no existe; y otra donde se lanza una excepción porque la especidad no existe.
