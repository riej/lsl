// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class LslVisitor extends PsiElementVisitor {

  public void visitAddExpr(@NotNull LslAddExpr o) {
    visitBinaryExpr(o);
  }

  public void visitAndExpr(@NotNull LslAndExpr o) {
    visitBinaryExpr(o);
  }

  public void visitArgument(@NotNull LslArgument o) {
    visitNamedTypedElement(o);
  }

  public void visitAssignExpr(@NotNull LslAssignExpr o) {
    visitExpression(o);
  }

  public void visitBinaryExpr(@NotNull LslBinaryExpr o) {
    visitExpression(o);
  }

  public void visitBlock(@NotNull LslBlock o) {
    visitPsiElement(o);
  }

  public void visitCallExpr(@NotNull LslCallExpr o) {
    visitExpression(o);
  }

  public void visitConditionalExpr(@NotNull LslConditionalExpr o) {
    visitBinaryExpr(o);
  }

  public void visitConstantValue(@NotNull LslConstantValue o) {
    visitTypedElement(o);
  }

  public void visitConversionExpr(@NotNull LslConversionExpr o) {
    visitBinaryExpr(o);
  }

  public void visitDefaultStateDeclaration(@NotNull LslDefaultStateDeclaration o) {
    visitNamedElement(o);
  }

  public void visitDoStatement(@NotNull LslDoStatement o) {
    visitPsiElement(o);
  }

  public void visitElseStatement(@NotNull LslElseStatement o) {
    visitPsiElement(o);
  }

  public void visitExpression(@NotNull LslExpression o) {
    visitTypedElement(o);
  }

  public void visitExpressionStatement(@NotNull LslExpressionStatement o) {
    visitPsiElement(o);
  }

  public void visitForStatement(@NotNull LslForStatement o) {
    visitPsiElement(o);
  }

  public void visitFunctionDeclaration(@NotNull LslFunctionDeclaration o) {
    visitNamedTypedElement(o);
  }

  public void visitGlobalVariableDeclaration(@NotNull LslGlobalVariableDeclaration o) {
    visitNamedTypedElement(o);
  }

  public void visitIdentifier(@NotNull LslIdentifier o) {
    visitPsiElement(o);
  }

  public void visitIfStatement(@NotNull LslIfStatement o) {
    visitPsiElement(o);
  }

  public void visitJumpStatement(@NotNull LslJumpStatement o) {
    visitPsiElement(o);
  }

  public void visitLValue(@NotNull LslLValue o) {
    visitPsiElement(o);
  }

  public void visitLabelStatement(@NotNull LslLabelStatement o) {
    visitNamedElement(o);
  }

  public void visitListExpr(@NotNull LslListExpr o) {
    visitExpression(o);
  }

  public void visitLiteral(@NotNull LslLiteral o) {
    visitExpression(o);
  }

  public void visitLocalVariableDeclaration(@NotNull LslLocalVariableDeclaration o) {
    visitNamedTypedElement(o);
  }

  public void visitMulExpr(@NotNull LslMulExpr o) {
    visitBinaryExpr(o);
  }

  public void visitOrExpr(@NotNull LslOrExpr o) {
    visitBinaryExpr(o);
  }

  public void visitParenthesesExpr(@NotNull LslParenthesesExpr o) {
    visitExpression(o);
  }

  public void visitPrintExpr(@NotNull LslPrintExpr o) {
    visitExpression(o);
  }

  public void visitReturnStatement(@NotNull LslReturnStatement o) {
    visitPsiElement(o);
  }

  public void visitStateDeclaration(@NotNull LslStateDeclaration o) {
    visitNamedElement(o);
  }

  public void visitStateEvent(@NotNull LslStateEvent o) {
    visitNamedElement(o);
  }

  public void visitStateStatement(@NotNull LslStateStatement o) {
    visitPsiElement(o);
  }

  public void visitStatement(@NotNull LslStatement o) {
    visitPsiElement(o);
  }

  public void visitTypeName(@NotNull LslTypeName o) {
    visitPsiElement(o);
  }

  public void visitUnaryExpr(@NotNull LslUnaryExpr o) {
    visitExpression(o);
  }

  public void visitUnaryPostfixExpr(@NotNull LslUnaryPostfixExpr o) {
    visitExpression(o);
  }

  public void visitVectorOrQuaternionExpr(@NotNull LslVectorOrQuaternionExpr o) {
    visitExpression(o);
  }

  public void visitWhileStatement(@NotNull LslWhileStatement o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull LslNamedElement o) {
    visitPsiElement(o);
  }

  public void visitNamedTypedElement(@NotNull LslNamedTypedElement o) {
    visitPsiElement(o);
  }

  public void visitTypedElement(@NotNull LslTypedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
