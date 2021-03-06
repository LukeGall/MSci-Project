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
 * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java5/frontend/BytecodeDescriptor.jrag:160
 */
 class MethodInfo extends java.lang.Object {
  
    private AbstractClassfileParser p;

  
    String name;

  
    int flags;

  
    private MethodDescriptor methodDescriptor;

  
    private Attributes.MethodAttributes attributes;

  

    public MethodInfo(AbstractClassfileParser parser) throws IOException {
      p = parser;
      flags = p.u2();
      if (AbstractClassfileParser.VERBOSE) {
        p.print("  Flags: " + Integer.toBinaryString(flags));
      }
      int name_index = p.u2();
      CONSTANT_Info info = p.constantPool[name_index];
      if (info == null || !(info instanceof CONSTANT_Utf8_Info)) {
        System.err.println("Expected CONSTANT_Utf8_Info but found: " + info.getClass().getName());
      }
      name = ((CONSTANT_Utf8_Info) info).string();
      methodDescriptor = new MethodDescriptor(p, name);
      attributes = new Attributes.MethodAttributes(p);
    }

  

    public BodyDecl bodyDecl() {
      Signatures.MethodSignature s = attributes.methodSignature;
      Access returnType = (s != null && s.hasReturnType())
          ? s.returnType()
          : methodDescriptor.type();
      List parameterList;
      if (isConstructor() && p.isInnerClass) {
        parameterList = methodDescriptor.parameterListSkipFirst();
        if (s != null) {
          Iterator<Access> iter = s.parameterTypes().iterator();
          if (iter.hasNext()) {
            iter.next();
          }
          for (int i = 0; iter.hasNext(); i++) {
            Access a = iter.next();
            ((ParameterDeclaration) parameterList.getChildNoTransform(i)).setTypeAccess(a);
          }
        }
      } else {
        parameterList = methodDescriptor.parameterList();
        if (s != null) {
          int i = 0;
          for (Access a : s.parameterTypes()) {
            ((ParameterDeclaration) parameterList.getChildNoTransform(i++)).setTypeAccess(a);
          }
        }
      }
      if ((flags & Flags.ACC_VARARGS) != 0) {
        int lastIndex = parameterList.getNumChildNoTransform() - 1;
        // The last index is -1 if this is the constructor of a static anonymous
        // inner class with a variable arity parameter list.
        // Since this only affects anonymous inner classes we don't have to
        // generate a precise AST for the bytecode. We could even skip loading
        // the bytecode entirely.
        if (lastIndex >= 0) {
          ParameterDeclaration p = (ParameterDeclaration)
              parameterList.getChildNoTransform(lastIndex);
          parameterList.setChild(
              new VariableArityParameterDeclaration(
                  p.getModifiersNoTransform(),
                  ((ArrayTypeAccess) p.getTypeAccessNoTransform()).getAccessNoTransform(),
                  p.getID()),
              lastIndex);
        }
      }
      List exceptionList = (s != null && s.hasExceptionList())
          ? s.exceptionList()
          : attributes.exceptionList();

      if (attributes.parameterAnnotations != null) {
        for (int i = 0; i < attributes.parameterAnnotations.length; i++) {
          ParameterDeclaration p = (ParameterDeclaration) parameterList.getChildNoTransform(i);
          for (Annotation annotation : attributes.parameterAnnotations[i]) {
            p.getModifiersNoTransform().addModifier(annotation);
          }
        }
      }

      BodyDecl b;
      if (isConstructor()) {
        if (s == null || !s.hasFormalTypeParameters()) {
          b = new ConstructorDecl(AbstractClassfileParser.modifiers(flags), name, parameterList,
              exceptionList, new Opt(), new Block());
        } else {
          b = new GenericConstructorDecl(
              AbstractClassfileParser.modifiers(flags),
              name,
              parameterList,
              exceptionList,
              new Opt(),
              new Block(),
              s.typeParameters());
        }
      } else if (attributes.elementValue() != null) {
        b = new AnnotationMethodDecl(AbstractClassfileParser.modifiers(flags), returnType, name,
            parameterList, exceptionList,
            new Opt(new Block()), new Opt(attributes.elementValue()));
      } else if (s != null && s.hasFormalTypeParameters()) {
        b = new GenericMethodDecl(AbstractClassfileParser.modifiers(flags), returnType, name,
            parameterList, exceptionList, new Opt(new Block()), s.typeParameters());
      } else {
        b = new MethodDecl(AbstractClassfileParser.modifiers(flags), returnType,
            name, parameterList, exceptionList, new Opt(new Block()));
      }
      if (attributes.annotations != null) {
        for (Annotation annotation : attributes.annotations) {
          if (b instanceof MethodDecl) {
            ((MethodDecl) b).getModifiers().addModifier(annotation);
          } else if (b instanceof ConstructorDecl) {
            ((ConstructorDecl) b).getModifiers().addModifier(annotation);
          }
        }
      }
      return b;
    }

  

    private boolean isConstructor() {
      return name.equals("<init>");
    }

  

    public boolean isSynthetic() {
      return attributes.isSynthetic() || (flags & 0x1000) != 0;
    }


}
