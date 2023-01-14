// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.riej.lsl.psi.LslTypes.*;
import io.github.riej.lsl.psi.*;
import io.github.riej.lsl.LslPrimitiveType;

public class LslConditionalExprImpl extends LslBinaryExprImpl implements LslConditionalExpr {

  public LslConditionalExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitConditionalExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LslVisitor) accept((LslVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getEq() {
    return findChildByType(EQ);
  }

  @Override
  @Nullable
  public PsiElement getGreater() {
    return findChildByType(GREATER);
  }

  @Override
  @Nullable
  public PsiElement getGreaterEq() {
    return findChildByType(GREATER_EQ);
  }

  @Override
  @Nullable
  public PsiElement getLess() {
    return findChildByType(LESS);
  }

  @Override
  @Nullable
  public PsiElement getLessEq() {
    return findChildByType(LESS_EQ);
  }

  @Override
  @Nullable
  public PsiElement getNotEq() {
    return findChildByType(NOT_EQ);
  }

  @Override
  @NotNull
  public LslPrimitiveType getPrimitiveType() {
    return LslPsiImplUtil.getPrimitiveType(this);
  }

}
