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

public class LslUnaryExprImpl extends LslExpressionImpl implements LslUnaryExpr {

  public LslUnaryExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull LslVisitor visitor) {
    visitor.visitUnaryExpr(this);
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
  public PsiElement getBitwiseNot() {
    return findChildByType(BITWISE_NOT);
  }

  @Override
  @Nullable
  public PsiElement getBitwiseXor() {
    return findChildByType(BITWISE_XOR);
  }

  @Override
  @Nullable
  public PsiElement getMinus() {
    return findChildByType(MINUS);
  }

  @Override
  @Nullable
  public PsiElement getMinusMinus() {
    return findChildByType(MINUS_MINUS);
  }

  @Override
  @Nullable
  public PsiElement getNot() {
    return findChildByType(NOT);
  }

  @Override
  @Nullable
  public PsiElement getPlus() {
    return findChildByType(PLUS);
  }

  @Override
  @Nullable
  public PsiElement getPlusPlus() {
    return findChildByType(PLUS_PLUS);
  }

  @Override
  @NotNull
  public LslPrimitiveType getPrimitiveType() {
    return LslPsiImplUtil.getPrimitiveType(this);
  }

}
