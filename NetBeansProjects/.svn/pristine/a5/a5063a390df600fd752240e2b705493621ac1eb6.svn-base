BEGIN
DBMS_SCHEDULER.CREATE_JOB (
   job_name           =>  'tms_recaudacion_automatica_job',
   job_type           =>  'STORED_PROCEDURE',
   job_action         =>  'TMS_RECAUDACION_AUTOMATICA_PKG.TMS_RECAUDA_TARJETAS_PROC',
   start_date         =>  '01-MAR-14 12.00.00 AM',
   repeat_interval    =>  'FREQ=DAILY;INTERVAL=1', /* every other day */
   end_date           =>  '31-DIC-99 12.00.00 AM',
   job_class          =>  'batch_update_jobs',
   comments           =>  'Job de Recaudacion Automatica');
END;