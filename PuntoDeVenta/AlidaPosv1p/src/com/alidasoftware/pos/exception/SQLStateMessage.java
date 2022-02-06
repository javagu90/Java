package com.alidasoftware.pos.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SQLStateMessage {

	private static final Map<String, String> sqlCodeMap;
	
    static {
        Map<String, String> codeMap = new HashMap<String, String>();
        
        //Class 00 — Successful Completion
        codeMap.put("00000","successful_completion");
        
        //Class 01 — Warning
        codeMap.put("01000","warning");
        codeMap.put("0100C","dynamic_result_sets_returned");
        codeMap.put("01008","implicit_zero_bit_padding");
        codeMap.put("01003","null_value_eliminated_in_set_function");
        codeMap.put("01007","privilege_not_granted");
        codeMap.put("01006","privilege_not_revoked");
        codeMap.put("01004","string_data_right_truncation");
        codeMap.put("01P01","deprecated_feature");
        
        //Class 02 — No Data (this is also a warning class per the SQL standard)
        codeMap.put("02000","no_data");
        codeMap.put("02001","no_additional_dynamic_result_sets_returned");
        
        //Class 03 — SQL Statement Not Yet Complete
        codeMap.put("03000","sql_statement_not_yet_complete");

        //Class 08 — Connection Exception
        codeMap.put("08000","connection_exception");
        codeMap.put("08003","connection_does_not_exist");
        codeMap.put("08006","connection_failure");
        codeMap.put("08001","sqlclient_unable_to_establish_sqlconnection");
        codeMap.put("08004","sqlserver_rejected_establishment_of_sqlconnection");
        codeMap.put("08007","transaction_resolution_unknown");
        codeMap.put("08P01","protocol_violation");
        
        //Class 09 — Triggered Action Exception
        codeMap.put("09000","triggered_action_exception");
        
        //Class 0A — Feature Not Supported
        codeMap.put("0A000","feature_not_supported");
        
        //Class 0B — Invalid Transaction Initiation
        codeMap.put("0B000","invalid_transaction_initiation");
        
        //Class 0F — Locator Exception
        codeMap.put("0F000","locator_exception");
        codeMap.put("0F001","invalid_locator_specification");
        
        //Class 0L — Invalid Grantor
        codeMap.put("0L000","invalid_grantor");
        codeMap.put("0LP01","invalid_grant_operation");
        
        //Class 0P — Invalid Role Specification
        codeMap.put("0P000","invalid_role_specification");
        
        //Class 0Z — Diagnostics Exception
        codeMap.put("0Z000","diagnostics_exception");
        codeMap.put("0Z002","stacked_diagnostics_accessed_without_active_handler");
        
        //Class 20 — Case Not Found
        codeMap.put("20000","case_not_found");
        
        //Class 21 — Cardinality Violation
        codeMap.put("21000","cardinality_violation");
        
        //Class 22 — Data Exception
        codeMap.put("22000","data_exception");
        codeMap.put("2202E","array_subscript_error");
        codeMap.put("22021","character_not_in_repertoire");
        codeMap.put("22008","datetime_field_overflow");
        codeMap.put("22012","division_by_zero");
        codeMap.put("22005","error_in_assignment");
        codeMap.put("2200B","escape_character_conflict");
        codeMap.put("22022","indicator_overflow");
        codeMap.put("22015","interval_field_overflow");
        codeMap.put("2201E","invalid_argument_for_logarithm");
        codeMap.put("22014","invalid_argument_for_ntile_function");
        codeMap.put("22016","invalid_argument_for_nth_value_function");
        codeMap.put("2201F","invalid_argument_for_power_function");
        codeMap.put("2201G","invalid_argument_for_width_bucket_function");
        codeMap.put("22018","invalid_character_value_for_cast");
        codeMap.put("22007","invalid_datetime_format");
        codeMap.put("22019","invalid_escape_character");
        codeMap.put("2200D","invalid_escape_octet");
        codeMap.put("22025","invalid_escape_sequence");
        codeMap.put("22P06","nonstandard_use_of_escape_character");
        codeMap.put("22010","invalid_indicator_parameter_value");
        codeMap.put("22023","invalid_parameter_value");
        codeMap.put("2201B","invalid_regular_expression");
        codeMap.put("2201W","invalid_row_count_in_limit_clause");
        codeMap.put("2201X","invalid_row_count_in_result_offset_clause");
        codeMap.put("22009","invalid_time_zone_displacement_value");
        codeMap.put("2200C","invalid_use_of_escape_character");
        codeMap.put("2200G","most_specific_type_mismatch");
        codeMap.put("22004","null_value_not_allowed");
        codeMap.put("22002","null_value_no_indicator_parameter");
        codeMap.put("22003","numeric_value_out_of_range");
        codeMap.put("22026","string_data_length_mismatch");
        codeMap.put("22001","string_data_right_truncation");
        codeMap.put("22011","substring_error");
        codeMap.put("22027","trim_error");
        codeMap.put("22024","unterminated_c_string");
        codeMap.put("2200F","zero_length_character_string");
        codeMap.put("22P01","floating_point_exception");
        codeMap.put("22P02","invalid_text_representation");
        codeMap.put("22P03","invalid_binary_representation");
        codeMap.put("22P04","bad_copy_file_format");
        codeMap.put("22P05","untranslatable_character");
        codeMap.put("2200L","not_an_xml_document");
        codeMap.put("2200M","invalid_xml_document");
        codeMap.put("2200N","invalid_xml_content");
        codeMap.put("2200S","invalid_xml_comment");
        codeMap.put("2200T","invalid_xml_processing_instruction");
        
        //Class 23 — Integrity Constraint Violation
        codeMap.put("23000","integrity_constraint_violation");
        codeMap.put("23001","restrict_violation");
        codeMap.put("23502","not_null_violation");
        codeMap.put("23503","foreign_key_violation");
        codeMap.put("23505","unique_violation");
        codeMap.put("23514","check_violation");
        codeMap.put("23P01","exclusion_violation");
        
        //Class 24 — Invalid Cursor State
        codeMap.put("24000","invalid_cursor_state");
        
        //Class 25 — Invalid Transaction State
        codeMap.put("25000","invalid_transaction_state");
        codeMap.put("25001","active_sql_transaction");
        codeMap.put("25002","branch_transaction_already_active");
        codeMap.put("25008","held_cursor_requires_same_isolation_level");
        codeMap.put("25003","inappropriate_access_mode_for_branch_transaction");
        codeMap.put("25004","inappropriate_isolation_level_for_branch_transaction");
        codeMap.put("25005","no_active_sql_transaction_for_branch_transaction");
        codeMap.put("25006","read_only_sql_transaction");
        codeMap.put("25007","schema_and_data_statement_mixing_not_supported");
        codeMap.put("25P01","no_active_sql_transaction");
        codeMap.put("25P02","in_failed_sql_transaction");
        
        //Class 26 — Invalid SQL Statement Name
        codeMap.put("26000","invalid_sql_statement_name");
        
        //Class 27 — Triggered Data Change Violation
        codeMap.put("27000","triggered_data_change_violation");
        
        //Class 28 — Invalid Authorization Specification
        codeMap.put("28000","invalid_authorization_specification");
        codeMap.put("28P01","invalid_password");
        
        //Class 2B — Dependent Privilege Descriptors Still Exist
        codeMap.put("2B000","dependent_privilege_descriptors_still_exist");
        codeMap.put("2BP01","dependent_objects_still_exist");
        
        //Class 2D — Invalid Transaction Termination
        codeMap.put("2D000","invalid_transaction_termination");
        
        //Class 2F — SQL Routine Exception
        codeMap.put("2F000","sql_routine_exception");
        codeMap.put("2F005","function_executed_no_return_statement");
        codeMap.put("2F002","modifying_sql_data_not_permitted");
        codeMap.put("2F003","prohibited_sql_statement_attempted");
        codeMap.put("2F004","reading_sql_data_not_permitted");
        
        //Class 34 — Invalid Cursor Name
        codeMap.put("34000","invalid_cursor_name");
        
        //Class 38 — External Routine Exception
        codeMap.put("38000","external_routine_exception");
        codeMap.put("38001","containing_sql_not_permitted");
        codeMap.put("38002","modifying_sql_data_not_permitted");
        codeMap.put("38003","prohibited_sql_statement_attempted");
        codeMap.put("38004","reading_sql_data_not_permitted");
        
        //Class 39 — External Routine Invocation Exception
        codeMap.put("39000","external_routine_invocation_exception");
        codeMap.put("39001","invalid_sqlstate_returned");
        codeMap.put("39004","null_value_not_allowed");
        codeMap.put("39P01","trigger_protocol_violated");
        codeMap.put("39P02","srf_protocol_violated");
        
        //Class 3B — Savepoint Exception
        codeMap.put("3B000","savepoint_exception");
        codeMap.put("3B001","invalid_savepoint_specification");
        
        //Class 3D — Invalid Catalog Name
        codeMap.put("3D000","invalid_catalog_name");
        
        //Class 3F — Invalid Schema Name
        codeMap.put("3F000","invalid_schema_name");
        
        //Class 40 — Transaction Rollback
        codeMap.put("40000","transaction_rollback");
        codeMap.put("40002","transaction_integrity_constraint_violation");
        codeMap.put("40001","serialization_failure");
        codeMap.put("40003","statement_completion_unknown");
        codeMap.put("40P01","deadlock_detected");
        
        //Class 42 — Syntax Error or Access Rule Violation
        codeMap.put("42000","syntax_error_or_access_rule_violation");
        codeMap.put("42601","syntax_error");
        codeMap.put("42501","insufficient_privilege");
        codeMap.put("42846","cannot_coerce");
        codeMap.put("42803","grouping_error");
        codeMap.put("42P20","windowing_error");
        codeMap.put("42P19","invalid_recursion");
        codeMap.put("42830","invalid_foreign_key");
        codeMap.put("42602","invalid_name");
        codeMap.put("42622","name_too_long");
        codeMap.put("42939","reserved_name");
        codeMap.put("42804","datatype_mismatch");
        codeMap.put("42P18","indeterminate_datatype");
        codeMap.put("42P21","collation_mismatch");
        codeMap.put("42P22","indeterminate_collation");
        codeMap.put("42809","wrong_object_type");
        codeMap.put("42703","undefined_column");
        codeMap.put("42883","undefined_function");
        codeMap.put("42P01","undefined_table");
        codeMap.put("42P02","undefined_parameter");
        codeMap.put("42704","undefined_object");
        codeMap.put("42701","duplicate_column");
        codeMap.put("42P03","duplicate_cursor");
        codeMap.put("42P04","duplicate_database");
        codeMap.put("42723","duplicate_function");
        codeMap.put("42P05","duplicate_prepared_statement");
        codeMap.put("42P06","duplicate_schema");
        codeMap.put("42P07","duplicate_table");
        codeMap.put("42712","duplicate_alias");
        codeMap.put("42710","duplicate_object");
        codeMap.put("42702","ambiguous_column");
        codeMap.put("42725","ambiguous_function");
        codeMap.put("42P08","ambiguous_parameter");
        codeMap.put("42P09","ambiguous_alias");
        codeMap.put("42P10","invalid_column_reference");
        codeMap.put("42611","invalid_column_definition");
        codeMap.put("42P11","invalid_cursor_definition");
        codeMap.put("42P12","invalid_database_definition");
        codeMap.put("42P13","invalid_function_definition");
        codeMap.put("42P14","invalid_prepared_statement_definition");
        codeMap.put("42P15","invalid_schema_definition");
        codeMap.put("42P16","invalid_table_definition");
        codeMap.put("42P17","invalid_object_definition");
        
        //Class 44 — WITH CHECK OPTION Violation
        codeMap.put("44000","with_check_option_violation");
        
        //Class 53 — Insufficient Resources
        codeMap.put("53000","insufficient_resources");
        codeMap.put("53100","disk_full");
        codeMap.put("53200","out_of_memory");
        codeMap.put("53300","too_many_connections");
        codeMap.put("53400","configuration_limit_exceeded");
        
        //Class 54 — Program Limit Exceeded
        codeMap.put("54000","program_limit_exceeded");
        codeMap.put("54001","statement_too_complex");
        codeMap.put("54011","too_many_columns");
        codeMap.put("54023","too_many_arguments");
        
        //Class 55 — Object Not In Prerequisite State
        codeMap.put("55000","object_not_in_prerequisite_state");
        codeMap.put("55006","object_in_use");
        codeMap.put("55P02","cant_change_runtime_param");
        codeMap.put("55P03","lock_not_available");
        
        //Class 57 — Operator Intervention
        codeMap.put("57000","operator_intervention");
        codeMap.put("57014","query_canceled");
        codeMap.put("57P01","admin_shutdown");
        codeMap.put("57P02","crash_shutdown");
        codeMap.put("57P03","cannot_connect_now");
        codeMap.put("57P04","database_dropped");
        
        //Class 58 — System Error (errors external to PostgreSQL itself)
        codeMap.put("58000","system_error");
        codeMap.put("58030","io_error");
        codeMap.put("58P01","undefined_file");
        codeMap.put("58P02","duplicate_file");
        
        //Class F0 — Configuration File Error
        codeMap.put("F0000","config_file_error");
        codeMap.put("F0001","lock_file_exists");
        
        //Class HV — Foreign Data Wrapper Error (SQL/MED)
        codeMap.put("HV000","fdw_error");
        codeMap.put("HV005","fdw_column_name_not_found");
        codeMap.put("HV002","fdw_dynamic_parameter_value_needed");
        codeMap.put("HV010","fdw_function_sequence_error");
        codeMap.put("HV021","fdw_inconsistent_descriptor_information");
        codeMap.put("HV024","fdw_invalid_attribute_value");
        codeMap.put("HV007","fdw_invalid_column_name");
        codeMap.put("HV008","fdw_invalid_column_number");
        codeMap.put("HV004","fdw_invalid_data_type");
        codeMap.put("HV006","fdw_invalid_data_type_descriptors");
        codeMap.put("HV091","fdw_invalid_descriptor_field_identifier");
        codeMap.put("HV00B","fdw_invalid_handle");
        codeMap.put("HV00C","fdw_invalid_option_index");
        codeMap.put("HV00D","fdw_invalid_option_name");
        codeMap.put("HV090","fdw_invalid_string_length_or_buffer_length");
        codeMap.put("HV00A","fdw_invalid_string_format");
        codeMap.put("HV009","fdw_invalid_use_of_null_pointer");
        codeMap.put("HV014","fdw_too_many_handles");
        codeMap.put("HV001","fdw_out_of_memory");
        codeMap.put("HV00P","fdw_no_schemas");
        codeMap.put("HV00J","fdw_option_name_not_found");
        codeMap.put("HV00K","fdw_reply_handle");
        codeMap.put("HV00Q","fdw_schema_not_found");
        codeMap.put("HV00R","fdw_table_not_found");
        codeMap.put("HV00L","fdw_unable_to_create_execution");
        codeMap.put("HV00M","fdw_unable_to_create_reply");
        codeMap.put("HV00N","fdw_unable_to_establish_connection");
        
        //Class P0 — PL/pgSQL Error
        codeMap.put("P0000","plpgsql_error");
        codeMap.put("P0001","raise_exception");
        codeMap.put("P0002","no_data_found");
        codeMap.put("P0003","too_many_rows");
        
        //Class XX — Internal Error
        codeMap.put("XX000","internal_error");
        codeMap.put("XX001","data_corrupted");
        codeMap.put("XX002","index_corrupted");
        sqlCodeMap = Collections.unmodifiableMap(codeMap);
    }
	
	public static String getSQLMessageCode(String sqlState) {
		return sqlCodeMap.get(sqlState);
	}
}
