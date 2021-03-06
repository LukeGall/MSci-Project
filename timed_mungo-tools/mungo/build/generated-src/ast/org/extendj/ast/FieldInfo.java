package org.extendj.ast;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.*;
import org.jastadd.util.*;
import org.jastadd.util.PrettyPrintable;
import org.jastadd.util.PrettyPrinter;
import java.util.zip.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Set;
import beaver.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.lang.StringBuffer;
/**
 * @ast class
 * @aspect BytecodeDescriptor
 * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java5/frontend/BytecodeDescriptor.jrag:55
 */
 class FieldInfo extends java.lang.Object {
  
    private AbstractClassfileParser p;

  
    String name;

  
    int flags;

  
    private FieldDescriptor fieldDescriptor;

  
    private Attributes.FieldAttributes attributes;

  

    public FieldInfo(AbstractClassfileParser parser) throws IOException {
      p = parser;
      flags = p.u2();
      if (AbstractClassfileParser.VERBOSE) {
        p.print("Flags: " + flags);
      }
      int name_index = p.u2();
      name = ((CONSTANT_Utf8_Info) p.constantPool[name_index]).string();

      fieldDescriptor = new FieldDescriptor(p, name);
      attributes = new Attributes.FieldAttributes(p);
      if (attributes.isSynthetic()) {
        flags |= Flags.ACC_SYNTHETIC;
      }
    }

  

    public BodyDecl bodyDecl() {
      if ((flags & Flags.ACC_ENUM) != 0) {
        EnumConstant constant = new EnumConstant(
            AbstractClassfileParser.modifiers(flags),
            name,
            new List(),
            new List());
        if (attributes.constantValue() != null) {
          if (fieldDescriptor.isBoolean()) {
            constant.setInit(attributes.constantValue().exprAsBoolean());
          } else {
            constant.setInit(attributes.constantValue().expr());
          }
        }
        if (attributes.annotations != null) {
          for (Annotation annotation : attributes.annotations) {
            constant.getModifiersNoTransform().addModifier(annotation);
          }
        }
        return constant;
      } else {
        Signatures.FieldSignature s = attributes.fieldSignature;
        Access type = s != null ? s.fieldTypeAccess() : fieldDescriptor.type();
        FieldDeclarator decl = new FieldDeclarator(name, new List<Dims>(), new Opt<Expr>());
        FieldDecl f = new FieldDecl(
            AbstractClassfileParser.modifiers(flags),
            type,
            new List<FieldDeclarator>(decl));
        if (attributes.constantValue() != null) {
          if (fieldDescriptor.isBoolean()) {
            decl.setInit(attributes.constantValue().exprAsBoolean());
          } else {
            decl.setInit(attributes.constantValue().expr());
          }
        }
        if (attributes.annotations != null) {
          for (Annotation annotation : attributes.annotations) {
            f.getModifiersNoTransform().addModifier(annotation);
          }
        }
        return f;
      }
    }

  

    public boolean isSynthetic() {
      return (flags & Flags.ACC_SYNTHETIC) != 0;
    }


}
