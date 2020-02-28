DELIMITER $$
DROP PROCEDURE IF EXISTS tarer_dispositivo;
CREATE PROCEDURE tarer_dispositivo(
    IN _p_precio1 NUMERIC(6, 2),
    IN _p_precio2 NUMERIC(6, 2)
)
LANGUAGE SQL MODIFIES SQL DATA
BEGIN
    START TRANSACTION;
    SELECT p.nombre,
           p.precio,
           p.tipo
           
      FROM dispositivo
     WHERE p.precio BETWEEN _p_precio1 AND _p_precio2
     ORDER BY p.precio DESC;
    COMMIT;
END$$
DELIMITER ;