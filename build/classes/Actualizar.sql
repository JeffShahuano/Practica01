

DELIMITER $$
DROP PROCEDURE IF EXISTS actualizar_dispositivo;
CREATE PROCEDURE actualizar_dispositivo(
  IN  _p_nombre VARCHAR(30),
  IN  _p_precio NUMERIC(6,2),
  IN  _p_tipo  NUMERIC(6,2),
  OUT _p_confirm INT
)
BEGIN
    --
    UPDATE dispositivo
       SET nombre = _p_nombre,
           precio = _p_precio,
     WHERE nombre = _p_nombre
    --
    IF ROW_COUNT() > 0 THEN
        SET _p_confirm = 1;
    ELSE
        SET _p_confirm = 0;
    END IF;
END$$
DELIMITER ;