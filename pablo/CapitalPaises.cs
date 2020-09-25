using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

using Microsoft.AspNetCore.Routing;
using Microsoft.AspNetCore.Http;

namespace AgapeaNETCORE.Infraestructura.EndPoints
{
    // clase que va a definir metodo a invocar por un ENDPOINT
    // deberia ser estatico y x defecto cogera el segmento literal ( el mas restrictivo )
    // ?? operador C# que significa que si lo anterior es null mete el valor que haya " "
    public class CapitalPaises
    {
        public static async Task EndPoint(HttpContext contexto) {
            String pais = contexto.Request.RouteValues["pais"] as String ?? "España";
            String capital = " ";
            switch (pais.ToLower()) {
                case "españa":
                    capital = "Madrid";
                        break;
                case "francia":
                    capital = "Paris";
                        break;
                default:
                    capital = "NO LO CONOZCO";
                    break;
            }
          await contexto.Response.WriteAsync($"El pais: {pais} tiene de capital: {capital}");
        }
    }
}
