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
 * @ast interface
 * @aspect Variable
 * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java8/frontend/Variable.jadd:31
 */
public interface Variable {

     
    public String name();

     
    public TypeDecl type();

     
    public Collection<TypeDecl> throwTypes();

     
    public boolean isParameter();


    // 4.5.3
     

    // 4.5.3
    public boolean isClassVariable();

     
    public boolean isInstanceVariable();

     
    public boolean isMethodParameter();

     
    public boolean isConstructorParameter();

     
    public boolean isExceptionHandlerParameter();

     
    public boolean isLocalVariable();

     
    public boolean isField();


     

    public boolean isPublic();


    // 4.5.4
     

    // 4.5.4
    public boolean isFinal();

     
    public boolean isVolatile();


    // 4.12.4
     

    // 4.12.4
    public boolean isEffectivelyFinal();


     

    public boolean isBlank();

     
    public boolean isStatic();

     
    public boolean isSynthetic();


     

    public boolean accessibleFrom(TypeDecl type);

     
    public TypeDecl hostType();


     

    public Expr getInit();

     
    public boolean hasInit();


     

    public boolean isConstant();

     
    public Constant constant();


     

    public Modifiers getModifiers();
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/Modifiers.jrag:278
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/Modifiers.jrag:278")
  public boolean isProtected();
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/Modifiers.jrag:280
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/Modifiers.jrag:280")
  public boolean isPrivate();
  /**
   * @attribute inh
   * @aspect NestedTypes
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/TypeAnalysis.jrag:680
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NestedTypes", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/TypeAnalysis.jrag:680")
  public String hostPackage();
  /**
   * @attribute inh
   * @aspect LookupParTypeDecl
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java5/frontend/Generics.jrag:1400
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java5/frontend/Generics.jrag:1400")
  public FieldDecl fieldDecl();
}
