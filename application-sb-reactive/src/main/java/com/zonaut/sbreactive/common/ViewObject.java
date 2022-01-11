package com.zonaut.sbreactive.common;

import java.io.Serializable;
//import javax.persistence.MappedSuperclass;
//
//import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
//import org.hibernate.annotations.TypeDef;
//import org.hibernate.annotations.TypeDefs;
//
//@TypeDefs({
//    @TypeDef(name = ViewObject.TYPE_ENUM, typeClass = PostgreSQLEnumType.class),
//    @TypeDef(name = ViewObject.TYPE_JSONB, typeClass = JsonBinaryType.class)
//})
//@MappedSuperclass
public class ViewObject implements Serializable {

    public static final String TYPE_ENUM = "pgsql_enum";
    public final static String TYPE_JSONB = "jsonb";

    public static final String DEFINITION_INTEGER = "INTEGER";
    public static final String DEFINITION_TEXT = "TEXT";
    public static final String DEFINITION_UUID = "UUID";
    public static final String DEFINITION_JSON_BINARY = "JSONB";

}
