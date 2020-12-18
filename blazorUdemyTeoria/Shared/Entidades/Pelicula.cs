using System;
using System.Collections.Generic;
using System.Text;

namespace BlazorUdemy.Shared.Entidades
{
   public class Pelicula
    {
        public string Imagen { get; set; }
        public string Titulo { get; set; }
        public DateTime Lanzamiento { get; set; }

        public override bool Equals(object obj)
        {
            return obj is Pelicula pelicula &&
                   Titulo == pelicula.Titulo &&
                   Lanzamiento == pelicula.Lanzamiento;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Titulo, Lanzamiento);
        }
    }
}
