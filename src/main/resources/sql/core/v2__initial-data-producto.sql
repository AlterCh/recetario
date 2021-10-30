--
-- Unidades básicas
insert into producto (id, cantidad, nombre, precio, stock, unidad)
values (1, 2.0, 'producto1', 2.5, 3.5, select u from unidad u where u.nombre ="GRAMO"),
       (2, 2.0, 'producto2', 2.5, 3.5, select u from unidad u where u.nombre ="GRAMO"),
       (3, 2.0, 'producto3', 2.5, 3.5, select u from unidad u where u.nombre ="GRAMO");