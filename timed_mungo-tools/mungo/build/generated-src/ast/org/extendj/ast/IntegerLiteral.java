/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.2 */
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
 * Default Java integer literal. Should only be used for numbers
 * that can be stored in 32 bits binary.
 * @ast node
 * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/grammar/Literals.ast:35
 * @astdecl IntegerLiteral : Literal ::= <LITERAL:String>;
 * @production IntegerLiteral : {@link Literal};

 */
public class IntegerLiteral extends Literal implements Cloneable, NumericLiteral {
  /**
   * @aspect NodeConstructors
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/NodeConstructors.jrag:62
   */
  public IntegerLiteral(int i) {
    this(Integer.toString(i));
  }
  /**
   * @aspect Java7Literals
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:922
   */
  protected Constant constant = null;
  /**
   * @aspect Java7Literals
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:924
   */
  public IntegerLiteral(String literal, Constant constant) {
    this(literal);
    this.constant = constant;
  }
  /**
   * @declaredat ASTNode:1
   */
  public IntegerLiteral() {
    super();
  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @declaredat ASTNode:10
   */
  public void init$Children() {
  }
  /**
   * @declaredat ASTNode:12
   */
  @ASTNodeAnnotation.Constructor(
    name = {"LITERAL"},
    type = {"String"},
    kind = {"Token"}
  )
  public IntegerLiteral(String p0) {
    setLITERAL(p0);
  }
  /**
   * @declaredat ASTNode:20
   */
  public IntegerLiteral(beaver.Symbol p0) {
    setLITERAL(p0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:24
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:30
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:34
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    type_reset();
    constant_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:40
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:44
   */
  public IntegerLiteral clone() throws CloneNotSupportedException {
    IntegerLiteral node = (IntegerLiteral) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:49
   */
  public IntegerLiteral copy() {
    try {
      IntegerLiteral node = (IntegerLiteral) clone();
      node.parent = null;
      if (children != null) {
        node.children = (ASTNode[]) children.clone();
      }
      return node;
    } catch (CloneNotSupportedException e) {
      throw new Error("Error: clone not supported for " + getClass().getName());
    }
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:68
   */
  @Deprecated
  public IntegerLiteral fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:78
   */
  public IntegerLiteral treeCopyNoTransform() {
    IntegerLiteral tree = (IntegerLiteral) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) children[i];
        if (child != null) {
          child = child.treeCopyNoTransform();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:98
   */
  public IntegerLiteral treeCopy() {
    IntegerLiteral tree = (IntegerLiteral) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) getChild(i);
        if (child != null) {
          child = child.treeCopy();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:112
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node) && (tokenString_LITERAL == ((IntegerLiteral) node).tokenString_LITERAL);    
  }
  /**
   * Replaces the lexeme LITERAL.
   * @param value The new value for the lexeme LITERAL.
   * @apilevel high-level
   */
  public void setLITERAL(String value) {
    tokenString_LITERAL = value;
  }
  /**
   * JastAdd-internal setter for lexeme LITERAL using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme LITERAL
   * @apilevel internal
   */
  public void setLITERAL(beaver.Symbol symbol) {
    if (symbol.value != null && !(symbol.value instanceof String))
    throw new UnsupportedOperationException("setLITERAL is only valid for String lexemes");
    tokenString_LITERAL = (String)symbol.value;
    LITERALstart = symbol.getStart();
    LITERALend = symbol.getEnd();
  }
  /**
   * Retrieves the value for the lexeme LITERAL.
   * @return The value for the lexeme LITERAL.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="LITERAL")
  public String getLITERAL() {
    return tokenString_LITERAL != null ? tokenString_LITERAL : "";
  }
  /** @apilevel internal */
  private void type_reset() {
    type_computed = null;
    type_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle type_computed = null;

  /** @apilevel internal */
  protected TypeDecl type_value;

  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/TypeAnalysis.jrag:321
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/TypeAnalysis.jrag:295")
  public TypeDecl type() {
    ASTState state = state();
    if (type_computed == ASTState.NON_CYCLE || type_computed == state().cycle()) {
      return type_value;
    }
    type_value = typeInt();
    if (state().inCircle()) {
      type_computed = state().cycle();
    
    } else {
      type_computed = ASTState.NON_CYCLE;
    
    }
    return type_value;
  }
  /**
   * @attribute syn
   * @aspect Java7Literals
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:80
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/TypeCheck.jrag:755")
  public Problem sizeError() {
    Problem sizeError_value = errorf("The integer literal \"%s\" is too large for type int.", getLITERAL());
    return sizeError_value;
  }
  /**
   * @attribute syn
   * @aspect PositiveLiterals
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/PositiveLiterals.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PositiveLiterals", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/PositiveLiterals.jrag:36")
  public boolean isPositive() {
    boolean isPositive_value = !getLITERAL().startsWith("-");
    return isPositive_value;
  }
  /** @apilevel internal */
  private void constant_reset() {
    constant_computed = null;
    constant_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle constant_computed = null;

  /** @apilevel internal */
  protected Constant constant_value;

  /**
   * Parse this literal and return a fresh Constant.
   * @return a fresh Constant representing this IntegerLiteral
   * @attribute syn
   * @aspect Java7Literals
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:94
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/ConstantExpression.jrag:38")
  public Constant constant() {
    ASTState state = state();
    if (constant_computed == ASTState.NON_CYCLE || constant_computed == state().cycle()) {
      return constant_value;
    }
    constant_value = constant_compute();
    if (state().inCircle()) {
      constant_computed = state().cycle();
    
    } else {
      constant_computed = ASTState.NON_CYCLE;
    
    }
    return constant_value;
  }
  /** @apilevel internal */
  private Constant constant_compute() {
      if (constant != null) {
        return constant;
      }
      long l = 0;
      try {
        l = parseLong();
      } catch (NumberFormatException e) {
        Constant c = Constant.create(0L);
        c.error = true;
        return c;
      }
      int clamped = (int) l;
      Constant c = Constant.create(clamped);
      if (l != clamped) {
        if (l != (0xFFFFFFFFL & clamped) || numericLiteralKind() == NumericLiteralKind.DECIMAL) {
          c.error = true;
        }
      }
      return c;
    }
  /**
   * @attribute syn
   * @aspect Java7Literals
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:315
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java7Literals", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:303")
  public boolean isNegative() {
    boolean isNegative_value = getLITERAL().charAt(0) == '-';
    return isNegative_value;
  }
  /**
   * @attribute syn
   * @aspect Java7Literals
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:317
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java7Literals", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:317")
  public boolean isHex() {
    boolean isHex_value = numericLiteralKind() == NumericLiteralKind.HEXADECIMAL;
    return isHex_value;
  }
  /**
   * @attribute syn
   * @aspect Java7Literals
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:319
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java7Literals", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:319")
  public boolean isOctal() {
    boolean isOctal_value = numericLiteralKind() == NumericLiteralKind.OCTAL;
    return isOctal_value;
  }
  /**
   * @attribute syn
   * @aspect Java7Literals
   * @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:321
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java7Literals", declaredAt="/home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java7/frontend/Literals.jrag:321")
  public boolean isDecimal() {
    boolean isDecimal_value = numericLiteralKind() == NumericLiteralKind.DECIMAL;
    return isDecimal_value;
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }
  /** @apilevel internal */
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /home/luke/Level-5-Project/Mungo/updated_mungo-tools/mungo/extendj/java4/frontend/TypeCheck.jrag:759
    if (constant().error) {
      {
        java.util.Set<ASTNode> contributors = _map.get(_root);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) _root, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_CompilationUnit_problems(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    if (constant().error) {
      collection.add(sizeError());
    }
  }
}
