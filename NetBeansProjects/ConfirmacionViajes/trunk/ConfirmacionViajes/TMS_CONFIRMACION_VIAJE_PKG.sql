create or replace
PACKAGE TMS_CONFIRMACION_VIAJE_PKG AS 
FUNCTION TMS_PREFIJO_FNC(nombre VARCHAR2) RETURN VARCHAR2;
PROCEDURE TMS_FIND_TERMINAL_PRC(prefijo VARCHAR2, RESULT_CURSOR OUT SYS_REFCURSOR);
PROCEDURE TMS_FIND_BY_TERMINAL_PRC(origen NUMBER, RESULT_CURSOR OUT SYS_REFCURSOR);
PROCEDURE TMS_ALL_TERMINALS_PRC(RESULT_CURSOR OUT SYS_REFCURSOR);
PROCEDURE TMS_FIND_SESION(sessionnumber IN NUMBER, RESULT_CURSOR OUT SYS_REFCURSOR );
PROCEDURE TMS_FIND_USUARIO_PRC(usuario NUMBER, RESULT_CURSOR OUT SYS_REFCURSOR);
PROCEDURE TARJETAS_VIAJE_POR_FOLIO(folio    IN VARCHAR2,origen IN NUMBER := NULL,dblink VARCHAR2, RESULT_CURSOR OUT SYS_REFCURSOR );
PROCEDURE TARJETAS_VIAJE_POR_OPERADOR(clave IN VARCHAR2, ORIGEN NUMBER,dblink VARCHAR2, RESULT_CURSOR OUT SYS_REFCURSOR );
FUNCTION TMS_GET_DBLINK_FNC(ORIGEN NUMBER) RETURN VARCHAR2;
FUNCTION TMS_ESTATUS_TARJETA_FNC( tarjeta_id NUMBER) RETURN VARCHAR2;
PROCEDURE TMS_UPDATE_TARJETA_PROC(
    tarjeta_id IN NUMBER,
    usuario_id IN NUMBER,
    clave_operador VARCHAR2,
    boletos_venta_manual VARCHAR2,
    importe_venta_manual VARCHAR2);
PROCEDURE CONFIRMAR_TARJETA(
    tarjeta_id  IN NUMBER,
    usuario_id  IN NUMBER,
    origen IN NUMBER,
    clave_operador VARCHAR2, 
    boletos_venta_manual VARCHAR2,
    importe_venta_manual VARCHAR2,
    return_code OUT NUMBER,
    error_message OUT VARCHAR2);
PROCEDURE TMS_BUSCAR_IMPRESORA_SP(NOMBRE_CAJA VARCHAR2, RESULT_CURSOR OUT SYS_REFCURSOR);
PROCEDURE TMS_GET_VALORES_SP(
    TARJETA_ID NUMBER,
    OPERADOR   VARCHAR2,
    SUELDO OUT NUMBER,
    RETENCION OUT NUMBER,
    PAGOOPERADOR OUT NUMBER,
    saldo_operador OUT NUMBER);
END TMS_CONFIRMACION_VIAJE_PKG;

/

create or replace
PACKAGE BODY TMS_CONFIRMACION_VIAJE_PKG
AS
FUNCTION TMS_PREFIJO_FNC(
    nombre VARCHAR2)
  RETURN VARCHAR2
IS
BEGIN
  RETURN SUBSTR(SUBSTR(nombre,0,4)||'____',0,4);
END TMS_PREFIJO_FNC;
PROCEDURE TMS_FIND_TERMINAL_PRC(
    prefijo VARCHAR2,
    RESULT_CURSOR OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN RESULT_CURSOR FOR SELECT * FROM TMS_BASE_DATOS_CONFIG_TBL WHERE TMS_PREFIJO_FNC(NOMBRE_TERMINAL) = prefijo;
END TMS_FIND_TERMINAL_PRC;
PROCEDURE TMS_FIND_BY_TERMINAL_PRC(
    origen NUMBER,
    RESULT_CURSOR OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN RESULT_CURSOR FOR SELECT * FROM TMS_BASE_DATOS_CONFIG_TBL WHERE terminal_id = origen;
END TMS_FIND_BY_TERMINAL_PRC;
PROCEDURE TMS_ALL_TERMINALS_PRC(
    RESULT_CURSOR OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN RESULT_CURSOR FOR SELECT * FROM TMS_BASE_DATOS_CONFIG_TBL ORDER BY nombre_terminal;
END TMS_ALL_TERMINALS_PRC ;
PROCEDURE TMS_FIND_SESION(
    sessionnumber IN NUMBER,
    RESULT_CURSOR OUT SYS_REFCURSOR )
IS
BEGIN
  OPEN RESULT_CURSOR FOR SELECT t.* FROM Tms_Sesiones_Global_Tbl t WHERE t.numero_Sesion = sessionnumber;
END TMS_FIND_SESION;
PROCEDURE TMS_FIND_USUARIO_PRC(
    usuario NUMBER,
    RESULT_CURSOR OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN RESULT_CURSOR FOR SELECT * FROM TMS_USUARIOS_TBL WHERE USUARIO_ID = usuario;
END TMS_FIND_USUARIO_PRC;
PROCEDURE TARJETAS_VIAJE_POR_FOLIO(
    folio  IN VARCHAR2,
    origen IN NUMBER := NULL,
    dblink VARCHAR2,
    RESULT_CURSOR OUT SYS_REFCURSOR )
IS
BEGIN
  OPEN RESULT_CURSOR FOR 'SELECT T.TARJETA_VIAJE_ID, T.FOLIO_TARJETA, CS.CLAVE_CORRIDA, CS.FECHA_HORA_CORRIDA,     
OPS.CLAVE_OPERADOR, OPS.OPERADOR_NOMBRE_COMPLETO, T.AUTOBUS, S.SERVICIO_NOMBRE SERVICIO, CS.EMPRESA_ID, ES.EMPRESA_NOMBRE,
OS.ESTADO_NOMBRE ORIGEN, DS.ESTADO_NOMBRE DESTINO, T.ADICIONAL4 BOLETOS_VENTA_MANUAL, T.ADICIONAL5 IMPORTE_VENTA_MANUAL, ET.NOMBRE_ESTADO AS ESTADO_TARJETA     
FROM TMS_TARJETAS_VIAJE_TBL@'||dblink||' T     
INNER JOIN TMS_CORRIDAS_TBL@'||dblink||' CS ON CS.CORRIDA_ID = T.CORRIDA_ID     
INNER JOIN TMS_OPERADORES_TBL@'||dblink||' OPS ON OPS.CLAVE_OPERADOR = T.OPERADOR     
INNER JOIN TMS_RUTAS_TBL@'||dblink||' R ON R.RUTA_ID = CS.RUTA_ID     
INNER JOIN TMS_ESTADOS_TBL@'||dblink||' DS ON DS.ESTADO_ID = R.TRAMO_DESTINO_ID     
INNER JOIN TMS_ESTADOS_TBL@'||dblink||' OS ON OS.ESTADO_ID = R.TRAMO_ORIGEN_ID     
INNER JOIN TMS_SERVICIOS_TBL@'||dblink||' S ON S.SERVICIO_ID = R.SERVICIO_ID
INNER JOIN tms_empresas_tbl@'||dblink||' ES ON ES.EMPRESA_ID = CS.EMPRESA_ID     
INNER JOIN TMS_ESTADOS_TARJETA_VIAJE_TBL@'||dblink||
  ' ET ON ET.ESTADO_TARJETA_VIAJE_ID = T.ESTADO_TARJETA_ID     
WHERE T.FOLIO_TARJETA = '''||folio||''' AND R.TRAMO_ORIGEN_ID = '||origen ||' AND R.ADICIONAL5 = ''S'' '||
'and (SELECT E.PARAMETRO_VALOR
    FROM TMS_PARAMETROS_CONFIG_TBL@'||dblink||' P
    LEFT JOIN TMS_EMPRESA_PARAMETROS_TBL@'||dblink||' e on E.PARAMETRO_CONFIG_ID = P.PARAMETRO_CONFIG_ID
    WHERE P.PARAMETRO_CODIGO      = ''P_VLRPERREC''
    AND E.EMPRESA_ID = CS.EMPRESA_ID ) = ''S''';
END;
PROCEDURE TARJETAS_VIAJE_POR_OPERADOR(
    clave IN VARCHAR2,
    ORIGEN NUMBER,
    dblink VARCHAR2,
    RESULT_CURSOR OUT SYS_REFCURSOR )
IS
BEGIN
  OPEN RESULT_CURSOR FOR 'SELECT T.TARJETA_VIAJE_ID, T.FOLIO_TARJETA, CS.CLAVE_CORRIDA, CS.FECHA_HORA_CORRIDA,  
OPS.CLAVE_OPERADOR, OPS.OPERADOR_NOMBRE_COMPLETO, T.AUTOBUS, S.SERVICIO_NOMBRE SERVICIO, CS.EMPRESA_ID, ES.EMPRESA_NOMBRE,
OS.ESTADO_NOMBRE ORIGEN, DS.ESTADO_NOMBRE DESTINO, T.ADICIONAL4 BOLETOS_VENTA_MANUAL, T.ADICIONAL5 IMPORTE_VENTA_MANUAL, ET.NOMBRE_ESTADO AS ESTADO_TARJETA  
FROM TMS_TARJETAS_VIAJE_TBL@'||dblink||' T  
INNER JOIN TMS_CORRIDAS_TBL@'||dblink||' CS ON CS.CORRIDA_ID = T.CORRIDA_ID  
INNER JOIN TMS_OPERADORES_TBL@'||dblink||' OPS ON OPS.CLAVE_OPERADOR = T.OPERADOR  
INNER JOIN TMS_RUTAS_TBL@'||dblink||' R ON R.RUTA_ID = CS.RUTA_ID  
INNER JOIN TMS_ESTADOS_TBL@'||dblink||' DS ON DS.ESTADO_ID = R.TRAMO_DESTINO_ID  
INNER JOIN TMS_ESTADOS_TBL@'||dblink||' OS ON OS.ESTADO_ID = R.TRAMO_ORIGEN_ID  
INNER JOIN TMS_SERVICIOS_TBL@'||dblink||' S ON S.SERVICIO_ID = R.SERVICIO_ID  
INNER JOIN tms_empresas_tbl@'||dblink||' ES ON ES.EMPRESA_ID = CS.EMPRESA_ID   
INNER JOIN TMS_ESTADOS_TARJETA_VIAJE_TBL@'||dblink||
  ' ET ON ET.ESTADO_TARJETA_VIAJE_ID = T.ESTADO_TARJETA_ID  
WHERE T.OPERADOR = '''||clave||''' AND ET.NOMBRE_ESTADO IN (''ABIERTA'',''CONFIRMADA'')  
AND R.TRAMO_ORIGEN_ID = '||origen||' AND R.ADICIONAL5 = ''S'' '||
'and (SELECT E.PARAMETRO_VALOR
    FROM TMS_PARAMETROS_CONFIG_TBL@'||dblink||' P
    LEFT JOIN TMS_EMPRESA_PARAMETROS_TBL@'||dblink||' e on E.PARAMETRO_CONFIG_ID = P.PARAMETRO_CONFIG_ID
    WHERE P.PARAMETRO_CODIGO      = ''P_VLRPERREC''
    AND E.EMPRESA_ID = CS.EMPRESA_ID ) = ''S''';
END TARJETAS_VIAJE_POR_OPERADOR;
FUNCTION TMS_GET_DBLINK_FNC(
    ORIGEN NUMBER)
  RETURN VARCHAR2
IS
  DBLINK VARCHAR2(100);
BEGIN
  SELECT NOMBRE_DBLINK
  INTO DBLINK
  FROM TMS_BASE_DATOS_CONFIG_TBL
  WHERE TERMINAL_ID = ORIGEN;
  RETURN DBLINK;
EXCEPTION
WHEN NO_DATA_FOUND THEN
  RETURN '';
END;
FUNCTION TMS_ESTATUS_TARJETA_FNC(
    tarjeta_id NUMBER)
  RETURN VARCHAR2
IS
  exists_tarjeta  NUMBER := 0;
  estatus_tarjeta VARCHAR2(20);
BEGIN
  SELECT COUNT(TARJETA_VIAJE_ID)
  INTO exists_tarjeta
  FROM tms_tarjetas_viaje_tbl
  WHERE TARJETA_VIAJE_ID = tarjeta_id;
  IF exists_tarjeta      > 0 THEN
    SELECT NOMBRE_ESTADO
    INTO estatus_tarjeta
    FROM TMS_ESTADOS_TARJETA_VIAJE_TBL ET
    INNER JOIN TMS_TARJETAS_VIAJE_TBL T
    ON t.estado_tarjeta_iD   = et.estado_tarjeta_viaje_id
    WHERE t.tarjeta_viaje_id = tarjeta_id;
    RETURN estatus_tarjeta;
  ELSE
    RETURN '';
  END IF;
END;
PROCEDURE TMS_UPDATE_TARJETA_PROC(
    tarjeta_id IN NUMBER,
    usuario_id IN NUMBER,
    clave_operador       VARCHAR2,
    boletos_venta_manual VARCHAR2,
    importe_venta_manual VARCHAR2)
IS
  confirmada_id     NUMBER;
  exists_confirmada NUMBER := 0;
  terminal          VARCHAR2(100);
BEGIN
  SELECT COUNT(estado_tarjeta_viaje_id)
  INTO exists_confirmada
  FROM tms_estados_tarjeta_viaje_tbl
  WHERE nombre_estado  = 'CONFIRMADA';
  IF exists_confirmada = 0 THEN
    INSERT
    INTO TMS_ESTADOS_TARJETA_VIAJE_TBL
      (
        ESTADO_TARJETA_VIAJE_ID,
        NOMBRE_ESTADO,
        HABILITADO,
        DESCRIPCION_ESTADO,
        CREADO_POR,
        ULTIMA_ACTUALIZACION_POR,
        FECHA_CREACION,
        ULTIMA_FECHA_ACTUALIZACION
      )
      VALUES
      (
        5,
        'CONFIRMADA',
        'S',
        'ESTADO DE LA TARJETA CUANDO ES CONFIRMADA LA CORRIDA',
        usuario_id,
        usuario_id,
        CURRENT_TIMESTAMP(3),
        CURRENT_TIMESTAMP(3)
      );
  END IF;
  SELECT estado_tarjeta_viaje_id
  INTO confirmada_id
  FROM tms_estados_tarjeta_viaje_tbl
  WHERE nombre_estado = 'CONFIRMADA';
  SELECT dbs.NOMBRE_TERMINAL
  INTO terminal
  FROM TMS_TARJETAS_VIAJE_TBL t
  INNER JOIN TMS_CORRIDAS_TBL cs
  ON cs.CORRIDA_ID = t.CORRIDA_ID
  INNER JOIN TMS_BASE_DATOS_CONFIG_TBL dbs
  ON dbs.TERMINAL_ID       = cs.ORIGEN_ID
  WHERE t.TARJETA_VIAJE_ID = tarjeta_id;
  UPDATE TMS_TARJETAS_VIAJE_TBL
  SET ESTADO_TARJETA_ID        = confirmada_id,
    OPERADOR                   = clave_operador,
    ADICIONAL4                 = boletos_venta_manual,
    ADICIONAL5                 = importe_venta_manual,
    ULTIMA_ACTUALIZACION_POR   = usuario_id,
    ULTIMA_FECHA_ACTUALIZACION = CURRENT_TIMESTAMP(3),
    replicacion_estado         = 'P',
    replicacion_intentos       = 0,
    replicacion_origen         = terminal
  WHERE TARJETA_VIAJE_ID       = tarjeta_id;
END;
PROCEDURE CONFIRMAR_TARJETA(
    tarjeta_id IN NUMBER,
    usuario_id IN NUMBER,
    origen     IN NUMBER,
    clave_operador       VARCHAR2,
    boletos_venta_manual VARCHAR2,
    importe_venta_manual VARCHAR2,
    return_code OUT NUMBER,
    error_message OUT VARCHAR2)
IS
  dblink          VARCHAR2(100);
  exists_tarjeta  NUMBER := 0;
  estatus_tarjeta VARCHAR2(20);
BEGIN
  dblink    := TMS_GET_DBLINK_FNC(origen);
  IF dblink IS NOT NULL THEN
    EXECUTE immediate 'begin :result := TMS_CONFIRMACION_VIAJE_PKG.TMS_ESTATUS_TARJETA_FNC@'||dblink||'(:tarjeta); end;' USING OUT estatus_tarjeta,
                      IN tarjeta_id;
    IF estatus_tarjeta = 'ABIERTA' THEN
      EXECUTE immediate 'begin TMS_CONFIRMACION_VIAJE_PKG.TMS_UPDATE_TARJETA_PROC@'||dblink||'(:tarjeta, :usuario, :operador, :boletos, :importe); end;' USING tarjeta_id,
      usuario_id,
      clave_operador,
      boletos_venta_manual,
      importe_venta_manual;
    elsif estatus_tarjeta = '' THEN
      raise_application_error(-20404, 'La tarjeta no existe');
    ELSE
      raise_application_error(-20401, 'La tarjeta esta en estatus '||estatus_tarjeta);
    END IF;
  ELSE
    raise_application_error(-20403, 'Origen invalido'||dblink);
  END IF;
END CONFIRMAR_TARJETA;
PROCEDURE TMS_BUSCAR_IMPRESORA_SP(
    NOMBRE_CAJA VARCHAR2,
    RESULT_CURSOR OUT SYS_REFCURSOR)
IS
BEGIN
  OPEN RESULT_CURSOR FOR SELECT im.impresora_id,
  IM.IMPRESORA_NOMBRE,
  CI.SALIDA_IMPRESION PUERTO,
  ci.actividad_impresora formato 
  FROM TMS_CAJAS_TBL CS 
  LEFT JOIN TMS_CAJAS_IMPRESORAS_TBL CI ON CI.CAJA_ID = CS.CAJA_ID 
  LEFT JOIN TMS_IMPRESORAS_TBL IM ON IM.IMPRESORA_ID = CI.IMPRESORA_ID 
  WHERE UPPER(CS.CAJA_NOMBRE) = UPPER(NOMBRE_CAJA) AND CI.ACTIVIDAD_IMPRESORA = 'TICKETS';
END;
PROCEDURE TMS_GET_VALORES_SP(
    TARJETA_ID NUMBER,
    OPERADOR   VARCHAR2,
    SUELDO OUT NUMBER,
    RETENCION OUT NUMBER,
    PAGOOPERADOR OUT NUMBER,
    saldo_operador OUT NUMBER)
IS
  SERVICIO NUMBER;
  EMPRESA  NUMBER;
  RUTA     NUMBER;
  FECHA_CORRIDA TIMESTAMP;
  DIA_SEMANA                VARCHAR2(20);
  ANTICIPOS                 NUMBER(5,2);
  ORIGEN                    NUMBER;
  DESTINO                   NUMBER;
  DBLINK                    VARCHAR2(200);
  operador_aplica_retencion VARCHAR2(2);
  porcentaje_retencion      NUMBER(5,2)  :=0;
  saldo                     NUMBER(10,2) := 0;
BEGIN
  RETENCION := 0;
  PAGOOPERADOR := 0;
  SELECT NOMBRE_DBLINK
  INTO DBLINK
  FROM TMS_BASE_DATOS_CONFIG_TBL
  WHERE nombre_terminal = 'CENTRAL';
  SELECT CS.SERVICIO_ID,
    CS.EMPRESA_ID,
    CS.RUTA_ID,
    CS.fecha_hora_corrida,
    TO_CHAR(CS.fecha_hora_corrida,'DAY'),
    CS.monto_anticipos,
    CS.origen_id,
    CS.destino_id
  INTO servicio,
    empresa,
    ruta,
    fecha_corrida,
    dia_semana,
    anticipos,
    origen,
    destino
  FROM TMS_CORRIDAS_TBL CS
  INNER JOIN tms_tarjetas_viaje_tbl TS
  ON TS.CORRIDA_ID       = CS.CORRIDA_ID
  WHERE TARJETA_VIAJE_ID = TARJETA_ID;
  EXECUTE immediate 'begin :result := TMS_RECAUDACION_AUTOMATICA_PKG.TMS_FIND_TARIFA_FNC@'||dblink||'(''P_R_PGOTAR'',:servicio, :ruta, :empresa, :fecha_corrida); end;'
  USING OUT SUELDO,
  IN servicio,
  IN ruta,
  IN empresa,
  IN fecha_corrida;
  -- Aplica retencion
  SELECT aplica_retencion
  INTO operador_aplica_retencion
  FROM tms_operadores_tbl
  WHERE clave_operador         = OPERADOR;
  IF operador_aplica_retencion = 'S' THEN
    -- get porcentaje retencion
    porcentaje_retencion := TMS_RECAUDACION_AUTOMATICA_PKG.TMS_PORCENTAJE_RETENCION_FNC(empresa);
    RETENCION            := (SUELDO * porcentaje_retencion / 100);
  END IF;
  IF TMS_RECAUDACION_AUTOMATICA_PKG.TMS_SERVICIO_ACEPTA_SALDOS_FNC(servicio) = 'S' THEN
    saldo                                    := TMS_RECAUDACION_AUTOMATICA_PKG.TMS_ULTIMO_SALDO_OPERADOR_FNC(OPERADOR);
  END IF;
  IF anticipos     IS NOT NULL THEN
    saldo_operador := saldo - anticipos;
  ELSE
    saldo_operador := saldo;
  END IF;
  SELECT SUM(cas.CASETA_COSTO)
  INTO PAGOOPERADOR
  FROM TMS_CASETAS_TBL cas
  INNER JOIN TMS_TRAMOS_CASETAS_TBL tracas
  ON tracas.CASETA_ID = cas.CASETA_ID
  INNER JOIN tms_tramos_tbl tram
  ON tracas.TRAMO_ID = tram.TRAMO_ID
  INNER JOIN tms_rutas_tbl rut
  ON tram.RUTA_ID           = rut.RUTA_ID
  WHERE rut.SERVICIO_ID     = servicio
  AND tram.TRAMO_ORIGEN_ID  = origen
  AND tram.TRAMO_DESTINO_ID = destino;
END;
END TMS_CONFIRMACION_VIAJE_PKG;
