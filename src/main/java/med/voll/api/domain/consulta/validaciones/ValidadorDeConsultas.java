package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.DatosAgendarConsulta;

public interface ValidadorDeConsultas {
    public void validar(DatosAgendarConsulta datos); //esta seria la firma que representaria a todas las validaciones.
}



/*
Sin usar Interfaces tendríamos que instanciar en teoría cada una de esas clases,(validaciones) y pasar el parámetro datos al método validar que se encuentra dentro de la clase para poder validar, para
realizar esas validaciones de las reglas de negocio. Pero de esa forma no es la forma más adecuada, no sería una buena forma, ya que a la hora de que tengamos que eliminar alguna de esas validaciones,
porque ya no tiene sentido dentro del negocio, tendríamos que ir a eliminar esa clase, ese validador y luego venir a la clase de servicio, intervenir dentro de la clase de servicio y modificarlo,
eliminando ese atributo, ese parámetro que estamos instanciando.
Entonces usamos un método que nos permite seguir los principios solid, el pilar de polimorfismo que nos permite adaptar una interface a múltiples finalidades, y va a dejar el código con la mejor presentación posible.

Las interfaces, donde nosotros tenemos múltiples clases que implementan el mismo método con la misma firma, una misma funcionalidad.
Se crea una interface para indicarle a los desarrolladores que eso va a hacer un patrón que se debe seguir.

 */