DELIMITER $$
DROP PROCEDURE IF EXISTS insertar_dipositivo;
CREATE PROCEDURE insertar_dispositivo(
  IN  _p_nombre  VARCHAR(30),
  IN  _p_precio  NUMERIC(6,2),
  IN  _p_tipo  NUMERIC(6,2),
  OUT _p_confirm INT
)
BEGIN
    SET _p_nombre = UPPER(_p_nombre);
    SET _p_precio = _p_precio = 600;
    SET _p_tipo   = _p_tipo = 6

    INSERT INTO dispositivo
        (nombre,precio, tipo)
        VALUES
        (_p_nombre, _p_precio   , _p_tipo);
    --
    IF ROW_COUNT() > 0 THEN
        SET _p_confirm = 1;
    ELSE
        SET _p_confirm = 0;
    END IF;
END$$
DELIMITER ;