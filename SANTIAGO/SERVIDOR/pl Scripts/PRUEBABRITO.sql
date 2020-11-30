select ex.id_alumno, alu.nombre, pro.nombre, count(ex.id_alumno)
from examenes ex
inner join alumnosex alu
on ex.id_alumno= alu.id
inner join provincias pro
on alu.id_provincia=pro.id
group by ex.id_alumno, alu.nombre, pro.nombre
having ex.id_alumno not in (select  id_alumno from examenes where nota>4)
and count(ex.id_alumno) = (select count(*) from asignaturaex) ;
