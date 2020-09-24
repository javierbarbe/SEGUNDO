using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AgapeaNETCORE.Infraestructura.EndPoints;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Routing; 
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

namespace AgapeaNETCORE
{
    public class Startup
    {
        #region ".....propiedades de clase"
        public IConfiguration Configuration { get; }
        #endregion

        #region "....constructores..."
         public Startup(IConfiguration configuration)
                {
                    Configuration = configuration;
                }

        #endregion

        #region "...metodos de la clase..."

        #endregion

        #region ".....metodos privados o internos de la clase"

        #endregion

        #region "...metodos PUBLICOS de la clase...."
             // This method gets called by the runtime. Use this method to add services to the container.
            public void ConfigureServices(IServiceCollection services)
            {
                services.AddControllersWithViews();
            }

            // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
            public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
            {
                if (env.IsDevelopment())
                {
                    app.UseDeveloperExceptionPage();
                }
                else
                {
                    app.UseExceptionHandler("/Home/Error");
                    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                    app.UseHsts();
                }

                // ----- DEFINICION DE MODULOS DE LA PIPELINE -------
                app.UseHttpsRedirection();
                app.UseStaticFiles();

                app.UseRouting(); 

                app.UseAuthorization();  // variables de sesion ( autentificacion de usuarios )

            // middleware que detecta que endpoint va a ejecutar el modulo de enrutamiento..
            // ponerlo siempre tras el modulo app.UseRouting()....
            // el metodo USE definde el mildware necesita como parametro una funcion LAMBDA, con dos parametros, el 1º objeto HttpContext, el 2º RequestDelegate
            // 
            app.Use(async (contexto, next) => {
                Endpoint end = contexto.GetEndpoint();
                if (end != null)
                {
                    await contexto.Response.WriteAsync($"el endpoint seleccionado es: {end.DisplayName}");
                }
                else {
                    await contexto.Response.WriteAsync($"ningun endpoint seleccionado, no se cumple ningun patron");
                }
                await next(); // invoca al siguiente modulo del middleware
            });

                app.UseEndpoints(endpoints =>
                {
                    // ------------ 1º endpoint ----------------------
                    endpoints.MapControllerRoute(
                        name: "default",
                        pattern: "{controller=Home}/{action=Index}/{id?}").WithDisplayName("endpoint por defecto");

                    // ------------ 2º endpoint ---------------------
                    endpoints.MapGet("/capitalPais/{pais}", CapitalPaises.EndPoint);
                });
            }

        #endregion
       

        

        
    }
}
