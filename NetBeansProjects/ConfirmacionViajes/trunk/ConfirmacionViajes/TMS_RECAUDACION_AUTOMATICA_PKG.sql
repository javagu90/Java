create or replace
PACKAGE TMS_RECAUDACION_AUTOMATICA_PKG
AS

FUNCTION TMS_FIND_TARIFA_FNC(codigo   IN VARCHAR2,servicio IN NUMBER,ruta IN NUMBER,empresa  IN NUMBER,fecha IN TIMESTAMP ) RETURN NUMBER;
FUNCTION TMS_PORCENTAJE_RETENCION_FNC(empresa NUMBER) RETURN NUMBER;
FUNCTION TMS_ULTIMO_SALDO_OPERADOR_FNC(operador VARCHAR2) RETURN NUMBER;
FUNCTION TMS_SERVICIO_ACEPTA_SALDOS_FNC(servicio NUMBER) RETURN VARCHAR2;
PROCEDURE TMS_GUARDAR_GASTOS_CASETAS(terminal number,recaudacion NUMBER, empresa NUMBER, servicio NUMBER, origen number, destino number, usuario number );

FUNCTION TMS_CORTE_ACTUAL(terminal number,usuario NUMBER) RETURN NUMBER;
FUNCTION TMS_FIND_CAJA_FUNC(nombre VARCHAR2) RETURN NUMBER;
FUNCTION TMS_FIND_ACTIVIDAD_FUNC(nombre VARCHAR2) RETURN NUMBER;
FUNCTION TMS_GUARDA_SACTIVIDAD_FNC(corte NUMBER,caja NUMBER, actividad NUMBER,valor VARCHAR2, usuario NUMBER) RETURN NUMBER;
FUNCTION TMS_RECAUDA_TARJETA_FNC(tarjeta NUMBER,corte NUMBER, usuario NUMBER, terminal VARCHAR2, ciudad NUMBER, estado_recaudada NUMBER) RETURN NUMBER;

PROCEDURE TMS_RECAUDA_TARJETAS_PROC;
  
  END TMS_RECAUDACION_AUTOMATICA_PKG;
 
/

create or replace
PACKAGE BODY TMS_RECAUDACION_AUTOMATICA_PKG
AS

FUNCTION TMS_FIND_TARIFA_FNC
  (
    codigo   IN VARCHAR2,
    servicio IN NUMBER,
    ruta     IN NUMBER,
    empresa  IN NUMBER,
    fecha    IN TIMESTAMP
  )
  RETURN NUMBER
IS
  diaSemana VARCHAR2(200);
  tarifa    NUMBER;
BEGIN
  SELECT TO_CHAR(fecha,'DAY') INTO diaSemana FROM dual;
  SELECT tarifa
  INTO tarifa
  FROM
    (SELECT NVL(MAX(
      CASE
        WHEN tr.PARAMETRO_VALOR IS NOT NULL
        THEN tr.PARAMETRO_VALOR
        ELSE (
          CASE
            WHEN UPPER(diaSemana) = 'LUNES' OR UPPER(diaSemana)='MONDAY'
            THEN tr.LUNES_VALOR
            WHEN UPPER(diaSemana) = 'MARTES' OR UPPER(diaSemana)='TUESDAY'
            THEN tr.MARTES_VALOR
            WHEN UPPER(diaSemana) = 'MIÉRCOLES' OR UPPER(diaSemana)='WEDNESDAY'
            THEN tr.MIERCOLES_VALOR
            WHEN UPPER(diaSemana) = 'JUEVES' OR UPPER(diaSemana) = 'THURSDAY'
            THEN tr.JUEVES_VALOR
            WHEN UPPER(diaSemana) = 'VIERNES' OR UPPER(diaSemana) = 'FRIDAY'
            THEN tr.VIERNES_VALOR
            WHEN UPPER(diaSemana) = 'SÁBADO' OR UPPER(diaSemana) = 'SATURDAY'
            THEN tr.SABADO_VALOR
            WHEN UPPER(diaSemana) = 'DOMINGO' OR UPPER(diaSemana) = 'SUNDAY'
            THEN tr.DOMINGO_VALOR
          END)
      END ),0) tarifa
    FROM TMS_TARIFAS_RECAUDA_TBL tr
    INNER JOIN TMS_PARAMETROS_CONFIG_TBL p
    ON tr.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID
    WHERE p.PARAMETRO_CODIGO  = codigo
    AND tr.SERVICIO_ID        = servicio
    AND tr.RUTA_ID            = ruta
    AND tr.EMPRESA_ID         = empresa
    AND fecha                >= tr.FECHA_HORA_TARIFA
    ORDER BY tr.FECHA_HORA_TARIFA DESC
    )
  WHERE rownum < 2;
  RETURN tarifa;
EXCEPTION
WHEN OTHERS THEN
  raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END TMS_FIND_TARIFA_FNC;

FUNCTION TMS_PORCENTAJE_RETENCION_FNC(
    empresa NUMBER)
  RETURN NUMBER
IS
  porcentaje NUMBER :=0;
BEGIN
  SELECT ep.parametro_valor
  INTO porcentaje
  FROM TMS_EMPRESA_PARAMETROS_TBL ep
  INNER JOIN TMS_PARAMETROS_CONFIG_TBL p
  ON p.parametro_config_id = ep.parametro_config_id
  WHERE p.parametro_codigo = 'P_R_PORRET'
  AND empresa_id           = empresa;
  RETURN porcentaje;
EXCEPTION
WHEN OTHERS THEN
  RETURN 0;
END TMS_PORCENTAJE_RETENCION_FNC;

FUNCTION TMS_ULTIMO_SALDO_OPERADOR_FNC(
    operador VARCHAR2)
  RETURN NUMBER
IS
  saldo NUMBER(10,2) :=0;
BEGIN
  SELECT saldo
  INTO saldo
  FROM
    (SELECT r.saldo
    FROM TMS_RECAUDACION_TBL r
    INNER JOIN TMS_TARJETAS_VIAJE_TBL t
    ON t.tarjeta_viaje_id    = r.tarjeta_viaje_id
    WHERE t.operador         = operador
    AND r.estado_recaudacion = 'R'
    ORDER BY r.fecha_creacion DESC
    )
  WHERE rownum < 2;
  RETURN saldo;
END;

FUNCTION TMS_SERVICIO_ACEPTA_SALDOS_FNC(
    servicio NUMBER)
  RETURN VARCHAR2
IS
  acepta VARCHAR(2) := 'N';
BEGIN
  SELECT sp.parametro_valor
  INTO acepta
  FROM TMS_SERVICIO_PARAMETROS_TBL sp
  INNER JOIN TMS_PARAMETROS_CONFIG_TBL p
  ON p.parametro_config_id = sp.parametro_config_id
  WHERE p.parametro_codigo = 'P_R_BUSALACUM'
  AND sp.servicio_id       = servicio;
  RETURN acepta;
EXCEPTION
WHEN OTHERS THEN
  RETURN 'N';
  ROLLBACK;
END TMS_SERVICIO_ACEPTA_SALDOS_FNC;

PROCEDURE TMS_GUARDAR_GASTOS_CASETAS(
    terminal number,
    recaudacion NUMBER,
    empresa     NUMBER,
    servicio    NUMBER,
    origen      NUMBER,
    destino     NUMBER,
    usuario     NUMBER)
IS
  gasto NUMBER;
  costo NUMBER(10,2);
BEGIN
  
  SELECT rg.recaudacion_gasto_id
  INTO gasto
  FROM TMS_SERVICIOS_GASTOS_TBL sg
  INNER JOIN TMS_RECAUDACION_GASTOS_TBL rg
  ON rg.recaudacion_gasto_id      = sg.recaudacion_gasto_id
  WHERE sg.empresa_id             = empresa
  AND sg.servicio_id              = servicio
  AND rg.RECAUDACION_GASTO_NOMBRE ='Casetas';
  
  SELECT SUM(cas.CASETA_COSTO)
  INTO costo
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
  
  if costo is not null then
  INSERT
  INTO tms_recaudacion_lineas_tbl
    (
      recaudacion_linea_id,
      recaudacion_id,
      recaudacion_gasto_id,
      tipo_movimiento,
      recaudacion_valor,
      gasto_impuesto,
      CREADO_POR,
      FECHA_CREACION,
      ULTIMA_ACTUALIZACION_POR,
      ULTIMA_FECHA_ACTUALIZACION
    )
    VALUES
    (
      TERMINAL||tms_RECAUDACION_LIN_SEQ.NEXTVAL,
      recaudacion,
      gasto,
      'GASTO',
      NVL(costo,0),
      0,
      usuario,
      sysdate,
      usuario,
      sysdate
    );
    end if;
EXCEPTION
WHEN OTHERS THEN
  gasto := 0;
END TMS_GUARDAR_GASTOS_CASETAS;

FUNCTION TMS_CORTE_ACTUAL(terminal number,
    usuario NUMBER)
  RETURN NUMBER
IS
  corte NUMBER;
BEGIN
  SELECT corte_id
  INTO corte
  FROM TMS_CORTES_TBL
  WHERE NOMBRE_SESION = 'R_AMPERSA'
  AND ESTADO_CORTE    = 'P'
  AND USUARIO_ID      = usuario;
  RETURN corte;
EXCEPTION
WHEN OTHERS THEN
  INSERT
  INTO TMS_CORTES_TBL
    (
      CORTE_ID,
      NOMBRE_SESION,
      USUARIO_ID,
      ESTADO_CORTE,
      AUTORIZADO_POR,
      CREADO_POR,
      FECHA_CREACION,
      ULTIMA_ACTUALIZACION_POR,
      ULTIMA_FECHA_ACTUALIZACION
    )
    VALUES
    (
      terminal||TMS_CORTES_SEQ .NEXTVAL,
      'R_AMPERSA',
      usuario,
      'P',
      usuario,
      usuario,
      sysdate,
      usuario,
      sysdate
    )
  RETURNING corte_id
  INTO corte;
  RETURN corte;
END;

FUNCTION TMS_FIND_CAJA_FUNC
  (
    nombre VARCHAR2
  )
  RETURN NUMBER
IS
  caja NUMBER;
BEGIN
  SELECT CAJA_ID INTO caja FROM TMS_CAJAS_TBL WHERE CAJA_NOMBRE = nombre;
  RETURN caja;
END;

FUNCTION TMS_FIND_ACTIVIDAD_FUNC(
    nombre VARCHAR2)
  RETURN NUMBER
IS
  actividad NUMBER;
BEGIN
  SELECT actividad_sesion_id
  INTO actividad
  FROM TMS_ACTIVIDADES_SESION_TBL
  WHERE CLAVE_ACTIVIDAD = nombre;
  RETURN actividad;
END;

FUNCTION TMS_GUARDA_SACTIVIDAD_FNC(
    corte     NUMBER,
    caja      NUMBER,
    actividad NUMBER,
    valor     VARCHAR2,
    usuario   NUMBER)
  RETURN NUMBER
IS
  sesion_actividad NUMBER;
BEGIN
  INSERT
  INTO TMS_SESION_ACTIVIDADES_TBL
    (
      SESION_ACTIVIDAD_ID,
      CORTE_ID,
      CAJA_ID,
      EMPRESA_ID,
      ACTIVIDAD_SESION_ID,
      VALOR_ACTIVIDAD,
      FECHA_HORA_ACTIVIDAD,
      CREADO_POR,
      FECHA_CREACION,
      ULTIMA_ACTUALIZACION_POR,
      ULTIMA_FECHA_ACTUALIZACION
    )
    VALUES
    (
      TMS_SESION_ACTIVIDAD_SEQ.NEXTVAL,
      corte,
      caja,
      NULL,
      actividad,
      valor,
      sysdate,
      usuario,
      sysdate,
      usuario,
      sysdate
    )
  RETURNING SESION_ACTIVIDAD_ID
  INTO sesion_actividad;
  RETURN sesion_actividad;
END;

FUNCTION TMS_RECAUDA_TARJETA_FNC
  (
    tarjeta          NUMBER,
    corte            NUMBER,
    usuario          NUMBER,
    terminal         VARCHAR2,
    ciudad           NUMBER,
    estado_recaudada NUMBER
  )
  RETURN NUMBER
IS
  corrida      NUMBER;
  servicio     NUMBER;
  empresa      NUMBER;
  ruta         NUMBER;
  estado       VARCHAR2(200);
  tarifa       NUMBER;
  return_value NUMBER := 0;
  fecha_corrida TIMESTAMP;
  dia_semana                VARCHAR2(200);
  operador                  NUMBER;
  clv_operador              VARCHAR(20);
  operador_aplica_retencion VARCHAR2(2);
  porcentaje_retencion      NUMBER(5,2)  :=0;
  retencion                 NUMBER(5,2)  :=0;
  saldo                     NUMBER(10,2) := 0;
  saldo_operador            NUMBER(10,2) := 0;
  anticipos                 NUMBER(10,2) := 0;
  importe_corrida           NUMBER;
  ocupacion_corrida         NUMBER;
  recaudacion               NUMBER := -1;
  origen                    NUMBER;
  destino                   NUMBER;
  boletos_venta_manual    VARCHAR2(200);
  importe_venta_manual    VARCHAR2(200);
  fecha_recaudacion DATE;
BEGIN
  -- Get corrida and status from tarjeta
  SELECT T.CORRIDA_ID,
    E.NOMBRE_ESTADO,
    t.operador,
    T.ADICIONAL4,
    T.ADICIONAL5,
    t.ULTIMA_FECHA_ACTUALIZACION
  INTO corrida,
    estado,
    clv_operador,
    boletos_venta_manual,
    importe_venta_manual,
    fecha_recaudacion
  FROM TMS_TARJETAS_VIAJE_TBL T
  INNER JOIN TMS_ESTADOS_TARJETA_VIAJE_TBL E
  ON e.estado_tarjeta_viaje_id = t.estado_tarjeta_id
  WHERE T.TARJETA_VIAJE_ID     = tarjeta;
  IF estado                    = 'CONFIRMADA' THEN
    -- Get servicio, empresa, ruta, hora and dia from corrida
    SELECT SERVICIO_ID,
      EMPRESA_ID,
      RUTA_ID,
      fecha_hora_corrida,
      TO_CHAR(fecha_hora_corrida,'DAY'),
      monto_anticipos,
      origen_id,
      destino_id
    INTO servicio,
      empresa,
      ruta,
      fecha_corrida,
      dia_semana,
      anticipos,
      origen,
      destino
    FROM TMS_CORRIDAS_TBL
    WHERE CORRIDA_ID = corrida;
    -- Get aplica_retencion from operador
    SELECT aplica_retencion
    INTO operador_aplica_retencion
    FROM tms_operadores_tbl
    WHERE clave_operador = clv_operador;
    tarifa                      := TMS_FIND_TARIFA_FNC('P_R_PGOTAR',servicio, ruta, empresa, fecha_corrida);
    IF operador_aplica_retencion = 'S' THEN
      -- get porcentaje retencion
      porcentaje_retencion := TMS_PORCENTAJE_RETENCION_FNC(empresa);
      retencion            := (tarifa * porcentaje_retencion / 100);
    END IF;
    IF TMS_SERVICIO_ACEPTA_SALDOS_FNC(servicio) = 'S' THEN
      saldo                                    := TMS_ULTIMO_SALDO_OPERADOR_FNC(clv_operador);
    END IF;
    IF anticipos     IS NOT NULL THEN
      saldo_operador := saldo - anticipos;
    ELSE
      saldo_operador := saldo;
    END IF;
    importe_corrida   := Xer_Tms_Pkg.TMS_VENTA_NETA_CORRIDA_FNC(corrida);
    ocupacion_corrida := Xer_Tms_Pkg.TMS_PASAJEROS_NETO_CORRIDA_FNC(corrida);
    INSERT
    INTO TMS_RECAUDACION_TBL
      (
        RECAUDACION_ID,
        TARJETA_VIAJE_ID,
        CORTE_ID,
        RECAUDACION_REFERENCIA,
        IMPORTE_VENTA_SISTEMA,
        BOLETOS_VENTA_SISTEMA,
        IMPORTE_VENTA_ABORDO,
        BOLETOS_VENTA_ABORDO,
        IMPORTE_VENTA_MANUAL,
        BOLETOS_VENTA_MANUAL,
        FECHA_HORA_RECAUDACION,
        SUELDO_OPERADOR,
        RETENCION,
        ANTICIPOS,
        SALDO,
        ESTADO_RECAUDACION,
        RECAUDADOR_ID,
        AUTORIZADO_POR,
        CIUDAD_RECAUDACION_ID,
        ADICIONAL1,
        ADICIONAL2,
        ADICIONAL3,
        ADICIONAL4,
        ADICIONAL5,
        ADICIONAL6,
        ADICIONAL7,
        ADICIONAL8,
        ADICIONAL9,
        ADICIONAL10,
        CREADO_POR,
        FECHA_CREACION,
        ULTIMA_ACTUALIZACION_POR,
        ULTIMA_FECHA_ACTUALIZACION
      )
      VALUES
      (
        terminal||TMS_RECAUDACION_SEQ.NEXTVAL,
        tarjeta,
        corte,
        terminal||TMS_RECAUDACION_SEQ.NEXTVAL,
        importe_corrida,
        ocupacion_corrida,
        0,0,
        NVL(boletos_venta_manual,0),
        NVL(importe_venta_manual,0),
        fecha_recaudacion,
        tarifa,
        retencion,
        NVL(anticipos,0),
        saldo_operador,
        'R',
        usuario,
        usuario,
        ciudad,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        usuario,
        SYSDATE,
        usuario,
        SYSDATE
      )
    RETURNING RECAUDACION_ID
    INTO recaudacion;
    TMS_GUARDAR_GASTOS_CASETAS(terminal,recaudacion, empresa, servicio, origen , destino , usuario );
    UPDATE TMS_TARJETAS_VIAJE_TBL
    SET ESTADO_TARJETA_ID  = estado_recaudada, ultima_actualizacion_por = usuario, ultima_fecha_actualizacion = SYSDATE
    WHERE TARJETA_VIAJE_ID = tarjeta;
    RETURN recaudacion;
  ELSE
    RETURN 1;
  END IF;
END;

PROCEDURE TMS_RECAUDA_TARJETAS_PROC
IS
  usuario         NUMBER;
  usuarionombre   VARCHAR2(200);
  corte           NUMBER;
  caja            NUMBER;
  actividad       NUMBER;
  terminal        NUMBER;
  ciudad          NUMBER;
  estadorecaudada NUMBER;
  recaudacion     NUMBER;
  numero_tarjetas NUMBER;
BEGIN
  SELECT USUARIO_ID,
    USUARIO_NOMBRE
  INTO usuario,
    usuarionombre
  FROM TMS_USUARIOS_TBL
  WHERE USUARIO_NUMERO = 1004;
  SELECT terminal_id
  INTO terminal
  FROM tms_base_datos_config_tbl
  WHERE esquema_propio = 'S';
  
  IF terminal          < 100 THEN
    terminal          := terminal *10;
  END IF;
  corte               := TMS_CORTE_ACTUAL(terminal,usuario);
  
  caja                := TMS_FIND_CAJA_FUNC('CAJAWEB');
  
  actividad           := TMS_GUARDA_SACTIVIDAD_FNC(corte ,caja, TMS_FIND_ACTIVIDAD_FUNC('INISES'), usuarionombre||'-RECAUT-Recaudacion', usuario);
  
  
  
  SELECT ESTADO_ID INTO ciudad FROM TMS_ESTADOS_TBL WHERE ESTADO_NOMBRE = 'WEB';
  
  SELECT ESTADO_TARJETA_VIAJE_ID
  INTO estadorecaudada
  FROM TMS_ESTADOS_TARJETA_VIAJE_TBL
  WHERE NOMBRE_ESTADO = 'RECAUDADA';
  
  FOR tarjetas       IN
  (SELECT t.TARJETA_VIAJE_ID
  FROM tms_tarjetas_viaje_tbl t
  INNER JOIN tms_estados_tarjeta_viaje_tbl eds on eds.estado_tarjeta_viaje_id = t.estado_tarjeta_id
  WHERE eds.NOMBRE_ESTADO = 'CONFIRMADA'
  )
  LOOP
    dbms_output.put_line('Recaudar Tarjeta = '||tarjetas.TARJETA_VIAJE_ID); 
    recaudacion := TMS_RECAUDA_TARJETA_FNC(tarjetas.TARJETA_VIAJE_ID, corte, usuario, terminal, ciudad, estadorecaudada);
    dbms_output.put_line('Tarjeta recaudada ');
  END LOOP;
  actividad           := TMS_GUARDA_SACTIVIDAD_FNC(corte ,caja, TMS_FIND_ACTIVIDAD_FUNC('FINSES'), usuarionombre||'-RECAUT-Recaudacion', usuario);
  COMMIT;
EXCEPTION
WHEN OTHERS THEN
ROLLBACK;
dbms_output.put_line('Exception = '||SQLERRM); 
END;

END TMS_RECAUDACION_AUTOMATICA_PKG;