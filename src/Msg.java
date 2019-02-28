/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.
This file was generated from .idl using "rtiddsgen".
The rtiddsgen tool is part of the RTI Connext distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the RTI Connext manual.
*/

import com.rti.dds.cdr.CdrHelper;
import com.rti.dds.infrastructure.Copyable;

import java.io.Serializable;
public class Msg implements Copyable, Serializable{
    public String msg=  "" ; /* maximum length = (128) */
    public Msg() {
    }
    public Msg(Msg other) {
        this();
        copy_from(other);
    }
    public static Object create() {
        Msg self;
        self = new Msg();
        self.clear();
        return self;
    }
    public void clear() {
        msg=  "";
    }
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if(getClass() != o.getClass()) {
            return false;
        }
        Msg otherObj = (Msg)o;
        return msg.equals(otherObj.msg);
    }
    public int hashCode() {
        int __result = 0;
        __result += msg.hashCode();
        return __result;
    }
    public Object copy_from(Object src) {
        Msg typedSrc = (Msg) src;
        Msg typedDst = this;
        typedDst.msg = typedSrc.msg;
        return this;
    }
    public String toString(){
        return toString("", 0);
    }
    public String toString(String desc, int indent) {
        StringBuffer strBuffer = new StringBuffer();
        if (desc != null) {
            CdrHelper.printIndent(strBuffer, indent);
            strBuffer.append(desc).append(":\n");
        }
        CdrHelper.printIndent(strBuffer, indent+1);
        strBuffer.append("msg: ").append(msg).append("\n");
        return strBuffer.toString();
    }
}