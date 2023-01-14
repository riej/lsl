// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.riej.lsl.psi.LslTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.github.riej.lsl.psi.*;

public class LslDoStatementImpl extends ASTWrapperPsiElement implements LslDoStatement {

  public LslDoStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitDoStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LslExpression getExpression() {
    return findChildByClass(LslExpression.class);
  }

  @Override
  @Nullable
  public LslStatement getStatement() {
    return findChildByClass(LslStatement.class);
  }

  @Override
  @Nullable
  public PsiElement getParenthesesLeft() {
    return findChildByType(PARENTHESES_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getParenthesesRight() {
    return findChildByType(PARENTHESES_RIGHT);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

  @Override
  @NotNull
  public PsiElement getDo() {
    return findNotNullChildByType(DO);
  }

  @Override
  @Nullable
  public PsiElement getWhile() {
    return findChildByType(WHILE);
  }

}
