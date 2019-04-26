package position;/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.
This file was generated from .idl using "rtiddsgen".
The rtiddsgen tool is part of the RTI Connext distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the RTI Connext manual.
*/

import com.rti.dds.infrastructure.Copyable;
import com.rti.dds.util.LoanableSequence;
import com.rti.dds.util.Sequence;
import model.Position;

import java.util.Collection;

public final class PositionSeq extends LoanableSequence implements Copyable {
    // -----------------------------------------------------------------------
    // Package Fields
    // -----------------------------------------------------------------------
    /*package*/ transient Sequence _loanedInfoSequence = null;
    // -----------------------------------------------------------------------
    // Public Fields
    // -----------------------------------------------------------------------
    // --- Constructors: -----------------------------------------------------
    public PositionSeq() {
        super(Position.class);
    }
    public PositionSeq(int initialMaximum) {
        super(Position.class, initialMaximum);
    }
    public PositionSeq(Collection elements) {
        super(Position.class, elements);
    }
    public Position get(int index) {
        return (Position) super.get(index);
    }
    // --- From Copyable: ----------------------------------------------------
    public Object copy_from(Object src) {
        Sequence typedSrc = (Sequence) src;
        final int srcSize = typedSrc.size();
        final int origSize = size();
        // if this object's size is less than the source, ensure we have
        // enough room to store all of the objects
        if (getMaximum() < srcSize) {
            setMaximum(srcSize);
        }
        // trying to avoid clear() method here since it allocates memory
        // (an Iterator)
        // if the source object has fewer items than the current object,
        // remove from the end until the sizes are equal
        if (srcSize < origSize){
            removeRange(srcSize, origSize);
        }
        // copy the data from source into this (into positions that already
        // existed)
        for(int i = 0; (i < origSize) && (i < srcSize); i++){
            if (typedSrc.get(i) == null){
                set(i, null);
            } else {
                // check to see if our entry is null, if it is, a new instance has to be allocated
                if (get(i) == null){
                    set(i, Position.create());
                }
                set(i, ((Copyable) get(i)).copy_from(typedSrc.get(i)));
            }
        }
        // copy 'new' BusSystem.Position objects (beyond the original size of this object)
        for(int i = origSize; i < srcSize; i++){
            if (typedSrc.get(i) == null) {
                add(null);
            } else {
                // NOTE: we need to create a new object here to hold the copy
                add(Position.create());
                // we need to do a set here since enums aren't truely Copyable
                set(i, ((Copyable) get(i)).copy_from(typedSrc.get(i)));
            }
        }
        return this;
    }
}