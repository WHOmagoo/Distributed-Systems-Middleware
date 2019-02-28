/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.
This file was generated from .idl using "rtiddsgen".
The rtiddsgen tool is part of the RTI Connext distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the RTI Connext manual.
*/

import com.rti.dds.typecode.ExtensibilityKind;
import com.rti.dds.typecode.StructMember;
import com.rti.dds.typecode.TypeCode;
import com.rti.dds.typecode.TypeCodeFactory;
public class  HelloWorldTypeCode {
    public static final TypeCode VALUE = getTypeCode();
    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int __i=0;
        StructMember sm[]=new StructMember[1];
        sm[__i]=new  StructMember("data", false, (short)-1,  false, TypeCode.TC_LONG,0 , false);__i++;
        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("Msg",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,  sm);
        return tc;
    }
}