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
 * The classified name type of a parsed name or expression.
 * 
 * <p>Method Name is not in this enum because it never occurs in the ASTs
 * produced by our parser: the parser builds the correct node since it has a
 * different structure.
 * @ast enum
 * @aspect SyntacticClassification
 * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/SyntacticClassification.jrag:47
 */
// Circular.

  /**
   * The classified name type of a parsed name or expression.
   *
   * <p>Method Name is not in this enum because it never occurs in the ASTs
   * produced by our parser: the parser builds the correct node since it has a
   * different structure.
   */
  enum NameType {
    NOT_CLASSIFIED,
    PACKAGE_NAME,
    TYPE_NAME,
    PACKAGE_OR_TYPE_NAME,
    AMBIGUOUS_NAME,
    EXPRESSION_NAME
  }
